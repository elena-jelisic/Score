package org.oagi.score.gateway.http.api.specification_management.data;


import java.math.BigInteger;

public class SpecificationDataType {
    private BigInteger dataTypeId;
    private String dataTypeName;
    private String definition;
    private BigInteger statusCodeId;
    private BigInteger gapAnalysisCodeId;
    private BigInteger dtId;
    private Byte isApproved;
    private BigInteger specificationId;

    public BigInteger getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(BigInteger dataTypeId) {
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
