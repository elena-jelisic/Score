/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CdtAwdPri;


/**
 * This table capture allowed primitives of the CDT?s Content Component.  The
 * information in this table is captured from the Allowed Primitive column in
 * each of the CDT Content Component section/table in CCTS DTC3.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CdtAwdPriRecord extends UpdatableRecordImpl<CdtAwdPriRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.cdt_awd_pri.cdt_awd_pri_id</code>. Primary,
     * internal database key.
     */
    public void setCdtAwdPriId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.cdt_awd_pri.cdt_awd_pri_id</code>. Primary,
     * internal database key.
     */
    public ULong getCdtAwdPriId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.cdt_awd_pri.cdt_id</code>. Foreign key pointing to
     * a CDT in the DT table.
     */
    public void setCdtId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.cdt_awd_pri.cdt_id</code>. Foreign key pointing to
     * a CDT in the DT table.
     */
    public ULong getCdtId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>oagi.cdt_awd_pri.cdt_pri_id</code>. Foreign key from the
     * CDT_PRI table. It indicates the primative allowed for the CDT identified
     * in the CDT_ID column. 
     */
    public void setCdtPriId(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.cdt_awd_pri.cdt_pri_id</code>. Foreign key from the
     * CDT_PRI table. It indicates the primative allowed for the CDT identified
     * in the CDT_ID column. 
     */
    public ULong getCdtPriId() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>oagi.cdt_awd_pri.is_default</code>. Indicating a default
     * primitive for the CDT?s Content Component. True for a default primitive;
     * False otherwise.
     */
    public void setIsDefault(Byte value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.cdt_awd_pri.is_default</code>. Indicating a default
     * primitive for the CDT?s Content Component. True for a default primitive;
     * False otherwise.
     */
    public Byte getIsDefault() {
        return (Byte) get(3);
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
     * Create a detached CdtAwdPriRecord
     */
    public CdtAwdPriRecord() {
        super(CdtAwdPri.CDT_AWD_PRI);
    }

    /**
     * Create a detached, initialised CdtAwdPriRecord
     */
    public CdtAwdPriRecord(ULong cdtAwdPriId, ULong cdtId, ULong cdtPriId, Byte isDefault) {
        super(CdtAwdPri.CDT_AWD_PRI);

        setCdtAwdPriId(cdtAwdPriId);
        setCdtId(cdtId);
        setCdtPriId(cdtPriId);
        setIsDefault(isDefault);
        resetChangedOnNotNull();
    }
}
