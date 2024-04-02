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
import org.oagi.score.repo.api.impl.jooq.entity.tables.Specification.SpecificationPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.SpecificationTypeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SpecificationType extends TableImpl<SpecificationTypeRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.specification_type</code>
     */
    public static final SpecificationType SPECIFICATION_TYPE = new SpecificationType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SpecificationTypeRecord> getRecordType() {
        return SpecificationTypeRecord.class;
    }

    /**
     * The column <code>oagi.specification_type.specification_type_id</code>.
     */
    public final TableField<SpecificationTypeRecord, Long> SPECIFICATION_TYPE_ID = createField(DSL.name("specification_type_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.specification_type.specification_type_name</code>.
     */
    public final TableField<SpecificationTypeRecord, String> SPECIFICATION_TYPE_NAME = createField(DSL.name("specification_type_name"), SQLDataType.VARCHAR(100).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    private SpecificationType(Name alias, Table<SpecificationTypeRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private SpecificationType(Name alias, Table<SpecificationTypeRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.specification_type</code> table reference
     */
    public SpecificationType(String alias) {
        this(DSL.name(alias), SPECIFICATION_TYPE);
    }

    /**
     * Create an aliased <code>oagi.specification_type</code> table reference
     */
    public SpecificationType(Name alias) {
        this(alias, SPECIFICATION_TYPE);
    }

    /**
     * Create a <code>oagi.specification_type</code> table reference
     */
    public SpecificationType() {
        this(DSL.name("specification_type"), null);
    }

    public <O extends Record> SpecificationType(Table<O> path, ForeignKey<O, SpecificationTypeRecord> childPath, InverseForeignKey<O, SpecificationTypeRecord> parentPath) {
        super(path, childPath, parentPath, SPECIFICATION_TYPE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class SpecificationTypePath extends SpecificationType implements Path<SpecificationTypeRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> SpecificationTypePath(Table<O> path, ForeignKey<O, SpecificationTypeRecord> childPath, InverseForeignKey<O, SpecificationTypeRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private SpecificationTypePath(Name alias, Table<SpecificationTypeRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public SpecificationTypePath as(String alias) {
            return new SpecificationTypePath(DSL.name(alias), this);
        }

        @Override
        public SpecificationTypePath as(Name alias) {
            return new SpecificationTypePath(alias, this);
        }

        @Override
        public SpecificationTypePath as(Table<?> alias) {
            return new SpecificationTypePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<SpecificationTypeRecord, Long> getIdentity() {
        return (Identity<SpecificationTypeRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<SpecificationTypeRecord> getPrimaryKey() {
        return Keys.KEY_SPECIFICATION_TYPE_PRIMARY;
    }

    private transient SpecificationPath _specification;

    /**
     * Get the implicit to-many join path to the <code>oagi.specification</code>
     * table
     */
    public SpecificationPath specification() {
        if (_specification == null)
            _specification = new SpecificationPath(this, null, Keys.FKSPECIFICAT686471.getInverseKey());

        return _specification;
    }

    @Override
    public SpecificationType as(String alias) {
        return new SpecificationType(DSL.name(alias), this);
    }

    @Override
    public SpecificationType as(Name alias) {
        return new SpecificationType(alias, this);
    }

    @Override
    public SpecificationType as(Table<?> alias) {
        return new SpecificationType(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public SpecificationType rename(String name) {
        return new SpecificationType(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SpecificationType rename(Name name) {
        return new SpecificationType(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public SpecificationType rename(Table<?> name) {
        return new SpecificationType(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationType where(Condition condition) {
        return new SpecificationType(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationType where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationType where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationType where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SpecificationType where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SpecificationType where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SpecificationType where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public SpecificationType where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationType whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public SpecificationType whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
