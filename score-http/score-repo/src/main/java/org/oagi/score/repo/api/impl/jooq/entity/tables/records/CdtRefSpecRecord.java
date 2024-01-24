/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CdtRefSpec;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CdtRefSpecRecord extends UpdatableRecordImpl<CdtRefSpecRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.cdt_ref_spec.cdt_ref_spec_id</code>.
     */
    public void setCdtRefSpecId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.cdt_ref_spec.cdt_ref_spec_id</code>.
     */
    public ULong getCdtRefSpecId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.cdt_ref_spec.cdt_id</code>.
     */
    public void setCdtId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.cdt_ref_spec.cdt_id</code>.
     */
    public ULong getCdtId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>oagi.cdt_ref_spec.ref_spec_id</code>.
     */
    public void setRefSpecId(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.cdt_ref_spec.ref_spec_id</code>.
     */
    public ULong getRefSpecId() {
        return (ULong) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CdtRefSpecRecord
     */
    public CdtRefSpecRecord() {
        super(CdtRefSpec.CDT_REF_SPEC);
    }

    /**
     * Create a detached, initialised CdtRefSpecRecord
     */
    public CdtRefSpecRecord(ULong cdtRefSpecId, ULong cdtId, ULong refSpecId) {
        super(CdtRefSpec.CDT_REF_SPEC);

        setCdtRefSpecId(cdtRefSpecId);
        setCdtId(cdtId);
        setRefSpecId(refSpecId);
        resetChangedOnNotNull();
    }
}
