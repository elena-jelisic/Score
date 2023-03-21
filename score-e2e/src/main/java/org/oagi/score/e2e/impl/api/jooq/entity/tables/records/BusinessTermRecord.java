/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.BusinessTerm;


/**
 * The Business Term table stores information about the business term, which is
 * usually associated to BIE or CC. TODO: Placeeholder, definition is missing.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BusinessTermRecord extends UpdatableRecordImpl<BusinessTermRecord> implements Record11<ULong, String, String, String, ULong, ULong, LocalDateTime, LocalDateTime, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.business_term.business_term_id</code>. A internal,
     * primary database key of an Business term.
     */
    public void setBusinessTermId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.business_term.business_term_id</code>. A internal,
     * primary database key of an Business term.
     */
    public ULong getBusinessTermId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.business_term.guid</code>. A globally unique
     * identifier (GUID).
     */
    public void setGuid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.business_term.guid</code>. A globally unique
     * identifier (GUID).
     */
    public String getGuid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>oagi.business_term.business_term</code>. A main name of
     * the business term
     */
    public void setBusinessTerm(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.business_term.business_term</code>. A main name of
     * the business term
     */
    public String getBusinessTerm() {
        return (String) get(2);
    }

    /**
     * Setter for <code>oagi.business_term.definition</code>. Definition of the
     * business term.
     */
    public void setDefinition(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.business_term.definition</code>. Definition of the
     * business term.
     */
    public String getDefinition() {
        return (String) get(3);
    }

    /**
     * Setter for <code>oagi.business_term.created_by</code>. A foreign key
     * referring to the user who creates the business term. The creator of the
     * business term is also its owner by default.
     */
    public void setCreatedBy(ULong value) {
        set(4, value);
    }

    /**
     * Getter for <code>oagi.business_term.created_by</code>. A foreign key
     * referring to the user who creates the business term. The creator of the
     * business term is also its owner by default.
     */
    public ULong getCreatedBy() {
        return (ULong) get(4);
    }

    /**
     * Setter for <code>oagi.business_term.last_updated_by</code>. A foreign key
     * referring to the last user who has updated the business term record. This
     * may be the user who is in the same group as the creator.
     */
    public void setLastUpdatedBy(ULong value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.business_term.last_updated_by</code>. A foreign key
     * referring to the last user who has updated the business term record. This
     * may be the user who is in the same group as the creator.
     */
    public ULong getLastUpdatedBy() {
        return (ULong) get(5);
    }

    /**
     * Setter for <code>oagi.business_term.creation_timestamp</code>. Timestamp
     * when the business term record was first created.
     */
    public void setCreationTimestamp(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>oagi.business_term.creation_timestamp</code>. Timestamp
     * when the business term record was first created.
     */
    public LocalDateTime getCreationTimestamp() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>oagi.business_term.last_update_timestamp</code>. The
     * timestamp when the business term was last updated.
     */
    public void setLastUpdateTimestamp(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>oagi.business_term.last_update_timestamp</code>. The
     * timestamp when the business term was last updated.
     */
    public LocalDateTime getLastUpdateTimestamp() {
        return (LocalDateTime) get(7);
    }

    /**
     * Setter for <code>oagi.business_term.external_ref_uri</code>. TODO:
     * Definition is missing.
     */
    public void setExternalRefUri(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>oagi.business_term.external_ref_uri</code>. TODO:
     * Definition is missing.
     */
    public String getExternalRefUri() {
        return (String) get(8);
    }

    /**
     * Setter for <code>oagi.business_term.external_ref_id</code>. TODO:
     * Definition is missing.
     */
    public void setExternalRefId(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>oagi.business_term.external_ref_id</code>. TODO:
     * Definition is missing.
     */
    public String getExternalRefId() {
        return (String) get(9);
    }

    /**
     * Setter for <code>oagi.business_term.comment</code>. Comment of the
     * business term.
     */
    public void setComment(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>oagi.business_term.comment</code>. Comment of the
     * business term.
     */
    public String getComment() {
        return (String) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row11<ULong, String, String, String, ULong, ULong, LocalDateTime, LocalDateTime, String, String, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    public Row11<ULong, String, String, String, ULong, ULong, LocalDateTime, LocalDateTime, String, String, String> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return BusinessTerm.BUSINESS_TERM.BUSINESS_TERM_ID;
    }

    @Override
    public Field<String> field2() {
        return BusinessTerm.BUSINESS_TERM.GUID;
    }

    @Override
    public Field<String> field3() {
        return BusinessTerm.BUSINESS_TERM.BUSINESS_TERM_;
    }

    @Override
    public Field<String> field4() {
        return BusinessTerm.BUSINESS_TERM.DEFINITION;
    }

    @Override
    public Field<ULong> field5() {
        return BusinessTerm.BUSINESS_TERM.CREATED_BY;
    }

    @Override
    public Field<ULong> field6() {
        return BusinessTerm.BUSINESS_TERM.LAST_UPDATED_BY;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return BusinessTerm.BUSINESS_TERM.CREATION_TIMESTAMP;
    }

    @Override
    public Field<LocalDateTime> field8() {
        return BusinessTerm.BUSINESS_TERM.LAST_UPDATE_TIMESTAMP;
    }

    @Override
    public Field<String> field9() {
        return BusinessTerm.BUSINESS_TERM.EXTERNAL_REF_URI;
    }

    @Override
    public Field<String> field10() {
        return BusinessTerm.BUSINESS_TERM.EXTERNAL_REF_ID;
    }

    @Override
    public Field<String> field11() {
        return BusinessTerm.BUSINESS_TERM.COMMENT;
    }

    @Override
    public ULong component1() {
        return getBusinessTermId();
    }

    @Override
    public String component2() {
        return getGuid();
    }

    @Override
    public String component3() {
        return getBusinessTerm();
    }

    @Override
    public String component4() {
        return getDefinition();
    }

    @Override
    public ULong component5() {
        return getCreatedBy();
    }

    @Override
    public ULong component6() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime component7() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime component8() {
        return getLastUpdateTimestamp();
    }

    @Override
    public String component9() {
        return getExternalRefUri();
    }

    @Override
    public String component10() {
        return getExternalRefId();
    }

    @Override
    public String component11() {
        return getComment();
    }

    @Override
    public ULong value1() {
        return getBusinessTermId();
    }

    @Override
    public String value2() {
        return getGuid();
    }

    @Override
    public String value3() {
        return getBusinessTerm();
    }

    @Override
    public String value4() {
        return getDefinition();
    }

    @Override
    public ULong value5() {
        return getCreatedBy();
    }

    @Override
    public ULong value6() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime value7() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime value8() {
        return getLastUpdateTimestamp();
    }

    @Override
    public String value9() {
        return getExternalRefUri();
    }

    @Override
    public String value10() {
        return getExternalRefId();
    }

    @Override
    public String value11() {
        return getComment();
    }

    @Override
    public BusinessTermRecord value1(ULong value) {
        setBusinessTermId(value);
        return this;
    }

    @Override
    public BusinessTermRecord value2(String value) {
        setGuid(value);
        return this;
    }

    @Override
    public BusinessTermRecord value3(String value) {
        setBusinessTerm(value);
        return this;
    }

    @Override
    public BusinessTermRecord value4(String value) {
        setDefinition(value);
        return this;
    }

    @Override
    public BusinessTermRecord value5(ULong value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public BusinessTermRecord value6(ULong value) {
        setLastUpdatedBy(value);
        return this;
    }

    @Override
    public BusinessTermRecord value7(LocalDateTime value) {
        setCreationTimestamp(value);
        return this;
    }

    @Override
    public BusinessTermRecord value8(LocalDateTime value) {
        setLastUpdateTimestamp(value);
        return this;
    }

    @Override
    public BusinessTermRecord value9(String value) {
        setExternalRefUri(value);
        return this;
    }

    @Override
    public BusinessTermRecord value10(String value) {
        setExternalRefId(value);
        return this;
    }

    @Override
    public BusinessTermRecord value11(String value) {
        setComment(value);
        return this;
    }

    @Override
    public BusinessTermRecord values(ULong value1, String value2, String value3, String value4, ULong value5, ULong value6, LocalDateTime value7, LocalDateTime value8, String value9, String value10, String value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BusinessTermRecord
     */
    public BusinessTermRecord() {
        super(BusinessTerm.BUSINESS_TERM);
    }

    /**
     * Create a detached, initialised BusinessTermRecord
     */
    public BusinessTermRecord(ULong businessTermId, String guid, String businessTerm, String definition, ULong createdBy, ULong lastUpdatedBy, LocalDateTime creationTimestamp, LocalDateTime lastUpdateTimestamp, String externalRefUri, String externalRefId, String comment) {
        super(BusinessTerm.BUSINESS_TERM);

        setBusinessTermId(businessTermId);
        setGuid(guid);
        setBusinessTerm(businessTerm);
        setDefinition(definition);
        setCreatedBy(createdBy);
        setLastUpdatedBy(lastUpdatedBy);
        setCreationTimestamp(creationTimestamp);
        setLastUpdateTimestamp(lastUpdateTimestamp);
        setExternalRefUri(externalRefUri);
        setExternalRefId(externalRefId);
        setComment(comment);
    }
}
