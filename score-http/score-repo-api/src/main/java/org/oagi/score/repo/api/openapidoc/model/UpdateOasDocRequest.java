package org.oagi.score.repo.api.openapidoc.model;

import org.oagi.score.repo.api.base.Request;
import org.oagi.score.repo.api.user.model.ScoreUser;

public class UpdateOasDocRequest extends Request {
    private String oasDocId;
    private String openAPIVersion;
    private String title;
    private String description;
    private String termsOfService;
    private String version;
    private String contactName;
    private String contactUrl;
    private String contactEmail;
    private String licenseName;
    private String licenseUrl;
    private String ownerUserId;

    public UpdateOasDocRequest(ScoreUser requester) {
        super(requester);
    }

    public UpdateOasDocRequest withOasDocId(String oasDocId) {
        this.setOasDocId(oasDocId);
        return this;
    }

    public String getOasDocId() {
        return oasDocId;
    }

    public void setOasDocId(String oasDocId) {
        this.oasDocId = oasDocId;
    }

    public String getOpenAPIVersion() {
        return openAPIVersion;
    }

    public void setOpenAPIVersion(String openAPIVersion) {
        this.openAPIVersion = openAPIVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    @Override
    public String toString() {
        return "UpdateOasDocRequest{" +
                "oasDocId='" + oasDocId + '\'' +
                ", openAPIVersion='" + openAPIVersion + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", termsOfService='" + termsOfService + '\'' +
                ", version='" + version + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactUrl='" + contactUrl + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", licenseName='" + licenseName + '\'' +
                ", licenseUrl='" + licenseUrl + '\'' +
                ", ownerUserId='" + ownerUserId + '\'' +
                '}';
    }
}
