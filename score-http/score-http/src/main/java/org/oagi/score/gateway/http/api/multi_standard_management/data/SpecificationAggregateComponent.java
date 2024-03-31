package org.oagi.score.gateway.http.api.multi_standard_management.data;

import lombok.Data;
import org.jooq.types.ULong;

@Data
public class SpecificationAggregateComponent {
    private ULong componentId;
    private String definition;
    private String componentName;
    private ULong specificationId;
    private ULong statusCodeId;
    private ULong gapAnalysisCodeId;
    private ULong accId;
    private Byte isApproved;
    private ULong basedAggregateComponentId;
}
