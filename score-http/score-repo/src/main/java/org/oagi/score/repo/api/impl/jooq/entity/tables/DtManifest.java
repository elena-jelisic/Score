/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row9;
import org.jooq.Schema;
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
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.DtManifestRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DtManifest extends TableImpl<DtManifestRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.dt_manifest</code>
     */
    public static final DtManifest DT_MANIFEST = new DtManifest();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DtManifestRecord> getRecordType() {
        return DtManifestRecord.class;
    }

    /**
     * The column <code>oagi.dt_manifest.dt_manifest_id</code>.
     */
    public final TableField<DtManifestRecord, ULong> DT_MANIFEST_ID = createField(DSL.name("dt_manifest_id"), SQLDataType.BIGINTUNSIGNED.nullable(false).identity(true), this, "");

    /**
     * The column <code>oagi.dt_manifest.release_id</code>.
     */
    public final TableField<DtManifestRecord, ULong> RELEASE_ID = createField(DSL.name("release_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.dt_manifest.dt_id</code>.
     */
    public final TableField<DtManifestRecord, ULong> DT_ID = createField(DSL.name("dt_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.dt_manifest.based_dt_manifest_id</code>.
     */
    public final TableField<DtManifestRecord, ULong> BASED_DT_MANIFEST_ID = createField(DSL.name("based_dt_manifest_id"), SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column <code>oagi.dt_manifest.conflict</code>. This indicates that there is a conflict between self and relationship.
     */
    public final TableField<DtManifestRecord, Byte> CONFLICT = createField(DSL.name("conflict"), SQLDataType.TINYINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.TINYINT)), this, "This indicates that there is a conflict between self and relationship.");

    /**
     * The column <code>oagi.dt_manifest.log_id</code>. A foreign key pointed to a log for the current record.
     */
    public final TableField<DtManifestRecord, ULong> LOG_ID = createField(DSL.name("log_id"), SQLDataType.BIGINTUNSIGNED, this, "A foreign key pointed to a log for the current record.");

    /**
     * The column <code>oagi.dt_manifest.replacement_dt_manifest_id</code>. This refers to a replacement manifest if the record is deprecated.
     */
    public final TableField<DtManifestRecord, ULong> REPLACEMENT_DT_MANIFEST_ID = createField(DSL.name("replacement_dt_manifest_id"), SQLDataType.BIGINTUNSIGNED, this, "This refers to a replacement manifest if the record is deprecated.");

    /**
     * The column <code>oagi.dt_manifest.prev_dt_manifest_id</code>.
     */
    public final TableField<DtManifestRecord, ULong> PREV_DT_MANIFEST_ID = createField(DSL.name("prev_dt_manifest_id"), SQLDataType.BIGINTUNSIGNED, this, "");

    /**
     * The column <code>oagi.dt_manifest.next_dt_manifest_id</code>.
     */
    public final TableField<DtManifestRecord, ULong> NEXT_DT_MANIFEST_ID = createField(DSL.name("next_dt_manifest_id"), SQLDataType.BIGINTUNSIGNED, this, "");

    private DtManifest(Name alias, Table<DtManifestRecord> aliased) {
        this(alias, aliased, null);
    }

    private DtManifest(Name alias, Table<DtManifestRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.dt_manifest</code> table reference
     */
    public DtManifest(String alias) {
        this(DSL.name(alias), DT_MANIFEST);
    }

    /**
     * Create an aliased <code>oagi.dt_manifest</code> table reference
     */
    public DtManifest(Name alias) {
        this(alias, DT_MANIFEST);
    }

    /**
     * Create a <code>oagi.dt_manifest</code> table reference
     */
    public DtManifest() {
        this(DSL.name("dt_manifest"), null);
    }

    public <O extends Record> DtManifest(Table<O> child, ForeignKey<O, DtManifestRecord> key) {
        super(child, key, DT_MANIFEST);
    }

    @Override
    public Schema getSchema() {
        return Oagi.OAGI;
    }

    @Override
    public Identity<DtManifestRecord, ULong> getIdentity() {
        return (Identity<DtManifestRecord, ULong>) super.getIdentity();
    }

    @Override
    public UniqueKey<DtManifestRecord> getPrimaryKey() {
        return Keys.KEY_DT_MANIFEST_PRIMARY;
    }

    @Override
    public List<UniqueKey<DtManifestRecord>> getKeys() {
        return Arrays.<UniqueKey<DtManifestRecord>>asList(Keys.KEY_DT_MANIFEST_PRIMARY);
    }

    @Override
    public List<ForeignKey<DtManifestRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<DtManifestRecord, ?>>asList(Keys.DT_MANIFEST_RELEASE_ID_FK, Keys.DT_MANIFEST_DT_ID_FK, Keys.DT_MANIFEST_BASED_DT_MANIFEST_ID_FK, Keys.DT_MANIFEST_LOG_ID_FK, Keys.DT_REPLACEMENT_DT_MANIFEST_ID_FK, Keys.DT_MANIFEST_PREV_DT_MANIFEST_ID_FK, Keys.DT_MANIFEST_NEXT_DT_MANIFEST_ID_FK);
    }

    public Release release() {
        return new Release(this, Keys.DT_MANIFEST_RELEASE_ID_FK);
    }

    public Dt dt() {
        return new Dt(this, Keys.DT_MANIFEST_DT_ID_FK);
    }

    public DtManifest dtManifestBasedDtManifestIdFk() {
        return new DtManifest(this, Keys.DT_MANIFEST_BASED_DT_MANIFEST_ID_FK);
    }

    public Log log() {
        return new Log(this, Keys.DT_MANIFEST_LOG_ID_FK);
    }

    public DtManifest dtReplacementDtManifestIdFk() {
        return new DtManifest(this, Keys.DT_REPLACEMENT_DT_MANIFEST_ID_FK);
    }

    public DtManifest dtManifestPrevDtManifestIdFk() {
        return new DtManifest(this, Keys.DT_MANIFEST_PREV_DT_MANIFEST_ID_FK);
    }

    public DtManifest dtManifestNextDtManifestIdFk() {
        return new DtManifest(this, Keys.DT_MANIFEST_NEXT_DT_MANIFEST_ID_FK);
    }

    @Override
    public DtManifest as(String alias) {
        return new DtManifest(DSL.name(alias), this);
    }

    @Override
    public DtManifest as(Name alias) {
        return new DtManifest(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DtManifest rename(String name) {
        return new DtManifest(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DtManifest rename(Name name) {
        return new DtManifest(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<ULong, ULong, ULong, ULong, Byte, ULong, ULong, ULong, ULong> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}