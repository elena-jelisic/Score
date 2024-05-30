package org.oagi.score.gateway.http.api.specification_management.data;

import lombok.Data;

import java.math.BigInteger;

@Data
public class SimpleSpecification {
    private BigInteger specificationId;
    private String uri;
    private String specificationName;
}
