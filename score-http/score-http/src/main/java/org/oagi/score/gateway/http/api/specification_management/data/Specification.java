package org.oagi.score.gateway.http.api.specification_management.data;


import java.math.BigInteger;

public class Specification {

    private BigInteger specificationId;
    private BigInteger specificationTypeId;
    private BigInteger basedSpecificationId;
    private BigInteger sourceId;
    private String specificationName;

    public BigInteger getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(BigInteger specificationId) {
        this.specificationId = specificationId;
    }

    public BigInteger getSpecificationTypeId() {
        return specificationTypeId;
    }

    public void setSpecificationTypeId(BigInteger specificationTypeId) {
        this.specificationTypeId = specificationTypeId;
    }

    public BigInteger getBasedSpecificationId() {
        return basedSpecificationId;
    }

    public void setBasedSpecificationId(BigInteger basedSpecificationId) {
        this.basedSpecificationId = basedSpecificationId;
    }

    public BigInteger getSourceId() {
        return sourceId;
    }

    public void setSourceId(BigInteger sourceId) {
        this.sourceId = sourceId;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }
}
