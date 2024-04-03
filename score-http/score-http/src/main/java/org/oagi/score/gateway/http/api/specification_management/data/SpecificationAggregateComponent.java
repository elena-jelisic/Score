package org.oagi.score.gateway.http.api.specification_management.data;


import java.math.BigInteger;
import java.util.List;

public class SpecificationAggregateComponent {
    private BigInteger componentId;
    private String definition;
    private String componentName;
    private BigInteger specificationId;
    private BigInteger statusCodeId;
    private BigInteger gapAnalysisCodeId;
    private BigInteger accId;
    private Byte isApproved;
    private SpecificationAggregateComponent basedAggregateComponent;
    private List<SpecificationBasicComponent> specificationBasicsList;
    private List<SpecificationAssociationComponent> specificationAssociationsList;

    public BigInteger getComponentId() {
        return componentId;
    }

    public void setComponentId(BigInteger componentId) {
        this.componentId = componentId;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public BigInteger getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(BigInteger specificationId) {
        this.specificationId = specificationId;
    }

    public BigInteger getStatusCodeId() {
        return statusCodeId;
    }

    public void setStatusCodeId(BigInteger statusCodeId) {
        this.statusCodeId = statusCodeId;
    }

    public BigInteger getGapAnalysisCodeId() {
        return gapAnalysisCodeId;
    }

    public void setGapAnalysisCodeId(BigInteger gapAnalysisCodeId) {
        this.gapAnalysisCodeId = gapAnalysisCodeId;
    }

    public BigInteger getAccId() {
        return accId;
    }

    public void setAccId(BigInteger accId) {
        this.accId = accId;
    }

    public Byte getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Byte isApproved) {
        this.isApproved = isApproved;
    }

    public SpecificationAggregateComponent getBasedAggregateComponent() {
        return basedAggregateComponent;
    }

    public void setBasedAggregateComponent(SpecificationAggregateComponent basedAggregateComponent) {
        this.basedAggregateComponent = basedAggregateComponent;
    }

    public List<SpecificationBasicComponent> getSpecificationBasicsList() {
        return specificationBasicsList;
    }

    public void setSpecificationBasicsList(List<SpecificationBasicComponent> specificationBasicsList) {
        this.specificationBasicsList = specificationBasicsList;
    }

    public List<SpecificationAssociationComponent> getSpecificationAssociationsList() {
        return specificationAssociationsList;
    }

    public void setSpecificationAssociationsList(List<SpecificationAssociationComponent> specificationAssociationsList) {
        this.specificationAssociationsList = specificationAssociationsList;
    }
}
