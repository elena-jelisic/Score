package org.oagi.score.gateway.http.api.multi_standard_management.data;

import lombok.Data;
import org.jooq.types.ULong;

@Data
public class SpecificationType {
    private ULong specificationTypeId;
    private String specificationTypeName;
}
