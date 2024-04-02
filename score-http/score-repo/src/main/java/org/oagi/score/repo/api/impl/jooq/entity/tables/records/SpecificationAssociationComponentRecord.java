/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.ULong;
import org.oagi.score.repo.api.impl.jooq.entity.tables.SpecificationAssociationComponent;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SpecificationAssociationComponentRecord extends UpdatableRecordImpl<SpecificationAssociationComponentRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>oagi.specification_association_component.component_id</code>.
     */
    public void setComponentId(Long value) {
        set(0, value);
    }

    /**
     * Getter for
     * <code>oagi.specification_association_component.component_id</code>.
     */
    public Long getComponentId() {
        return (Long) get(0);
    }

    /**
     * Setter for
     * <code>oagi.specification_association_component.association_name</code>.
     */
    public void setAssociationName(String value) {
        set(1, value);
    }

    /**
     * Getter for
     * <code>oagi.specification_association_component.association_name</code>.
     */
    public String getAssociationName() {
        return (String) get(1);
    }

    /**
     * Setter for
     * <code>oagi.specification_association_component.definition</code>.
     */
    public void setDefinition(String value) {
        set(2, value);
    }

    /**
     * Getter for
     * <code>oagi.specification_association_component.definition</code>.
     */
    public String getDefinition() {
        return (String) get(2);
    }

    /**
     * Setter for
     * <code>oagi.specification_association_component.from_aggregate_component</code>.
     */
    public void setFromAggregateComponent(Long value) {
        set(3, value);
    }

    /**
     * Getter for
     * <code>oagi.specification_association_component.from_aggregate_component</code>.
     */
    public Long getFromAggregateComponent() {
        return (Long) get(3);
    }

    /**
     * Setter for
     * <code>oagi.specification_association_component.to_aggregate_component</code>.
     */
    public void setToAggregateComponent(Long value) {
        set(4, value);
    }

    /**
     * Getter for
     * <code>oagi.specification_association_component.to_aggregate_component</code>.
     */
    public Long getToAggregateComponent() {
        return (Long) get(4);
    }

    /**
     * Setter for
     * <code>oagi.specification_association_component.gap_analysis_code_id</code>.
     */
    public void setGapAnalysisCodeId(Long value) {
        set(5, value);
    }

    /**
     * Getter for
     * <code>oagi.specification_association_component.gap_analysis_code_id</code>.
     */
    public Long getGapAnalysisCodeId() {
        return (Long) get(5);
    }

    /**
     * Setter for
     * <code>oagi.specification_association_component.status_code_id</code>.
     */
    public void setStatusCodeId(Long value) {
        set(6, value);
    }

    /**
     * Getter for
     * <code>oagi.specification_association_component.status_code_id</code>.
     */
    public Long getStatusCodeId() {
        return (Long) get(6);
    }

    /**
     * Setter for
     * <code>oagi.specification_association_component.min_cardinality</code>.
     */
    public void setMinCardinality(Integer value) {
        set(7, value);
    }

    /**
     * Getter for
     * <code>oagi.specification_association_component.min_cardinality</code>.
     */
    public Integer getMinCardinality() {
        return (Integer) get(7);
    }

    /**
     * Setter for
     * <code>oagi.specification_association_component.max_cardinality</code>.
     */
    public void setMaxCardinality(Integer value) {
        set(8, value);
    }

    /**
     * Getter for
     * <code>oagi.specification_association_component.max_cardinality</code>.
     */
    public Integer getMaxCardinality() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>oagi.specification_association_component.ascc_id</code>.
     */
    public void setAsccId(ULong value) {
        set(9, value);
    }

    /**
     * Getter for <code>oagi.specification_association_component.ascc_id</code>.
     */
    public ULong getAsccId() {
        return (ULong) get(9);
    }

    /**
     * Setter for
     * <code>oagi.specification_association_component.is_approved</code>.
     */
    public void setIsApproved(Byte value) {
        set(10, value);
    }

    /**
     * Getter for
     * <code>oagi.specification_association_component.is_approved</code>.
     */
    public Byte getIsApproved() {
        return (Byte) get(10);
    }

    /**
     * Setter for
     * <code>oagi.specification_association_component.specification_id</code>.
     */
    public void setSpecificationId(Long value) {
        set(11, value);
    }

    /**
     * Getter for
     * <code>oagi.specification_association_component.specification_id</code>.
     */
    public Long getSpecificationId() {
        return (Long) get(11);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SpecificationAssociationComponentRecord
     */
    public SpecificationAssociationComponentRecord() {
        super(SpecificationAssociationComponent.SPECIFICATION_ASSOCIATION_COMPONENT);
    }

    /**
     * Create a detached, initialised SpecificationAssociationComponentRecord
     */
    public SpecificationAssociationComponentRecord(Long componentId, String associationName, String definition, Long fromAggregateComponent, Long toAggregateComponent, Long gapAnalysisCodeId, Long statusCodeId, Integer minCardinality, Integer maxCardinality, ULong asccId, Byte isApproved, Long specificationId) {
        super(SpecificationAssociationComponent.SPECIFICATION_ASSOCIATION_COMPONENT);

        setComponentId(componentId);
        setAssociationName(associationName);
        setDefinition(definition);
        setFromAggregateComponent(fromAggregateComponent);
        setToAggregateComponent(toAggregateComponent);
        setGapAnalysisCodeId(gapAnalysisCodeId);
        setStatusCodeId(statusCodeId);
        setMinCardinality(minCardinality);
        setMaxCardinality(maxCardinality);
        setAsccId(asccId);
        setIsApproved(isApproved);
        setSpecificationId(specificationId);
        resetChangedOnNotNull();
    }
}
