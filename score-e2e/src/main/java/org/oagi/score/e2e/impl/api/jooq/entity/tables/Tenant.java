/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables;


import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
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
import org.oagi.score.e2e.impl.api.jooq.entity.Keys;
import org.oagi.score.e2e.impl.api.jooq.entity.Oagi;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.TenantRecord;


/**
 * This table about the user tenant role.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tenant extends TableImpl<TenantRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.tenant</code>
     */
    public static final Tenant TENANT = new Tenant();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TenantRecord> getRecordType() {
        return TenantRecord.class;
    }

    /**
     * The column <code>oagi.tenant.tenant_id</code>. Primary key column.
     */
    public final TableField<TenantRecord, ULong> TENANT_ID = createField(DSL.name("tenant_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key column.");

    /**
     * The column <code>oagi.tenant.name</code>. The name of the tenant.
     */
    public final TableField<TenantRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "The name of the tenant.");

    private Tenant(Name alias, Table<TenantRecord> aliased) {
        this(alias, aliased, null);
    }

    private Tenant(Name alias, Table<TenantRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("This table about the user tenant role."), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.tenant</code> table reference
     */
    public Tenant(String alias) {
        this(DSL.name(alias), TENANT);
    }

    /**
     * Create an aliased <code>oagi.tenant</code> table reference
     */
    public Tenant(Name alias) {
        this(alias, TENANT);
    }

    /**
     * Create a <code>oagi.tenant</code> table reference
     */
    public Tenant() {
        this(DSL.name("tenant"), null);
    }

    public <O extends Record> Tenant(Table<O> child, ForeignKey<O, TenantRecord> key) {
        super(child, key, TENANT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<TenantRecord, ULong> getIdentity() {
        return (Identity<TenantRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<TenantRecord> getPrimaryKey() {
        return Keys.KEY_TENANT_PRIMARY;
    }

    @Override
    public Tenant as(String alias) {
        return new Tenant(DSL.name(alias), this);
    }

    @Override
    public Tenant as(Name alias) {
        return new Tenant(alias, this);
    }

    @Override
    public Tenant as(Table<?> alias) {
        return new Tenant(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Tenant rename(String name) {
        return new Tenant(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Tenant rename(Name name) {
        return new Tenant(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Tenant rename(Table<?> name) {
        return new Tenant(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<ULong, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super ULong, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super ULong, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
