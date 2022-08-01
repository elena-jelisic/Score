/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CdtRefSpec;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CdtRefSpecRecord extends UpdatableRecordImpl<CdtRefSpecRecord> implements Record3<ULong, ULong, ULong> {

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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<ULong, ULong, ULong> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<ULong, ULong, ULong> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return CdtRefSpec.CDT_REF_SPEC.CDT_REF_SPEC_ID;
    }

    @Override
    public Field<ULong> field2() {
        return CdtRefSpec.CDT_REF_SPEC.CDT_ID;
    }

    @Override
    public Field<ULong> field3() {
        return CdtRefSpec.CDT_REF_SPEC.REF_SPEC_ID;
    }

    @Override
    public ULong component1() {
        return getCdtRefSpecId();
    }

    @Override
    public ULong component2() {
        return getCdtId();
    }

    @Override
    public ULong component3() {
        return getRefSpecId();
    }

    @Override
    public ULong value1() {
        return getCdtRefSpecId();
    }

    @Override
    public ULong value2() {
        return getCdtId();
    }

    @Override
    public ULong value3() {
        return getRefSpecId();
    }

    @Override
    public CdtRefSpecRecord value1(ULong value) {
        setCdtRefSpecId(value);
        return this;
    }

    @Override
    public CdtRefSpecRecord value2(ULong value) {
        setCdtId(value);
        return this;
    }

    @Override
    public CdtRefSpecRecord value3(ULong value) {
        setRefSpecId(value);
        return this;
    }

    @Override
    public CdtRefSpecRecord values(ULong value1, ULong value2, ULong value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
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
    }
}