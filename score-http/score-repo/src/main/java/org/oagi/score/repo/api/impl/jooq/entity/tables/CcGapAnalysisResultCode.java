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
import org.oagi.score.repo.api.impl.jooq.entity.tables.SpecificationAggregateComponent.SpecificationAggregateComponentPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SpecificationAssociationComponent.SpecificationAssociationComponentPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SpecificationBasicComponent.SpecificationBasicComponentPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SpecificationDataType.SpecificationDataTypePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.CcGapAnalysisResultCodeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CcGapAnalysisResultCode extends TableImpl<CcGapAnalysisResultCodeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.cc_gap_analysis_result_code</code>
     */
    public static final CcGapAnalysisResultCode CC_GAP_ANALYSIS_RESULT_CODE = new CcGapAnalysisResultCode();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CcGapAnalysisResultCodeRecord> getRecordType() {
        return CcGapAnalysisResultCodeRecord.class;
    }

    /**
     * The column <code>oagi.cc_gap_analysis_result_code.code_id</code>.
     */
    public final TableField<CcGapAnalysisResultCodeRecord, Long> CODE_ID = createField(DSL.name("code_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.cc_gap_analysis_result_code.code</code>.
     */
    public final TableField<CcGapAnalysisResultCodeRecord, String> CODE = createField(DSL.name("code"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    private CcGapAnalysisResultCode(Name alias, Table<CcGapAnalysisResultCodeRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private CcGapAnalysisResultCode(Name alias, Table<CcGapAnalysisResultCodeRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.cc_gap_analysis_result_code</code> table
     * reference
     */
    public CcGapAnalysisResultCode(String alias) {
        this(DSL.name(alias), CC_GAP_ANALYSIS_RESULT_CODE);
    }

    /**
     * Create an aliased <code>oagi.cc_gap_analysis_result_code</code> table
     * reference
     */
    public CcGapAnalysisResultCode(Name alias) {
        this(alias, CC_GAP_ANALYSIS_RESULT_CODE);
    }

    /**
     * Create a <code>oagi.cc_gap_analysis_result_code</code> table reference
     */
    public CcGapAnalysisResultCode() {
        this(DSL.name("cc_gap_analysis_result_code"), null);
    }

    public <O extends Record> CcGapAnalysisResultCode(Table<O> path, ForeignKey<O, CcGapAnalysisResultCodeRecord> childPath, InverseForeignKey<O, CcGapAnalysisResultCodeRecord> parentPath) {
        super(path, childPath, parentPath, CC_GAP_ANALYSIS_RESULT_CODE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class CcGapAnalysisResultCodePath extends CcGapAnalysisResultCode implements Path<CcGapAnalysisResultCodeRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> CcGapAnalysisResultCodePath(Table<O> path, ForeignKey<O, CcGapAnalysisResultCodeRecord> childPath, InverseForeignKey<O, CcGapAnalysisResultCodeRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private CcGapAnalysisResultCodePath(Name alias, Table<CcGapAnalysisResultCodeRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public CcGapAnalysisResultCodePath as(String alias) {
            return new CcGapAnalysisResultCodePath(DSL.name(alias), this);
        }

        @Override
        public CcGapAnalysisResultCodePath as(Name alias) {
            return new CcGapAnalysisResultCodePath(alias, this);
        }

        @Override
        public CcGapAnalysisResultCodePath as(Table<?> alias) {
            return new CcGapAnalysisResultCodePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<CcGapAnalysisResultCodeRecord, Long> getIdentity() {
        return (Identity<CcGapAnalysisResultCodeRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<CcGapAnalysisResultCodeRecord> getPrimaryKey() {
        return Keys.KEY_CC_GAP_ANALYSIS_RESULT_CODE_PRIMARY;
    }

    private transient SpecificationDataTypePath _specificationDataType;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.specification_data_type</code> table
     */
    public SpecificationDataTypePath specificationDataType() {
        if (_specificationDataType == null)
            _specificationDataType = new SpecificationDataTypePath(this, null, Keys.FKSPECIFICAT36977.getInverseKey());

        return _specificationDataType;
    }

    private transient SpecificationBasicComponentPath _specificationBasicComponent;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.specification_basic_component</code> table
     */
    public SpecificationBasicComponentPath specificationBasicComponent() {
        if (_specificationBasicComponent == null)
            _specificationBasicComponent = new SpecificationBasicComponentPath(this, null, Keys.FKSPECIFICAT591284.getInverseKey());

        return _specificationBasicComponent;
    }

    private transient SpecificationAssociationComponentPath _specificationAssociationComponent;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.specification_association_component</code> table
     */
    public SpecificationAssociationComponentPath specificationAssociationComponent() {
        if (_specificationAssociationComponent == null)
            _specificationAssociationComponent = new SpecificationAssociationComponentPath(this, null, Keys.FKSPECIFICAT801095.getInverseKey());

        return _specificationAssociationComponent;
    }

    private transient SpecificationAggregateComponentPath _specificationAggregateComponent;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.specification_aggregate_component</code> table
     */
    public SpecificationAggregateComponentPath specificationAggregateComponent() {
        if (_specificationAggregateComponent == null)
            _specificationAggregateComponent = new SpecificationAggregateComponentPath(this, null, Keys.FKSPECIFICAT868297.getInverseKey());

        return _specificationAggregateComponent;
    }

    @Override
    public CcGapAnalysisResultCode as(String alias) {
        return new CcGapAnalysisResultCode(DSL.name(alias), this);
    }

    @Override
    public CcGapAnalysisResultCode as(Name alias) {
        return new CcGapAnalysisResultCode(alias, this);
    }

    @Override
    public CcGapAnalysisResultCode as(Table<?> alias) {
        return new CcGapAnalysisResultCode(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CcGapAnalysisResultCode rename(String name) {
        return new CcGapAnalysisResultCode(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CcGapAnalysisResultCode rename(Name name) {
        return new CcGapAnalysisResultCode(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CcGapAnalysisResultCode rename(Table<?> name) {
        return new CcGapAnalysisResultCode(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CcGapAnalysisResultCode where(Condition condition) {
        return new CcGapAnalysisResultCode(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CcGapAnalysisResultCode where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CcGapAnalysisResultCode where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CcGapAnalysisResultCode where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CcGapAnalysisResultCode where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CcGapAnalysisResultCode where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CcGapAnalysisResultCode where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CcGapAnalysisResultCode where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CcGapAnalysisResultCode whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CcGapAnalysisResultCode whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
