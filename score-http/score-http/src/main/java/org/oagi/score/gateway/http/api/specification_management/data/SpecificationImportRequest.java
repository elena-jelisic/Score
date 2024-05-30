package org.oagi.score.gateway.http.api.specification_management.data;

import lombok.Data;

@Data
public class SpecificationImportRequest {
    private String documentName;
    private String rootFolderPath;
    private String specificationType;
    private String sourceName;

}
