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
    private Map<String, List<SpecificationBasicComponent>> basicComponentsList;
    private Map<String, List<SpecificationAssociationComponent>> associationComponentsList;
    private Map<String, Element> complexTypeMapFull = new HashMap<>();
    private Map<String, Element> elementMapFull = new HashMap<>();
    private Map<String, Element> complexTypeMapInitial = new HashMap<>();
    private Map<String, Element> simpleTypeMapFull = new HashMap<>();
    private Document doc;
    private String rootFolder;

    @Transactional
    public void insertNewSpecification(AuthenticatedPrincipal user, SpecificationImportRequest request) {
        aggregateComponentsList = new ArrayList<>();
        basicComponentsList = new HashMap<>();
        associationComponentsList = new HashMap<>();
        rootFolder = "/Users/enj2/Documents/QIF3.0-2018-ANSI/xsd/QIFApplications";
        rootFolder = request.getRootFolderPath();
        String documentName = request.getDocumentName();
        doc = loadSchema(rootFolder, documentName);
        complexTypeMapInitial = loadComplexTypesFromSchema(doc);
        elementMapFull = loadElementsFromSchema(doc);
        loadIncludedSchemas(doc);
        for (String key : complexTypeMapInitial.keySet()) {
            resolveComplexTypeStructure(complexTypeMapInitial.get(key));
        }
        CreateSpecificationRequest createSpec = new CreateSpecificationRequest();
        Source source = new Source();
        source.setSourceName(request.getSourceName());
        Specification spec = new Specification();
        spec.setSpecificationName(source.getSourceName() + " " + getSpecificationVersion());
        createSpec.setSpecificationType(request.getSpecificationType());
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
    private boolean elementHasAttributes(Element element) {
        return element.getElementsByTagName("xs:attribute").getLength() > 0;
    }

    private boolean elementIsReferenced(Element element) {
        return !element.getAttribute("ref").equals("");
    }
    private boolean elementIsRestriction(Element element) {
        return element.getElementsByTagName("xs:restriction").getLength() > 0;
    }
    private boolean elementIsList(Element element) {
        return element.getElementsByTagName("xs:list").getLength() > 0;
    }
    private boolean typeIsEnumeration(Element element) {
        return element.getElementsByTagName("xs:enumeration").getLength() > 0;
    }
    private boolean typeIsPattern(Element element) {
        return element.getElementsByTagName("xs:pattern").getLength() > 0;
    }

    private List<String> resolveEnumerationValues (Element element){
        List<String> enumerationValues = new ArrayList<>();
        Element enumerationElement;
        NodeList enumerationElements = element.getElementsByTagName("xs:enumeration");
        for (int i = 0; i < enumerationElements.getLength(); i++) {
            enumerationElement = (Element) enumerationElements.item(i);
            enumerationValues.add(enumerationElement.getAttribute("value"));
        }
        return enumerationValues;
    }

    private String resolvePattern (Element element){
        String pattern = "";
        Element patternElement;
        NodeList patternElements = element.getElementsByTagName("xs:pattern");
        for (int i = 0; i < patternElements.getLength(); i++) {
            patternElement = (Element) patternElements.item(i);
            pattern = patternElement.getAttribute("value");
        }
        return pattern;
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
    private boolean complexTypeISDefinedAsSimpleContent (Element complexType) {
        return complexType.getElementsByTagName("xs:simpleContent").getLength() > 0;
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
                    dt.setDataTypeName(elementType);
                    basic.setDataType(dt);
                    addBasicComponentToTheList(fromAggregate.getComponentName(), basic);
                } else if (complexTypeMapFull.containsKey(elementType)) {
                    if (complexTypeISDefinedAsSimpleContent(complexTypeMapFull.get(elementType))) {
                        SpecificationBasicComponent basic = new SpecificationBasicComponent();
                        basic.setComponentName(resolveElementName(element));
                        basic.setDefinition(resolveElementDefinition(element));
                        basic.setAggregateComponentId(fromAggregate.getComponentId());
                        basic.setMinCardinality(resolveElementMinCardinality(element));
                        basic.setMaxCardinality(resolveElementMaxCardinality(element));
                        basic.setDataType(specificationDTManagement(elementType));
                        addBasicComponentToTheList(fromAggregate.getComponentName(), basic);
                    } else {
                        SpecificationAssociationComponent association = new SpecificationAssociationComponent();
                        association.setAssociationName(resolveElementName(element));
                        association.setMinCardinality(resolveElementMinCardinality(element));
                        association.setMaxCardinality(resolveElementMaxCardinality(element));
                        association.setDefinition(resolveElementDefinition(element));
                        association.setFromAggregateComponent(fromAggregate);
                        SpecificationAggregateComponent toAggregate = resolveComplexTypeStructure(complexTypeMapFull.get(elementType));
                        association.setToAggregateComponent(toAggregate);
                        addAssociationComponentToTheList(fromAggregate.getComponentName(), association);
                    }

                } else if (simpleTypeMapFull.containsKey(elementType)) {
                    SpecificationBasicComponent basic = new SpecificationBasicComponent();
                    basic.setComponentName(resolveElementName(element));
                    basic.setDefinition(resolveElementDefinition(element));
                    basic.setAggregateComponentId(fromAggregate.getComponentId());
                    basic.setMinCardinality(resolveElementMinCardinality(element));
                    basic.setMaxCardinality(resolveElementMaxCardinality(element));
                    basic.setDataType(specificationDTManagement(elementType));
                    addBasicComponentToTheList(fromAggregate.getComponentName(), basic);
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
                    basic.setDataType(specificationDTManagement(elementType));
                    addBasicComponentToTheList(fromAggregate.getComponentName(), basic);
                } else if (complexTypeMapFull.containsKey(elementType)) {
                    if (complexTypeISDefinedAsSimpleContent(complexTypeMapFull.get(elementType))) {
                        SpecificationBasicComponent basic = new SpecificationBasicComponent();
                        basic.setComponentName(resolveElementName(element));
                        basic.setDefinition(resolveElementDefinition(element));
                        basic.setAggregateComponentId(fromAggregate.getComponentId());
                        basic.setMinCardinality(resolveElementMinCardinality(element));
                        basic.setMaxCardinality(resolveElementMaxCardinality(element));
                        basic.setDataType(specificationDTManagement(elementType));
                        addBasicComponentToTheList(fromAggregate.getComponentName(), basic);
                    } else {
                        SpecificationAssociationComponent association = new SpecificationAssociationComponent();
                        association.setAssociationName(resolveElementName(element));
                        association.setMinCardinality(resolveElementMinCardinality(element));
                        association.setMaxCardinality(resolveElementMaxCardinality(element));
                        association.setDefinition(resolveElementDefinition(element));
                        association.setFromAggregateComponent(fromAggregate);
                        SpecificationAggregateComponent toAggregate = resolveComplexTypeStructure(complexTypeMapFull.get(elementType));
                        association.setToAggregateComponent(toAggregate);
                        addAssociationComponentToTheList(fromAggregate.getComponentName(), association);
                    }

                } else if (simpleTypeMapFull.containsKey(elementType)) {
                    SpecificationBasicComponent basic = new SpecificationBasicComponent();
                    basic.setComponentName(resolveElementName(element));
                    basic.setDefinition(resolveElementDefinition(element));
                    basic.setAggregateComponentId(fromAggregate.getComponentId());
                    basic.setMinCardinality(resolveElementMinCardinality(element));
                    basic.setMaxCardinality(resolveElementMaxCardinality(element));
                    basic.setDataType(specificationDTManagement(elementType));
                    addBasicComponentToTheList(fromAggregate.getComponentName(), basic);
                }
            }

        }
        if (elementHasAttributes(complexType)){
            NodeList attributeList = complexType.getElementsByTagName("xs:attribute");
            for (int i = 0; i < attributeList.getLength(); i++) {
                Element element = (Element) attributeList.item(i);
                String elementType = resolveElementType(element);
                if (elementTypeIsPrimitive(elementType)) {
                    SpecificationBasicComponent basic = new SpecificationBasicComponent();
                    basic.setComponentName(resolveElementName(element));
                    basic.setDefinition(resolveElementDefinition(element));
                    basic.setAggregateComponentId(fromAggregate.getComponentId());
                    basic.setMinCardinality(resolveElementMinCardinality(element));
                    basic.setMaxCardinality(resolveElementMaxCardinality(element));
                    basic.setDataType(specificationDTManagement(elementType));
                    addBasicComponentToTheList(fromAggregate.getComponentName(), basic);
                } else if (simpleTypeMapFull.containsKey(elementType)) {
                    SpecificationBasicComponent basic = new SpecificationBasicComponent();
                    basic.setComponentName(resolveElementName(element));
                    basic.setDefinition(resolveElementDefinition(element));
                    basic.setAggregateComponentId(fromAggregate.getComponentId());
                    basic.setMinCardinality(resolveElementMinCardinality(element));
                    basic.setMaxCardinality(resolveElementMaxCardinality(element));
                    basic.setDataType(specificationDTManagement(elementType));
                    addBasicComponentToTheList(fromAggregate.getComponentName(), basic);
                }
            }
        }
        fromAggregate.setSpecificationBasicsList(basicComponentsList.get(fromAggregate.getComponentName()));
        fromAggregate.setSpecificationAssociationsList(associationComponentsList.get(fromAggregate.getComponentName()));
        aggregateComponentsList.add(fromAggregate);
        return fromAggregate;
    }

    private SpecificationDataType specificationDTManagement (String type){
        SpecificationDataType dt = new SpecificationDataType();
        if (simpleTypeMapFull.get(type)!= null){
            if (elementIsRestriction(simpleTypeMapFull.get(type))){
                Element restrictionElement = resolveRestriction(simpleTypeMapFull.get(type));
                String baseType = resolveTheBaseType(restrictionElement);
                SpecificationDataType baseDT = specificationDTManagement(baseType);
                dt.setBasedDT(baseDT);
                if (typeIsEnumeration(restrictionElement)){
                    dt.setConstraintType(ConstraintType.ENUMERATION.toString());
                    List<String> enumerationValues = resolveEnumerationValues(restrictionElement);
                    dt.setConstraint(String.join(",", enumerationValues));
                } else if (typeIsPattern(restrictionElement)){
                    dt.setConstraintType(ConstraintType.PATTERN.toString());
                    String pattern = resolvePattern(restrictionElement);
                    dt.setConstraint(pattern);
                }
            } else if (elementIsList(simpleTypeMapFull.get(type))){
                Element listElement = resolveListElement (simpleTypeMapFull.get(type));
                dt.setConstraintType(ConstraintType.LIST.toString());
                dt.setConstraint(resolveItemTypeInAList(listElement));
            }
        } else if (complexTypeMapFull.containsKey(type)){
            if (elementIsExtension(complexTypeMapFull.get(type))){
                Element extensionElement = resolveExtension(complexTypeMapFull.get(type));
                String baseType = resolveTheBaseType(extensionElement);
                SpecificationDataType baseDT = specificationDTManagement(baseType);
                dt.setBasedDT(baseDT);
                if (elementHasAttributes(complexTypeMapFull.get(type))){
                    NodeList attributeList = complexTypeMapFull.get(type).getElementsByTagName("xs:attribute");
                    List<SpecificationDataTypeAttribute> dtAttributeList = new ArrayList<>();
                    for (int i = 0; i < attributeList.getLength(); i++) {
                        Element element = (Element) attributeList.item(i);
                        String elementType = resolveElementType(element);
                        if (elementTypeIsPrimitive(elementType)) {
                            SpecificationDataTypeAttribute dtAttribute = new SpecificationDataTypeAttribute();
                            dtAttribute.setDataTypeAttributeName(resolveElementName(element));
                            dtAttribute.setDefinition(resolveElementDefinition(element));
                            dtAttribute.setMinCardinality(resolveElementMinCardinality(element));
                            dtAttribute.setMaxCardinality(resolveElementMaxCardinality(element));
                            dtAttribute.setToDataType(specificationDTManagement(elementType));
                            dtAttributeList.add(dtAttribute);
                        } else if (simpleTypeMapFull.containsKey(elementType)) {
                            SpecificationDataTypeAttribute dtAttribute = new SpecificationDataTypeAttribute();
                            dtAttribute.setDataTypeAttributeName(resolveElementName(element));
                            dtAttribute.setDefinition(resolveElementDefinition(element));
                            dtAttribute.setMinCardinality(resolveElementMinCardinality(element));
                            dtAttribute.setMaxCardinality(resolveElementMaxCardinality(element));
                            dtAttribute.setToDataType(specificationDTManagement(elementType));
                            dtAttributeList.add(dtAttribute);
                        }
                    }
                    dt.setAttributeList(dtAttributeList);
                }
            }
        }
        dt.setDataTypeName(type);
        return dt;
    }
    private void addBasicComponentToTheList (String aggregate, SpecificationBasicComponent basic){
            if (basicComponentsList.containsKey(aggregate)){
                basicComponentsList.get(aggregate).add(basic);
            } else {
                List<SpecificationBasicComponent> basics = new ArrayList<>();
                basics.add(basic);
                basicComponentsList.put(aggregate, basics);
            }

    }
    private void addAssociationComponentToTheList (String aggregate, SpecificationAssociationComponent association){
            if (associationComponentsList.containsKey(aggregate)){
                associationComponentsList.get(aggregate).add(association);
            } else {
                List<SpecificationAssociationComponent> associations = new ArrayList<>();
                associations.add(association);
                associationComponentsList.put(aggregate, associations);
            }

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
            loadElementsFromSchema(includedSchema);
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
            if (!resolveElementName(first).equals("")){
                map.put(resolveElementName(first), first);
                elementMapFull.put(resolveElementName(first), first);
            }
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
        if (documentation.item(0) != null) {
            return documentation.item(0).getTextContent().trim().replaceAll(" +", " ");
        } else {
            return "";
        }

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

    private Element resolveListElement (Element element){
        NodeList list = element.getElementsByTagName("xs:list");
        return (Element) list.item(0);
    }
    private String resolveTheBaseType(Element element) {
        return element.getAttribute("base");
    }

    private String resolveItemTypeInAList (Element element){
        return element.getAttribute("itemType");
    }

    private String resolveElementType(Element element) {
        String type = "";
        if (element.getAttribute("type").equals("")){
            if (elementIsReferenced(element)){
                type = findElementDefinition(element);
            }
        } else {
            type = element.getAttribute("type");
        }
        return type;
    }

    private String findElementDefinition (Element element){
        Element referencedElement = elementMapFull.get(resolveTheReferencedElements(element));
        return resolveElementType(referencedElement);
    }

    private String resolveElementName(Element element) {
        String name = "";
        if (element.getAttribute("name").equals("")){
            if (elementIsReferenced(element)){
                name = resolveTheReferencedElements(element);
            }
        } else {
            name = element.getAttribute("name");
        }
        return name;
    }
    private String resolveTheReferencedElements(Element element) {
        return element.getAttribute("ref");
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

