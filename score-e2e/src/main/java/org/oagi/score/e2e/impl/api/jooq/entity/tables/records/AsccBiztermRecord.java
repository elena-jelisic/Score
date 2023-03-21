/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.AsccBizterm;


/**
 * The ascc_bizterm table stores information about the aggregation between the
 * business term and ASCC. TODO: Placeholder, definition is missing.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AsccBiztermRecord extends UpdatableRecordImpl<AsccBiztermRecord> implements Record7<ULong, ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.ascc_bizterm.ascc_bizterm_id</code>. An internal,
     * primary database key of an Business term.
     */
    public void setAsccBiztermId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.ascc_bizterm.ascc_bizterm_id</code>. An internal,
     * primary database key of an Business term.
     */
    public ULong getAsccBiztermId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.ascc_bizterm.business_term_id</code>. An internal
     * ID of the associated business term
     */
    public void setBusinessTermId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.ascc_bizterm.business_term_id</code>. An internal
     * ID of the associated business term
     */
    public ULong getBusinessTermId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>oagi.ascc_bizterm.ascc_id</code>. An internal ID of the
     * associated ASCC
     */
    public void setAsccId(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.ascc_bizterm.ascc_id</code>. An internal ID of the
     * associated ASCC
     */
    public ULong getAsccId() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>oagi.ascc_bizterm.created_by</code>. A foreign key
     * referring to the user who creates the ascc_bizterm record. The creator of
     * the ascc_bizterm is also its owner by default.
     */
    public void setCreatedBy(ULong value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.ascc_bizterm.created_by</code>. A foreign key
     * referring to the user who creates the ascc_bizterm record. The creator of
     * the ascc_bizterm is also its owner by default.
     */
    public ULong getCreatedBy() {
        return (ULong) get(3);
    }

    /**
     * Setter for <code>oagi.ascc_bizterm.last_updated_by</code>. A foreign key
     * referring to the last user who has updated the ascc_bizterm record. This
     * may be the user who is in the same group as the creator.
     */
    public void setLastUpdatedBy(ULong value) {
        set(4, value);
    }

    /**
     * Getter for <code>oagi.ascc_bizterm.last_updated_by</code>. A foreign key
     * referring to the last user who has updated the ascc_bizterm record. This
     * may be the user who is in the same group as the creator.
     */
    public ULong getLastUpdatedBy() {
        return (ULong) get(4);
    }

    /**
     * Setter for <code>oagi.ascc_bizterm.creation_timestamp</code>. Timestamp
     * when the ascc_bizterm record was first created.
     */
    public void setCreationTimestamp(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.ascc_bizterm.creation_timestamp</code>. Timestamp
     * when the ascc_bizterm record was first created.
     */
    public LocalDateTime getCreationTimestamp() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>oagi.ascc_bizterm.last_update_timestamp</code>. The
     * timestamp when the ascc_bizterm was last updated.
     */
    public void setLastUpdateTimestamp(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>oagi.ascc_bizterm.last_update_timestamp</code>. The
     * timestamp when the ascc_bizterm was last updated.
     */
    public LocalDateTime getLastUpdateTimestamp() {
        return (LocalDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<ULong, ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<ULong, ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return AsccBizterm.ASCC_BIZTERM.ASCC_BIZTERM_ID;
    }

    @Override
    public Field<ULong> field2() {
        return AsccBizterm.ASCC_BIZTERM.BUSINESS_TERM_ID;
    }

    @Override
    public Field<ULong> field3() {
        return AsccBizterm.ASCC_BIZTERM.ASCC_ID;
    }

    @Override
    public Field<ULong> field4() {
        return AsccBizterm.ASCC_BIZTERM.CREATED_BY;
    }

    @Override
    public Field<ULong> field5() {
        return AsccBizterm.ASCC_BIZTERM.LAST_UPDATED_BY;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return AsccBizterm.ASCC_BIZTERM.CREATION_TIMESTAMP;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return AsccBizterm.ASCC_BIZTERM.LAST_UPDATE_TIMESTAMP;
    }

    @Override
    public ULong component1() {
        return getAsccBiztermId();
    }

    @Override
    public ULong component2() {
        return getBusinessTermId();
    }

    @Override
    public ULong component3() {
        return getAsccId();
    }

    @Override
    public ULong component4() {
        return getCreatedBy();
    }

    @Override
    public ULong component5() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime component6() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime component7() {
        return getLastUpdateTimestamp();
    }

    @Override
    public ULong value1() {
        return getAsccBiztermId();
    }

    @Override
    public ULong value2() {
        return getBusinessTermId();
    }

    @Override
    public ULong value3() {
        return getAsccId();
    }

    @Override
    public ULong value4() {
        return getCreatedBy();
    }

    @Override
    public ULong value5() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime value6() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime value7() {
        return getLastUpdateTimestamp();
    }

    @Override
    public AsccBiztermRecord value1(ULong value) {
        setAsccBiztermId(value);
        return this;
    }

    @Override
    public AsccBiztermRecord value2(ULong value) {
        setBusinessTermId(value);
        return this;
    }

    @Override
    public AsccBiztermRecord value3(ULong value) {
        setAsccId(value);
        return this;
    }

    @Override
    public AsccBiztermRecord value4(ULong value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public AsccBiztermRecord value5(ULong value) {
        setLastUpdatedBy(value);
        return this;
    }

    @Override
    public AsccBiztermRecord value6(LocalDateTime value) {
        setCreationTimestamp(value);
        return this;
    }

    @Override
    public AsccBiztermRecord value7(LocalDateTime value) {
        setLastUpdateTimestamp(value);
        return this;
    }

    @Override
    public AsccBiztermRecord values(ULong value1, ULong value2, ULong value3, ULong value4, ULong value5, LocalDateTime value6, LocalDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AsccBiztermRecord
     */
    public AsccBiztermRecord() {
        super(AsccBizterm.ASCC_BIZTERM);
    }

    /**
     * Create a detached, initialised AsccBiztermRecord
     */
    public AsccBiztermRecord(ULong asccBiztermId, ULong businessTermId, ULong asccId, ULong createdBy, ULong lastUpdatedBy, LocalDateTime creationTimestamp, LocalDateTime lastUpdateTimestamp) {
        super(AsccBizterm.ASCC_BIZTERM);

        setAsccBiztermId(asccBiztermId);
        setBusinessTermId(businessTermId);
        setAsccId(asccId);
        setCreatedBy(createdBy);
        setLastUpdatedBy(lastUpdatedBy);
        setCreationTimestamp(creationTimestamp);
        setLastUpdateTimestamp(lastUpdateTimestamp);
    }
}
