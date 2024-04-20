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
import org.oagi.score.repo.api.impl.jooq.entity.tables.CcGapAnalysisResultCode.CcGapAnalysisResultCodePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Dt.DtPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.Specification.SpecificationPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SpecificationBasicComponent.SpecificationBasicComponentPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.StatusCode.StatusCodePath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.SpecificationDataTypeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SpecificationDataType extends TableImpl<SpecificationDataTypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.specification_data_type</code>
     */
    public static final SpecificationDataType SPECIFICATION_DATA_TYPE = new SpecificationDataType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SpecificationDataTypeRecord> getRecordType() {
        return SpecificationDataTypeRecord.class;
    }

    /**
     * The column <code>oagi.specification_data_type.data_type_id</code>.
     */
    public final TableField<SpecificationDataTypeRecord, Long> DATA_TYPE_ID = createField(DSL.name("data_type_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.specification_data_type.data_type_name</code>.
     */
    public final TableField<SpecificationDataTypeRecord, String> DATA_TYPE_NAME = createField(DSL.name("data_type_name"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>oagi.specification_data_type.definition</code>.
     */
    public final TableField<SpecificationDataTypeRecord, String> DEFINITION = createField(DSL.name("definition"), SQLDataType.VARCHAR(500).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>oagi.specification_data_type.status_code_id</code>.
     */
    public final TableField<SpecificationDataTypeRecord, Long> STATUS_CODE_ID = createField(DSL.name("status_code_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column
     * <code>oagi.specification_data_type.gap_analysis_code_id</code>.
     */
    public final TableField<SpecificationDataTypeRecord, Long> GAP_ANALYSIS_CODE_ID = createField(DSL.name("gap_analysis_code_id"), SQLDataType.BIGINT.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>oagi.specification_data_type.dt_id</code>.
     */
    public final TableField<SpecificationDataTypeRecord, ULong> DT_ID = createField(DSL.name("dt_id"), SQLDataType.BIGINTUNSIGNED.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINTUNSIGNED)), this, "");

    /**
     * The column <code>oagi.specification_data_type.is_approved</code>.
     */
    public final TableField<SpecificationDataTypeRecord, Byte> IS_APPROVED = createField(DSL.name("is_approved"), SQLDataType.TINYINT.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.TINYINT)), this, "");

    /**
     * The column <code>oagi.specification_data_type.specification_id</code>.
     */
    public final TableField<SpecificationDataTypeRecord, Long> SPECIFICATION_ID = createField(DSL.name("specification_id"), SQLDataType.BIGINT.nullable(false), this, "");

    private SpecificationDataType(Name alias, Table<SpecificationDataTypeRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private SpecificationDataType(Name alias, Table<SpecificationDataTypeRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.specification_data_type</code> table
     * reference
     */
    public SpecificationDataType(String alias) {
        this(DSL.name(alias), SPECIFICATION_DATA_TYPE);
    }

    /**
     * Create an aliased <code>oagi.specification_data_type</code> table
     * reference
     */
    public SpecificationDataType(Name alias) {
        this(alias, SPECIFICATION_DATA_TYPE);
    }

    /**
     * Create a <code>oagi.specification_data_type</code> table reference
     */
    public SpecificationDataType() {
        this(DSL.name("specification_data_type"), null);
    }

    public <O extends Record> SpecificationDataType(Table<O> path, ForeignKey<O, SpecificationDataTypeRecord> childPath, InverseForeignKey<O, SpecificationDataTypeRecord> parentPath) {
        super(path, childPath, parentPath, SPECIFICATION_DATA_TYPE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class SpecificationDataTypePath extends SpecificationDataType implements Path<SpecificationDataTypeRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> SpecificationDataTypePath(Table<O> path, ForeignKey<O, SpecificationDataTypeRecord> childPath, InverseForeignKey<O, SpecificationDataTypeRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private SpecificationDataTypePath(Name alias, Table<SpecificationDataTypeRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public SpecificationDataTypePath as(String alias) {
            return new SpecificationDataTypePath(DSL.name(alias), this);
        }

        @Override
        public SpecificationDataTypePath as(Name alias) {
            return new SpecificationDataTypePath(alias, this);
        }

        @Override
        public SpecificationDataTypePath as(Table<?> alias) {
            return new SpecificationDataTypePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.SPECIFICATION_DATA_TYPE_FKSPECIFICAT57299_IDX);
    }

    @Override
    public Identity<SpecificationDataTypeRecord, Long> getIdentity() {
        return (Identity<SpecificationDataTypeRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<SpecificationDataTypeRecord> getPrimaryKey() {
        return Keys.KEY_SPECIFICATION_DATA_TYPE_PRIMARY;
    }

    @Override
    public List<ForeignKey<SpecificationDataTypeRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FKSPECIFICAT532397, Keys.FKSPECIFICAT36977, Keys.FKSPECIFICAT57299, Keys.FKSPECIFICAT57298);
    }

    private transient StatusCodePath _statusCode;

    /**
     * Get the implicit join path to the <code>oagi.status_code</code> table.
     */
    public StatusCodePath statusCode() {
        if (_statusCode == null)
            _statusCode = new StatusCodePath(this, Keys.FKSPECIFICAT532397, null);

        return _statusCode;
    }

    private transient CcGapAnalysisResultCodePath _ccGapAnalysisResultCode;

    /**
     * Get the implicit join path to the
     * <code>oagi.cc_gap_analysis_result_code</code> table.
     */
    public CcGapAnalysisResultCodePath ccGapAnalysisResultCode() {
        if (_ccGapAnalysisResultCode == null)
            _ccGapAnalysisResultCode = new CcGapAnalysisResultCodePath(this, Keys.FKSPECIFICAT36977, null);

        return _ccGapAnalysisResultCode;
    }

    private transient DtPath _dt;

    /**
     * Get the implicit join path to the <code>oagi.dt</code> table.
     */
    public DtPath dt() {
        if (_dt == null)
            _dt = new DtPath(this, Keys.FKSPECIFICAT57299, null);

        return _dt;
    }

    private transient SpecificationPath _specification;

    /**
     * Get the implicit join path to the <code>oagi.specification</code> table.
     */
    public SpecificationPath specification() {
        if (_specification == null)
            _specification = new SpecificationPath(this, Keys.FKSPECIFICAT57298, null);

        return _specification;
    }

    private transient SpecificationBasicComponentPath _specificationBasicComponent;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.specification_basic_component</code> table
     */
    public SpecificationBasicComponentPath specificationBasicComponent() {
        if (_specificationBasicComponent == null)
            _specificationBasicComponent = new SpecificationBasicComponentPath(this, null, Keys.FKSPECIFICAT559853.getInverseKey());

        return _specificationBasicComponent;
    }

    @Override
    public SpecificationDataType as(String alias) {
        return new SpecificationDataType(DSL.name(alias), this);
    }

    @Override
    public SpecificationDataType as(Name alias) {
        return new SpecificationDataType(alias, this);
    }

    @Override
    public SpecificationDataType as(Table<?> alias) {
        return new SpecificationDataType(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SpecificationDataType rename(String name) {
        return new SpecificationDataType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SpecificationDataType rename(Name name) {
        return new SpecificationDataType(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SpecificationDataType rename(Table<?> name) {
        return new SpecificationDataType(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationDataType where(Condition condition) {
        return new SpecificationDataType(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationDataType where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationDataType where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationDataType where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SpecificationDataType where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SpecificationDataType where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SpecificationDataType where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SpecificationDataType where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationDataType whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationDataType whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}