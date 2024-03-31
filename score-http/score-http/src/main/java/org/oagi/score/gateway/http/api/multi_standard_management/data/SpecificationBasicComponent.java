package org.oagi.score.gateway.http.api.multi_standard_management.data;

import lombok.Data;
import org.jooq.types.ULong;

@Data
public class SpecificationBasicComponent {
    private ULong componentId;
    private String componentName;
    private String definition;
    private Integer maxCardinality;
    private Integer minCardinality;
    private String valueConstraint;
    private ULong statusCodeId;
    private ULong aggregateComponentId;
    private ULong gapAnalysisCodeId;
    private ULong bccId;
    private ULong dataTypeId;
    private Byte isApproved;
    private ULong specificationId;
}
