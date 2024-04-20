/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SuperAcc;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SuperAccRecord extends UpdatableRecordImpl<SuperAccRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.super_acc.super_acc_id</code>.
     */
    public void setSuperAccId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.super_acc.super_acc_id</code>.
     */
    public Long getSuperAccId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>oagi.super_acc.super_acc_name</code>.
     */
    public void setSuperAccName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.super_acc.super_acc_name</code>.
     */
    public String getSuperAccName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SuperAccRecord
     */
    public SuperAccRecord() {
        super(SuperAcc.SUPER_ACC);
    }

    /**
     * Create a detached, initialised SuperAccRecord
     */
    public SuperAccRecord(Long superAccId, String superAccName) {
        super(SuperAcc.SUPER_ACC);

        setSuperAccId(superAccId);
        setSuperAccName(superAccName);
        resetChangedOnNotNull();
    }
}