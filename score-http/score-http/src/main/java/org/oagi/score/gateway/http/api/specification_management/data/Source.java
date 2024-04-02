package org.oagi.score.gateway.http.api.specification_management.data;

import lombok.Data;
import org.jooq.types.ULong;

@Data
public class Source {
    private ULong sourceId;
    private String sourceName;
}
