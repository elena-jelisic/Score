package org.oagi.score.gateway.http.api.specification_management.data;

import lombok.Data;
import org.jooq.types.ULong;

@Data
public class SpecificationType {
    private ULong specificationTypeId;
    private String specificationTypeName;
}
