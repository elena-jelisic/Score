package org.oagi.score.gateway.http.api.multi_standard_management.data;

import lombok.Data;
import org.jooq.types.ULong;

@Data
public class SpecificationAssociationComponent {
    private ULong componentId;
    private String associationName;
    private String definition;
    private ULong fromAggregateComponent;
    private ULong toAggregateComponent;
    private ULong statusCodeId;
    private ULong gapAnalysisCodeId;
    private Integer maxCardinality;
    private Integer minCardinality;
    private ULong asccId;
    private Byte isApproved;
    private ULong specificationId;
}
