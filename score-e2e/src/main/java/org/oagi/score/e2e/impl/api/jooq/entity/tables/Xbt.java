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
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.XbtRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * This table stores XML schema built-in types and OAGIS built-in types. OAGIS
 * built-in types are those types defined in the XMLSchemaBuiltinType and the
 * XMLSchemaBuiltinType Patterns schemas.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Xbt extends TableImpl<XbtRecord> {

    /**
     * The reference instance of <code>oagi.xbt</code>
     */
    public static final Xbt XBT = new Xbt();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>oagi.xbt.xbt_id</code>. Primary, internal database key.
     */
    public final TableField<XbtRecord, ULong> XBT_ID = createField(DSL.name("xbt_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary, internal database key.");
    /**
     * The column <code>oagi.xbt.guid</code>. A globally unique identifier
     * (GUID).
     */
    public final TableField<XbtRecord, String> GUID = createField(DSL.name("guid"), SQLDataType.CHAR(32).nullable(false), this, "A globally unique identifier (GUID).");
    /**
     * The column <code>oagi.xbt.name</code>. Human understandable name of the
     * built-in type.
     */
    public final TableField<XbtRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(45), this, "Human understandable name of the built-in type.");
    /**
     * The column <code>oagi.xbt.builtIn_type</code>. Built-in type as it should
     * appear in the XML schema including the namespace prefix. Namespace prefix
     * for the XML schema namespace is assumed to be 'xsd' and a default prefix
     * for the OAGIS built-int type.
     */
    public final TableField<XbtRecord, String> BUILTIN_TYPE = createField(DSL.name("builtIn_type"), SQLDataType.VARCHAR(45), this, "Built-in type as it should appear in the XML schema including the namespace prefix. Namespace prefix for the XML schema namespace is assumed to be 'xsd' and a default prefix for the OAGIS built-int type.");
    /**
     * The column <code>oagi.xbt.jbt_draft05_map</code>.
     */
    public final TableField<XbtRecord, String> JBT_DRAFT05_MAP = createField(DSL.name("jbt_draft05_map"), SQLDataType.VARCHAR(500), this, "");
    /**
     * The column <code>oagi.xbt.openapi30_map</code>.
     */
    public final TableField<XbtRecord, String> OPENAPI30_MAP = createField(DSL.name("openapi30_map"), SQLDataType.VARCHAR(500), this, "");
    /**
     * The column <code>oagi.xbt.subtype_of_xbt_id</code>. Foreign key to the
     * XBT table itself. It indicates a super type of this XSD built-in type.
     */
    public final TableField<XbtRecord, ULong> SUBTYPE_OF_XBT_ID = createField(DSL.name("subtype_of_xbt_id"), SQLDataType.BIGINTUNSIGNED, this, "Foreign key to the XBT table itself. It indicates a super type of this XSD built-in type.");
    /**
     * The column <code>oagi.xbt.schema_definition</code>.
     */
    public final TableField<XbtRecord, String> SCHEMA_DEFINITION = createField(DSL.name("schema_definition"), SQLDataType.CLOB, this, "");
    /**
     * The column <code>oagi.xbt.revision_doc</code>.
     */
    public final TableField<XbtRecord, String> REVISION_DOC = createField(DSL.name("revision_doc"), SQLDataType.CLOB, this, "");
    /**
     * The column <code>oagi.xbt.state</code>.
     */
    public final TableField<XbtRecord, Integer> STATE = createField(DSL.name("state"), SQLDataType.INTEGER, this, "");
    /**
     * The column <code>oagi.xbt.created_by</code>.
     */
    public final TableField<XbtRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");
    /**
     * The column <code>oagi.xbt.owner_user_id</code>.
     */
    public final TableField<XbtRecord, ULong> OWNER_USER_ID = createField(DSL.name("owner_user_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");
    /**
     * The column <code>oagi.xbt.last_updated_by</code>.
     */
    public final TableField<XbtRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");
    /**
     * The column <code>oagi.xbt.creation_timestamp</code>.
     */
    public final TableField<XbtRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");
    /**
     * The column <code>oagi.xbt.last_update_timestamp</code>.
     */
    public final TableField<XbtRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");
    /**
     * The column <code>oagi.xbt.is_deprecated</code>.
     */
    public final TableField<XbtRecord, Byte> IS_DEPRECATED = createField(DSL.name("is_deprecated"), SQLDataType.TINYINT.defaultValue(DSL.inline("0", SQLDataType.TINYINT)), this, "");
    private transient Xbt _xbt;
    private transient AppUser _xbtCreatedByFk;
    private transient AppUser _xbtOwnerUserIdFk;
    private transient AppUser _xbtLastUpdatedByFk;

    private Xbt(Name alias, Table<XbtRecord> aliased) {
        this(alias, aliased, null);
    }

    private Xbt(Name alias, Table<XbtRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("This table stores XML schema built-in types and OAGIS built-in types. OAGIS built-in types are those types defined in the XMLSchemaBuiltinType and the XMLSchemaBuiltinType Patterns schemas."), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.xbt</code> table reference
     */
    public Xbt(String alias) {
        this(DSL.name(alias), XBT);
    }

    /**
     * Create an aliased <code>oagi.xbt</code> table reference
     */
    public Xbt(Name alias) {
        this(alias, XBT);
    }

    /**
     * Create a <code>oagi.xbt</code> table reference
     */
    public Xbt() {
        this(DSL.name("xbt"), null);
    }

    public <O extends Record> Xbt(Table<O> child, ForeignKey<O, XbtRecord> key) {
        super(child, key, XBT);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<XbtRecord> getRecordType() {
        return XbtRecord.class;
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.XBT_XBT_GUID_IDX, Indexes.XBT_XBT_LAST_UPDATE_TIMESTAMP_DESC_IDX);
    }

    @Override
    public Identity<XbtRecord, ULong> getIdentity() {
        return (Identity<XbtRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<XbtRecord> getPrimaryKey() {
        return Keys.KEY_XBT_PRIMARY;
    }

    @Override
    public List<ForeignKey<XbtRecord, ?>> getReferences() {
        return Arrays.asList(Keys.XBT_SUBTYPE_OF_XBT_ID_FK, Keys.XBT_CREATED_BY_FK, Keys.XBT_OWNER_USER_ID_FK, Keys.XBT_LAST_UPDATED_BY_FK);
    }

    /**
     * Get the implicit join path to the <code>oagi.xbt</code> table.
     */
    public Xbt xbt() {
        if (_xbt == null)
            _xbt = new Xbt(this, Keys.XBT_SUBTYPE_OF_XBT_ID_FK);

        return _xbt;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>xbt_created_by_fk</code> key.
     */
    public AppUser xbtCreatedByFk() {
        if (_xbtCreatedByFk == null)
            _xbtCreatedByFk = new AppUser(this, Keys.XBT_CREATED_BY_FK);

        return _xbtCreatedByFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>xbt_owner_user_id_fk</code> key.
     */
    public AppUser xbtOwnerUserIdFk() {
        if (_xbtOwnerUserIdFk == null)
            _xbtOwnerUserIdFk = new AppUser(this, Keys.XBT_OWNER_USER_ID_FK);

        return _xbtOwnerUserIdFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>xbt_last_updated_by_fk</code> key.
     */
    public AppUser xbtLastUpdatedByFk() {
        if (_xbtLastUpdatedByFk == null)
            _xbtLastUpdatedByFk = new AppUser(this, Keys.XBT_LAST_UPDATED_BY_FK);

        return _xbtLastUpdatedByFk;
    }

    @Override
    public Xbt as(String alias) {
        return new Xbt(DSL.name(alias), this);
    }

    @Override
    public Xbt as(Name alias) {
        return new Xbt(alias, this);
    }

    @Override
    public Xbt as(Table<?> alias) {
        return new Xbt(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Xbt rename(String name) {
        return new Xbt(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Xbt rename(Name name) {
        return new Xbt(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Xbt rename(Table<?> name) {
        return new Xbt(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row16 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row16<ULong, String, String, String, String, String, ULong, String, String, Integer, ULong, ULong, ULong, LocalDateTime, LocalDateTime, Byte> fieldsRow() {
        return (Row16) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function16<? super ULong, ? super String, ? super String, ? super String, ? super String, ? super String, ? super ULong, ? super String, ? super String, ? super Integer, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? super Byte, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function16<? super ULong, ? super String, ? super String, ? super String, ? super String, ? super String, ? super ULong, ? super String, ? super String, ? super Integer, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? super Byte, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
