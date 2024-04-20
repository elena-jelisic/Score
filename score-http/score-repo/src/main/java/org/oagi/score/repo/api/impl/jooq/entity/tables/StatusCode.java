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
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.StatusCodeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StatusCode extends TableImpl<StatusCodeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.status_code</code>
     */
    public static final StatusCode STATUS_CODE = new StatusCode();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StatusCodeRecord> getRecordType() {
        return StatusCodeRecord.class;
    }

    /**
     * The column <code>oagi.status_code.status_code_id</code>.
     */
    public final TableField<StatusCodeRecord, Long> STATUS_CODE_ID = createField(DSL.name("status_code_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.status_code.code</code>.
     */
    public final TableField<StatusCodeRecord, String> CODE = createField(DSL.name("code"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    private StatusCode(Name alias, Table<StatusCodeRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private StatusCode(Name alias, Table<StatusCodeRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.status_code</code> table reference
     */
    public StatusCode(String alias) {
        this(DSL.name(alias), STATUS_CODE);
    }

    /**
     * Create an aliased <code>oagi.status_code</code> table reference
     */
    public StatusCode(Name alias) {
        this(alias, STATUS_CODE);
    }

    /**
     * Create a <code>oagi.status_code</code> table reference
     */
    public StatusCode() {
        this(DSL.name("status_code"), null);
    }

    public <O extends Record> StatusCode(Table<O> path, ForeignKey<O, StatusCodeRecord> childPath, InverseForeignKey<O, StatusCodeRecord> parentPath) {
        super(path, childPath, parentPath, STATUS_CODE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class StatusCodePath extends StatusCode implements Path<StatusCodeRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> StatusCodePath(Table<O> path, ForeignKey<O, StatusCodeRecord> childPath, InverseForeignKey<O, StatusCodeRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private StatusCodePath(Name alias, Table<StatusCodeRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public StatusCodePath as(String alias) {
            return new StatusCodePath(DSL.name(alias), this);
        }

        @Override
        public StatusCodePath as(Name alias) {
            return new StatusCodePath(alias, this);
        }

        @Override
        public StatusCodePath as(Table<?> alias) {
            return new StatusCodePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<StatusCodeRecord, Long> getIdentity() {
        return (Identity<StatusCodeRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<StatusCodeRecord> getPrimaryKey() {
        return Keys.KEY_STATUS_CODE_PRIMARY;
    }

    private transient SpecificationAssociationComponentPath _specificationAssociationComponent;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.specification_association_component</code> table
     */
    public SpecificationAssociationComponentPath specificationAssociationComponent() {
        if (_specificationAssociationComponent == null)
            _specificationAssociationComponent = new SpecificationAssociationComponentPath(this, null, Keys.FKSPECIFICAT203312.getInverseKey());

        return _specificationAssociationComponent;
    }

    private transient SpecificationAggregateComponentPath _specificationAggregateComponent;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.specification_aggregate_component</code> table
     */
    public SpecificationAggregateComponentPath specificationAggregateComponent() {
        if (_specificationAggregateComponent == null)
            _specificationAggregateComponent = new SpecificationAggregateComponentPath(this, null, Keys.FKSPECIFICAT466081.getInverseKey());

        return _specificationAggregateComponent;
    }

    private transient SpecificationDataTypePath _specificationDataType;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.specification_data_type</code> table
     */
    public SpecificationDataTypePath specificationDataType() {
        if (_specificationDataType == null)
            _specificationDataType = new SpecificationDataTypePath(this, null, Keys.FKSPECIFICAT532397.getInverseKey());

        return _specificationDataType;
    }

    private transient SpecificationBasicComponentPath _specificationBasicComponent;

    /**
     * Get the implicit to-many join path to the
     * <code>oagi.specification_basic_component</code> table
     */
    public SpecificationBasicComponentPath specificationBasicComponent() {
        if (_specificationBasicComponent == null)
            _specificationBasicComponent = new SpecificationBasicComponentPath(this, null, Keys.FKSPECIFICAT993500.getInverseKey());

        return _specificationBasicComponent;
    }

    @Override
    public StatusCode as(String alias) {
        return new StatusCode(DSL.name(alias), this);
    }

    @Override
    public StatusCode as(Name alias) {
        return new StatusCode(alias, this);
    }

    @Override
    public StatusCode as(Table<?> alias) {
        return new StatusCode(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public StatusCode rename(String name) {
        return new StatusCode(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public StatusCode rename(Name name) {
        return new StatusCode(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public StatusCode rename(Table<?> name) {
        return new StatusCode(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public StatusCode where(Condition condition) {
        return new StatusCode(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public StatusCode where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public StatusCode where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public StatusCode where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public StatusCode where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public StatusCode where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public StatusCode where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public StatusCode where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public StatusCode whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public StatusCode whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}