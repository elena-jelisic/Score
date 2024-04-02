/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SuperBcc;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SuperBccRecord extends UpdatableRecordImpl<SuperBccRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.super_bcc.super_bcc_id</code>.
     */
    public void setSuperBccId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.super_bcc.super_bcc_id</code>.
     */
    public Long getSuperBccId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>oagi.super_bcc.super_bcc_name</code>.
     */
    public void setSuperBccName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.super_bcc.super_bcc_name</code>.
     */
    public String getSuperBccName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>oagi.super_bcc.min_cardinality</code>.
     */
    public void setMinCardinality(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.super_bcc.min_cardinality</code>.
     */
    public Integer getMinCardinality() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>oagi.super_bcc.max_cardinality</code>.
     */
    public void setMaxCardinality(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.super_bcc.max_cardinality</code>.
     */
    public Integer getMaxCardinality() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>oagi.super_bcc.from_super_acc_id</code>.
     */
    public void setFromSuperAccId(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>oagi.super_bcc.from_super_acc_id</code>.
     */
    public Long getFromSuperAccId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>oagi.super_bcc.super_bccp_id</code>.
     */
    public void setSuperBccpId(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.super_bcc.super_bccp_id</code>.
     */
    public Long getSuperBccpId() {
        return (Long) get(5);
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
     * Create a detached SuperBccRecord
     */
    public SuperBccRecord() {
        super(SuperBcc.SUPER_BCC);
    }

    /**
     * Create a detached, initialised SuperBccRecord
     */
    public SuperBccRecord(Long superBccId, String superBccName, Integer minCardinality, Integer maxCardinality, Long fromSuperAccId, Long superBccpId) {
        super(SuperBcc.SUPER_BCC);

        setSuperBccId(superBccId);
        setSuperBccName(superBccName);
        setMinCardinality(minCardinality);
        setMaxCardinality(maxCardinality);
        setFromSuperAccId(fromSuperAccId);
        setSuperBccpId(superBccpId);
        resetChangedOnNotNull();
    }
}
