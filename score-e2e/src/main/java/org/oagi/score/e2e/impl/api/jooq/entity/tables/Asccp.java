/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables;


import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;
import org.oagi.score.e2e.impl.api.jooq.entity.Indexes;
import org.oagi.score.e2e.impl.api.jooq.entity.Keys;
import org.oagi.score.e2e.impl.api.jooq.entity.Oagi;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.AsccpRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * An ASCCP specifies a role (or property) an ACC may play under another ACC.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Asccp extends TableImpl<AsccpRecord> {

    /**
     * The reference instance of <code>oagi.asccp</code>
     */
    public static final Asccp ASCCP = new Asccp();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>oagi.asccp.asccp_id</code>. An internal, primary
     * database key of an ASCCP.
     */
    public final TableField<AsccpRecord, ULong> ASCCP_ID = createField(DSL.name("asccp_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "An internal, primary database key of an ASCCP.");
    /**
     * The column <code>oagi.asccp.guid</code>. A globally unique identifier
     * (GUID).
     */
    public final TableField<AsccpRecord, String> GUID = createField(DSL.name("guid"), SQLDataType.CHAR(32).nullable(false), this, "A globally unique identifier (GUID).");
    /**
     * The column <code>oagi.asccp.type</code>. The Type of the ASCCP. List:
     * Default, Extension
     */
    public final TableField<AsccpRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(32).defaultValue(DSL.inline("Default", SQLDataType.VARCHAR)), this, "The Type of the ASCCP. List: Default, Extension ");
    /**
     * The column <code>oagi.asccp.property_term</code>. The role (or property)
     * the ACC as referred to by the Role_Of_ACC_ID play when the ASCCP is used
     * by another ACC. There must be only one ASCCP without a Property_Term for
     * a particular ACC.
     */
    public final TableField<AsccpRecord, String> PROPERTY_TERM = createField(DSL.name("property_term"), SQLDataType.VARCHAR(100), this, "The role (or property) the ACC as referred to by the Role_Of_ACC_ID play when the ASCCP is used by another ACC. There must be only one ASCCP without a Property_Term for a particular ACC.");
    /**
     * The column <code>oagi.asccp.definition</code>. Description of the ASCCP.
     */
    public final TableField<AsccpRecord, String> DEFINITION = createField(DSL.name("definition"), SQLDataType.CLOB, this, "Description of the ASCCP.");
    /**
     * The column <code>oagi.asccp.definition_source</code>. This is typically a
     * URL identifying the source of the DEFINITION column.
     */
    public final TableField<AsccpRecord, String> DEFINITION_SOURCE = createField(DSL.name("definition_source"), SQLDataType.VARCHAR(100), this, "This is typically a URL identifying the source of the DEFINITION column.");
    /**
     * The column <code>oagi.asccp.role_of_acc_id</code>. The ACC from which
     * this ASCCP is created (ASCCP applies role to the ACC).
     */
    public final TableField<AsccpRecord, ULong> ROLE_OF_ACC_ID = createField(DSL.name("role_of_acc_id"), SQLDataType.BIGINTUNSIGNED, this, "The ACC from which this ASCCP is created (ASCCP applies role to the ACC).");
    /**
     * The column <code>oagi.asccp.den</code>. The dictionary entry name of the
     * ASCCP.
     */
    public final TableField<AsccpRecord, String> DEN = createField(DSL.name("den"), SQLDataType.VARCHAR(200), this, "The dictionary entry name of the ASCCP.");
    /**
     * The column <code>oagi.asccp.created_by</code>. Foreign key to the
     * APP_USER table referring to the user who creates the entity.
     * <p>
     * This column never change between the history and the current record for a
     * given revision. The history record should have the same value as that of
     * its current record.
     */
    public final TableField<AsccpRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table referring to the user who creates the entity. \n\nThis column never change between the history and the current record for a given revision. The history record should have the same value as that of its current record.");
    /**
     * The column <code>oagi.asccp.owner_user_id</code>. Foreign key to the
     * APP_USER table. This is the user who owns the entity, is allowed to edit
     * the entity, and who can transfer the ownership to another user.
     * <p>
     * The ownership can change throughout the history, but undoing shouldn't
     * rollback the ownership.
     */
    public final TableField<AsccpRecord, ULong> OWNER_USER_ID = createField(DSL.name("owner_user_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table. This is the user who owns the entity, is allowed to edit the entity, and who can transfer the ownership to another user.\n\nThe ownership can change throughout the history, but undoing shouldn't rollback the ownership. ");
    /**
     * The column <code>oagi.asccp.last_updated_by</code>. Foreign key to the
     * APP_USER table referring to the last user who has updated the record.
     * <p>
     * In the history record, this should always be the user who is editing the
     * entity (perhaps except when the ownership has just been changed).
     */
    public final TableField<AsccpRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table referring to the last user who has updated the record. \n\nIn the history record, this should always be the user who is editing the entity (perhaps except when the ownership has just been changed).");
    /**
     * The column <code>oagi.asccp.creation_timestamp</code>. Timestamp when the
     * revision of the ASCCP was created.
     * <p>
     * This never change for a revision.
     */
    public final TableField<AsccpRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "Timestamp when the revision of the ASCCP was created. \n\nThis never change for a revision.");
    /**
     * The column <code>oagi.asccp.last_update_timestamp</code>. The timestamp
     * when the record was last updated.
     * <p>
     * The value of this column in the latest history record should be the same
     * as that of the current record. This column keeps the record of when the
     * revision has occurred.
     */
    public final TableField<AsccpRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record was last updated.\n\nThe value of this column in the latest history record should be the same as that of the current record. This column keeps the record of when the revision has occurred.");
    /**
     * The column <code>oagi.asccp.state</code>. Deleted, WIP, Draft, QA,
     * Candidate, Production, Release Draft, Published. This the revision life
     * cycle state of the ASCCP.
     * <p>
     * State change can't be undone. But the history record can still keep the
     * records of when the state was changed.
     */
    public final TableField<AsccpRecord, String> STATE = createField(DSL.name("state"), SQLDataType.VARCHAR(20), this, "Deleted, WIP, Draft, QA, Candidate, Production, Release Draft, Published. This the revision life cycle state of the ASCCP.\n\nState change can't be undone. But the history record can still keep the records of when the state was changed.");
    /**
     * The column <code>oagi.asccp.namespace_id</code>. Foreign key to the
     * Namespace table. This is the namespace to which the entity belongs. This
     * namespace column is primarily used in the case the component is a user's
     * component because there is also a namespace assigned at the release
     * level.
     */
    public final TableField<AsccpRecord, ULong> NAMESPACE_ID = createField(DSL.name("namespace_id"), SQLDataType.BIGINTUNSIGNED, this, "Foreign key to the Namespace table. This is the namespace to which the entity belongs. This namespace column is primarily used in the case the component is a user's component because there is also a namespace assigned at the release level.");
    /**
     * The column <code>oagi.asccp.reusable_indicator</code>. This indicates
     * whether the ASCCP can be used by more than one ASCC. This maps directly
     * to the XML schema local element declaration.
     */
    public final TableField<AsccpRecord, Byte> REUSABLE_INDICATOR = createField(DSL.name("reusable_indicator"), SQLDataType.TINYINT.defaultValue(DSL.inline("1", SQLDataType.TINYINT)), this, "This indicates whether the ASCCP can be used by more than one ASCC. This maps directly to the XML schema local element declaration.");
    /**
     * The column <code>oagi.asccp.is_deprecated</code>. Indicates whether the
     * CC is deprecated and should not be reused (i.e., no new reference to this
     * record should be created).
     */
    public final TableField<AsccpRecord, Byte> IS_DEPRECATED = createField(DSL.name("is_deprecated"), SQLDataType.TINYINT.nullable(false), this, "Indicates whether the CC is deprecated and should not be reused (i.e., no new reference to this record should be created).");
    /**
     * The column <code>oagi.asccp.replacement_asccp_id</code>. This refers to a
     * replacement if the record is deprecated.
     */
    public final TableField<AsccpRecord, ULong> REPLACEMENT_ASCCP_ID = createField(DSL.name("replacement_asccp_id"), SQLDataType.BIGINTUNSIGNED, this, "This refers to a replacement if the record is deprecated.");
    /**
     * The column <code>oagi.asccp.is_nillable</code>. This is corresponding to
     * the XML schema nillable flag. Although the nillable may not apply in
     * certain cases of the ASCCP (e.g., when it corresponds to an XSD group),
     * the value is default to false for simplification.
     */
    public final TableField<AsccpRecord, Byte> IS_NILLABLE = createField(DSL.name("is_nillable"), SQLDataType.TINYINT, this, "This is corresponding to the XML schema nillable flag. Although the nillable may not apply in certain cases of the ASCCP (e.g., when it corresponds to an XSD group), the value is default to false for simplification.");
    /**
     * The column <code>oagi.asccp.prev_asccp_id</code>. A self-foreign key to
     * indicate the previous history record.
     */
    public final TableField<AsccpRecord, ULong> PREV_ASCCP_ID = createField(DSL.name("prev_asccp_id"), SQLDataType.BIGINTUNSIGNED, this, "A self-foreign key to indicate the previous history record.");
    /**
     * The column <code>oagi.asccp.next_asccp_id</code>. A self-foreign key to
     * indicate the next history record.
     */
    public final TableField<AsccpRecord, ULong> NEXT_ASCCP_ID = createField(DSL.name("next_asccp_id"), SQLDataType.BIGINTUNSIGNED, this, "A self-foreign key to indicate the next history record.");
    private transient Acc _acc;
    private transient AppUser _asccpCreatedByFk;
    private transient AppUser _asccpOwnerUserIdFk;
    private transient AppUser _asccpLastUpdatedByFk;
    private transient Namespace _namespace;
    private transient Asccp _asccpReplacementAsccpIdFk;
    private transient Asccp _asccpPrevAsccpIdFk;
    private transient Asccp _asccpNextAsccpIdFk;

    private Asccp(Name alias, Table<AsccpRecord> aliased) {
        this(alias, aliased, null);
    }

    private Asccp(Name alias, Table<AsccpRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("An ASCCP specifies a role (or property) an ACC may play under another ACC."), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.asccp</code> table reference
     */
    public Asccp(String alias) {
        this(DSL.name(alias), ASCCP);
    }

    /**
     * Create an aliased <code>oagi.asccp</code> table reference
     */
    public Asccp(Name alias) {
        this(alias, ASCCP);
    }

    /**
     * Create a <code>oagi.asccp</code> table reference
     */
    public Asccp() {
        this(DSL.name("asccp"), null);
    }
    public <O extends Record> Asccp(Table<O> child, ForeignKey<O, AsccpRecord> key) {
        super(child, key, ASCCP);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AsccpRecord> getRecordType() {
        return AsccpRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.ASCCP_ASCCP_GUID_IDX, Indexes.ASCCP_ASCCP_LAST_UPDATE_TIMESTAMP_DESC_IDX);
    }

    @Override
    public Identity<AsccpRecord, ULong> getIdentity() {
        return (Identity<AsccpRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<AsccpRecord> getPrimaryKey() {
        return Keys.KEY_ASCCP_PRIMARY;
    }

    @Override
    public List<ForeignKey<AsccpRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ASCCP_ROLE_OF_ACC_ID_FK, Keys.ASCCP_CREATED_BY_FK, Keys.ASCCP_OWNER_USER_ID_FK, Keys.ASCCP_LAST_UPDATED_BY_FK, Keys.ASCCP_NAMESPACE_ID_FK, Keys.ASCCP_REPLACEMENT_ASCCP_ID_FK, Keys.ASCCP_PREV_ASCCP_ID_FK, Keys.ASCCP_NEXT_ASCCP_ID_FK);
    }

    /**
     * Get the implicit join path to the <code>oagi.acc</code> table.
     */
    public Acc acc() {
        if (_acc == null)
            _acc = new Acc(this, Keys.ASCCP_ROLE_OF_ACC_ID_FK);

        return _acc;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>asccp_created_by_fk</code> key.
     */
    public AppUser asccpCreatedByFk() {
        if (_asccpCreatedByFk == null)
            _asccpCreatedByFk = new AppUser(this, Keys.ASCCP_CREATED_BY_FK);

        return _asccpCreatedByFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>asccp_owner_user_id_fk</code> key.
     */
    public AppUser asccpOwnerUserIdFk() {
        if (_asccpOwnerUserIdFk == null)
            _asccpOwnerUserIdFk = new AppUser(this, Keys.ASCCP_OWNER_USER_ID_FK);

        return _asccpOwnerUserIdFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>asccp_last_updated_by_fk</code> key.
     */
    public AppUser asccpLastUpdatedByFk() {
        if (_asccpLastUpdatedByFk == null)
            _asccpLastUpdatedByFk = new AppUser(this, Keys.ASCCP_LAST_UPDATED_BY_FK);

        return _asccpLastUpdatedByFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.namespace</code> table.
     */
    public Namespace namespace() {
        if (_namespace == null)
            _namespace = new Namespace(this, Keys.ASCCP_NAMESPACE_ID_FK);

        return _namespace;
    }

    /**
     * Get the implicit join path to the <code>oagi.asccp</code> table, via the
     * <code>asccp_replacement_asccp_id_fk</code> key.
     */
    public Asccp asccpReplacementAsccpIdFk() {
        if (_asccpReplacementAsccpIdFk == null)
            _asccpReplacementAsccpIdFk = new Asccp(this, Keys.ASCCP_REPLACEMENT_ASCCP_ID_FK);

        return _asccpReplacementAsccpIdFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.asccp</code> table, via the
     * <code>asccp_prev_asccp_id_fk</code> key.
     */
    public Asccp asccpPrevAsccpIdFk() {
        if (_asccpPrevAsccpIdFk == null)
            _asccpPrevAsccpIdFk = new Asccp(this, Keys.ASCCP_PREV_ASCCP_ID_FK);

        return _asccpPrevAsccpIdFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.asccp</code> table, via the
     * <code>asccp_next_asccp_id_fk</code> key.
     */
    public Asccp asccpNextAsccpIdFk() {
        if (_asccpNextAsccpIdFk == null)
            _asccpNextAsccpIdFk = new Asccp(this, Keys.ASCCP_NEXT_ASCCP_ID_FK);

        return _asccpNextAsccpIdFk;
    }

    @Override
    public Asccp as(String alias) {
        return new Asccp(DSL.name(alias), this);
    }

    @Override
    public Asccp as(Name alias) {
        return new Asccp(alias, this);
    }

    @Override
    public Asccp as(Table<?> alias) {
        return new Asccp(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Asccp rename(String name) {
        return new Asccp(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Asccp rename(Name name) {
        return new Asccp(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Asccp rename(Table<?> name) {
        return new Asccp(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row21 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row21<ULong, String, String, String, String, String, ULong, String, ULong, ULong, ULong, LocalDateTime, LocalDateTime, String, ULong, Byte, Byte, ULong, Byte, ULong, ULong> fieldsRow() {
        return (Row21) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function21<? super ULong, ? super String, ? super String, ? super String, ? super String, ? super String, ? super ULong, ? super String, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? super String, ? super ULong, ? super Byte, ? super Byte, ? super ULong, ? super Byte, ? super ULong, ? super ULong, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function21<? super ULong, ? super String, ? super String, ? super String, ? super String, ? super String, ? super ULong, ? super String, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? super String, ? super ULong, ? super Byte, ? super Byte, ? super ULong, ? super Byte, ? super ULong, ? super ULong, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
