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
import org.oagi.score.e2e.impl.api.jooq.entity.tables.OasExternalDoc;


/**
 * Allows referencing an external resource for extended documentation.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OasExternalDocRecord extends UpdatableRecordImpl<OasExternalDocRecord> implements Record7<ULong, String, String, ULong, ULong, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.oas_external_doc.oas_external_doc_id</code>. The
     * primary key of the record.
     */
    public void setOasExternalDocId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.oas_external_doc.oas_external_doc_id</code>. The
     * primary key of the record.
     */
    public ULong getOasExternalDocId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.oas_external_doc.url</code>. REQUIRED. The URL for
     * the target documentation. Value MUST be in the format of a URL.
     */
    public void setUrl(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.oas_external_doc.url</code>. REQUIRED. The URL for
     * the target documentation. Value MUST be in the format of a URL.
     */
    public String getUrl() {
        return (String) get(1);
    }

    /**
     * Setter for <code>oagi.oas_external_doc.description</code>. A short
     * description of the target documentation. CommonMark syntax MAY be used
     * for rich text representation.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.oas_external_doc.description</code>. A short
     * description of the target documentation. CommonMark syntax MAY be used
     * for rich text representation.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>oagi.oas_external_doc.created_by</code>. The user who
     * creates the record.
     */
    public void setCreatedBy(ULong value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.oas_external_doc.created_by</code>. The user who
     * creates the record.
     */
    public ULong getCreatedBy() {
        return (ULong) get(3);
    }

    /**
     * Setter for <code>oagi.oas_external_doc.last_updated_by</code>. The user
     * who last updates the record.
     */
    public void setLastUpdatedBy(ULong value) {
        set(4, value);
    }

    /**
     * Getter for <code>oagi.oas_external_doc.last_updated_by</code>. The user
     * who last updates the record.
     */
    public ULong getLastUpdatedBy() {
        return (ULong) get(4);
    }

    /**
     * Setter for <code>oagi.oas_external_doc.creation_timestamp</code>. The
     * timestamp when the record is created.
     */
    public void setCreationTimestamp(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.oas_external_doc.creation_timestamp</code>. The
     * timestamp when the record is created.
     */
    public LocalDateTime getCreationTimestamp() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>oagi.oas_external_doc.last_update_timestamp</code>. The
     * timestamp when the record is last updated.
     */
    public void setLastUpdateTimestamp(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>oagi.oas_external_doc.last_update_timestamp</code>. The
     * timestamp when the record is last updated.
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
    public Row7<ULong, String, String, ULong, ULong, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<ULong, String, String, ULong, ULong, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return OasExternalDoc.OAS_EXTERNAL_DOC.OAS_EXTERNAL_DOC_ID;
    }

    @Override
    public Field<String> field2() {
        return OasExternalDoc.OAS_EXTERNAL_DOC.URL;
    }

    @Override
    public Field<String> field3() {
        return OasExternalDoc.OAS_EXTERNAL_DOC.DESCRIPTION;
    }

    @Override
    public Field<ULong> field4() {
        return OasExternalDoc.OAS_EXTERNAL_DOC.CREATED_BY;
    }

    @Override
    public Field<ULong> field5() {
        return OasExternalDoc.OAS_EXTERNAL_DOC.LAST_UPDATED_BY;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return OasExternalDoc.OAS_EXTERNAL_DOC.CREATION_TIMESTAMP;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return OasExternalDoc.OAS_EXTERNAL_DOC.LAST_UPDATE_TIMESTAMP;
    }

    @Override
    public ULong component1() {
        return getOasExternalDocId();
    }

    @Override
    public String component2() {
        return getUrl();
    }

    @Override
    public String component3() {
        return getDescription();
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
        return getOasExternalDocId();
    }

    @Override
    public String value2() {
        return getUrl();
    }

    @Override
    public String value3() {
        return getDescription();
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
    public OasExternalDocRecord value1(ULong value) {
        setOasExternalDocId(value);
        return this;
    }

    @Override
    public OasExternalDocRecord value2(String value) {
        setUrl(value);
        return this;
    }

    @Override
    public OasExternalDocRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public OasExternalDocRecord value4(ULong value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public OasExternalDocRecord value5(ULong value) {
        setLastUpdatedBy(value);
        return this;
    }

    @Override
    public OasExternalDocRecord value6(LocalDateTime value) {
        setCreationTimestamp(value);
        return this;
    }

    @Override
    public OasExternalDocRecord value7(LocalDateTime value) {
        setLastUpdateTimestamp(value);
        return this;
    }

    @Override
    public OasExternalDocRecord values(ULong value1, String value2, String value3, ULong value4, ULong value5, LocalDateTime value6, LocalDateTime value7) {
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
     * Create a detached OasExternalDocRecord
     */
    public OasExternalDocRecord() {
        super(OasExternalDoc.OAS_EXTERNAL_DOC);
    }

    /**
     * Create a detached, initialised OasExternalDocRecord
     */
    public OasExternalDocRecord(ULong oasExternalDocId, String url, String description, ULong createdBy, ULong lastUpdatedBy, LocalDateTime creationTimestamp, LocalDateTime lastUpdateTimestamp) {
        super(OasExternalDoc.OAS_EXTERNAL_DOC);

        setOasExternalDocId(oasExternalDocId);
        setUrl(url);
        setDescription(description);
        setCreatedBy(createdBy);
        setLastUpdatedBy(lastUpdatedBy);
        setCreationTimestamp(creationTimestamp);
        setLastUpdateTimestamp(lastUpdateTimestamp);
        resetChangedOnNotNull();
    }
}
