package org.oagi.score.gateway.http.api.multi_standard_management.data;

import lombok.Data;
import org.jooq.types.ULong;

@Data
public class Specification {

    private ULong specificationId;
    private ULong specificationTypeId;
    private ULong basedSpecificationId;
    private ULong sourceId;
    private String specificationName;
}
