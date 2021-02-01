/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record20;
import org.jooq.Row20;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.AgencyIdList;


/**
 * The AGENCY_ID_LIST table stores information about agency identification 
 * lists. The list's values are however kept in the AGENCY_ID_LIST_VALUE.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AgencyIdListRecord extends UpdatableRecordImpl<AgencyIdListRecord> implements Record20<ULong, String, String, String, String, ULong, String, ULong, String, ULong, ULong, ULong, LocalDateTime, LocalDateTime, String, Byte, ULong, ULong, ULong, ULong> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>oagi.agency_id_list.agency_id_list_id</code>. A internal, primary database key.
     */
    public void setAgencyIdListId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.agency_id_list_id</code>. A internal, primary database key.
     */
    public ULong getAgencyIdListId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>oagi.agency_id_list.guid</code>. A globally unique identifier (GUID).
     */
    public void setGuid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.guid</code>. A globally unique identifier (GUID).
     */
    public String getGuid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>oagi.agency_id_list.enum_type_guid</code>. This column stores the GUID of the type containing the enumerated values. In OAGIS, most code lists and agnecy ID lists are defined by an XyzCodeContentType (or XyzAgencyIdentificationContentType) and XyzCodeEnumerationType (or XyzAgencyIdentificationEnumerationContentType). However, some don't have the enumeration type. When that is the case, this column is null.
     */
    public void setEnumTypeGuid(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.enum_type_guid</code>. This column stores the GUID of the type containing the enumerated values. In OAGIS, most code lists and agnecy ID lists are defined by an XyzCodeContentType (or XyzAgencyIdentificationContentType) and XyzCodeEnumerationType (or XyzAgencyIdentificationEnumerationContentType). However, some don't have the enumeration type. When that is the case, this column is null.
     */
    public String getEnumTypeGuid() {
        return (String) get(2);
    }

    /**
     * Setter for <code>oagi.agency_id_list.name</code>. Name of the agency identification list.
     */
    public void setName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.name</code>. Name of the agency identification list.
     */
    public String getName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>oagi.agency_id_list.list_id</code>. This is a business or standard identification assigned to the agency identification list.
     */
    public void setListId(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.list_id</code>. This is a business or standard identification assigned to the agency identification list.
     */
    public String getListId() {
        return (String) get(4);
    }

    /**
     * Setter for <code>oagi.agency_id_list.agency_id_list_value_id</code>. This is the identification of the agency or organization which developed and/or maintains the list. Theoretically, this can be modeled as a self-reference foreign key, but it is not implemented at this point.
     */
    public void setAgencyIdListValueId(ULong value) {
        set(5, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.agency_id_list_value_id</code>. This is the identification of the agency or organization which developed and/or maintains the list. Theoretically, this can be modeled as a self-reference foreign key, but it is not implemented at this point.
     */
    public ULong getAgencyIdListValueId() {
        return (ULong) get(5);
    }

    /**
     * Setter for <code>oagi.agency_id_list.version_id</code>. Version number of the agency identification list (assigned by the agency).
     */
    public void setVersionId(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.version_id</code>. Version number of the agency identification list (assigned by the agency).
     */
    public String getVersionId() {
        return (String) get(6);
    }

    /**
     * Setter for <code>oagi.agency_id_list.based_agency_id_list_id</code>. This is a foreign key to the AGENCY_ID_LIST table itself. This identifies the agency id list on which this agency id list is based, if any. The derivation may be restriction and/or extension.
     */
    public void setBasedAgencyIdListId(ULong value) {
        set(7, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.based_agency_id_list_id</code>. This is a foreign key to the AGENCY_ID_LIST table itself. This identifies the agency id list on which this agency id list is based, if any. The derivation may be restriction and/or extension.
     */
    public ULong getBasedAgencyIdListId() {
        return (ULong) get(7);
    }

    /**
     * Setter for <code>oagi.agency_id_list.definition</code>. Description of the agency identification list.
     */
    public void setDefinition(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.definition</code>. Description of the agency identification list.
     */
    public String getDefinition() {
        return (String) get(8);
    }

    /**
     * Setter for <code>oagi.agency_id_list.namespace_id</code>. Foreign key to the NAMESPACE table. This is the namespace to which the entity belongs. This namespace column is primarily used in the case the component is a user's component because there is also a namespace assigned at the release level.
     */
    public void setNamespaceId(ULong value) {
        set(9, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.namespace_id</code>. Foreign key to the NAMESPACE table. This is the namespace to which the entity belongs. This namespace column is primarily used in the case the component is a user's component because there is also a namespace assigned at the release level.
     */
    public ULong getNamespaceId() {
        return (ULong) get(9);
    }

    /**
     * Setter for <code>oagi.agency_id_list.created_by</code>. Foreign key to the APP_USER table. It indicates the user who created the agency ID list.
     */
    public void setCreatedBy(ULong value) {
        set(10, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.created_by</code>. Foreign key to the APP_USER table. It indicates the user who created the agency ID list.
     */
    public ULong getCreatedBy() {
        return (ULong) get(10);
    }

    /**
     * Setter for <code>oagi.agency_id_list.last_updated_by</code>. Foreign key to the APP_USER table. It identifies the user who last updated the agency ID list.
     */
    public void setLastUpdatedBy(ULong value) {
        set(11, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.last_updated_by</code>. Foreign key to the APP_USER table. It identifies the user who last updated the agency ID list.
     */
    public ULong getLastUpdatedBy() {
        return (ULong) get(11);
    }

    /**
     * Setter for <code>oagi.agency_id_list.creation_timestamp</code>. Timestamp when the agency ID list was created.
     */
    public void setCreationTimestamp(LocalDateTime value) {
        set(12, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.creation_timestamp</code>. Timestamp when the agency ID list was created.
     */
    public LocalDateTime getCreationTimestamp() {
        return (LocalDateTime) get(12);
    }

    /**
     * Setter for <code>oagi.agency_id_list.last_update_timestamp</code>. Timestamp when the agency ID list was last updated.
     */
    public void setLastUpdateTimestamp(LocalDateTime value) {
        set(13, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.last_update_timestamp</code>. Timestamp when the agency ID list was last updated.
     */
    public LocalDateTime getLastUpdateTimestamp() {
        return (LocalDateTime) get(13);
    }

    /**
     * Setter for <code>oagi.agency_id_list.state</code>. Life cycle state of the agency ID list. Possible values are Editing, Published, or Deleted. Only the agency ID list in published state is available for derivation and for used by the CC and BIE. Once the agency ID list is published, it cannot go back to Editing. A new version would have to be created.
     */
    public void setState(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.state</code>. Life cycle state of the agency ID list. Possible values are Editing, Published, or Deleted. Only the agency ID list in published state is available for derivation and for used by the CC and BIE. Once the agency ID list is published, it cannot go back to Editing. A new version would have to be created.
     */
    public String getState() {
        return (String) get(14);
    }

    /**
     * Setter for <code>oagi.agency_id_list.is_deprecated</code>. Indicates whether the agency id list is deprecated and should not be reused (i.e., no new reference to this record should be allowed).
     */
    public void setIsDeprecated(Byte value) {
        set(15, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.is_deprecated</code>. Indicates whether the agency id list is deprecated and should not be reused (i.e., no new reference to this record should be allowed).
     */
    public Byte getIsDeprecated() {
        return (Byte) get(15);
    }

    /**
     * Setter for <code>oagi.agency_id_list.replacement_agency_id_list_id</code>. This refers to a replacement if the record is deprecated.
     */
    public void setReplacementAgencyIdListId(ULong value) {
        set(16, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.replacement_agency_id_list_id</code>. This refers to a replacement if the record is deprecated.
     */
    public ULong getReplacementAgencyIdListId() {
        return (ULong) get(16);
    }

    /**
     * Setter for <code>oagi.agency_id_list.owner_user_id</code>. Foreign key to the APP_USER table. This is the user who owns the entity, is allowed to edit the entity, and who can transfer the ownership to another user.

The ownership can change throughout the history, but undoing shouldn't rollback the ownership.
     */
    public void setOwnerUserId(ULong value) {
        set(17, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.owner_user_id</code>. Foreign key to the APP_USER table. This is the user who owns the entity, is allowed to edit the entity, and who can transfer the ownership to another user.

The ownership can change throughout the history, but undoing shouldn't rollback the ownership.
     */
    public ULong getOwnerUserId() {
        return (ULong) get(17);
    }

    /**
     * Setter for <code>oagi.agency_id_list.prev_agency_id_list_id</code>. A self-foreign key to indicate the previous history record.
     */
    public void setPrevAgencyIdListId(ULong value) {
        set(18, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.prev_agency_id_list_id</code>. A self-foreign key to indicate the previous history record.
     */
    public ULong getPrevAgencyIdListId() {
        return (ULong) get(18);
    }

    /**
     * Setter for <code>oagi.agency_id_list.next_agency_id_list_id</code>. A self-foreign key to indicate the next history record.
     */
    public void setNextAgencyIdListId(ULong value) {
        set(19, value);
    }

    /**
     * Getter for <code>oagi.agency_id_list.next_agency_id_list_id</code>. A self-foreign key to indicate the next history record.
     */
    public ULong getNextAgencyIdListId() {
        return (ULong) get(19);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record20 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row20<ULong, String, String, String, String, ULong, String, ULong, String, ULong, ULong, ULong, LocalDateTime, LocalDateTime, String, Byte, ULong, ULong, ULong, ULong> fieldsRow() {
        return (Row20) super.fieldsRow();
    }

    @Override
    public Row20<ULong, String, String, String, String, ULong, String, ULong, String, ULong, ULong, ULong, LocalDateTime, LocalDateTime, String, Byte, ULong, ULong, ULong, ULong> valuesRow() {
        return (Row20) super.valuesRow();
    }

    @Override
    public Field<ULong> field1() {
        return AgencyIdList.AGENCY_ID_LIST.AGENCY_ID_LIST_ID;
    }

    @Override
    public Field<String> field2() {
        return AgencyIdList.AGENCY_ID_LIST.GUID;
    }

    @Override
    public Field<String> field3() {
        return AgencyIdList.AGENCY_ID_LIST.ENUM_TYPE_GUID;
    }

    @Override
    public Field<String> field4() {
        return AgencyIdList.AGENCY_ID_LIST.NAME;
    }

    @Override
    public Field<String> field5() {
        return AgencyIdList.AGENCY_ID_LIST.LIST_ID;
    }

    @Override
    public Field<ULong> field6() {
        return AgencyIdList.AGENCY_ID_LIST.AGENCY_ID_LIST_VALUE_ID;
    }

    @Override
    public Field<String> field7() {
        return AgencyIdList.AGENCY_ID_LIST.VERSION_ID;
    }

    @Override
    public Field<ULong> field8() {
        return AgencyIdList.AGENCY_ID_LIST.BASED_AGENCY_ID_LIST_ID;
    }

    @Override
    public Field<String> field9() {
        return AgencyIdList.AGENCY_ID_LIST.DEFINITION;
    }

    @Override
    public Field<ULong> field10() {
        return AgencyIdList.AGENCY_ID_LIST.NAMESPACE_ID;
    }

    @Override
    public Field<ULong> field11() {
        return AgencyIdList.AGENCY_ID_LIST.CREATED_BY;
    }

    @Override
    public Field<ULong> field12() {
        return AgencyIdList.AGENCY_ID_LIST.LAST_UPDATED_BY;
    }

    @Override
    public Field<LocalDateTime> field13() {
        return AgencyIdList.AGENCY_ID_LIST.CREATION_TIMESTAMP;
    }

    @Override
    public Field<LocalDateTime> field14() {
        return AgencyIdList.AGENCY_ID_LIST.LAST_UPDATE_TIMESTAMP;
    }

    @Override
    public Field<String> field15() {
        return AgencyIdList.AGENCY_ID_LIST.STATE;
    }

    @Override
    public Field<Byte> field16() {
        return AgencyIdList.AGENCY_ID_LIST.IS_DEPRECATED;
    }

    @Override
    public Field<ULong> field17() {
        return AgencyIdList.AGENCY_ID_LIST.REPLACEMENT_AGENCY_ID_LIST_ID;
    }

    @Override
    public Field<ULong> field18() {
        return AgencyIdList.AGENCY_ID_LIST.OWNER_USER_ID;
    }

    @Override
    public Field<ULong> field19() {
        return AgencyIdList.AGENCY_ID_LIST.PREV_AGENCY_ID_LIST_ID;
    }

    @Override
    public Field<ULong> field20() {
        return AgencyIdList.AGENCY_ID_LIST.NEXT_AGENCY_ID_LIST_ID;
    }

    @Override
    public ULong component1() {
        return getAgencyIdListId();
    }

    @Override
    public String component2() {
        return getGuid();
    }

    @Override
    public String component3() {
        return getEnumTypeGuid();
    }

    @Override
    public String component4() {
        return getName();
    }

    @Override
    public String component5() {
        return getListId();
    }

    @Override
    public ULong component6() {
        return getAgencyIdListValueId();
    }

    @Override
    public String component7() {
        return getVersionId();
    }

    @Override
    public ULong component8() {
        return getBasedAgencyIdListId();
    }

    @Override
    public String component9() {
        return getDefinition();
    }

    @Override
    public ULong component10() {
        return getNamespaceId();
    }

    @Override
    public ULong component11() {
        return getCreatedBy();
    }

    @Override
    public ULong component12() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime component13() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime component14() {
        return getLastUpdateTimestamp();
    }

    @Override
    public String component15() {
        return getState();
    }

    @Override
    public Byte component16() {
        return getIsDeprecated();
    }

    @Override
    public ULong component17() {
        return getReplacementAgencyIdListId();
    }

    @Override
    public ULong component18() {
        return getOwnerUserId();
    }

    @Override
    public ULong component19() {
        return getPrevAgencyIdListId();
    }

    @Override
    public ULong component20() {
        return getNextAgencyIdListId();
    }

    @Override
    public ULong value1() {
        return getAgencyIdListId();
    }

    @Override
    public String value2() {
        return getGuid();
    }

    @Override
    public String value3() {
        return getEnumTypeGuid();
    }

    @Override
    public String value4() {
        return getName();
    }

    @Override
    public String value5() {
        return getListId();
    }

    @Override
    public ULong value6() {
        return getAgencyIdListValueId();
    }

    @Override
    public String value7() {
        return getVersionId();
    }

    @Override
    public ULong value8() {
        return getBasedAgencyIdListId();
    }

    @Override
    public String value9() {
        return getDefinition();
    }

    @Override
    public ULong value10() {
        return getNamespaceId();
    }

    @Override
    public ULong value11() {
        return getCreatedBy();
    }

    @Override
    public ULong value12() {
        return getLastUpdatedBy();
    }

    @Override
    public LocalDateTime value13() {
        return getCreationTimestamp();
    }

    @Override
    public LocalDateTime value14() {
        return getLastUpdateTimestamp();
    }

    @Override
    public String value15() {
        return getState();
    }

    @Override
    public Byte value16() {
        return getIsDeprecated();
    }

    @Override
    public ULong value17() {
        return getReplacementAgencyIdListId();
    }

    @Override
    public ULong value18() {
        return getOwnerUserId();
    }

    @Override
    public ULong value19() {
        return getPrevAgencyIdListId();
    }

    @Override
    public ULong value20() {
        return getNextAgencyIdListId();
    }

    @Override
    public AgencyIdListRecord value1(ULong value) {
        setAgencyIdListId(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value2(String value) {
        setGuid(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value3(String value) {
        setEnumTypeGuid(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value4(String value) {
        setName(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value5(String value) {
        setListId(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value6(ULong value) {
        setAgencyIdListValueId(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value7(String value) {
        setVersionId(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value8(ULong value) {
        setBasedAgencyIdListId(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value9(String value) {
        setDefinition(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value10(ULong value) {
        setNamespaceId(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value11(ULong value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value12(ULong value) {
        setLastUpdatedBy(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value13(LocalDateTime value) {
        setCreationTimestamp(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value14(LocalDateTime value) {
        setLastUpdateTimestamp(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value15(String value) {
        setState(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value16(Byte value) {
        setIsDeprecated(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value17(ULong value) {
        setReplacementAgencyIdListId(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value18(ULong value) {
        setOwnerUserId(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value19(ULong value) {
        setPrevAgencyIdListId(value);
        return this;
    }

    @Override
    public AgencyIdListRecord value20(ULong value) {
        setNextAgencyIdListId(value);
        return this;
    }

    @Override
    public AgencyIdListRecord values(ULong value1, String value2, String value3, String value4, String value5, ULong value6, String value7, ULong value8, String value9, ULong value10, ULong value11, ULong value12, LocalDateTime value13, LocalDateTime value14, String value15, Byte value16, ULong value17, ULong value18, ULong value19, ULong value20) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AgencyIdListRecord
     */
    public AgencyIdListRecord() {
        super(AgencyIdList.AGENCY_ID_LIST);
    }

    /**
     * Create a detached, initialised AgencyIdListRecord
     */
    public AgencyIdListRecord(ULong agencyIdListId, String guid, String enumTypeGuid, String name, String listId, ULong agencyIdListValueId, String versionId, ULong basedAgencyIdListId, String definition, ULong namespaceId, ULong createdBy, ULong lastUpdatedBy, LocalDateTime creationTimestamp, LocalDateTime lastUpdateTimestamp, String state, Byte isDeprecated, ULong replacementAgencyIdListId, ULong ownerUserId, ULong prevAgencyIdListId, ULong nextAgencyIdListId) {
        super(AgencyIdList.AGENCY_ID_LIST);

        setAgencyIdListId(agencyIdListId);
        setGuid(guid);
        setEnumTypeGuid(enumTypeGuid);
        setName(name);
        setListId(listId);
        setAgencyIdListValueId(agencyIdListValueId);
        setVersionId(versionId);
        setBasedAgencyIdListId(basedAgencyIdListId);
        setDefinition(definition);
        setNamespaceId(namespaceId);
        setCreatedBy(createdBy);
        setLastUpdatedBy(lastUpdatedBy);
        setCreationTimestamp(creationTimestamp);
        setLastUpdateTimestamp(lastUpdateTimestamp);
        setState(state);
        setIsDeprecated(isDeprecated);
        setReplacementAgencyIdListId(replacementAgencyIdListId);
        setOwnerUserId(ownerUserId);
        setPrevAgencyIdListId(prevAgencyIdListId);
        setNextAgencyIdListId(nextAgencyIdListId);
    }
}