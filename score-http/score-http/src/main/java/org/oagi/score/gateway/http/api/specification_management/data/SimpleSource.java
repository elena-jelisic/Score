package org.oagi.score.gateway.http.api.specification_management.data;

import lombok.Data;

import java.math.BigInteger;

@Data
public class SimpleSource {
    private BigInteger sourceId;
    private String sourceName;
}
