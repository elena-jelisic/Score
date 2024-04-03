package org.oagi.score.gateway.http.api.specification_management.data;

import java.util.List;

public class CreateSpecificationRequest {

    private Source source;
    private String specificationType;
    private Specification specification;
    private List<SpecificationAggregateComponent> specificationAggregatesList;
    private List<SpecificationDataType> specificationDTsList;



    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getSpecificationType() {
        return specificationType;
    }

    public void setSpecificationType(String specificationType) {
        this.specificationType = specificationType;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public List<SpecificationAggregateComponent> getSpecificationAggregatesList() {
        return specificationAggregatesList;
    }

    public void setSpecificationAggregatesList(List<SpecificationAggregateComponent> specificationAggregatesList) {
        this.specificationAggregatesList = specificationAggregatesList;
    }

    public List<SpecificationDataType> getSpecificationDTsList() {
        return specificationDTsList;
    }

    public void setSpecificationDTsList(List<SpecificationDataType> specificationDTsList) {
        this.specificationDTsList = specificationDTsList;
    }
}
