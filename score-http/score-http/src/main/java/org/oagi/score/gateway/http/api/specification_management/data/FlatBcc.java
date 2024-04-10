package org.oagi.score.gateway.http.api.specification_management.data;

import java.math.BigInteger;

public class FlatBcc {
    private BigInteger flatBccId;
    private BigInteger accId;
    private BigInteger bccID;
    private BigInteger dtSCId;
    private BigInteger superBccId;
    private String path;

    public BigInteger getFlatBccId() {
        return flatBccId;
    }

    public void setFlatBccId(BigInteger flatBccId) {
        this.flatBccId = flatBccId;
    }

    public BigInteger getAccId() {
        return accId;
    }

    public void setAccId(BigInteger accId) {
        this.accId = accId;
    }

    public BigInteger getBccID() {
        return bccID;
    }

    public void setBccID(BigInteger bccID) {
        this.bccID = bccID;
    }

    public BigInteger getDtSCId() {
        return dtSCId;
    }

    public void setDtSCId(BigInteger dtSCId) {
        this.dtSCId = dtSCId;
    }

    public BigInteger getSuperBccId() {
        return superBccId;
    }

    public void setSuperBccId(BigInteger superBccId) {
        this.superBccId = superBccId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
