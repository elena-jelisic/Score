package org.oagi.score.gateway.http.api.specification_management.data;

import org.oagi.score.repo.api.base.Response;

import java.util.List;

public class CreateSpecificationResponse extends Response {

    private List<SpecificationAggregateComponent> aggregates;

    public CreateSpecificationResponse(List<SpecificationAggregateComponent> aggregates) {
        this.aggregates = aggregates;
    }

    public List<SpecificationAggregateComponent> getAggregates() {
        return aggregates;
    }

    public void setAggregates(List<SpecificationAggregateComponent> aggregates) {
        this.aggregates = aggregates;
    }
}
