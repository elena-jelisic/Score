/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.SelectField;
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
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.OasExternalDocRecord;


/**
 * Allows referencing an external resource for extended documentation.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OasExternalDoc extends TableImpl<OasExternalDocRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.oas_external_doc</code>
     */
    public static final OasExternalDoc OAS_EXTERNAL_DOC = new OasExternalDoc();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OasExternalDocRecord> getRecordType() {
        return OasExternalDocRecord.class;
    }

    /**
     * The column <code>oagi.oas_external_doc.oas_external_doc_id</code>. The
     * primary key of the record.
     */
    public final TableField<OasExternalDocRecord, ULong> OAS_EXTERNAL_DOC_ID = createField(DSL.name("oas_external_doc_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "The primary key of the record.");

    /**
     * The column <code>oagi.oas_external_doc.url</code>. REQUIRED. The URL for
     * the target documentation. Value MUST be in the format of a URL.
     */
    public final TableField<OasExternalDocRecord, String> URL = createField(DSL.name("url"), SQLDataType.VARCHAR(250).nullable(false), this, "REQUIRED. The URL for the target documentation. Value MUST be in the format of a URL.");

    /**
     * The column <code>oagi.oas_external_doc.description</code>. A short
     * description of the target documentation. CommonMark syntax MAY be used
     * for rich text representation.
     */
    public final TableField<OasExternalDocRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.CLOB)), this, "A short description of the target documentation. CommonMark syntax MAY be used for rich text representation.");

    /**
     * The column <code>oagi.oas_external_doc.created_by</code>. The user who
     * creates the record.
     */
    public final TableField<OasExternalDocRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who creates the record.");

    /**
     * The column <code>oagi.oas_external_doc.last_updated_by</code>. The user
     * who last updates the record.
     */
    public final TableField<OasExternalDocRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who last updates the record.");

    /**
     * The column <code>oagi.oas_external_doc.creation_timestamp</code>. The
     * timestamp when the record is created.
     */
    public final TableField<OasExternalDocRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is created.");

    /**
     * The column <code>oagi.oas_external_doc.last_update_timestamp</code>. The
     * timestamp when the record is last updated.
     */
    public final TableField<OasExternalDocRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is last updated.");

    private OasExternalDoc(Name alias, Table<OasExternalDocRecord> aliased) {
        this(alias, aliased, null);
    }

    private OasExternalDoc(Name alias, Table<OasExternalDocRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Allows referencing an external resource for extended documentation."), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.oas_external_doc</code> table reference
     */
    public OasExternalDoc(String alias) {
        this(DSL.name(alias), OAS_EXTERNAL_DOC);
    }

    /**
     * Create an aliased <code>oagi.oas_external_doc</code> table reference
     */
    public OasExternalDoc(Name alias) {
        this(alias, OAS_EXTERNAL_DOC);
    }

    /**
     * Create a <code>oagi.oas_external_doc</code> table reference
     */
    public OasExternalDoc() {
        this(DSL.name("oas_external_doc"), null);
    }

    public <O extends Record> OasExternalDoc(Table<O> child, ForeignKey<O, OasExternalDocRecord> key) {
        super(child, key, OAS_EXTERNAL_DOC);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<OasExternalDocRecord, ULong> getIdentity() {
        return (Identity<OasExternalDocRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<OasExternalDocRecord> getPrimaryKey() {
        return Keys.KEY_OAS_EXTERNAL_DOC_PRIMARY;
    }

    @Override
    public List<ForeignKey<OasExternalDocRecord, ?>> getReferences() {
        return Arrays.asList(Keys.OAS_EXTERNAL_DOC_CREATED_BY_FK, Keys.OAS_EXTERNAL_DOC_LAST_UPDATED_BY_FK);
    }

    private transient AppUser _oasExternalDocCreatedByFk;
    private transient AppUser _oasExternalDocLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_external_doc_created_by_fk</code> key.
     */
    public AppUser oasExternalDocCreatedByFk() {
        if (_oasExternalDocCreatedByFk == null)
            _oasExternalDocCreatedByFk = new AppUser(this, Keys.OAS_EXTERNAL_DOC_CREATED_BY_FK);

        return _oasExternalDocCreatedByFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_external_doc_last_updated_by_fk</code> key.
     */
    public AppUser oasExternalDocLastUpdatedByFk() {
        if (_oasExternalDocLastUpdatedByFk == null)
            _oasExternalDocLastUpdatedByFk = new AppUser(this, Keys.OAS_EXTERNAL_DOC_LAST_UPDATED_BY_FK);

        return _oasExternalDocLastUpdatedByFk;
    }

    @Override
    public OasExternalDoc as(String alias) {
        return new OasExternalDoc(DSL.name(alias), this);
    }

    @Override
    public OasExternalDoc as(Name alias) {
        return new OasExternalDoc(alias, this);
    }

    @Override
    public OasExternalDoc as(Table<?> alias) {
        return new OasExternalDoc(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OasExternalDoc rename(String name) {
        return new OasExternalDoc(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasExternalDoc rename(Name name) {
        return new OasExternalDoc(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasExternalDoc rename(Table<?> name) {
        return new OasExternalDoc(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<ULong, String, String, ULong, ULong, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super ULong, ? super String, ? super String, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super ULong, ? super String, ? super String, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
