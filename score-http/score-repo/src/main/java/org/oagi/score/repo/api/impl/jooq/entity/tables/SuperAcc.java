/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables;


import java.util.Collection;

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
import org.oagi.score.repo.api.impl.jooq.entity.tables.Acc.AccPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SuperAscc.SuperAsccPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SuperAsccp.SuperAsccpPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SuperBcc.SuperBccPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.SuperAccRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SuperAcc extends TableImpl<SuperAccRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.super_acc</code>
     */
    public static final SuperAcc SUPER_ACC = new SuperAcc();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SuperAccRecord> getRecordType() {
        return SuperAccRecord.class;
    }

    /**
     * The column <code>oagi.super_acc.super_acc_id</code>.
     */
    public final TableField<SuperAccRecord, Long> SUPER_ACC_ID = createField(DSL.name("super_acc_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.super_acc.super_acc_name</code>.
     */
    public final TableField<SuperAccRecord, String> SUPER_ACC_NAME = createField(DSL.name("super_acc_name"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    private SuperAcc(Name alias, Table<SuperAccRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private SuperAcc(Name alias, Table<SuperAccRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.super_acc</code> table reference
     */
    public SuperAcc(String alias) {
        this(DSL.name(alias), SUPER_ACC);
    }

    /**
     * Create an aliased <code>oagi.super_acc</code> table reference
     */
    public SuperAcc(Name alias) {
        this(alias, SUPER_ACC);
    }

    /**
     * Create a <code>oagi.super_acc</code> table reference
     */
    public SuperAcc() {
        this(DSL.name("super_acc"), null);
    }

    public <O extends Record> SuperAcc(Table<O> path, ForeignKey<O, SuperAccRecord> childPath, InverseForeignKey<O, SuperAccRecord> parentPath) {
        super(path, childPath, parentPath, SUPER_ACC);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class SuperAccPath extends SuperAcc implements Path<SuperAccRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> SuperAccPath(Table<O> path, ForeignKey<O, SuperAccRecord> childPath, InverseForeignKey<O, SuperAccRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private SuperAccPath(Name alias, Table<SuperAccRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public SuperAccPath as(String alias) {
            return new SuperAccPath(DSL.name(alias), this);
        }

        @Override
        public SuperAccPath as(Name alias) {
            return new SuperAccPath(alias, this);
        }

        @Override
        public SuperAccPath as(Table<?> alias) {
            return new SuperAccPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<SuperAccRecord, Long> getIdentity() {
        return (Identity<SuperAccRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<SuperAccRecord> getPrimaryKey() {
        return Keys.KEY_SUPER_ACC_PRIMARY;
    }

    private transient AccPath _acc;

    /**
     * Get the implicit to-many join path to the <code>oagi.acc</code> table
     */
    public AccPath acc() {
        if (_acc == null)
            _acc = new AccPath(this, null, Keys.ACC_IBFK_1.getInverseKey());

        return _acc;
    }

    private transient SuperAsccpPath _superAsccp;

    /**
     * Get the implicit to-many join path to the <code>oagi.super_asccp</code>
     * table
     */
    public SuperAsccpPath superAsccp() {
        if (_superAsccp == null)
            _superAsccp = new SuperAsccpPath(this, null, Keys.FKSUPER_ASCC560206.getInverseKey());

        return _superAsccp;
    }

    private transient SuperAsccPath _superAscc;

    /**
     * Get the implicit to-many join path to the <code>oagi.super_ascc</code>
     * table
     */
    public SuperAsccPath superAscc() {
        if (_superAscc == null)
            _superAscc = new SuperAsccPath(this, null, Keys.FKSUPER_ASCC934485.getInverseKey());

        return _superAscc;
    }

    private transient SuperBccPath _superBcc;

    /**
     * Get the implicit to-many join path to the <code>oagi.super_bcc</code>
     * table
     */
    public SuperBccPath superBcc() {
        if (_superBcc == null)
            _superBcc = new SuperBccPath(this, null, Keys.FKSUPER_BCC759875.getInverseKey());

        return _superBcc;
    }

    @Override
    public SuperAcc as(String alias) {
        return new SuperAcc(DSL.name(alias), this);
    }

    @Override
    public SuperAcc as(Name alias) {
        return new SuperAcc(alias, this);
    }

    @Override
    public SuperAcc as(Table<?> alias) {
        return new SuperAcc(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SuperAcc rename(String name) {
        return new SuperAcc(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SuperAcc rename(Name name) {
        return new SuperAcc(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SuperAcc rename(Table<?> name) {
        return new SuperAcc(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAcc where(Condition condition) {
        return new SuperAcc(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAcc where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAcc where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAcc where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SuperAcc where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SuperAcc where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SuperAcc where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SuperAcc where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAcc whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SuperAcc whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}