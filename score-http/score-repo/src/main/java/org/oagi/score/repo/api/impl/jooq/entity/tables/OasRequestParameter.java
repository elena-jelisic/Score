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
import org.jooq.Function6;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row6;
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
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.OasRequestParameterRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OasRequestParameter extends TableImpl<OasRequestParameterRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.oas_request_parameter</code>
     */
    public static final OasRequestParameter OAS_REQUEST_PARAMETER = new OasRequestParameter();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OasRequestParameterRecord> getRecordType() {
        return OasRequestParameterRecord.class;
    }

    /**
     * The column <code>oagi.oas_request_parameter.oas_parameter_id</code>. The
     * primary key of the record.
     */
    public final TableField<OasRequestParameterRecord, ULong> OAS_PARAMETER_ID = createField(DSL.name("oas_parameter_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The primary key of the record.");

    /**
     * The column <code>oagi.oas_request_parameter.oas_request_id</code>. The
     * primary key of the record.
     */
    public final TableField<OasRequestParameterRecord, ULong> OAS_REQUEST_ID = createField(DSL.name("oas_request_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The primary key of the record.");

    /**
     * The column <code>oagi.oas_request_parameter.created_by</code>. The user
     * who creates the record.
     */
    public final TableField<OasRequestParameterRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who creates the record.");

    /**
     * The column <code>oagi.oas_request_parameter.last_updated_by</code>. The
     * user who last updates the record.
     */
    public final TableField<OasRequestParameterRecord, ULong> LAST_UPDATED_BY = createField(DSL.name("last_updated_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "The user who last updates the record.");

    /**
     * The column <code>oagi.oas_request_parameter.creation_timestamp</code>.
     * The timestamp when the record is created.
     */
    public final TableField<OasRequestParameterRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is created.");

    /**
     * The column <code>oagi.oas_request_parameter.last_update_timestamp</code>.
     * The timestamp when the record is last updated.
     */
    public final TableField<OasRequestParameterRecord, LocalDateTime> LAST_UPDATE_TIMESTAMP = createField(DSL.name("last_update_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "The timestamp when the record is last updated.");

    private OasRequestParameter(Name alias, Table<OasRequestParameterRecord> aliased) {
        this(alias, aliased, null);
    }

    private OasRequestParameter(Name alias, Table<OasRequestParameterRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.oas_request_parameter</code> table reference
     */
    public OasRequestParameter(String alias) {
        this(DSL.name(alias), OAS_REQUEST_PARAMETER);
    }

    /**
     * Create an aliased <code>oagi.oas_request_parameter</code> table reference
     */
    public OasRequestParameter(Name alias) {
        this(alias, OAS_REQUEST_PARAMETER);
    }

    /**
     * Create a <code>oagi.oas_request_parameter</code> table reference
     */
    public OasRequestParameter() {
        this(DSL.name("oas_request_parameter"), null);
    }

    public <O extends Record> OasRequestParameter(Table<O> child, ForeignKey<O, OasRequestParameterRecord> key) {
        super(child, key, OAS_REQUEST_PARAMETER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public UniqueKey<OasRequestParameterRecord> getPrimaryKey() {
        return Keys.KEY_OAS_REQUEST_PARAMETER_PRIMARY;
    }

    @Override
    public List<ForeignKey<OasRequestParameterRecord, ?>> getReferences() {
        return Arrays.asList(Keys.OAS_REQUEST_PARAMETER_OAS_PARAMETER_ID_FK, Keys.OAS_REQUEST_PARAMETER_OAS_REQUEST_ID_FK, Keys.OAS_REQUEST_PARAMETER_CREATED_BY_FK, Keys.OAS_REQUEST_PARAMETER_LAST_UPDATED_BY_FK);
    }

    private transient OasParameter _oasParameter;
    private transient OasRequest _oasRequest;
    private transient AppUser _oasRequestParameterCreatedByFk;
    private transient AppUser _oasRequestParameterLastUpdatedByFk;

    /**
     * Get the implicit join path to the <code>oagi.oas_parameter</code> table.
     */
    public OasParameter oasParameter() {
        if (_oasParameter == null)
            _oasParameter = new OasParameter(this, Keys.OAS_REQUEST_PARAMETER_OAS_PARAMETER_ID_FK);

        return _oasParameter;
    }

    /**
     * Get the implicit join path to the <code>oagi.oas_request</code> table.
     */
    public OasRequest oasRequest() {
        if (_oasRequest == null)
            _oasRequest = new OasRequest(this, Keys.OAS_REQUEST_PARAMETER_OAS_REQUEST_ID_FK);

        return _oasRequest;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_request_parameter_created_by_fk</code> key.
     */
    public AppUser oasRequestParameterCreatedByFk() {
        if (_oasRequestParameterCreatedByFk == null)
            _oasRequestParameterCreatedByFk = new AppUser(this, Keys.OAS_REQUEST_PARAMETER_CREATED_BY_FK);

        return _oasRequestParameterCreatedByFk;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table, via
     * the <code>oas_request_parameter_last_updated_by_fk</code> key.
     */
    public AppUser oasRequestParameterLastUpdatedByFk() {
        if (_oasRequestParameterLastUpdatedByFk == null)
            _oasRequestParameterLastUpdatedByFk = new AppUser(this, Keys.OAS_REQUEST_PARAMETER_LAST_UPDATED_BY_FK);

        return _oasRequestParameterLastUpdatedByFk;
    }

    @Override
    public OasRequestParameter as(String alias) {
        return new OasRequestParameter(DSL.name(alias), this);
    }

    @Override
    public OasRequestParameter as(Name alias) {
        return new OasRequestParameter(alias, this);
    }

    @Override
    public OasRequestParameter as(Table<?> alias) {
        return new OasRequestParameter(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OasRequestParameter rename(String name) {
        return new OasRequestParameter(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasRequestParameter rename(Name name) {
        return new OasRequestParameter(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OasRequestParameter rename(Table<?> name) {
        return new OasRequestParameter(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<ULong, ULong, ULong, ULong, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function6<? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function6<? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}