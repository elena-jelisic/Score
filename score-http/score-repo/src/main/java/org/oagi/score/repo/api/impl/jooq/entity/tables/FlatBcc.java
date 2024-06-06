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
import org.jooq.Index;
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
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.Indexes;
import org.oagi.score.repo.api.impl.jooq.entity.Keys;
import org.oagi.score.repo.api.impl.jooq.entity.Oagi;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Acc.AccPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Bcc.BccPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.DtSc.DtScPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SuperBcc.SuperBccPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.FlatBccRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FlatBcc extends TableImpl<FlatBccRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.flat_bcc</code>
     */
    public static final FlatBcc FLAT_BCC = new FlatBcc();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FlatBccRecord> getRecordType() {
        return FlatBccRecord.class;
    }

    /**
     * The column <code>oagi.flat_bcc.flat_bcc_id</code>.
     */
    public final TableField<FlatBccRecord, Long> FLAT_BCC_ID = createField(DSL.name("flat_bcc_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.flat_bcc.acc_id</code>.
     */
    public final TableField<FlatBccRecord, ULong> ACC_ID = createField(DSL.name("acc_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.flat_bcc.bcc_id</code>.
     */
    public final TableField<FlatBccRecord, ULong> BCC_ID = createField(DSL.name("bcc_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.flat_bcc.dt_sc_id</code>.
     */
    public final TableField<FlatBccRecord, ULong> DT_SC_ID = createField(DSL.name("dt_sc_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "");

    /**
     * The column <code>oagi.flat_bcc.super_bcc_id</code>.
     */
    public final TableField<FlatBccRecord, Long> SUPER_BCC_ID = createField(DSL.name("super_bcc_id"), SQLDataType.BIGINT.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>oagi.flat_bcc.Path</code>.
     */
    public final TableField<FlatBccRecord, String> PATH = createField(DSL.name("Path"), SQLDataType.VARCHAR(10000).nullable(false), this, "");

    private FlatBcc(Name alias, Table<FlatBccRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private FlatBcc(Name alias, Table<FlatBccRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.flat_bcc</code> table reference
     */
    public FlatBcc(String alias) {
        this(DSL.name(alias), FLAT_BCC);
    }

    /**
     * Create an aliased <code>oagi.flat_bcc</code> table reference
     */
    public FlatBcc(Name alias) {
        this(alias, FLAT_BCC);
    }

    /**
     * Create a <code>oagi.flat_bcc</code> table reference
     */
    public FlatBcc() {
        this(DSL.name("flat_bcc"), null);
    }

    public <O extends Record> FlatBcc(Table<O> path, ForeignKey<O, FlatBccRecord> childPath, InverseForeignKey<O, FlatBccRecord> parentPath) {
        super(path, childPath, parentPath, FLAT_BCC);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class FlatBccPath extends FlatBcc implements Path<FlatBccRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> FlatBccPath(Table<O> path, ForeignKey<O, FlatBccRecord> childPath, InverseForeignKey<O, FlatBccRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private FlatBccPath(Name alias, Table<FlatBccRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public FlatBccPath as(String alias) {
            return new FlatBccPath(DSL.name(alias), this);
        }

        @Override
        public FlatBccPath as(Name alias) {
            return new FlatBccPath(alias, this);
        }

        @Override
        public FlatBccPath as(Table<?> alias) {
            return new FlatBccPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.FLAT_BCC_FKFLAT_BCC2545379_IDX, Indexes.FLAT_BCC_FKFLAT_BCC254538_IDX, Indexes.FLAT_BCC_FKFLAT_BCC2545399_IDX);
    }

    @Override
    public Identity<FlatBccRecord, Long> getIdentity() {
        return (Identity<FlatBccRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<FlatBccRecord> getPrimaryKey() {
        return Keys.KEY_FLAT_BCC_PRIMARY;
    }

    @Override
    public List<ForeignKey<FlatBccRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FKFLAT_BCC254538, Keys.FKFLAT_BCC2545399, Keys.FKFLAT_BCC2545379, Keys.FKFLAT_BCC254537);
    }

    private transient AccPath _acc;

    /**
     * Get the implicit join path to the <code>oagi.acc</code> table.
     */
    public AccPath acc() {
        if (_acc == null)
            _acc = new AccPath(this, Keys.FKFLAT_BCC254538, null);

        return _acc;
    }

    private transient BccPath _bcc;

    /**
     * Get the implicit join path to the <code>oagi.bcc</code> table.
     */
    public BccPath bcc() {
        if (_bcc == null)
            _bcc = new BccPath(this, Keys.FKFLAT_BCC2545399, null);

        return _bcc;
    }

    private transient DtScPath _dtSc;

    /**
     * Get the implicit join path to the <code>oagi.dt_sc</code> table.
     */
    public DtScPath dtSc() {
        if (_dtSc == null)
            _dtSc = new DtScPath(this, Keys.FKFLAT_BCC2545379, null);

        return _dtSc;
    }

    private transient SuperBccPath _superBcc;

    /**
     * Get the implicit join path to the <code>oagi.super_bcc</code> table.
     */
    public SuperBccPath superBcc() {
        if (_superBcc == null)
            _superBcc = new SuperBccPath(this, Keys.FKFLAT_BCC254537, null);

        return _superBcc;
    }

    @Override
    public FlatBcc as(String alias) {
        return new FlatBcc(DSL.name(alias), this);
    }

    @Override
    public FlatBcc as(Name alias) {
        return new FlatBcc(alias, this);
    }

    @Override
    public FlatBcc as(Table<?> alias) {
        return new FlatBcc(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public FlatBcc rename(String name) {
        return new FlatBcc(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public FlatBcc rename(Name name) {
        return new FlatBcc(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public FlatBcc rename(Table<?> name) {
        return new FlatBcc(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FlatBcc where(Condition condition) {
        return new FlatBcc(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FlatBcc where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FlatBcc where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FlatBcc where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public FlatBcc where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public FlatBcc where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public FlatBcc where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public FlatBcc where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FlatBcc whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public FlatBcc whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
