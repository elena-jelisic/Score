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
import org.jooq.Function4;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row4;
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
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.AsccpManifestTagRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AsccpManifestTag extends TableImpl<AsccpManifestTagRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>oagi.asccp_manifest_tag</code>
     */
    public static final AsccpManifestTag ASCCP_MANIFEST_TAG = new AsccpManifestTag();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AsccpManifestTagRecord> getRecordType() {
        return AsccpManifestTagRecord.class;
    }

    /**
     * The column <code>oagi.asccp_manifest_tag.asccp_manifest_id</code>.
     */
    public final TableField<AsccpManifestTagRecord, ULong> ASCCP_MANIFEST_ID = createField(DSL.name("asccp_manifest_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.asccp_manifest_tag.tag_id</code>.
     */
    public final TableField<AsccpManifestTagRecord, ULong> TAG_ID = createField(DSL.name("tag_id"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>oagi.asccp_manifest_tag.created_by</code>. A foreign key
     * referring to the user who creates the record.
     */
    public final TableField<AsccpManifestTagRecord, ULong> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINTUNSIGNED.nullable(false), this, "A foreign key referring to the user who creates the record.");

    /**
     * The column <code>oagi.asccp_manifest_tag.creation_timestamp</code>.
     * Timestamp when the record was first created.
     */
    public final TableField<AsccpManifestTagRecord, LocalDateTime> CREATION_TIMESTAMP = createField(DSL.name("creation_timestamp"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "Timestamp when the record was first created.");

    private AsccpManifestTag(Name alias, Table<AsccpManifestTagRecord> aliased) {
        this(alias, aliased, null);
    }

    private AsccpManifestTag(Name alias, Table<AsccpManifestTagRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>oagi.asccp_manifest_tag</code> table reference
     */
    public AsccpManifestTag(String alias) {
        this(DSL.name(alias), ASCCP_MANIFEST_TAG);
    }

    /**
     * Create an aliased <code>oagi.asccp_manifest_tag</code> table reference
     */
    public AsccpManifestTag(Name alias) {
        this(alias, ASCCP_MANIFEST_TAG);
    }

    /**
     * Create a <code>oagi.asccp_manifest_tag</code> table reference
     */
    public AsccpManifestTag() {
        this(DSL.name("asccp_manifest_tag"), null);
    }

    public <O extends Record> AsccpManifestTag(Table<O> child, ForeignKey<O, AsccpManifestTagRecord> key) {
        super(child, key, ASCCP_MANIFEST_TAG);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Oagi.OAGI;
    }

    @Override
    public UniqueKey<AsccpManifestTagRecord> getPrimaryKey() {
        return Keys.KEY_ASCCP_MANIFEST_TAG_PRIMARY;
    }

    @Override
    public List<ForeignKey<AsccpManifestTagRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ASCCP_MANIFEST_TAG_ASCCP_MANIFEST_ID_FK, Keys.ASCCP_MANIFEST_TAG_TAG_ID_FK, Keys.ASCCP_MANIFEST_TAG_CREATED_BY_FK);
    }

    private transient AsccpManifest _asccpManifest;
    private transient Tag _tag;
    private transient AppUser _appUser;

    /**
     * Get the implicit join path to the <code>oagi.asccp_manifest</code> table.
     */
    public AsccpManifest asccpManifest() {
        if (_asccpManifest == null)
            _asccpManifest = new AsccpManifest(this, Keys.ASCCP_MANIFEST_TAG_ASCCP_MANIFEST_ID_FK);

        return _asccpManifest;
    }

    /**
     * Get the implicit join path to the <code>oagi.tag</code> table.
     */
    public Tag tag() {
        if (_tag == null)
            _tag = new Tag(this, Keys.ASCCP_MANIFEST_TAG_TAG_ID_FK);

        return _tag;
    }

    /**
     * Get the implicit join path to the <code>oagi.app_user</code> table.
     */
    public AppUser appUser() {
        if (_appUser == null)
            _appUser = new AppUser(this, Keys.ASCCP_MANIFEST_TAG_CREATED_BY_FK);

        return _appUser;
    }

    @Override
    public AsccpManifestTag as(String alias) {
        return new AsccpManifestTag(DSL.name(alias), this);
    }

    @Override
    public AsccpManifestTag as(Name alias) {
        return new AsccpManifestTag(alias, this);
    }

    @Override
    public AsccpManifestTag as(Table<?> alias) {
        return new AsccpManifestTag(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public AsccpManifestTag rename(String name) {
        return new AsccpManifestTag(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AsccpManifestTag rename(Name name) {
        return new AsccpManifestTag(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public AsccpManifestTag rename(Table<?> name) {
        return new AsccpManifestTag(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<ULong, ULong, ULong, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super ULong, ? super ULong, ? super ULong, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}