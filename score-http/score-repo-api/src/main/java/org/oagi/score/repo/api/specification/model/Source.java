package org.oagi.score.repo.api.specification.model;

import java.math.BigInteger;


public class Source {
    private BigInteger sourceId;
    private String sourceName;

    public BigInteger getSourceId() {
        return sourceId;
    }

    public void setSourceId(BigInteger sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
