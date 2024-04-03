package org.oagi.score.repo.api.specification.model;


import java.math.BigInteger;


public class SpecificationBasicComponent {
    private BigInteger componentId;
    private String componentName;
    private String definition;
    private Integer maxCardinality;
    private Integer minCardinality;
    private String valueConstraint;
    private BigInteger statusCodeId;
    private BigInteger aggregateComponentId;
    private BigInteger gapAnalysisCodeId;
    private BigInteger bccId;
    private SpecificationDataType dataType;
    private Byte isApproved;
    private BigInteger specificationId;

    public BigInteger getComponentId() {
        return componentId;
    }

    public void setComponentId(BigInteger componentId) {
        this.componentId = componentId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
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

    public String getValueConstraint() {
        return valueConstraint;
    }

    public void setValueConstraint(String valueConstraint) {
        this.valueConstraint = valueConstraint;
    }

    public BigInteger getStatusCodeId() {
        return statusCodeId;
    }

    public void setStatusCodeId(BigInteger statusCodeId) {
        this.statusCodeId = statusCodeId;
    }

    public BigInteger getAggregateComponentId() {
        return aggregateComponentId;
    }

    public void setAggregateComponentId(BigInteger aggregateComponentId) {
        this.aggregateComponentId = aggregateComponentId;
    }

    public BigInteger getGapAnalysisCodeId() {
        return gapAnalysisCodeId;
    }

    public void setGapAnalysisCodeId(BigInteger gapAnalysisCodeId) {
        this.gapAnalysisCodeId = gapAnalysisCodeId;
    }

    public BigInteger getBccId() {
        return bccId;
    }

    public void setBccId(BigInteger bccId) {
        this.bccId = bccId;
    }

    public SpecificationDataType getDataType() {
        return dataType;
    }

    public void setDataType(SpecificationDataType dataType) {
        this.dataType = dataType;
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
