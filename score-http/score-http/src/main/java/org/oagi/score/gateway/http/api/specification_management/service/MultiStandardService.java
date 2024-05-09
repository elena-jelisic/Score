package org.oagi.score.gateway.http.api.specification_management.service;

import org.oagi.score.gateway.http.api.namespace_management.data.Namespace;
import org.oagi.score.gateway.http.api.namespace_management.service.NamespaceService;
import org.oagi.score.gateway.http.api.release_management.data.ReleaseState;
import org.oagi.score.gateway.http.api.specification_management.data.*;
import org.oagi.score.gateway.http.configuration.security.SessionService;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.ReleaseRecord;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.SpecificationRecord;
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

import javax.imageio.metadata.IIOMetadataNode;
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
    private Map<String, Element> elementMapFull = new HashMap<>();
    private Map<String, Element> complexTypeMapInitial = new HashMap<>();
    private Map<String, Element> simpleTypeMapFull = new HashMap<>();
    private Document doc;
    private String rootFolder;

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
        spec.setSpecificationName(source.getSourceName() + " " + getSpecificationVersion());
        createSpec.setSpecificationType("Standard library");
        createSpec.setSpecificationAggregatesList(aggregateComponentsList);
        createSpec.setSource(source);
        createSpec.setSpecification(spec);
        CreateSpecificationResponse response = specRepo.createSpecification(createSpec);
        Namespace namespace = new Namespace();
        namespace.setUri(getTargetNamespace());
        namespace.setPrefix(source.getSourceName());
        namespace.setStd(true);
        BigInteger namespaceID = namespaceService.create(user, namespace);
        AppUser targetUser = session.getAppUserByUsername("oagis");
        SpecificationRecord specificationRecord = specRepo.getSpecificationByName(spec.getSpecificationName());
        ReleaseRecord releaseRecord = releaseRepo.createForSpecification(
                targetUser.getAppUserId(), getSpecificationVersion(), source.getSourceName() + " " +getSpecificationVersion(),
                        source.getSourceName(), namespaceID, BigInteger.valueOf(specificationRecord.getSpecificationId()));
        releaseRepo.updateState(targetUser.getAppUserId(), releaseRecord.getReleaseId().toBigInteger(), ReleaseState.Published);
    }

    private boolean elementIsExtension(Element element) {
        return element.getElementsByTagName("xs:extension").getLength() > 0;
    }
    private boolean elementIsRestriction(Element element) {
        return element.getElementsByTagName("xs:restriction").getLength() > 0;
    }

    private boolean complexTypeIsDefinedByCompositor(Element complexType) {
        if (complexType.getElementsByTagName("xs:sequence").getLength() > 0) {
            return true;
        } else if (complexType.getElementsByTagName("xs:choice").getLength() > 0) {
            return true;
        } else return complexType.getElementsByTagName("xs:all").getLength() > 0;
    }
    private boolean complexTypeIsDefinedAsComplexContent(Element complexType) {
        return complexType.getElementsByTagName("xs:complexContent").getLength() > 0;
    }

    private String getTargetNamespace (){
        NodeList schemaElement = doc.getElementsByTagName("xs:schema");
        Element schema = new IIOMetadataNode();
        for (int i = 0; i < schemaElement.getLength(); i++) {
             schema = (Element) schemaElement.item(i);
        }
        return schema.getAttribute("targetNamespace");
    }
    private String getSpecificationVersion (){
        NodeList schemaElement = doc.getElementsByTagName("xs:schema");
        Element schema = new IIOMetadataNode();
        for (int i = 0; i < schemaElement.getLength(); i++) {
            schema = (Element) schemaElement.item(i);
        }
        return schema.getAttribute("version");
    }

    private SpecificationAggregateComponent resolveComplexTypeStructure(Element complexType) {
        basicComponentsList = new ArrayList<>();
        associationComponentsList = new ArrayList<>();
        SpecificationAggregateComponent fromAggregate = new SpecificationAggregateComponent();
        fromAggregate.setComponentName(resolveElementName(complexType));
        fromAggregate.setDefinition(resolveElementDefinition(complexType));
        if (!complexTypeIsDefinedAsComplexContent(complexType)) {
            NodeList structure = complexType.getElementsByTagName("xs:element");
            for (int i = 0; i < structure.getLength(); i++) {
                Element element = (Element) structure.item(i);
                String elementType = resolveElementType(element);
                if (elementTypeIsPrimitive(elementType)) {
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
                } else if (complexTypeMapFull.containsKey(elementType)) {
                    SpecificationAssociationComponent association = new SpecificationAssociationComponent();
                    association.setAssociationName(resolveElementName(element));
                    association.setMinCardinality(resolveElementMinCardinality(element));
                    association.setMaxCardinality(resolveElementMaxCardinality(element));
                    association.setDefinition(resolveElementDefinition(element));
                    association.setFromAggregateComponent(fromAggregate);
                    SpecificationAggregateComponent toAggregate = resolveComplexTypeStructure(complexTypeMapFull.get(elementType));
                    association.setToAggregateComponent(toAggregate);
                    associationComponentsList.add(association);
                } else if (simpleTypeMapFull.containsKey(elementType)) {
                    System.out.println("is simple");
                }
            }
        } else {
            Element complexContent = resolveComplexContent(complexType);
            if (elementIsExtension(complexContent)){
                Element extension = resolveExtension(complexContent);
                String baseType = resolveTheBaseType(extension);
                if (elementTypeIsComplex(baseType)){
                    SpecificationAggregateComponent baseAggregate = resolveComplexTypeStructure(complexTypeMapFull.get(baseType));
                    fromAggregate.setBasedAggregateComponent(baseAggregate);
                } else{
                    //is this possible?
                }
            }
            fromAggregate.setSpecificationBasicsList(basicComponentsList);
            fromAggregate.setSpecificationAssociationsList(associationComponentsList);
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
            NodeList includedSchemasLevelTwo = includedSchema.getElementsByTagName("xs:include");
            loadComplexTypesFromSchema(includedSchema);
            loadSimpleTypesFromSchema(includedSchema);
            if (includedSchemasLevelTwo != null){
                loadIncludedSchemas(includedSchema);
            }
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

    private Map<String, Element> loadElementsFromSchema(Document document) {
        Map<String, Element> map = new HashMap<>();
        NodeList elementList = document.getElementsByTagName("xs:element");
        for (int i = 0; i < elementList.getLength(); i++) {
            Element first = (Element) elementList.item(i);
            map.put(resolveElementName(first), first);
            elementMapFull.put(resolveElementName(first), first);
        }
        return map;
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

    private boolean elementTypeIsPrimitive(String type) {
        return type.contains("xs:");
    }
    private boolean elementTypeIsSimple(String type) {
        return simpleTypeMapFull.containsKey(type);
    }
    private boolean elementTypeIsComplex(String type) {
        return complexTypeMapFull.containsKey(type);
    }

    private String resolveElementDefinition(Element element) {
        NodeList documentation = element.getElementsByTagName("xs:documentation");
        return documentation.item(0).getTextContent().trim();
    }
    private Element resolveExtension(Element element) {
        NodeList extension = element.getElementsByTagName("xs:extension");
        return (Element) extension.item(0);
    }
    private Element resolveComplexContent(Element element) {
        NodeList extension = element.getElementsByTagName("xs:complexContent");
        return (Element) extension.item(0);
    }
    private Element resolveRestriction(Element element) {
        NodeList extension = element.getElementsByTagName("xs:restriction");
        return (Element) extension.item(0);
    }
    private String resolveTheBaseType(Element element) {
        return element.getAttribute("base");
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


