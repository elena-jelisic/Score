/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.oagi.score.repo.api.impl.jooq.entity.Keys;
import org.oagi.score.repo.api.impl.jooq.entity.Oagi;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Ascc.AsccPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SuperAcc.SuperAccPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SuperAsccp.SuperAsccpPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.SuperAsccRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SuperAscc extends TableImpl<SuperAsccRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.super_ascc</code>
     */
    public static final SuperAscc SUPER_ASCC = new SuperAscc();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SuperAsccRecord> getRecordType() {
        return SuperAsccRecord.class;
    }

    /**
     * The column <code>oagi.super_ascc.super_ascc_id</code>.
     */
    public final TableField<SuperAsccRecord, Long> SUPER_ASCC_ID = createField(DSL.name("super_ascc_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.super_ascc.super_ascc_name</code>.
     */
    public final TableField<SuperAsccRecord, String> SUPER_ASCC_NAME = createField(DSL.name("super_ascc_name"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>oagi.super_ascc.min_cardinality</code>.
     */
    public final TableField<SuperAsccRecord, Integer> MIN_CARDINALITY = createField(DSL.name("min_cardinality"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>oagi.super_ascc.max_cardinality</code>.
     */
    public final TableField<SuperAsccRecord, Integer> MAX_CARDINALITY = createField(DSL.name("max_cardinality"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>oagi.super_ascc.from_super_acc_id</code>.
     */
    public final TableField<SuperAsccRecord, Long> FROM_SUPER_ACC_ID = createField(DSL.name("from_super_acc_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>oagi.super_ascc.super_asccp_id</code>.
     */
    public final TableField<SuperAsccRecord, Long> SUPER_ASCCP_ID = createField(DSL.name("super_asccp_id"), SQLDataType.BIGINT.nullable(false), this, "");

    private SuperAscc(Name alias, Table<SuperAsccRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private SuperAscc(Name alias, Table<SuperAsccRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.super_ascc</code> table reference
     */
    public SuperAscc(String alias) {
        this(DSL.name(alias), SUPER_ASCC);
    }

    /**
     * Create an aliased <code>oagi.super_ascc</code> table reference
     */
    public SuperAscc(Name alias) {
        this(alias, SUPER_ASCC);
    }

    /**
     * Create a <code>oagi.super_ascc</code> table reference
     */
    public SuperAscc() {
        this(DSL.name("super_ascc"), null);
    }

    public <O extends Record> SuperAscc(Table<O> path, ForeignKey<O, SuperAsccRecord> childPath, InverseForeignKey<O, SuperAsccRecord> parentPath) {
        super(path, childPath, parentPath, SUPER_ASCC);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class SuperAsccPath extends SuperAscc implements Path<SuperAsccRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> SuperAsccPath(Table<O> path, ForeignKey<O, SuperAsccRecord> childPath, InverseForeignKey<O, SuperAsccRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private SuperAsccPath(Name alias, Table<SuperAsccRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public SuperAsccPath as(String alias) {
            return new SuperAsccPath(DSL.name(alias), this);
        }

        @Override
        public SuperAsccPath as(Name alias) {
            return new SuperAsccPath(alias, this);
        }

        @Override
        public SuperAsccPath as(Table<?> alias) {
            return new SuperAsccPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<SuperAsccRecord, Long> getIdentity() {
        return (Identity<SuperAsccRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<SuperAsccRecord> getPrimaryKey() {
        return Keys.KEY_SUPER_ASCC_PRIMARY;
    }

    @Override
    public List<ForeignKey<SuperAsccRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FKSUPER_ASCC934485, Keys.FKSUPER_ASCC367658);
    }

    private transient SuperAccPath _superAcc;

    /**
     * Get the implicit join path to the <code>oagi.super_acc</code> table.
     */
    public SuperAccPath superAcc() {
        if (_superAcc == null)
            _superAcc = new SuperAccPath(this, Keys.FKSUPER_ASCC934485, null);

        return _superAcc;
    }

    private transient SuperAsccpPath _superAsccp;

    /**
     * Get the implicit join path to the <code>oagi.super_asccp</code> table.
     */
    public SuperAsccpPath superAsccp() {
        if (_superAsccp == null)
            _superAsccp = new SuperAsccpPath(this, Keys.FKSUPER_ASCC367658, null);

        return _superAsccp;
    }

    private transient AsccPath _ascc;

    /**
     * Get the implicit to-many join path to the <code>oagi.ascc</code> table
     */
    public AsccPath ascc() {
        if (_ascc == null)
            _ascc = new AsccPath(this, null, Keys.ASCC_IBFK_1.getInverseKey());

        return _ascc;
    }

    @Override
    public SuperAscc as(String alias) {
        return new SuperAscc(DSL.name(alias), this);
    }

    @Override
    public SuperAscc as(Name alias) {
        return new SuperAscc(alias, this);
    }

    @Override
    public SuperAscc as(Table<?> alias) {
        return new SuperAscc(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SuperAscc rename(String name) {
        return new SuperAscc(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SuperAscc rename(Name name) {
        return new SuperAscc(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SuperAscc rename(Table<?> name) {
        return new SuperAscc(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAscc where(Condition condition) {
        return new SuperAscc(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAscc where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAscc where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAscc where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SuperAscc where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SuperAscc where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SuperAscc where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SuperAscc where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAscc whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAscc whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}