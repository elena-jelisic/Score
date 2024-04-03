package org.oagi.score.gateway.http.api.specification_management.data;


import java.math.BigInteger;

public class SpecificationAssociationComponent {
    private BigInteger componentId;
    private String associationName;
    private String definition;
    private SpecificationAggregateComponent fromAggregateComponent;
    private SpecificationAggregateComponent toAggregateComponent;
    private BigInteger statusCodeId;
    private BigInteger gapAnalysisCodeId;
    private Integer maxCardinality;
    private Integer minCardinality;
    private BigInteger asccId;
    private Byte isApproved;
    private BigInteger specificationId;

    public BigInteger getComponentId() {
        return componentId;
    }

    public void setComponentId(BigInteger componentId) {
        this.componentId = componentId;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public SpecificationAggregateComponent getFromAggregateComponent() {
        return fromAggregateComponent;
    }

    public void setFromAggregateComponent(SpecificationAggregateComponent fromAggregateComponent) {
        this.fromAggregateComponent = fromAggregateComponent;
    }

    public SpecificationAggregateComponent getToAggregateComponent() {
        return toAggregateComponent;
    }

    public void setToAggregateComponent(SpecificationAggregateComponent toAggregateComponent) {
        this.toAggregateComponent = toAggregateComponent;
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

    public Integer getMaxCardinality() {
        return maxCardinality;
    }

    public void setMaxCardinality(Integer maxCardinality) {
        this.maxCardinality = maxCardinality;
    }

    public Integer getMinCardinality() {
        return minCardinality;
    }

    public void setMinCardinality(Integer minCardinality) {
        this.minCardinality = minCardinality;
    }

    public BigInteger getAsccId() {
        return asccId;
    }

    public void setAsccId(BigInteger asccId) {
        this.asccId = asccId;
    }

    public Byte getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Byte isApproved) {
        this.isApproved = isApproved;
    }

    public BigInteger getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(BigInteger specificationId) {
        this.specificationId = specificationId;
    }
}
