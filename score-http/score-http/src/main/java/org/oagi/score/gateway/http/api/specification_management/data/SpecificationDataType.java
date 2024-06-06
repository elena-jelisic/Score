package org.oagi.score.gateway.http.api.specification_management.data;


import java.math.BigInteger;

public class SpecificationDataType {
    private Long dataTypeId;
    private String dataTypeName;
    private String definition;
    private BigInteger statusCodeId;
    private BigInteger gapAnalysisCodeId;
    private BigInteger dtId;
    private Byte isApproved;
    private BigInteger specificationId;
    private SpecificationDataType basedDT;
    private String constraint;
    private String constraintType;

    public SpecificationDataType getBasedDT() {
        return basedDT;
    }

    public void setBasedDT(SpecificationDataType basedDT) {
        this.basedDT = basedDT;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getConstraintType() {
        return constraintType;
    }

    public void setConstraintType(String constraintType) {
        this.constraintType = constraintType;
    }

    public Long getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Long dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
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

    public BigInteger getDtId() {
        return dtId;
    }

    public void setDtId(BigInteger dtId) {
        this.dtId = dtId;
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
