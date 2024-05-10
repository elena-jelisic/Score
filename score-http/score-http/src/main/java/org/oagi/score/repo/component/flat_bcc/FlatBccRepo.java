package org.oagi.score.repo.component.flat_bcc;

import org.jooq.DSLContext;
import org.jooq.types.ULong;
import org.oagi.score.gateway.http.api.specification_management.data.FlatBcc;
import org.oagi.score.gateway.http.api.specification_management.data.SuperBcc;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.FlatBccRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.oagi.score.repo.api.impl.jooq.entity.Tables.FLAT_BCC;

@Repository
public class FlatBccRepo {

    @Autowired
    private DSLContext dslContext;

    public FlatBccRecord insertFlatBcc(FlatBcc flatBCC) {
        FlatBccRecord flatBccRecord = new FlatBccRecord();
        flatBccRecord.setAccId(ULong.valueOf(flatBCC.getAccId()));
        flatBccRecord.setBccId(ULong.valueOf(flatBCC.getBccID()));
        flatBccRecord.setDtScId(flatBCC.getDtSCId() != null ? ULong.valueOf(flatBCC.getDtSCId()) : null);
        flatBccRecord.setSuperBccId(flatBCC.getSuperBccId() != null ? flatBCC.getSuperBccId().longValue() : null);
        flatBccRecord.setPath(flatBCC.getPath());

//        flatBCC.setFlatBccId(BigInteger.valueOf(dslContext.insertInto(FLAT_BCC)
//                .set(flatBccRecord)
//                .returning(FLAT_BCC.FLAT_BCC_ID).fetchOne()
//                .getFlatBccId()));

        return flatBccRecord;
    }

    public void insertFlatBccList(List<FlatBccRecord> flatBccRecordList) {
        dslContext.batch(
                        flatBccRecordList.stream()
                                .map(record -> dslContext.insertInto(FLAT_BCC)
                                        .set(record))
                                .collect(Collectors.toList()))
                .execute();
    }

    public FlatBcc updateSuperBCCId (FlatBcc flatBCC, SuperBcc superBCC){

        return flatBCC;
    }
}
