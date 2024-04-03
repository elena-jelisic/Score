package org.oagi.score.gateway.http.api.specification_management.service;

import org.oagi.score.gateway.http.api.namespace_management.data.Namespace;
import org.oagi.score.gateway.http.api.namespace_management.service.NamespaceService;
import org.oagi.score.gateway.http.api.release_management.data.ReleaseState;
import org.oagi.score.gateway.http.configuration.security.SessionService;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.ReleaseRecord;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.SpecificationRecord;
import org.oagi.score.repo.api.specification.model.*;
import org.oagi.score.repo.component.release.ReleaseRepository;
import org.oagi.score.repo.component.specification.SpecificationRepository;
import org.oagi.score.service.common.data.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class MultiStandardService {
    @Autowired
    private SpecificationRepository specRepo;
    @Autowired
    private NamespaceService namespaceService;
    @Autowired
    private ReleaseRepository releaseRepo;
    @Autowired
    private SessionService session;
    private List<SpecificationAggregateComponent> aggregateComponentsList;
    private List<SpecificationBasicComponent> basicComponentsList;
    private List<SpecificationAssociationComponent> associationComponentsList;
    private Map<String, Element> complexTypeMapFull = new HashMap<>();
    private Map<String, Element> complexTypeMapInitial = new HashMap<>();
    private Map<String, Element> simpleTypeMapFull = new HashMap<>();
    private Document doc;
    private String rootFolder;

   /* public static void main(String args[]) {
        MultiStandardService service = new MultiStandardService();
        AuthenticatedPrincipal user = new AuthenticatedPrincipal() {
            @Override
            public String getName() {
                return null;
            }
        };
        service.insertNewSpecification(new ScoreUser());
    }*/

    @Transactional
    public void insertNewSpecification(AuthenticatedPrincipal user) {
        aggregateComponentsList = new ArrayList<>();
        basicComponentsList = new ArrayList<>();
        associationComponentsList = new ArrayList<>();
        rootFolder = "/Users/enj2/Documents/QIF3.0-2018-ANSI/xsd/QIFApplications";
        String documentName = "QIFResults.xsd";
        doc = loadSchema(rootFolder, documentName);
        complexTypeMapInitial = loadComplexTypesFromSchema(doc);
        loadIncludedSchemas(doc);
        for (String key : complexTypeMapInitial.keySet()) {
            resolveComplexTypeStructure(complexTypeMapInitial.get(key));
        }
        CreateSpecificationRequest createSpec = new CreateSpecificationRequest();
        Source source = new Source();
        source.setSourceName("QIF");
        Specification spec = new Specification();
        spec.setSpecificationName("QIF 3");
        createSpec.setSpecificationType("Standard library");
        createSpec.setSpecificationAggregatesList(aggregateComponentsList);
        createSpec.setSource(source);
        createSpec.setSpecification(spec);
        CreateSpecificationResponse response = specRepo.createSpecification(createSpec);
        Namespace namespace = new Namespace();
        namespace.setUri("http://qifstandards.org/xsd/qif3");
        namespace.setPrefix("QIF");
        namespace.setStd(true);
        BigInteger namespaceID = namespaceService.create(user, namespace);
        AppUser targetUser = session.getAppUserByUsername("oagis");
        SpecificationRecord specificationRecord = specRepo.getSpecificationByName(spec.getSpecificationName());
        ReleaseRecord releaseRecord = releaseRepo.createForSpecification(
                targetUser.getAppUserId(), "QIF 3", "QIF release 3",
                        "QIF", namespaceID, BigInteger.valueOf(specificationRecord.getSpecificationId()));
        releaseRepo.updateState(targetUser.getAppUserId(), releaseRecord.getReleaseId().toBigInteger(), ReleaseState.Published);
    }


    private boolean complexTypeIsStructure(Element complexType) {
        if (complexType.getElementsByTagName("xs:sequence").getLength() > 0) {
            return true;
        } else if (complexType.getElementsByTagName("xs:choice").getLength() > 0) {
            return true;
        } else return complexType.getElementsByTagName("xs:all").getLength() > 0;
    }

    private SpecificationAggregateComponent resolveComplexTypeStructure(Element complexType) {
        SpecificationAggregateComponent fromAggregate = new SpecificationAggregateComponent();
        if (complexTypeIsStructure(complexType)) {
            fromAggregate.setComponentName(resolveElementName(complexType));
            fromAggregate.setDefinition(resolveElementDefinition(complexType));
            NodeList structure = complexType.getElementsByTagName("xs:element");
            for (int i = 0; i < structure.getLength(); i++) {
                Element element = (Element) structure.item(i);
                if (elementTypeIsPrimitive(element)) {
                    SpecificationBasicComponent basic = new SpecificationBasicComponent();
                    basic.setComponentName(resolveElementName(element));
                    basic.setDefinition(resolveElementDefinition(element));
                    basic.setAggregateComponentId(fromAggregate.getComponentId());
                    basic.setMinCardinality(resolveElementMinCardinality(element));
                    basic.setMaxCardinality(resolveElementMaxCardinality(element));
                    SpecificationDataType dt = new SpecificationDataType();
                    dt.setDataTypeName("Text");
                    basic.setDataType(dt);
                    basicComponentsList.add(basic);
                    fromAggregate.setSpecificationBasicsList(basicComponentsList);
                } else if (complexTypeMapFull.containsKey(resolveElementType(element))) {
                    SpecificationAssociationComponent association = new SpecificationAssociationComponent();
                    association.setAssociationName(resolveElementName(element));
                    association.setMinCardinality(resolveElementMinCardinality(element));
                    association.setMaxCardinality(resolveElementMaxCardinality(element));
                    association.setDefinition(resolveElementDefinition(element));
                    association.setFromAggregateComponent(fromAggregate);
                    SpecificationAggregateComponent toAggregate = resolveComplexTypeStructure(complexTypeMapFull.get(resolveElementType(element)));
                    association.setToAggregateComponent(toAggregate);
                    associationComponentsList.add(association);
                    fromAggregate.setSpecificationAssociationsList(associationComponentsList);
                } else if (simpleTypeMapFull.containsKey(resolveElementType(element))) {
                    System.out.println("is simple");
                }
            }
            aggregateComponentsList.add(fromAggregate);

        }
        return fromAggregate;
    }

    private void loadIncludedSchemas(Document document) {
        NodeList includedSchemas = document.getElementsByTagName("xs:include");
        for (int i = 0; i < includedSchemas.getLength(); i++) {
            Element first = (Element) includedSchemas.item(i);
            String schemaLocation = first.getAttribute("schemaLocation");
            Document includedSchema = loadSchema(rootFolder, schemaLocation);
            loadComplexTypesFromSchema(includedSchema);
            loadSimpleTypesFromSchema(includedSchema);
        }

    }

    private Document loadSchema(String rootFolder, String schemaLocation) {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse(new File(rootFolder + "/" + schemaLocation));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return doc;
    }

    private Map<String, Element> loadComplexTypesFromSchema(Document document) {
        Map<String, Element> map = new HashMap<>();
        NodeList complexTypeList = document.getElementsByTagName("xs:complexType");
        for (int i = 0; i < complexTypeList.getLength(); i++) {
            Element first = (Element) complexTypeList.item(i);
            map.put(resolveElementName(first), first);
            complexTypeMapFull.put(resolveElementName(first), first);
        }
        return map;
    }

    private void loadSimpleTypesFromSchema(Document document) {
        NodeList simpleTypeList = doc.getElementsByTagName("xs:simpleType");
        for (int i = 0; i < simpleTypeList.getLength(); i++) {
            Element first = (Element) simpleTypeList.item(i);
            simpleTypeMapFull.put(resolveElementName(first), first);
        }
    }

    private boolean elementTypeIsPrimitive(Element element) {
        return element.getAttribute("type").contains("xs:");
    }

    private String resolveElementDefinition(Element element) {
        NodeList documentation = element.getElementsByTagName("xs:documentation");
        return documentation.item(0).getTextContent().trim();
    }

    private String resolveElementType(Element element) {
        return element.getAttribute("type");
    }

    private String resolveElementName(Element element) {
        return element.getAttribute("name");
    }

    private Integer resolveElementMinCardinality(Element element) {
        if (!element.getAttribute("minOccurs").isEmpty()) {
            return Integer.parseInt(element.getAttribute("minOccurs"));
        }
        return null;
    }

    private Integer resolveElementMaxCardinality(Element element) {
        if (!element.getAttribute("maxOccurs").isEmpty()) {
            if (element.getAttribute("maxOccurs").equals("unbounded")) {
                return -1;
            } else return Integer.parseInt(element.getAttribute("maxOccurs"));
        } else return null;
    }

}


