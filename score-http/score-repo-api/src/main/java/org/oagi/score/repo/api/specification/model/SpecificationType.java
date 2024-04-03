package org.oagi.score.repo.api.specification.model;


import java.math.BigInteger;

public class SpecificationType {
    private BigInteger specificationTypeId;
    private String specificationTypeName;

    public BigInteger getSpecificationTypeId() {
        return specificationTypeId;
    }

    public void setSpecificationTypeId(BigInteger specificationTypeId) {
        this.specificationTypeId = specificationTypeId;
    }

    public String getSpecificationTypeName() {
        return specificationTypeName;
    }

    public void setSpecificationTypeName(String specificationTypeName) {
        this.specificationTypeName = specificationTypeName;
    }
}
