package org.oagi.score.gateway.http.api.specification_management.data;

import java.math.BigInteger;

public class SpecificationDataTypeAttribute {
    private Long dataTypeAttributeId;
    private String dataTypeAttributeName;
    private String definition;
    private Integer maxCardinality;
    private Integer minCardinality;
    private SpecificationDataType toDataType;

    public Long getDataTypeAttributeId() {
        return dataTypeAttributeId;
    }

    public void setDataTypeAttributeId(Long dataTypeAttributeId) {
        this.dataTypeAttributeId = dataTypeAttributeId;
    }

    public String getDataTypeAttributeName() {
        return dataTypeAttributeName;
    }

    public void setDataTypeAttributeName(String dataTypeAttributeName) {
        this.dataTypeAttributeName = dataTypeAttributeName;
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

    public SpecificationDataType getToDataType() {
        return toDataType;
    }

    public void setToDataType(SpecificationDataType toDataType) {
        this.toDataType = toDataType;
    }
}
