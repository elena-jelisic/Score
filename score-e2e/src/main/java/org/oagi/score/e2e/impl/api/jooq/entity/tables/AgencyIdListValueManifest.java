/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.e2e.impl.api.jooq.entity.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function9;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row9;
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
import org.oagi.score.e2e.impl.api.jooq.entity.Keys;
import org.oagi.score.e2e.impl.api.jooq.entity.Oagi;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.AgencyIdListValueManifestRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AgencyIdListValueManifest extends TableImpl<AgencyIdListValueManifestRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.agency_id_list_value_manifest</code>
     */
    public static final AgencyIdListValueManifest AGENCY_ID_LIST_VALUE_MANIFEST = new AgencyIdListValueManifest();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AgencyIdListValueManifestRecord> getRecordType() {
        return AgencyIdListValueManifestRecord.class;
    }

    /**
     * The column
     * <code>oagi.agency_id_list_value_manifest.agency_id_list_value_manifest_id</code>.
     */
    public final TableField<AgencyIdListValueManifestRecord, ULong> AGENCY_ID_LIST_VALUE_MANIFEST_ID = createField(DSL.name("agency_id_list_value_manifest_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.agency_id_list_value_manifest.release_id</code>.
     */
    public final TableField<AgencyIdListValueManifestRecord, ULong> RELEASE_ID = createField(DSL.name("release_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column
     * <code>oagi.agency_id_list_value_manifest.agency_id_list_value_id</code>.
     */
    public final TableField<AgencyIdListValueManifestRecord, ULong> AGENCY_ID_LIST_VALUE_ID = createField(DSL.name("agency_id_list_value_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column
     * <code>oagi.agency_id_list_value_manifest.agency_id_list_manifest_id</code>.
     */
    public final TableField<AgencyIdListValueManifestRecord, ULong> AGENCY_ID_LIST_MANIFEST_ID = createField(DSL.name("agency_id_list_manifest_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column
     * <code>oagi.agency_id_list_value_manifest.based_agency_id_list_value_manifest_id</code>.
     */
    public final TableField<AgencyIdListValueManifestRecord, ULong> BASED_AGENCY_ID_LIST_VALUE_MANIFEST_ID = createField(DSL.name("based_agency_id_list_value_manifest_id"), SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column <code>oagi.agency_id_list_value_manifest.conflict</code>. This
     * indicates that there is a conflict between self and relationship.
     */
    public final TableField<AgencyIdListValueManifestRecord, Byte> CONFLICT = createField(DSL.name("conflict"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.TINYINT)), this, "This indicates that there is a conflict between self and relationship.");

    /**
     * The column
     * <code>oagi.agency_id_list_value_manifest.replacement_agency_id_list_value_manifest_id</code>.
     * This refers to a replacement manifest if the record is deprecated.
     */
    public final TableField<AgencyIdListValueManifestRecord, ULong> REPLACEMENT_AGENCY_ID_LIST_VALUE_MANIFEST_ID = createField(DSL.name("replacement_agency_id_list_value_manifest_id"), SQLDataType.BIGINTUNSIGNED, this, "This refers to a replacement manifest if the record is deprecated.");

    /**
     * The column
     * <code>oagi.agency_id_list_value_manifest.prev_agency_id_list_value_manifest_id</code>.
     */
    public final TableField<AgencyIdListValueManifestRecord, ULong> PREV_AGENCY_ID_LIST_VALUE_MANIFEST_ID = createField(DSL.name("prev_agency_id_list_value_manifest_id"), SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column
     * <code>oagi.agency_id_list_value_manifest.next_agency_id_list_value_manifest_id</code>.
     */
    public final TableField<AgencyIdListValueManifestRecord, ULong> NEXT_AGENCY_ID_LIST_VALUE_MANIFEST_ID = createField(DSL.name("next_agency_id_list_value_manifest_id"), SQLDataType.BIGINTUNSIGNED, this, "");

    private AgencyIdListValueManifest(Name alias, Table<AgencyIdListValueManifestRecord> aliased) {
        this(alias, aliased, null);
    }

    private AgencyIdListValueManifest(Name alias, Table<AgencyIdListValueManifestRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.agency_id_list_value_manifest</code> table
     * reference
     */
    public AgencyIdListValueManifest(String alias) {
        this(DSL.name(alias), AGENCY_ID_LIST_VALUE_MANIFEST);
    }

    /**
     * Create an aliased <code>oagi.agency_id_list_value_manifest</code> table
     * reference
     */
    public AgencyIdListValueManifest(Name alias) {
        this(alias, AGENCY_ID_LIST_VALUE_MANIFEST);
    }

    /**
     * Create a <code>oagi.agency_id_list_value_manifest</code> table reference
     */
    public AgencyIdListValueManifest() {
        this(DSL.name("agency_id_list_value_manifest"), null);
    }

    public <O extends Record> AgencyIdListValueManifest(Table<O> child, ForeignKey<O, AgencyIdListValueManifestRecord> key) {
        super(child, key, AGENCY_ID_LIST_VALUE_MANIFEST);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public Identity<AgencyIdListValueManifestRecord, ULong> getIdentity() {
        return (Identity<AgencyIdListValueManifestRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<AgencyIdListValueManifestRecord> getPrimaryKey() {
        return Keys.KEY_AGENCY_ID_LIST_VALUE_MANIFEST_PRIMARY;
    }

    @Override
    public List<ForeignKey<AgencyIdListValueManifestRecord, ?>> getReferences() {
        return Arrays.asList(Keys.AGENCY_ID_LIST_VALUE_MANIFEST_RELEASE_ID_FK, Keys.AGENCY_ID_LIST_VALUE_MANIFEST_AGENCY_ID_LIST_VALUE_ID_FK, Keys.AGENCY_ID_LIST_VALUE_MANIFEST_AGENCY_ID_LIST_MANIFEST_ID_FK, Keys.AGENCY_ID_LIST_VALUE_MANIFEST_BASED_AGENCY_ID_LIST_VAL_MNF_ID_FK, Keys.AGENCY_ID_LIST_VALUE_REPLACEMENT_AGENCY_ID_LIST_MANIF_FK, Keys.AGENCY_ID_LIST_VALUE_MANIFEST_PREV_AGENCY_ID_LIST_VALUE_MANIF_FK, Keys.AGENCY_ID_LIST_VALUE_MANIFEST_NEXT_AGENCY_ID_LIST_VALUE_MANIF_FK);
    }

    private transient Release _release;
    private transient AgencyIdListValue _agencyIdListValue;
    private transient AgencyIdListManifest _agencyIdListManifest;
    private transient AgencyIdListValueManifest _agencyIdListValueManifestBasedAgencyIdListValMnfIdFk;
    private transient AgencyIdListValueManifest _agencyIdListValueReplacementAgencyIdListManifFk;
    private transient AgencyIdListValueManifest _agencyIdListValueManifestPrevAgencyIdListValueManifFk;
    private transient AgencyIdListValueManifest _agencyIdListValueManifestNextAgencyIdListValueManifFk;

    /**
     * Get the implicit join path to the <code>oagi.release</code> table.
     */
    public Release release() {
        if (_release == null)
            _release = new Release(this, Keys.AGENCY_ID_LIST_VALUE_MANIFEST_RELEASE_ID_FK);

        return _release;
    }

    /**
     * Get the implicit join path to the <code>oagi.agency_id_list_value</code>
     * table.
     */
    public AgencyIdListValue agencyIdListValue() {
        if (_agencyIdListValue == null)
            _agencyIdListValue = new AgencyIdListValue(this, Keys.AGENCY_ID_LIST_VALUE_MANIFEST_AGENCY_ID_LIST_VALUE_ID_FK);

        return _agencyIdListValue;
    }

    /**
     * Get the implicit join path to the
     * <code>oagi.agency_id_list_manifest</code> table.
     */
    public AgencyIdListManifest agencyIdListManifest() {
        if (_agencyIdListManifest == null)
            _agencyIdListManifest = new AgencyIdListManifest(this, Keys.AGENCY_ID_LIST_VALUE_MANIFEST_AGENCY_ID_LIST_MANIFEST_ID_FK);

        return _agencyIdListManifest;
    }

    /**
     * Get the implicit join path to the
     * <code>oagi.agency_id_list_value_manifest</code> table, via the
     * <code>agency_id_list_value_manifest_based_agency_id_list_val_mnf_id_fk</code>
     * key.
     */
    public AgencyIdListValueManifest agencyIdListValueManifestBasedAgencyIdListValMnfIdFk() {
        if (_agencyIdListValueManifestBasedAgencyIdListValMnfIdFk == null)
            _agencyIdListValueManifestBasedAgencyIdListValMnfIdFk = new AgencyIdListValueManifest(this, Keys.AGENCY_ID_LIST_VALUE_MANIFEST_BASED_AGENCY_ID_LIST_VAL_MNF_ID_FK);

        return _agencyIdListValueManifestBasedAgencyIdListValMnfIdFk;
    }

    /**
     * Get the implicit join path to the
     * <code>oagi.agency_id_list_value_manifest</code> table, via the
     * <code>agency_id_list_value_replacement_agency_id_list_manif_fk</code>
     * key.
     */
    public AgencyIdListValueManifest agencyIdListValueReplacementAgencyIdListManifFk() {
        if (_agencyIdListValueReplacementAgencyIdListManifFk == null)
            _agencyIdListValueReplacementAgencyIdListManifFk = new AgencyIdListValueManifest(this, Keys.AGENCY_ID_LIST_VALUE_REPLACEMENT_AGENCY_ID_LIST_MANIF_FK);

        return _agencyIdListValueReplacementAgencyIdListManifFk;
    }

    /**
     * Get the implicit join path to the
     * <code>oagi.agency_id_list_value_manifest</code> table, via the
     * <code>agency_id_list_value_manifest_prev_agency_id_list_value_manif_fk</code>
     * key.
     */
    public AgencyIdListValueManifest agencyIdListValueManifestPrevAgencyIdListValueManifFk() {
        if (_agencyIdListValueManifestPrevAgencyIdListValueManifFk == null)
            _agencyIdListValueManifestPrevAgencyIdListValueManifFk = new AgencyIdListValueManifest(this, Keys.AGENCY_ID_LIST_VALUE_MANIFEST_PREV_AGENCY_ID_LIST_VALUE_MANIF_FK);

        return _agencyIdListValueManifestPrevAgencyIdListValueManifFk;
    }

    /**
     * Get the implicit join path to the
     * <code>oagi.agency_id_list_value_manifest</code> table, via the
     * <code>agency_id_list_value_manifest_next_agency_id_list_value_manif_fk</code>
     * key.
     */
    public AgencyIdListValueManifest agencyIdListValueManifestNextAgencyIdListValueManifFk() {
        if (_agencyIdListValueManifestNextAgencyIdListValueManifFk == null)
            _agencyIdListValueManifestNextAgencyIdListValueManifFk = new AgencyIdListValueManifest(this, Keys.AGENCY_ID_LIST_VALUE_MANIFEST_NEXT_AGENCY_ID_LIST_VALUE_MANIF_FK);

        return _agencyIdListValueManifestNextAgencyIdListValueManifFk;
    }

    @Override
    public AgencyIdListValueManifest as(String alias) {
        return new AgencyIdListValueManifest(DSL.name(alias), this);
    }

    @Override
    public AgencyIdListValueManifest as(Name alias) {
        return new AgencyIdListValueManifest(alias, this);
    }

    @Override
    public AgencyIdListValueManifest as(Table<?> alias) {
        return new AgencyIdListValueManifest(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public AgencyIdListValueManifest rename(String name) {
        return new AgencyIdListValueManifest(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AgencyIdListValueManifest rename(Name name) {
        return new AgencyIdListValueManifest(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public AgencyIdListValueManifest rename(Table<?> name) {
        return new AgencyIdListValueManifest(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<ULong, ULong, ULong, ULong, ULong, Byte, ULong, ULong, ULong> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function9<? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super Byte, ? super ULong, ? super ULong, ? super ULong, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function9<? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super ULong, ? super Byte, ? super ULong, ? super ULong, ? super ULong, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
