package org.oagi.score.repo.api.openapidoc.model;

import org.oagi.score.repo.api.base.Response;

import java.math.BigInteger;
import java.util.List;

public class DeleteOasDocResponse extends Response {
    private final List<BigInteger> oasDocIdList;

    public DeleteOasDocResponse(List<BigInteger> oasDocIdList) {
        this.oasDocIdList = oasDocIdList;
    }

    public List<BigInteger> getOasDocIdList() {
        return oasDocIdList;
    }

    public boolean contains(BigInteger oasDocId) {
        return this.oasDocIdList.contains(oasDocId);
    }

    public boolean containsAll(List<BigInteger> oasDocIdList) {
        for (BigInteger oasDocId : oasDocIdList) {
            if (!this.oasDocIdList.contains(oasDocId)) {
                return false;
            }
        }
        return true;
    }
}
