/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record14;
import org.jooq.Row14;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasRequest;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OasRequestRecord extends UpdatableRecordImpl<OasRequestRecord> implements Record14<ULong, ULong, String, Byte, ULong, Byte, Byte, Byte, Byte, Byte, ULong, ULong, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.oas_request.oas_request_id</code>. The primary key
     * of the record.
     */
    public void setOasRequestId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.oas_request.oas_request_id</code>. The primary key
     * of the record.
     */
    public ULong getOasRequestId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.oas_request.oas_operation_id</code>.
     */
    public void setOasOperationId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.oas_request.oas_operation_id</code>.
     */
    public ULong getOasOperationId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>oagi.oas_request.description</code>. A brief description
     * of the request body. This could contain examples of use. CommonMark
     * syntax MAY be used for rich text representation.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.oas_request.description</code>. A brief description
     * of the request body. This could contain examples of use. CommonMark
     * syntax MAY be used for rich text representation.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>oagi.oas_request.required</code>. Determines if the
     * request body is required in the request. Defaults to false.
     */
    public void setRequired(Byte value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.oas_request.required</code>. Determines if the
     * request body is required in the request. Defaults to false.
     */
    public Byte getRequired() {
        return (Byte) get(3);
    }

    /**
     * Setter for <code>oagi.oas_request.oas_message_body_id</code>.
     */
    public void setOasMessageBodyId(ULong value) {
        set(4, value);
    }

    /**
     * Getter for <code>oagi.oas_request.oas_message_body_id</code>.
     */
    public ULong getOasMessageBodyId() {
        return (ULong) get(4);
    }

    /**
     * Setter for <code>oagi.oas_request.make_array_indicator</code>.
     */
    public void setMakeArrayIndicator(Byte value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.oas_request.make_array_indicator</code>.
     */
    public Byte getMakeArrayIndicator() {
        return (Byte) get(5);
    }

    /**
     * Setter for <code>oagi.oas_request.suppress_root_indicator</code>.
     */
    public void setSuppressRootIndicator(Byte value) {
        set(6, value);
    }

    /**
     * Getter for <code>oagi.oas_request.suppress_root_indicator</code>.
     */
    public Byte getSuppressRootIndicator() {
        return (Byte) get(6);
    }

    /**
     * Setter for <code>oagi.oas_request.include_meta_header_indicator</code>.
     */
    public void setIncludeMetaHeaderIndicator(Byte value) {
        set(7, value);
    }

    /**
     * Getter for <code>oagi.oas_request.include_meta_header_indicator</code>.
     */
    public Byte getIncludeMetaHeaderIndicator() {
        return (Byte) get(7);
    }

    /**
     * Setter for <code>oagi.oas_request.include_pagination_indicator</code>.
     */
    public void setIncludePaginationIndicator(Byte value) {
        set(8, value);
    }

    /**
     * Getter for <code>oagi.oas_request.include_pagination_indicator</code>.
     */
    public Byte getIncludePaginationIndicator() {
        return (Byte) get(8);
    }

    /**
     * Setter for <code>oagi.oas_request.is_callback</code>. If is_callback ==
     * true, oas_callback table has URL rows in it, with eventType=Success or
     * Failed values to allow different endpoints to be called when a successful
     * request is processed, or failure endpoint when an exception occurs.
     */
    public void setIsCallback(Byte value) {
        set(9, value);
    }

    /**
     * Getter for <code>oagi.oas_request.is_callback</code>. If is_callback ==
     * true, oas_callback table has URL rows in it, with eventType=Success or
     * Failed values to allow different endpoints to be called when a successful
     * request is processed, or failure endpoint when an exception occurs.
     */
    public Byte getIsCallback() {
        return (Byte) get(9);
    }

    /**
     * Setter for <code>oagi.oas_request.created_by</code>. The user who creates
     * the record.
     */
    public void setCreatedBy(ULong value) {
        set(10, value);
    }

    /**
     * Getter for <code>oagi.oas_request.created_by</code>. The user who creates
     * the record.
     */
    public ULong getCreatedBy() {
        return (ULong) get(10);
    }

    /**
     * Setter for <code>oagi.oas_request.last_updated_by</code>. The user who
     * last updates the record.
     */
    public void setLastUpdatedBy(ULong value) {
        set(11, value);
    }

    /**
     * Getter for <code>oagi.oas_request.last_updated_by</code>. The user who
     * last updates the record.
     */
    public ULong getLastUpdatedBy() {
        return (ULong) get(11);
    }

    /**
     * Setter for <code>oagi.oas_request.creation_timestamp</code>. The
     * timestamp when the record is created.
     */
    public void setCreationTimestamp(LocalDateTime value) {
        set(12, value);
    }

    /**
     * Getter for <code>oagi.oas_request.creation_timestamp</code>. The
     * timestamp when the record is created.
     */
    public LocalDateTime getCreationTimestamp() {
        return (LocalDateTime) get(12);
    }

    /**
     * Setter for <code>oagi.oas_request.last_update_timestamp</code>. The
     * timestamp when the record is last updated.
     */
    public void setLastUpdateTimestamp(LocalDateTime value) {
        set(13, value);
    }

    /**
     * Getter for <code>oagi.oas_request.last_update_timestamp</code>. The
     * timestamp when the record is last updated.
     */
    public LocalDateTime getLastUpdateTimestamp() {
        return (LocalDateTime) get(13);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record14 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row14<ULong, ULong, String, Byte, ULong, Byte, Byte, Byte, Byte, Byte, ULong, ULong, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    @Override
    public Row14<ULong, ULong, String, Byte, ULong, Byte, Byte, Byte, Byte, Byte, ULong, ULong, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row14) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return OasRequest.OAS_REQUEST.OAS_REQUEST_ID;
    }

    @Override
    public Field<ULong> field2() {
        return OasRequest.OAS_REQUEST.OAS_OPERATION_ID;
    }

    @Override
    public Field<String> field3() {
        return OasRequest.OAS_REQUEST.DESCRIPTION;
    }

    @Override
    public Field<Byte> field4() {
        return OasRequest.OAS_REQUEST.REQUIRED;
    }

    @Override
    public Field<ULong> field5() {
        return OasRequest.OAS_REQUEST.OAS_MESSAGE_BODY_ID;
    }

    @Override
    public Field<Byte> field6() {
        return OasRequest.OAS_REQUEST.MAKE_ARRAY_INDICATOR;
    }

    @Override
    public Field<Byte> field7() {
        return OasRequest.OAS_REQUEST.SUPPRESS_ROOT_INDICATOR;
    }

    @Override
    public Field<Byte> field8() {
        return OasRequest.OAS_REQUEST.INCLUDE_META_HEADER_INDICATOR;
    }

    @Override
    public Field<Byte> field9() {
        return OasRequest.OAS_REQUEST.INCLUDE_PAGINATION_INDICATOR;
    }

    @Override
    public Field<Byte> field10() {
        return OasRequest.OAS_REQUEST.IS_CALLBACK;
    }

    @Override
    public Field<ULong> field11() {
        return OasRequest.OAS_REQUEST.CREATED_BY;
    }

    @Override
    public Field<ULong> field12() {
        return OasRequest.OAS_REQUEST.LAST_UPDATED_BY;
    }

    @Override
    public Field<LocalDateTime> field13() {
        return OasRequest.OAS_REQUEST.CREATION_TIMESTAMP;
    }

    @Override
    public Field<LocalDateTime> field14() {
        return OasRequest.OAS_REQUEST.LAST_UPDATE_TIMESTAMP;
    }

    @Override
    public ULong component1() {
        return getOasRequestId();
    }

    @Override
    public ULong component2() {
        return getOasOperationId();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public Byte component4() {
        return getRequired();
    }

    @Override
    public ULong component5() {
        return getOasMessageBodyId();
    }

    @Override
    public Byte component6() {
        return getMakeArrayIndicator();
    }

    @Override
    public Byte component7() {
        return getSuppressRootIndicator();
    }

    @Override
    public Byte component8() {
        return getIncludeMetaHeaderIndicator();
    }

    @Override
    public Byte component9() {
        return getIncludePaginationIndicator();
    }

    @Override
    public Byte component10() {
        return getIsCallback();
    }

    @Override
    public ULong component11() {
        return getCreatedBy();
    }

    @Override
    public ULong component12() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime component13() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime component14() {
        return getLastUpdateTimestamp();
    }

    @Override
    public ULong value1() {
        return getOasRequestId();
    }

    @Override
    public ULong value2() {
        return getOasOperationId();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public Byte value4() {
        return getRequired();
    }

    @Override
    public ULong value5() {
        return getOasMessageBodyId();
    }

    @Override
    public Byte value6() {
        return getMakeArrayIndicator();
    }

    @Override
    public Byte value7() {
        return getSuppressRootIndicator();
    }

    @Override
    public Byte value8() {
        return getIncludeMetaHeaderIndicator();
    }

    @Override
    public Byte value9() {
        return getIncludePaginationIndicator();
    }

    @Override
    public Byte value10() {
        return getIsCallback();
    }

    @Override
    public ULong value11() {
        return getCreatedBy();
    }

    @Override
    public ULong value12() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime value13() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime value14() {
        return getLastUpdateTimestamp();
    }

    @Override
    public OasRequestRecord value1(ULong value) {
        setOasRequestId(value);
        return this;
    }

    @Override
    public OasRequestRecord value2(ULong value) {
        setOasOperationId(value);
        return this;
    }

    @Override
    public OasRequestRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public OasRequestRecord value4(Byte value) {
        setRequired(value);
        return this;
    }

    @Override
    public OasRequestRecord value5(ULong value) {
        setOasMessageBodyId(value);
        return this;
    }

    @Override
    public OasRequestRecord value6(Byte value) {
        setMakeArrayIndicator(value);
        return this;
    }

    @Override
    public OasRequestRecord value7(Byte value) {
        setSuppressRootIndicator(value);
        return this;
    }

    @Override
    public OasRequestRecord value8(Byte value) {
        setIncludeMetaHeaderIndicator(value);
        return this;
    }

    @Override
    public OasRequestRecord value9(Byte value) {
        setIncludePaginationIndicator(value);
        return this;
    }

    @Override
    public OasRequestRecord value10(Byte value) {
        setIsCallback(value);
        return this;
    }

    @Override
    public OasRequestRecord value11(ULong value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public OasRequestRecord value12(ULong value) {
        setLastUpdatedBy(value);
        return this;
    }

    @Override
    public OasRequestRecord value13(LocalDateTime value) {
        setCreationTimestamp(value);
        return this;
    }

    @Override
    public OasRequestRecord value14(LocalDateTime value) {
        setLastUpdateTimestamp(value);
        return this;
    }

    @Override
    public OasRequestRecord values(ULong value1, ULong value2, String value3, Byte value4, ULong value5, Byte value6, Byte value7, Byte value8, Byte value9, Byte value10, ULong value11, ULong value12, LocalDateTime value13, LocalDateTime value14) {
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
        value12(value12);
        value13(value13);
        value14(value14);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OasRequestRecord
     */
    public OasRequestRecord() {
        super(OasRequest.OAS_REQUEST);
    }

    /**
     * Create a detached, initialised OasRequestRecord
     */
    public OasRequestRecord(ULong oasRequestId, ULong oasOperationId, String description, Byte required, ULong oasMessageBodyId, Byte makeArrayIndicator, Byte suppressRootIndicator, Byte includeMetaHeaderIndicator, Byte includePaginationIndicator, Byte isCallback, ULong createdBy, ULong lastUpdatedBy, LocalDateTime creationTimestamp, LocalDateTime lastUpdateTimestamp) {
        super(OasRequest.OAS_REQUEST);

        setOasRequestId(oasRequestId);
        setOasOperationId(oasOperationId);
        setDescription(description);
        setRequired(required);
        setOasMessageBodyId(oasMessageBodyId);
        setMakeArrayIndicator(makeArrayIndicator);
        setSuppressRootIndicator(suppressRootIndicator);
        setIncludeMetaHeaderIndicator(includeMetaHeaderIndicator);
        setIncludePaginationIndicator(includePaginationIndicator);
        setIsCallback(isCallback);
        setCreatedBy(createdBy);
        setLastUpdatedBy(lastUpdatedBy);
        setCreationTimestamp(creationTimestamp);
        setLastUpdateTimestamp(lastUpdateTimestamp);
        resetChangedOnNotNull();
    }
}
