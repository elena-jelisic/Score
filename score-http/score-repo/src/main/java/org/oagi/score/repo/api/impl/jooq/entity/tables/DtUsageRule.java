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
import org.oagi.score.repo.api.impl.jooq.entity.tables.Dt.DtPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.DtSc.DtScPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.UsageRule.UsageRulePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.DtUsageRuleRecord;


/**
 * This is an intersection table. Per CCTS, a usage rule may be reused. This
 * table allows m-m relationships between the usage rule and the DT content
 * component and usage rules and DT supplementary component. In a particular
 * record, either a TARGET_DT_ID or TARGET_DT_SC_ID must be present but not
 * both.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DtUsageRule extends TableImpl<DtUsageRuleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.dt_usage_rule</code>
     */
    public static final DtUsageRule DT_USAGE_RULE = new DtUsageRule();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DtUsageRuleRecord> getRecordType() {
        return DtUsageRuleRecord.class;
    }

    /**
     * The column <code>oagi.dt_usage_rule.dt_usage_rule_id</code>. Primary key
     * of the table.
     */
    public final TableField<DtUsageRuleRecord, ULong> DT_USAGE_RULE_ID = createField(DSL.name("dt_usage_rule_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "Primary key of the table.");

    /**
     * The column <code>oagi.dt_usage_rule.assigned_usage_rule_id</code>.
     * Foreign key to the USAGE_RULE table indicating the usage rule assigned to
     * the DT content component or DT_SC.
     */
    public final TableField<DtUsageRuleRecord, ULong> ASSIGNED_USAGE_RULE_ID = createField(DSL.name("assigned_usage_rule_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "Foreign key to the USAGE_RULE table indicating the usage rule assigned to the DT content component or DT_SC.");

    /**
     * The column <code>oagi.dt_usage_rule.target_dt_id</code>. Foreing key to
     * the DT_ID for assigning a usage rule to the corresponding DT content
     * component.
     */
    public final TableField<DtUsageRuleRecord, ULong> TARGET_DT_ID = createField(DSL.name("target_dt_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "Foreing key to the DT_ID for assigning a usage rule to the corresponding DT content component.");

    /**
     * The column <code>oagi.dt_usage_rule.target_dt_sc_id</code>. Foreing key
     * to the DT_SC_ID for assigning a usage rule to the corresponding DT_SC.
     */
    public final TableField<DtUsageRuleRecord, ULong> TARGET_DT_SC_ID = createField(DSL.name("target_dt_sc_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "Foreing key to the DT_SC_ID for assigning a usage rule to the corresponding DT_SC.");

    private DtUsageRule(Name alias, Table<DtUsageRuleRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private DtUsageRule(Name alias, Table<DtUsageRuleRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment("This is an intersection table. Per CCTS, a usage rule may be reused. This table allows m-m relationships between the usage rule and the DT content component and usage rules and DT supplementary component. In a particular record, either a TARGET_DT_ID or TARGET_DT_SC_ID must be present but not both."), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.dt_usage_rule</code> table reference
     */
    public DtUsageRule(String alias) {
        this(DSL.name(alias), DT_USAGE_RULE);
    }

    /**
     * Create an aliased <code>oagi.dt_usage_rule</code> table reference
     */
    public DtUsageRule(Name alias) {
        this(alias, DT_USAGE_RULE);
    }

    /**
     * Create a <code>oagi.dt_usage_rule</code> table reference
     */
    public DtUsageRule() {
        this(DSL.name("dt_usage_rule"), null);
    }

    public <O extends Record> DtUsageRule(Table<O> path, ForeignKey<O, DtUsageRuleRecord> childPath, InverseForeignKey<O, DtUsageRuleRecord> parentPath) {
        super(path, childPath, parentPath, DT_USAGE_RULE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class DtUsageRulePath extends DtUsageRule implements Path<DtUsageRuleRecord> {
        public <O extends Record> DtUsageRulePath(Table<O> path, ForeignKey<O, DtUsageRuleRecord> childPath, InverseForeignKey<O, DtUsageRuleRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private DtUsageRulePath(Name alias, Table<DtUsageRuleRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public DtUsageRulePath as(String alias) {
            return new DtUsageRulePath(DSL.name(alias), this);
        }

        @Override
        public DtUsageRulePath as(Name alias) {
            return new DtUsageRulePath(alias, this);
        }

        @Override
        public DtUsageRulePath as(Table<?> alias) {
            return new DtUsageRulePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<DtUsageRuleRecord, ULong> getIdentity() {
        return (Identity<DtUsageRuleRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<DtUsageRuleRecord> getPrimaryKey() {
        return Keys.KEY_DT_USAGE_RULE_PRIMARY;
    }

    @Override
    public List<ForeignKey<DtUsageRuleRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DT_USAGE_RULE_ASSIGNED_USAGE_RULE_ID_FK, Keys.DT_USAGE_RULE_TARGET_DT_ID_FK, Keys.DT_USAGE_RULE_TARGET_DT_SC_ID_FK);
    }

    private transient UsageRulePath _usageRule;

    /**
     * Get the implicit join path to the <code>oagi.usage_rule</code> table.
     */
    public UsageRulePath usageRule() {
        if (_usageRule == null)
            _usageRule = new UsageRulePath(this, Keys.DT_USAGE_RULE_ASSIGNED_USAGE_RULE_ID_FK, null);

        return _usageRule;
    }

    private transient DtPath _dt;

    /**
     * Get the implicit join path to the <code>oagi.dt</code> table.
     */
    public DtPath dt() {
        if (_dt == null)
            _dt = new DtPath(this, Keys.DT_USAGE_RULE_TARGET_DT_ID_FK, null);

        return _dt;
    }

    private transient DtScPath _dtSc;

    /**
     * Get the implicit join path to the <code>oagi.dt_sc</code> table.
     */
    public DtScPath dtSc() {
        if (_dtSc == null)
            _dtSc = new DtScPath(this, Keys.DT_USAGE_RULE_TARGET_DT_SC_ID_FK, null);

        return _dtSc;
    }

    @Override
    public DtUsageRule as(String alias) {
        return new DtUsageRule(DSL.name(alias), this);
    }

    @Override
    public DtUsageRule as(Name alias) {
        return new DtUsageRule(alias, this);
    }

    @Override
    public DtUsageRule as(Table<?> alias) {
        return new DtUsageRule(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public DtUsageRule rename(String name) {
        return new DtUsageRule(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DtUsageRule rename(Name name) {
        return new DtUsageRule(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public DtUsageRule rename(Table<?> name) {
        return new DtUsageRule(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public DtUsageRule where(Condition condition) {
        return new DtUsageRule(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public DtUsageRule where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public DtUsageRule where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public DtUsageRule where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public DtUsageRule where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public DtUsageRule where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public DtUsageRule where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public DtUsageRule where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public DtUsageRule whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public DtUsageRule whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
