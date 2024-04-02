package org.oagi.score.gateway.http.api.specification_management.data;

import lombok.Data;
import org.jooq.types.ULong;

@Data
public class SpecificationDataType {
    private ULong dataTypeId;
    private String dataTypeName;
    private String definition;
    private ULong statusCodeId;
    private ULong gapAnalysisCodeId;
    private ULong dtId;
    private Byte isApproved;
    private ULong specificationId;
}
