/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.OasDoc;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OasDocRecord extends UpdatableRecordImpl<OasDocRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.oas_doc.oas_doc_id</code>. The primary key of the
     * record.
     */
    public void setOasDocId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.oas_doc_id</code>. The primary key of the
     * record.
     */
    public ULong getOasDocId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.oas_doc.guid</code>. The GUID of the record.
     */
    public void setGuid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.guid</code>. The GUID of the record.
     */
    public String getGuid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>oagi.oas_doc.open_api_version</code>. REQUIRED. This
     * string MUST be the semantic version number of the OpenAPI Specification
     * version that the OpenAPI document uses. The openapi field SHOULD be used
     * by tooling specifications and clients to interpret the OpenAPI document.
     * This is not related to the API info.version string.
     */
    public void setOpenApiVersion(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.open_api_version</code>. REQUIRED. This
     * string MUST be the semantic version number of the OpenAPI Specification
     * version that the OpenAPI document uses. The openapi field SHOULD be used
     * by tooling specifications and clients to interpret the OpenAPI document.
     * This is not related to the API info.version string.
     */
    public String getOpenApiVersion() {
        return (String) get(2);
    }

    /**
     * Setter for <code>oagi.oas_doc.title</code>. The title of the API.
     */
    public void setTitle(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.title</code>. The title of the API.
     */
    public String getTitle() {
        return (String) get(3);
    }

    /**
     * Setter for <code>oagi.oas_doc.description</code>. A short description of
     * the API. CommonMark syntax MAY be used for rich text representation.
     */
    public void setDescription(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.description</code>. A short description of
     * the API. CommonMark syntax MAY be used for rich text representation.
     */
    public String getDescription() {
        return (String) get(4);
    }

    /**
     * Setter for <code>oagi.oas_doc.terms_of_service</code>. A URL to the Terms
     * of Service for the API. MUST be in the format of a URL.
     */
    public void setTermsOfService(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.terms_of_service</code>. A URL to the Terms
     * of Service for the API. MUST be in the format of a URL.
     */
    public String getTermsOfService() {
        return (String) get(5);
    }

    /**
     * Setter for <code>oagi.oas_doc.version</code>. REQUIRED. The version of
     * the OpenAPI document (which is distinct from the OpenAPI Specification
     * version or the API implementation version).
     */
    public void setVersion(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.version</code>. REQUIRED. The version of
     * the OpenAPI document (which is distinct from the OpenAPI Specification
     * version or the API implementation version).
     */
    public String getVersion() {
        return (String) get(6);
    }

    /**
     * Setter for <code>oagi.oas_doc.contact_name</code>. The identifying name
     * of the contact person/organization.
     */
    public void setContactName(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.contact_name</code>. The identifying name
     * of the contact person/organization.
     */
    public String getContactName() {
        return (String) get(7);
    }

    /**
     * Setter for <code>oagi.oas_doc.contact_url</code>. The URL pointing to the
     * contact information. MUST be in the format of a URL.
     */
    public void setContactUrl(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.contact_url</code>. The URL pointing to the
     * contact information. MUST be in the format of a URL.
     */
    public String getContactUrl() {
        return (String) get(8);
    }

    /**
     * Setter for <code>oagi.oas_doc.contact_email</code>. The email address of
     * the contact person/organization. MUST be in the format of an email
     * address.
     */
    public void setContactEmail(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.contact_email</code>. The email address of
     * the contact person/organization. MUST be in the format of an email
     * address.
     */
    public String getContactEmail() {
        return (String) get(9);
    }

    /**
     * Setter for <code>oagi.oas_doc.license_name</code>. REQUIRED if the
     * license used for the API. The license name used for the API.
     */
    public void setLicenseName(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.license_name</code>. REQUIRED if the
     * license used for the API. The license name used for the API.
     */
    public String getLicenseName() {
        return (String) get(10);
    }

    /**
     * Setter for <code>oagi.oas_doc.license_url</code>. A URL to the license
     * used for the API. MUST be in the format of a URL.
     */
    public void setLicenseUrl(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.license_url</code>. A URL to the license
     * used for the API. MUST be in the format of a URL.
     */
    public String getLicenseUrl() {
        return (String) get(11);
    }

    /**
     * Setter for <code>oagi.oas_doc.owner_user_id</code>. The user who owns the
     * record.
     */
    public void setOwnerUserId(ULong value) {
        set(12, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.owner_user_id</code>. The user who owns the
     * record.
     */
    public ULong getOwnerUserId() {
        return (ULong) get(12);
    }

    /**
     * Setter for <code>oagi.oas_doc.created_by</code>. The user who creates the
     * record.
     */
    public void setCreatedBy(ULong value) {
        set(13, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.created_by</code>. The user who creates the
     * record.
     */
    public ULong getCreatedBy() {
        return (ULong) get(13);
    }

    /**
     * Setter for <code>oagi.oas_doc.last_updated_by</code>. The user who last
     * updates the record.
     */
    public void setLastUpdatedBy(ULong value) {
        set(14, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.last_updated_by</code>. The user who last
     * updates the record.
     */
    public ULong getLastUpdatedBy() {
        return (ULong) get(14);
    }

    /**
     * Setter for <code>oagi.oas_doc.creation_timestamp</code>. The timestamp
     * when the record is created.
     */
    public void setCreationTimestamp(LocalDateTime value) {
        set(15, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.creation_timestamp</code>. The timestamp
     * when the record is created.
     */
    public LocalDateTime getCreationTimestamp() {
        return (LocalDateTime) get(15);
    }

    /**
     * Setter for <code>oagi.oas_doc.last_update_timestamp</code>. The timestamp
     * when the record is last updated.
     */
    public void setLastUpdateTimestamp(LocalDateTime value) {
        set(16, value);
    }

    /**
     * Getter for <code>oagi.oas_doc.last_update_timestamp</code>. The timestamp
     * when the record is last updated.
     */
    public LocalDateTime getLastUpdateTimestamp() {
        return (LocalDateTime) get(16);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OasDocRecord
     */
    public OasDocRecord() {
        super(OasDoc.OAS_DOC);
    }

    /**
     * Create a detached, initialised OasDocRecord
     */
    public OasDocRecord(ULong oasDocId, String guid, String openApiVersion, String title, String description, String termsOfService, String version, String contactName, String contactUrl, String contactEmail, String licenseName, String licenseUrl, ULong ownerUserId, ULong createdBy, ULong lastUpdatedBy, LocalDateTime creationTimestamp, LocalDateTime lastUpdateTimestamp) {
        super(OasDoc.OAS_DOC);

        setOasDocId(oasDocId);
        setGuid(guid);
        setOpenApiVersion(openApiVersion);
        setTitle(title);
        setDescription(description);
        setTermsOfService(termsOfService);
        setVersion(version);
        setContactName(contactName);
        setContactUrl(contactUrl);
        setContactEmail(contactEmail);
        setLicenseName(licenseName);
        setLicenseUrl(licenseUrl);
        setOwnerUserId(ownerUserId);
        setCreatedBy(createdBy);
        setLastUpdatedBy(lastUpdatedBy);
        setCreationTimestamp(creationTimestamp);
        setLastUpdateTimestamp(lastUpdateTimestamp);
        resetChangedOnNotNull();
    }
}
