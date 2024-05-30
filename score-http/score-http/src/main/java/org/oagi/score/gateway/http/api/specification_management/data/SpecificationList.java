package org.oagi.score.gateway.http.api.specification_management.data;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class SpecificationList {
    private BigInteger componentId;
    private String status;
    private String componentName;
    private String componentType;
    private String description;
    private Date updatedOn;
    private String owner;
}
