/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function22;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row22;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;
import org.oagi.score.e2e.impl.api.jooq.entity.Indexes;
import org.oagi.score.e2e.impl.api.jooq.entity.Keys;
import org.oagi.score.e2e.impl.api.jooq.entity.Oagi;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.DtScRecord;


/**
 * This table represents the supplementary component (SC) of a DT. Revision is
 * not tracked at the supplementary component. It is considered intrinsic part
 * of the DT. In other words, when a new revision of a DT is created a new set
 * of supplementary components is created along with it. 
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DtSc extends TableImpl<DtScRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.dt_sc</code>
     */
    public static final DtSc DT_SC = new DtSc();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DtScRecord> getRecordType() {
        return DtScRecord.class;
    }

    /**
     * The column <code>oagi.dt_sc.dt_sc_id</code>. Internal, primary database
     * key.
     */
    public final TableField<DtScRecord, ULong> DT_SC_ID = createField(DSL.name("dt_sc_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Internal, primary database key.");

    /**
     * The column <code>oagi.dt_sc.guid</code>. A globally unique identifier
     * (GUID).
     */
    public final TableField<DtScRecord, String> GUID = createField(DSL.name("guid"), SQLDataType.CHAR(32).nullable(false), this, "A globally unique identifier (GUID).");

    /**
     * The column <code>oagi.dt_sc.object_class_term</code>. Object class term
     * of the SC.
     */
    public final TableField<DtScRecord, String> OBJECT_CLASS_TERM = createField(DSL.name("object_class_term"), SQLDataType.VARCHAR(60), this, "Object class term of the SC.");

    /**
     * The column <code>oagi.dt_sc.property_term</code>. Property term of the
     * SC.
     */
    public final TableField<DtScRecord, String> PROPERTY_TERM = createField(DSL.name("property_term"), SQLDataType.VARCHAR(60), this, "Property term of the SC.");

    /**
     * The column <code>oagi.dt_sc.representation_term</code>. Representation of
     * the supplementary component.
     */
    public final TableField<DtScRecord, String> REPRESENTATION_TERM = createField(DSL.name("representation_term"), SQLDataType.VARCHAR(20), this, "Representation of the supplementary component.");

    /**
     * The column <code>oagi.dt_sc.definition</code>. Description of the
     * supplementary component.
     */
    public final TableField<DtScRecord, String> DEFINITION = createField(DSL.name("definition"), SQLDataType.CLOB, this, "Description of the supplementary component.");

    /**
     * The column <code>oagi.dt_sc.definition_source</code>. This is typically a
     * URL identifying the source of the DEFINITION column.
     */
    public final TableField<DtScRecord, String> DEFINITION_SOURCE = createField(DSL.name("definition_source"), SQLDataType.VARCHAR(200), this, "This is typically a URL identifying the source of the DEFINITION column.");

    /**
     * The column <code>oagi.dt_sc.owner_dt_id</code>. Foreigned key to the DT
     * table indicating the data type, to which this supplementary component
     * belongs.
     */
    public final TableField<DtScRecord, ULong> OWNER_DT_ID = createField(DSL.name("owner_dt_id"), SQLDataType.BIGINTUNSIGNED, this, "Foreigned key to the DT table indicating the data type, to which this supplementary component belongs.");

    /**
     * The column <code>oagi.dt_sc.cardinality_min</code>. The minimum
     * occurrence constraint associated with the supplementary component. The
     * valid values zero or one.
     */
    public final TableField<DtScRecord, Integer> CARDINALITY_MIN = createField(DSL.name("cardinality_min"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.inline("0", SQLDataType.INTEGER)), this, "The minimum occurrence constraint associated with the supplementary component. The valid values zero or one.");

    /**
     * The column <code>oagi.dt_sc.cardinality_max</code>. The maximum
     * occurrence constraint associated with the supplementary component. The
     * valid values are zero or one. Zero is used when the SC is restricted from
     * an instantiation in the data type.
     */
    public final TableField<DtScRecord, Integer> CARDINALITY_MAX = createField(DSL.name("cardinality_max"), SQLDataType.INTEGER, this, "The maximum occurrence constraint associated with the supplementary component. The valid values are zero or one. Zero is used when the SC is restricted from an instantiation in the data type.");

    /**
     * The column <code>oagi.dt_sc.based_dt_sc_id</code>. Foreign key to the
     * DT_SC table itself. This column is used when the SC is derived from the
     * based DT.
     */
    public final TableField<DtScRecord, ULong> BASED_DT_SC_ID = createField(DSL.name("based_dt_sc_id"), SQLDataType.BIGINTUNSIGNED, this, "Foreign key to the DT_SC table itself. This column is used when the SC is derived from the based DT.");

    /**
     * The column <code>oagi.dt_sc.default_value</code>. This column specifies
     * the default value constraint. Default and fixed value constraints cannot
     * be used at the same time.
     */
    public final TableField<DtScRecord, String> DEFAULT_VALUE = createField(DSL.name("default_value"), SQLDataType.CLOB, this, "This column specifies the default value constraint. Default and fixed value constraints cannot be used at the same time.");

    /**
     * The column <code>oagi.dt_sc.fixed_value</code>. This column captures the
     * fixed value constraint. Default and fixed value constraints cannot be
     * used at the same time.
     */
    public final TableField<DtScRecord, String> FIXED_VALUE = createField(DSL.name("fixed_value"), SQLDataType.CLOB, this, "This column captures the fixed value constraint. Default and fixed value constraints cannot be used at the same time.");

    /**
     * The column <code>oagi.dt_sc.is_deprecated</code>. Indicates whether this
     * is deprecated and should not be reused (i.e., no new reference to this
     * record should be created).
     */
    public final TableField<DtScRecord, Byte> IS_DEPRECATED = createField(DSL.name("is_deprecated"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.TINYINT)), this, "Indicates whether this is deprecated and should not be reused (i.e., no new reference to this record should be created).");

    /**
     * The column <code>oagi.dt_sc.replacement_dt_sc_id</code>. This refers to a
     * replacement if the record is deprecated.
     */
    public final TableField<DtScRecord, ULong> REPLACEMENT_DT_SC_ID = createField(DSL.name("replacement_dt_sc_id"), SQLDataType.BIGINTUNSIGNED, this, "This refers to a replacement if the record is deprecated.");

    /**
     * The column <code>oagi.dt_sc.created_by</code>. Foreign key to the
     * APP_USER table. It indicates the user who created the code list.
     */
    public final TableField<DtScRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table. It indicates the user who created the code list.");

    /**
     * The column <code>oagi.dt_sc.owner_user_id</code>. Foreign key to the
     * APP_USER table. This is the user who owns the entity, is allowed to edit
     * the entity, and who can transfer the ownership to another user.
     * 
     * The ownership can change throughout the history, but undoing shouldn't
     * rollback the ownership.
     */
    public final TableField<DtScRecord, ULong> OWNER_USER_ID = createField(DSL.name("owner_user_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table. This is the user who owns the entity, is allowed to edit the entity, and who can transfer the ownership to another user.\n\nThe ownership can change throughout the history, but undoing shouldn't rollback the ownership.");

    /**
     * The column <code>oagi.dt_sc.last_updated_by</code>. Foreign key to the
     * APP_USER table. It identifies the user who last updated the code list.
     */
    public final TableField<DtScRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the APP_USER table. It identifies the user who last updated the code list.");

    /**
     * The column <code>oagi.dt_sc.creation_timestamp</code>. Timestamp when the
     * code list was created.
     */
    public final TableField<DtScRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP(6)", SQLDataType.LOCALDATETIME)), this, "Timestamp when the code list was created.");

    /**
     * The column <code>oagi.dt_sc.last_update_timestamp</code>. Timestamp when
     * the code list was last updated.
     */
    public final TableField<DtScRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP(6)", SQLDataType.LOCALDATETIME)), this, "Timestamp when the code list was last updated.");

    /**
     * The column <code>oagi.dt_sc.prev_dt_sc_id</code>. A self-foreign key to
     * indicate the previous history record.
     */
    public final TableField<DtScRecord, ULong> PREV_DT_SC_ID = createField(DSL.name("prev_dt_sc_id"), SQLDataType.BIGINTUNSIGNED, this, "A self-foreign key to indicate the previous history record.");

    /**
     * The column <code>oagi.dt_sc.next_dt_sc_id</code>. A self-foreign key to
     * indicate the next history record.
     */
    public final TableField<DtScRecord, ULong> NEXT_DT_SC_ID = createField(DSL.name("next_dt_sc_id"), SQLDataType.BIGINTUNSIGNED, this, "A self-foreign key to indicate the next history record.");

    private DtSc(Name alias, Table<DtScRecord> aliased) {
        this(alias, aliased, null);
    }

    private DtSc(Name alias, Table<DtScRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("This table represents the supplementary component (SC) of a DT. Revision is not tracked at the supplementary component. It is considered intrinsic part of the DT. In other words, when a new revision of a DT is created a new set of supplementary components is created along with it. "), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.dt_sc</code> table reference
     */
    public DtSc(String alias) {
        this(DSL.name(alias), DT_SC);
    }

    /**
     * Create an aliased <code>oagi.dt_sc</code> table reference
     */
    public DtSc(Name alias) {
        this(alias, DT_SC);
    }

    /**
     * Create a <code>oagi.dt_sc</code> table reference
     */
    public DtSc() {
        this(DSL.name("dt_sc"), null);
    }

    public <O extends Record> DtSc(Table<O> child, ForeignKey<O, DtScRecord> key) {
        super(child, key, DT_SC);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.DT_SC_DT_SC_GUID_IDX);
    }

    @Override
    public Identity<DtScRecord, ULong> getIdentity() {
        return (Identity<DtScRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<DtScRecord> getPrimaryKey() {
        return Keys.KEY_DT_SC_PRIMARY;
    }

    @Override
    public List<ForeignKey<DtScRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DT_SC_OWNER_DT_ID_FK, Keys.DT_SC_BASED_DT_SC_ID_FK, Keys.DT_SC_REPLACEMENT_DT_SC_ID_FK, Keys.DT_SC_CREATED_BY_FK, Keys.DT_SC_OWNER_USER_ID_FK, Keys.DT_SC_LAST_UPDATED_BY_FK, Keys.DT_SC_PREV_DT_SC_ID_FK, Keys.DT_SC_NEXT_DT_SC_ID_FK);
    }

    private transient Dt _dt;
    private transient DtSc _dtScBasedDtScIdFk;
    private transient DtSc _dtScReplacementDtScIdFk;
    private transient AppUser _dtScCreatedByFk;
    private transient AppUser _dtScOwnerUserIdFk;
    private transient AppUser _dtScLastUpdatedByFk;
    private transient DtSc _dtScPrevDtScIdFk;
    private transient DtSc _dtScNextDtScIdFk;

    /**
     * Get the implicit join path to the <code>oagi.dt</code> table.
     */
    public Dt dt() {
        if (_dt == null)
            _dt = new Dt(this, Keys.DT_SC_OWNER_DT_ID_FK);

        return _dt;
    }

    /**
     * Get the implicit join path to the <code>oagi.dt_sc</code> table, via the
     * <code>dt_sc_based_dt_sc_id_fk</code> key.
     */
    public DtSc dtScBasedDtScIdFk() {
        if (_dtScBasedDtScIdFk == null)
            _dtScBasedDtScIdFk = new DtSc(this, Keys.DT_SC_BASED_DT_SC_ID_FK);

        return _dtScBasedDtScIdFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.dt_sc</code> table, via the
     * <code>dt_sc_replacement_dt_sc_id_fk</code> key.
     */
    public DtSc dtScReplacementDtScIdFk() {
        if (_dtScReplacementDtScIdFk == null)
            _dtScReplacementDtScIdFk = new DtSc(this, Keys.DT_SC_REPLACEMENT_DT_SC_ID_FK);

        return _dtScReplacementDtScIdFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>dt_sc_created_by_fk</code> key.
     */
    public AppUser dtScCreatedByFk() {
        if (_dtScCreatedByFk == null)
            _dtScCreatedByFk = new AppUser(this, Keys.DT_SC_CREATED_BY_FK);

        return _dtScCreatedByFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>dt_sc_owner_user_id_fk</code> key.
     */
    public AppUser dtScOwnerUserIdFk() {
        if (_dtScOwnerUserIdFk == null)
            _dtScOwnerUserIdFk = new AppUser(this, Keys.DT_SC_OWNER_USER_ID_FK);

        return _dtScOwnerUserIdFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>dt_sc_last_updated_by_fk</code> key.
     */
    public AppUser dtScLastUpdatedByFk() {
        if (_dtScLastUpdatedByFk == null)
            _dtScLastUpdatedByFk = new AppUser(this, Keys.DT_SC_LAST_UPDATED_BY_FK);

        return _dtScLastUpdatedByFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.dt_sc</code> table, via the
     * <code>dt_sc_prev_dt_sc_id_fk</code> key.
     */
    public DtSc dtScPrevDtScIdFk() {
        if (_dtScPrevDtScIdFk == null)
            _dtScPrevDtScIdFk = new DtSc(this, Keys.DT_SC_PREV_DT_SC_ID_FK);

        return _dtScPrevDtScIdFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.dt_sc</code> table, via the
     * <code>dt_sc_next_dt_sc_id_fk</code> key.
     */
    public DtSc dtScNextDtScIdFk() {
        if (_dtScNextDtScIdFk == null)
            _dtScNextDtScIdFk = new DtSc(this, Keys.DT_SC_NEXT_DT_SC_ID_FK);

        return _dtScNextDtScIdFk;
    }

    @Override
    public DtSc as(String alias) {
        return new DtSc(DSL.name(alias), this);
    }

    @Override
    public DtSc as(Name alias) {
        return new DtSc(alias, this);
    }

    @Override
    public DtSc as(Table<?> alias) {
        return new DtSc(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public DtSc rename(String name) {
        return new DtSc(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DtSc rename(Name name) {
        return new DtSc(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public DtSc rename(Table<?> name) {
        return new DtSc(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row22 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row22<ULong, String, String, String, String, String, String, ULong, Integer, Integer, ULong, String, String, Byte, ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime, ULong, ULong> fieldsRow() {
        return (Row22) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function22<? super ULong, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super ULong, ? super Integer, ? super Integer, ? super ULong, ? super String, ? super String, ? super Byte, ? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? super ULong, ? super ULong, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function22<? super ULong, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super ULong, ? super Integer, ? super Integer, ? super ULong, ? super String, ? super String, ? super Byte, ? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? super ULong, ? super ULong, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
