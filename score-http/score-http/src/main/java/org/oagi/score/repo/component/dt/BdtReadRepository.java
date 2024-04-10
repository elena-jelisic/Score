package org.oagi.score.repo.component.dt;

import org.apache.poi.ss.formula.functions.DMax;
import org.jooq.DSLContext;
import org.jooq.impl.QOM;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.BccRecord;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.DtRecord;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.DtScManifestRecord;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.DtScRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

import static org.jooq.impl.DSL.max;
import static org.oagi.score.repo.api.impl.jooq.entity.Tables.*;
import static org.oagi.score.repo.api.impl.jooq.entity.tables.Acc.ACC;

@Repository
public class BdtReadRepository {

    @Autowired
    private DSLContext dslContext;

    public DtRecord getDtByBccpManifestId(BigInteger bccpManifestId) {
        return dslContext.select(DT.fields())
                .from(DT)
                .join(DT_MANIFEST).on(DT.DT_ID.eq(DT_MANIFEST.DT_ID))
                .join(BCCP_MANIFEST).on(DT_MANIFEST.DT_MANIFEST_ID.eq(BCCP_MANIFEST.BDT_MANIFEST_ID))
                .where(BCCP_MANIFEST.BCCP_MANIFEST_ID.eq(ULong.valueOf(bccpManifestId)))
                .fetchOptionalInto(DtRecord.class).orElse(null);
    }

    public DtRecord getDtByDtManifestId(BigInteger dtManifestId) {
        return dslContext.select(DT.fields())
                .from(DT)
                .join(DT_MANIFEST).on(DT.DT_ID.eq(DT_MANIFEST.DT_ID))
                .where(DT_MANIFEST.DT_MANIFEST_ID.eq(ULong.valueOf(dtManifestId)))
                .fetchOptionalInto(DtRecord.class).orElse(null);
    }

    public BdtNode getBdtNode(BigInteger topLevelAsbiepId, BigInteger dtManifestId) {
        DtRecord dtRecord = getDtByDtManifestId(dtManifestId);
        if (dtRecord == null) {
            return null;
        }

        BdtNode bdtNode = new BdtNode();

        bdtNode.setDataTypeTerm(dtRecord.getDataTypeTerm());
        bdtNode.setQualifier(dtRecord.getQualifier_());
        bdtNode.setDefinition(dtRecord.getDefinition());
        String den = dslContext.select(DT_MANIFEST.DEN)
                .from(DT_MANIFEST)
                .where(DT_MANIFEST.DT_MANIFEST_ID.eq(ULong.valueOf(dtManifestId)))
                .fetchOneInto(String.class);
        bdtNode.setDen(den);
        bdtNode.setBdtManifestId(dtManifestId);

        return bdtNode;
    }

    public List<DtScManifestRecord> getLatestDTSCList() {
        BigInteger latestRelease = dslContext.select(max(RELEASE.RELEASE_ID))
                .from(RELEASE)
                .where(RELEASE.SPECIFICATION_ID.isNull())
                .fetchOneInto(BigInteger.class);
        return dslContext.select(DT_SC_MANIFEST.DT_SC_ID, DT_SC_MANIFEST.OWNER_DT_MANIFEST_ID)
                .from(DT_MANIFEST)
                .join(DT_SC_MANIFEST).on(DT_MANIFEST.DT_MANIFEST_ID.eq(DT_SC_MANIFEST.OWNER_DT_MANIFEST_ID))
                .join(DT_SC).on(DT_SC_MANIFEST.DT_SC_ID.eq(DT_SC.DT_SC_ID))
                .where(DT_SC_MANIFEST.RELEASE_ID.eq(ULong.valueOf(latestRelease)))
                .and(DT_SC.CARDINALITY_MAX.greaterThan(0))
                .fetchInto(DtScManifestRecord.class);
    }
}
