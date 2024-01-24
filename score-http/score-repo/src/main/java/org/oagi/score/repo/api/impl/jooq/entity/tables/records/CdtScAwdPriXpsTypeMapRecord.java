/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.CdtScAwdPriXpsTypeMap;


/**
 * The purpose of this table is the same as that of the
 * CDT_AWD_PRI_XPS_TYPE_MAP, but it is for the supplementary component (SC). It
 * allows for the concrete mapping between the CDT Primitives and types in a
 * particular expression such as XML Schema, JSON. 
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CdtScAwdPriXpsTypeMapRecord extends UpdatableRecordImpl<CdtScAwdPriXpsTypeMapRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>oagi.cdt_sc_awd_pri_xps_type_map.cdt_sc_awd_pri_xps_type_map_id</code>.
     * Internal, primary database key.
     */
    public void setCdtScAwdPriXpsTypeMapId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for
     * <code>oagi.cdt_sc_awd_pri_xps_type_map.cdt_sc_awd_pri_xps_type_map_id</code>.
     * Internal, primary database key.
     */
    public ULong getCdtScAwdPriXpsTypeMapId() {
        return (ULong) get(0);
    }

    /**
     * Setter for
     * <code>oagi.cdt_sc_awd_pri_xps_type_map.cdt_sc_awd_pri_id</code>. Foreign
     * key to the CDT_SC_AWD_PRI table.
     */
    public void setCdtScAwdPriId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for
     * <code>oagi.cdt_sc_awd_pri_xps_type_map.cdt_sc_awd_pri_id</code>. Foreign
     * key to the CDT_SC_AWD_PRI table.
     */
    public ULong getCdtScAwdPriId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>oagi.cdt_sc_awd_pri_xps_type_map.xbt_id</code>. Foreign
     * key to the Xbt table. It identifies an XML schema built-in type that maps
     * to the CDT SC Allowed Primitive identified in the CDT_SC_AWD_PRI column.
     */
    public void setXbtId(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.cdt_sc_awd_pri_xps_type_map.xbt_id</code>. Foreign
     * key to the Xbt table. It identifies an XML schema built-in type that maps
     * to the CDT SC Allowed Primitive identified in the CDT_SC_AWD_PRI column.
     */
    public ULong getXbtId() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>oagi.cdt_sc_awd_pri_xps_type_map.is_default</code>.
     * Indicating a default value domain mapping.
     */
    public void setIsDefault(Byte value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.cdt_sc_awd_pri_xps_type_map.is_default</code>.
     * Indicating a default value domain mapping.
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
     * Create a detached CdtScAwdPriXpsTypeMapRecord
     */
    public CdtScAwdPriXpsTypeMapRecord() {
        super(CdtScAwdPriXpsTypeMap.CDT_SC_AWD_PRI_XPS_TYPE_MAP);
    }

    /**
     * Create a detached, initialised CdtScAwdPriXpsTypeMapRecord
     */
    public CdtScAwdPriXpsTypeMapRecord(ULong cdtScAwdPriXpsTypeMapId, ULong cdtScAwdPriId, ULong xbtId, Byte isDefault) {
        super(CdtScAwdPriXpsTypeMap.CDT_SC_AWD_PRI_XPS_TYPE_MAP);

        setCdtScAwdPriXpsTypeMapId(cdtScAwdPriXpsTypeMapId);
        setCdtScAwdPriId(cdtScAwdPriId);
        setXbtId(xbtId);
        setIsDefault(isDefault);
        resetChangedOnNotNull();
    }
}
