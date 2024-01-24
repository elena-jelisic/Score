/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
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
import org.oagi.score.repo.api.impl.jooq.entity.tables.AppUser.AppUserPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasDoc.OasDocPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasTag.OasTagPath;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.OasDocTagRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OasDocTag extends TableImpl<OasDocTagRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.oas_doc_tag</code>
     */
    public static final OasDocTag OAS_DOC_TAG = new OasDocTag();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OasDocTagRecord> getRecordType() {
        return OasDocTagRecord.class;
    }

    /**
     * The column <code>oagi.oas_doc_tag.oas_doc_id</code>. The primary key of
     * the record.
     */
    public final TableField<OasDocTagRecord, ULong> OAS_DOC_ID = createField(DSL.name("oas_doc_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The primary key of the record.");

    /**
     * The column <code>oagi.oas_doc_tag.oas_tag_id</code>. The primary key of
     * the record.
     */
    public final TableField<OasDocTagRecord, ULong> OAS_TAG_ID = createField(DSL.name("oas_tag_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The primary key of the record.");

    /**
     * The column <code>oagi.oas_doc_tag.created_by</code>. The user who creates
     * the record.
     */
    public final TableField<OasDocTagRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who creates the record.");

    /**
     * The column <code>oagi.oas_doc_tag.last_updated_by</code>. The user who
     * last updates the record.
     */
    public final TableField<OasDocTagRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who last updates the record.");

    /**
     * The column <code>oagi.oas_doc_tag.creation_timestamp</code>. The
     * timestamp when the record is created.
     */
    public final TableField<OasDocTagRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is created.");

    /**
     * The column <code>oagi.oas_doc_tag.last_update_timestamp</code>. The
     * timestamp when the record is last updated.
     */
    public final TableField<OasDocTagRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is last updated.");

    private OasDocTag(Name alias, Table<OasDocTagRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private OasDocTag(Name alias, Table<OasDocTagRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>oagi.oas_doc_tag</code> table reference
     */
    public OasDocTag(String alias) {
        this(DSL.name(alias), OAS_DOC_TAG);
    }

    /**
     * Create an aliased <code>oagi.oas_doc_tag</code> table reference
     */
    public OasDocTag(Name alias) {
        this(alias, OAS_DOC_TAG);
    }

    /**
     * Create a <code>oagi.oas_doc_tag</code> table reference
     */
    public OasDocTag() {
        this(DSL.name("oas_doc_tag"), null);
    }

    public <O extends Record> OasDocTag(Table<O> path, ForeignKey<O, OasDocTagRecord> childPath, InverseForeignKey<O, OasDocTagRecord> parentPath) {
        super(path, childPath, parentPath, OAS_DOC_TAG);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class OasDocTagPath extends OasDocTag implements Path<OasDocTagRecord> {
        public <O extends Record> OasDocTagPath(Table<O> path, ForeignKey<O, OasDocTagRecord> childPath, InverseForeignKey<O, OasDocTagRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private OasDocTagPath(Name alias, Table<OasDocTagRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public OasDocTagPath as(String alias) {
            return new OasDocTagPath(DSL.name(alias), this);
        }

        @Override
        public OasDocTagPath as(Name alias) {
            return new OasDocTagPath(alias, this);
        }

        @Override
        public OasDocTagPath as(Table<?> alias) {
            return new OasDocTagPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public UniqueKey<OasDocTagRecord> getPrimaryKey() {
        return Keys.KEY_OAS_DOC_TAG_PRIMARY;
    }

    @Override
    public List<ForeignKey<OasDocTagRecord, ?>> getReferences() {
        return Arrays.asList(Keys.OAS_DOC_TAG_OAS_DOC_ID_FK, Keys.OAS_DOC_TAG_OAS_TAG_ID_FK, Keys.OAS_DOC_TAG_CREATED_BY_FK, Keys.OAS_DOC_TAG_LAST_UPDATED_BY_FK);
    }

    private transient OasDocPath _oasDoc;

    /**
     * Get the implicit join path to the <code>oagi.oas_doc</code> table.
     */
    public OasDocPath oasDoc() {
        if (_oasDoc == null)
            _oasDoc = new OasDocPath(this, Keys.OAS_DOC_TAG_OAS_DOC_ID_FK, null);

        return _oasDoc;
    }

    private transient OasTagPath _oasTag;

    /**
     * Get the implicit join path to the <code>oagi.oas_tag</code> table.
     */
    public OasTagPath oasTag() {
        if (_oasTag == null)
            _oasTag = new OasTagPath(this, Keys.OAS_DOC_TAG_OAS_TAG_ID_FK, null);

        return _oasTag;
    }

    private transient AppUserPath _oasDocTagCreatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_doc_tag_created_by_fk</code> key.
     */
    public AppUserPath oasDocTagCreatedByFk() {
        if (_oasDocTagCreatedByFk == null)
            _oasDocTagCreatedByFk = new AppUserPath(this, Keys.OAS_DOC_TAG_CREATED_BY_FK, null);

        return _oasDocTagCreatedByFk;
    }

    private transient AppUserPath _oasDocTagLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_doc_tag_last_updated_by_fk</code> key.
     */
    public AppUserPath oasDocTagLastUpdatedByFk() {
        if (_oasDocTagLastUpdatedByFk == null)
            _oasDocTagLastUpdatedByFk = new AppUserPath(this, Keys.OAS_DOC_TAG_LAST_UPDATED_BY_FK, null);

        return _oasDocTagLastUpdatedByFk;
    }

    @Override
    public OasDocTag as(String alias) {
        return new OasDocTag(DSL.name(alias), this);
    }

    @Override
    public OasDocTag as(Name alias) {
        return new OasDocTag(alias, this);
    }

    @Override
    public OasDocTag as(Table<?> alias) {
        return new OasDocTag(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OasDocTag rename(String name) {
        return new OasDocTag(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasDocTag rename(Name name) {
        return new OasDocTag(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasDocTag rename(Table<?> name) {
        return new OasDocTag(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDocTag where(Condition condition) {
        return new OasDocTag(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDocTag where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDocTag where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDocTag where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasDocTag where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasDocTag where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasDocTag where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public OasDocTag where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDocTag whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public OasDocTag whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
