/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.Tag;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TagRecord extends UpdatableRecordImpl<TagRecord> implements Record9<ULong, String, String, String, String, ULong, ULong, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.tag.tag_id</code>. An internal, primary database
     * key of a tag record.
     */
    public void setTagId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.tag.tag_id</code>. An internal, primary database
     * key of a tag record.
     */
    public ULong getTagId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.tag.name</code>. The name of the tag.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.tag.name</code>. The name of the tag.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>oagi.tag.description</code>. The description of the tag.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.tag.description</code>. The description of the tag.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>oagi.tag.text_color</code>. The text color of the tag.
     */
    public void setTextColor(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.tag.text_color</code>. The text color of the tag.
     */
    public String getTextColor() {
        return (String) get(3);
    }

    /**
     * Setter for <code>oagi.tag.background_color</code>. The background color
     * of the tag.
     */
    public void setBackgroundColor(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>oagi.tag.background_color</code>. The background color
     * of the tag.
     */
    public String getBackgroundColor() {
        return (String) get(4);
    }

    /**
     * Setter for <code>oagi.tag.created_by</code>. A foreign key referring to
     * the user who creates the tag record.
     */
    public void setCreatedBy(ULong value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.tag.created_by</code>. A foreign key referring to
     * the user who creates the tag record.
     */
    public ULong getCreatedBy() {
        return (ULong) get(5);
    }

    /**
     * Setter for <code>oagi.tag.last_updated_by</code>. A foreign key referring
     * to the last user who has updated the tag record.
     */
    public void setLastUpdatedBy(ULong value) {
        set(6, value);
    }

    /**
     * Getter for <code>oagi.tag.last_updated_by</code>. A foreign key referring
     * to the last user who has updated the tag record.
     */
    public ULong getLastUpdatedBy() {
        return (ULong) get(6);
    }

    /**
     * Setter for <code>oagi.tag.creation_timestamp</code>. Timestamp when the
     * tag record was first created.
     */
    public void setCreationTimestamp(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>oagi.tag.creation_timestamp</code>. Timestamp when the
     * tag record was first created.
     */
    public LocalDateTime getCreationTimestamp() {
        return (LocalDateTime) get(7);
    }

    /**
     * Setter for <code>oagi.tag.last_update_timestamp</code>. The timestamp
     * when the tag was last updated.
     */
    public void setLastUpdateTimestamp(LocalDateTime value) {
        set(8, value);
    }

    /**
     * Getter for <code>oagi.tag.last_update_timestamp</code>. The timestamp
     * when the tag was last updated.
     */
    public LocalDateTime getLastUpdateTimestamp() {
        return (LocalDateTime) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<ULong, String, String, String, String, ULong, ULong, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<ULong, String, String, String, String, ULong, ULong, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return Tag.TAG.TAG_ID;
    }

    @Override
    public Field<String> field2() {
        return Tag.TAG.NAME;
    }

    @Override
    public Field<String> field3() {
        return Tag.TAG.DESCRIPTION;
    }

    @Override
    public Field<String> field4() {
        return Tag.TAG.TEXT_COLOR;
    }

    @Override
    public Field<String> field5() {
        return Tag.TAG.BACKGROUND_COLOR;
    }

    @Override
    public Field<ULong> field6() {
        return Tag.TAG.CREATED_BY;
    }

    @Override
    public Field<ULong> field7() {
        return Tag.TAG.LAST_UPDATED_BY;
    }

    @Override
    public Field<LocalDateTime> field8() {
        return Tag.TAG.CREATION_TIMESTAMP;
    }

    @Override
    public Field<LocalDateTime> field9() {
        return Tag.TAG.LAST_UPDATE_TIMESTAMP;
    }

    @Override
    public ULong component1() {
        return getTagId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public String component4() {
        return getTextColor();
    }

    @Override
    public String component5() {
        return getBackgroundColor();
    }

    @Override
    public ULong component6() {
        return getCreatedBy();
    }

    @Override
    public ULong component7() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime component8() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime component9() {
        return getLastUpdateTimestamp();
    }

    @Override
    public ULong value1() {
        return getTagId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public String value4() {
        return getTextColor();
    }

    @Override
    public String value5() {
        return getBackgroundColor();
    }

    @Override
    public ULong value6() {
        return getCreatedBy();
    }

    @Override
    public ULong value7() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime value8() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime value9() {
        return getLastUpdateTimestamp();
    }

    @Override
    public TagRecord value1(ULong value) {
        setTagId(value);
        return this;
    }

    @Override
    public TagRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public TagRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public TagRecord value4(String value) {
        setTextColor(value);
        return this;
    }

    @Override
    public TagRecord value5(String value) {
        setBackgroundColor(value);
        return this;
    }

    @Override
    public TagRecord value6(ULong value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public TagRecord value7(ULong value) {
        setLastUpdatedBy(value);
        return this;
    }

    @Override
    public TagRecord value8(LocalDateTime value) {
        setCreationTimestamp(value);
        return this;
    }

    @Override
    public TagRecord value9(LocalDateTime value) {
        setLastUpdateTimestamp(value);
        return this;
    }

    @Override
    public TagRecord values(ULong value1, String value2, String value3, String value4, String value5, ULong value6, ULong value7, LocalDateTime value8, LocalDateTime value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TagRecord
     */
    public TagRecord() {
        super(Tag.TAG);
    }

    /**
     * Create a detached, initialised TagRecord
     */
    public TagRecord(ULong tagId, String name, String description, String textColor, String backgroundColor, ULong createdBy, ULong lastUpdatedBy, LocalDateTime creationTimestamp, LocalDateTime lastUpdateTimestamp) {
        super(Tag.TAG);

        setTagId(tagId);
        setName(name);
        setDescription(description);
        setTextColor(textColor);
        setBackgroundColor(backgroundColor);
        setCreatedBy(createdBy);
        setLastUpdatedBy(lastUpdatedBy);
        setCreationTimestamp(creationTimestamp);
        setLastUpdateTimestamp(lastUpdateTimestamp);
        resetChangedOnNotNull();
    }
}