package org.oagi.score.gateway.http.api.specification_management.data;

import lombok.Data;
import org.oagi.score.service.common.data.PageRequest;


@Data
public class SpecificationListRequest {
    private Long specificationId;
    private Long sourceId;
    private PageRequest pageRequest = PageRequest.EMPTY_INSTANCE;
}
