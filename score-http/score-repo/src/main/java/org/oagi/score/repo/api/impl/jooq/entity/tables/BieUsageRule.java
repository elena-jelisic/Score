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
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.Keys;
import org.oagi.score.repo.api.impl.jooq.entity.Oagi;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Abie.AbiePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Asbie.AsbiePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Asbiep.AsbiepPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Bbie.BbiePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Bbiep.BbiepPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.UsageRule.UsageRulePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.BieUsageRuleRecord;


/**
 * This is an intersection table. Per CCTS, a usage rule may be reused. This
 * table allows m-m relationships between the usage rule and all kinds of BIEs.
 * In a particular record, either only one of the TARGET_ABIE_ID,
 * TARGET_ASBIE_ID, TARGET_ASBIEP_ID, TARGET_BBIE_ID, or TARGET_BBIEP_ID.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BieUsageRule extends TableImpl<BieUsageRuleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.bie_usage_rule</code>
     */
    public static final BieUsageRule BIE_USAGE_RULE = new BieUsageRule();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BieUsageRuleRecord> getRecordType() {
        return BieUsageRuleRecord.class;
    }

    /**
     * The column <code>oagi.bie_usage_rule.bie_usage_rule_id</code>. Primary
     * key of the table.
     */
    public final TableField<BieUsageRuleRecord, ULong> BIE_USAGE_RULE_ID = createField(DSL.name("bie_usage_rule_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key of the table.");

    /**
     * The column <code>oagi.bie_usage_rule.assigned_usage_rule_id</code>.
     * Foreign key to the USAGE_RULE table indicating the usage rule assigned to
     * a BIE.
     */
    public final TableField<BieUsageRuleRecord, ULong> ASSIGNED_USAGE_RULE_ID = createField(DSL.name("assigned_usage_rule_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the USAGE_RULE table indicating the usage rule assigned to a BIE.");

    /**
     * The column <code>oagi.bie_usage_rule.target_abie_id</code>. Foreign key
     * to the ABIE table indicating the ABIE, to which the usage rule is
     * applied.
     */
    public final TableField<BieUsageRuleRecord, ULong> TARGET_ABIE_ID = createField(DSL.name("target_abie_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "Foreign key to the ABIE table indicating the ABIE, to which the usage rule is applied.");

    /**
     * The column <code>oagi.bie_usage_rule.target_asbie_id</code>. Foreign key
     * to the ASBIE table indicating the ASBIE, to which the usage rule is
     * applied.
     */
    public final TableField<BieUsageRuleRecord, ULong> TARGET_ASBIE_ID = createField(DSL.name("target_asbie_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "Foreign key to the ASBIE table indicating the ASBIE, to which the usage rule is applied.");

    /**
     * The column <code>oagi.bie_usage_rule.target_asbiep_id</code>. Foreign key
     * to the ASBIEP table indicating the ASBIEP, to which the usage rule is
     * applied.
     */
    public final TableField<BieUsageRuleRecord, ULong> TARGET_ASBIEP_ID = createField(DSL.name("target_asbiep_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "Foreign key to the ASBIEP table indicating the ASBIEP, to which the usage rule is applied.");

    /**
     * The column <code>oagi.bie_usage_rule.target_bbie_id</code>. Foreign key
     * to the BBIE table indicating the BBIE, to which the usage rule is
     * applied.
     */
    public final TableField<BieUsageRuleRecord, ULong> TARGET_BBIE_ID = createField(DSL.name("target_bbie_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "Foreign key to the BBIE table indicating the BBIE, to which the usage rule is applied.");

    /**
     * The column <code>oagi.bie_usage_rule.target_bbiep_id</code>. Foreign key
     * to the BBIEP table indicating the ABIEP, to which the usage rule is
     * applied.
     */
    public final TableField<BieUsageRuleRecord, ULong> TARGET_BBIEP_ID = createField(DSL.name("target_bbiep_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "Foreign key to the BBIEP table indicating the ABIEP, to which the usage rule is applied.");

    private BieUsageRule(Name alias, Table<BieUsageRuleRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private BieUsageRule(Name alias, Table<BieUsageRuleRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment("This is an intersection table. Per CCTS, a usage rule may be reused. This table allows m-m relationships between the usage rule and all kinds of BIEs. In a particular record, either only one of the TARGET_ABIE_ID, TARGET_ASBIE_ID, TARGET_ASBIEP_ID, TARGET_BBIE_ID, or TARGET_BBIEP_ID."), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.bie_usage_rule</code> table reference
     */
    public BieUsageRule(String alias) {
        this(DSL.name(alias), BIE_USAGE_RULE);
    }

    /**
     * Create an aliased <code>oagi.bie_usage_rule</code> table reference
     */
    public BieUsageRule(Name alias) {
        this(alias, BIE_USAGE_RULE);
    }

    /**
     * Create a <code>oagi.bie_usage_rule</code> table reference
     */
    public BieUsageRule() {
        this(DSL.name("bie_usage_rule"), null);
    }

    public <O extends Record> BieUsageRule(Table<O> path, ForeignKey<O, BieUsageRuleRecord> childPath, InverseForeignKey<O, BieUsageRuleRecord> parentPath) {
        super(path, childPath, parentPath, BIE_USAGE_RULE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class BieUsageRulePath extends BieUsageRule implements Path<BieUsageRuleRecord> {
        public <O extends Record> BieUsageRulePath(Table<O> path, ForeignKey<O, BieUsageRuleRecord> childPath, InverseForeignKey<O, BieUsageRuleRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private BieUsageRulePath(Name alias, Table<BieUsageRuleRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public BieUsageRulePath as(String alias) {
            return new BieUsageRulePath(DSL.name(alias), this);
        }

        @Override
        public BieUsageRulePath as(Name alias) {
            return new BieUsageRulePath(alias, this);
        }

        @Override
        public BieUsageRulePath as(Table<?> alias) {
            return new BieUsageRulePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<BieUsageRuleRecord, ULong> getIdentity() {
        return (Identity<BieUsageRuleRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<BieUsageRuleRecord> getPrimaryKey() {
        return Keys.KEY_BIE_USAGE_RULE_PRIMARY;
    }

    @Override
    public List<ForeignKey<BieUsageRuleRecord, ?>> getReferences() {
        return Arrays.asList(Keys.BIE_USAGE_RULE_ASSIGNED_USAGE_RULE_ID_FK, Keys.BIE_USAGE_RULE_TARGET_ABIE_ID_FK, Keys.BIE_USAGE_RULE_TARGET_ASBIE_ID_FK, Keys.BIE_USAGE_RULE_TARGET_ASBIEP_ID_FK, Keys.BIE_USAGE_RULE_TARGET_BBIE_ID_FK, Keys.BIE_USAGE_RULE_TARGET_BBIEP_ID_FK);
    }

    private transient UsageRulePath _usageRule;

    /**
     * Get the implicit join path to the <code>oagi.usage_rule</code> table.
     */
    public UsageRulePath usageRule() {
        if (_usageRule == null)
            _usageRule = new UsageRulePath(this, Keys.BIE_USAGE_RULE_ASSIGNED_USAGE_RULE_ID_FK, null);

        return _usageRule;
    }

    private transient AbiePath _abie;

    /**
     * Get the implicit join path to the <code>oagi.abie</code> table.
     */
    public AbiePath abie() {
        if (_abie == null)
            _abie = new AbiePath(this, Keys.BIE_USAGE_RULE_TARGET_ABIE_ID_FK, null);

        return _abie;
    }

    private transient AsbiePath _asbie;

    /**
     * Get the implicit join path to the <code>oagi.asbie</code> table.
     */
    public AsbiePath asbie() {
        if (_asbie == null)
            _asbie = new AsbiePath(this, Keys.BIE_USAGE_RULE_TARGET_ASBIE_ID_FK, null);

        return _asbie;
    }

    private transient AsbiepPath _asbiep;

    /**
     * Get the implicit join path to the <code>oagi.asbiep</code> table.
     */
    public AsbiepPath asbiep() {
        if (_asbiep == null)
            _asbiep = new AsbiepPath(this, Keys.BIE_USAGE_RULE_TARGET_ASBIEP_ID_FK, null);

        return _asbiep;
    }

    private transient BbiePath _bbie;

    /**
     * Get the implicit join path to the <code>oagi.bbie</code> table.
     */
    public BbiePath bbie() {
        if (_bbie == null)
            _bbie = new BbiePath(this, Keys.BIE_USAGE_RULE_TARGET_BBIE_ID_FK, null);

        return _bbie;
    }

    private transient BbiepPath _bbiep;

    /**
     * Get the implicit join path to the <code>oagi.bbiep</code> table.
     */
    public BbiepPath bbiep() {
        if (_bbiep == null)
            _bbiep = new BbiepPath(this, Keys.BIE_USAGE_RULE_TARGET_BBIEP_ID_FK, null);

        return _bbiep;
    }

    @Override
    public BieUsageRule as(String alias) {
        return new BieUsageRule(DSL.name(alias), this);
    }

    @Override
    public BieUsageRule as(Name alias) {
        return new BieUsageRule(alias, this);
    }

    @Override
    public BieUsageRule as(Table<?> alias) {
        return new BieUsageRule(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public BieUsageRule rename(String name) {
        return new BieUsageRule(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BieUsageRule rename(Name name) {
        return new BieUsageRule(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public BieUsageRule rename(Table<?> name) {
        return new BieUsageRule(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BieUsageRule where(Condition condition) {
        return new BieUsageRule(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BieUsageRule where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BieUsageRule where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BieUsageRule where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BieUsageRule where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BieUsageRule where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BieUsageRule where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public BieUsageRule where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BieUsageRule whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public BieUsageRule whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
