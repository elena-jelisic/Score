/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables;


import java.util.Collection;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
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
import org.oagi.score.e2e.impl.api.jooq.entity.Keys;
import org.oagi.score.e2e.impl.api.jooq.entity.Oagi;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.TextTemplateRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TextTemplate extends TableImpl<TextTemplateRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.text_template</code>
     */
    public static final TextTemplate TEXT_TEMPLATE = new TextTemplate();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TextTemplateRecord> getRecordType() {
        return TextTemplateRecord.class;
    }

    /**
     * The column <code>oagi.text_template.text_template_id</code>.
     */
    public final TableField<TextTemplateRecord, ULong> TEXT_TEMPLATE_ID = createField(DSL.name("text_template_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.text_template.name</code>.
     */
    public final TableField<TextTemplateRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).nullable(false).defaultValue(DSL.field(DSL.raw("''"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>oagi.text_template.subject</code>.
     */
    public final TableField<TextTemplateRecord, String> SUBJECT = createField(DSL.name("subject"), SQLDataType.VARCHAR(200).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>oagi.text_template.content_type</code>.
     */
    public final TableField<TextTemplateRecord, String> CONTENT_TYPE = createField(DSL.name("content_type"), SQLDataType.VARCHAR(100).defaultValue(DSL.field(DSL.raw("'text/plain'"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>oagi.text_template.template</code>.
     */
    public final TableField<TextTemplateRecord, String> TEMPLATE = createField(DSL.name("template"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "");

    private TextTemplate(Name alias, Table<TextTemplateRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private TextTemplate(Name alias, Table<TextTemplateRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.text_template</code> table reference
     */
    public TextTemplate(String alias) {
        this(DSL.name(alias), TEXT_TEMPLATE);
    }

    /**
     * Create an aliased <code>oagi.text_template</code> table reference
     */
    public TextTemplate(Name alias) {
        this(alias, TEXT_TEMPLATE);
    }

    /**
     * Create a <code>oagi.text_template</code> table reference
     */
    public TextTemplate() {
        this(DSL.name("text_template"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<TextTemplateRecord, ULong> getIdentity() {
        return (Identity<TextTemplateRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<TextTemplateRecord> getPrimaryKey() {
        return Keys.KEY_TEXT_TEMPLATE_PRIMARY;
    }

    @Override
    public TextTemplate as(String alias) {
        return new TextTemplate(DSL.name(alias), this);
    }

    @Override
    public TextTemplate as(Name alias) {
        return new TextTemplate(alias, this);
    }

    @Override
    public TextTemplate as(Table<?> alias) {
        return new TextTemplate(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public TextTemplate rename(String name) {
        return new TextTemplate(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TextTemplate rename(Name name) {
        return new TextTemplate(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public TextTemplate rename(Table<?> name) {
        return new TextTemplate(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextTemplate where(Condition condition) {
        return new TextTemplate(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextTemplate where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextTemplate where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextTemplate where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TextTemplate where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TextTemplate where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TextTemplate where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public TextTemplate where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextTemplate whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public TextTemplate whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}