package org.oagi.score.gateway.http.api.multi_standard_management.service;

import org.jooq.meta.derby.sys.Sys;
import org.jooq.types.ULong;
import org.oagi.score.gateway.http.api.multi_standard_management.data.SpecificationAggregateComponent;
import org.oagi.score.gateway.http.api.multi_standard_management.data.SpecificationAssociationComponent;
import org.oagi.score.gateway.http.api.multi_standard_management.data.SpecificationBasicComponent;
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
import java.util.*;

@Service
@Transactional(readOnly = true)
public class MultiStandardService {

    private List<SpecificationAggregateComponent> aggregateComponentsList;
    private List<SpecificationBasicComponent> basicComponentsList;
    private List<SpecificationAssociationComponent> associationComponentsList;
    private Map<String, Element> complexTypeMapFull = new HashMap<>();
    private Map<String, Element> complexTypeMapInitial = new HashMap<>();
    private Map<String, Element> simpleTypeMapFull = new HashMap<>();
    private Document doc;
    private String rootFolder;

    public static void main(String args[]) {
        MultiStandardService service = new MultiStandardService();
        service.insertNewSpecification();
    }

    @Transactional
    public void insertNewSpecification() {
        aggregateComponentsList = new ArrayList<>();
        basicComponentsList = new ArrayList<>();
        associationComponentsList = new ArrayList<>();
        //rootFolder = "/Users/enj2/Documents/QIF3.0-2018-ANSI/xsd/QIFApplications";
        rootFolder = "/Users/enj2/Documents/4.3.0 FINAL/Common/xml/person/";
        //String documentName = "QIFResults.xsd";
        String documentName = "LegalDocumentType.xsd";
        doc = loadSchema(rootFolder, documentName);
        complexTypeMapInitial = loadComplexTypesFromSchema(doc);
        loadIncludedSchemas(doc);
        for (String key : complexTypeMapInitial.keySet()) {
            resolveComplexTypeStructure(complexTypeMapInitial.get(key));
        }
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
            fromAggregate.setComponentId(ULong.valueOf(new Random().nextInt(1000)));
            fromAggregate.setComponentName(resolveElementName(complexType));
            fromAggregate.setDefinition(resolveElementDefinition(complexType));
            aggregateComponentsList.add(fromAggregate);
            NodeList structure = complexType.getElementsByTagName("xs:element");
            for (int i = 0; i < structure.getLength(); i++) {
                Element element = (Element) structure.item(i);
                if (elementTypeIsPrimitive(element)) {
                    SpecificationBasicComponent basic = new SpecificationBasicComponent();
                    basic.setComponentId(ULong.valueOf(i));
                    basic.setComponentName(resolveElementName(element));
                    basic.setDefinition(resolveElementDefinition(element));
                    basic.setAggregateComponentId(fromAggregate.getComponentId());
                    basic.setMinCardinality(resolveElementMinCardinality(element));
                    basic.setMaxCardinality(resolveElementMaxCardinality(element));
                    basicComponentsList.add(basic);
                } else if (complexTypeMapFull.containsKey(resolveElementType(element))){
                    SpecificationAssociationComponent association = new SpecificationAssociationComponent();
                    association.setComponentId(ULong.valueOf(i));
                    association.setAssociationName(resolveElementName(element));
                    association.setMinCardinality(resolveElementMinCardinality(element));
                    association.setMaxCardinality(resolveElementMaxCardinality(element));
                    association.setDefinition(resolveElementDefinition(element));
                    association.setFromAggregateComponent(fromAggregate.getComponentId());
                    SpecificationAggregateComponent toAggregate = resolveComplexTypeStructure(complexTypeMapFull.get(resolveElementType(element)));
                    association.setToAggregateComponent(toAggregate.getComponentId());
                    associationComponentsList.add(association);
                } else if (simpleTypeMapFull.containsKey(resolveElementType(element))){
                    System.out.println ("is simple");
                }
            }
        }
        return fromAggregate;
    }

    private void loadIncludedSchemas (Document document){
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

    private Map<String, Element> loadComplexTypesFromSchema (Document document){
        Map<String, Element> map = new HashMap<>();
        NodeList complexTypeList = document.getElementsByTagName("xs:complexType");
        for (int i = 0; i < complexTypeList.getLength(); i++) {
            Element first = (Element) complexTypeList.item(i);
            map.put(resolveElementName(first), first);
            complexTypeMapFull.put(resolveElementName(first), first);
        }
        return map;
    }
    private void loadSimpleTypesFromSchema (Document document){
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


