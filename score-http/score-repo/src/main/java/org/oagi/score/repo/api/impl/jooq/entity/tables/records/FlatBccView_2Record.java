/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import org.jooq.impl.TableRecordImpl;
import org.oagi.score.repo.api.impl.jooq.entity.tables.FlatBccView_2;


/**
 * VIEW
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FlatBccView_2Record extends TableRecordImpl<FlatBccView_2Record> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.flat_bcc_view_2.object_class_term</code>. Object
     * class name of the ACC concept. For OAGIS, this is generally name of a
     * type with the "Type" truncated from the end. Per CCS the name is space
     * separated. "ID" is expanded to "Identifier".
     */
    public void setObjectClassTerm(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.flat_bcc_view_2.object_class_term</code>. Object
     * class name of the ACC concept. For OAGIS, this is generally name of a
     * type with the "Type" truncated from the end. Per CCS the name is space
     * separated. "ID" is expanded to "Identifier".
     */
    public String getObjectClassTerm() {
        return (String) get(0);
    }

    /**
     * Setter for <code>oagi.flat_bcc_view_2.property</code>.
     */
    public void setProperty(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.flat_bcc_view_2.property</code>.
     */
    public String getProperty() {
        return (String) get(1);
    }

    /**
     * Setter for <code>oagi.flat_bcc_view_2.path</code>.
     */
    public void setPath(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.flat_bcc_view_2.path</code>.
     */
    public String getPath() {
        return (String) get(2);
    }

    /**
     * Setter for <code>oagi.flat_bcc_view_2.super_bcc_id</code>.
     */
    public void setSuperBccId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.flat_bcc_view_2.super_bcc_id</code>.
     */
    public Long getSuperBccId() {
        return (Long) get(3);
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FlatBccView_2Record
     */
    public FlatBccView_2Record() {
        super(FlatBccView_2.FLAT_BCC_VIEW_2);
    }

    /**
     * Create a detached, initialised FlatBccView_2Record
     */
    public FlatBccView_2Record(String objectClassTerm, String property, String path, Long superBccId) {
        super(FlatBccView_2.FLAT_BCC_VIEW_2);

        setObjectClassTerm(objectClassTerm);
        setProperty(property);
        setPath(path);
        setSuperBccId(superBccId);
        resetChangedOnNotNull();
    }
}
