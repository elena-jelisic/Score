/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.tables.records;


import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;
import org.oagi.score.repo.api.impl.jooq.entity.tables.BieGapAnalysisConstraintsResult;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BieGapAnalysisConstraintsResultRecord extends UpdatableRecordImpl<BieGapAnalysisConstraintsResultRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for
     * <code>oagi.bie_gap_analysis_constraints_result.result_id</code>.
     */
    public void setResultId(Long value) {
        set(0, value);
    }

    /**
     * Getter for
     * <code>oagi.bie_gap_analysis_constraints_result.result_id</code>.
     */
    public Long getResultId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>oagi.bie_gap_analysis_constraints_result.code</code>.
     */
    public void setCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>oagi.bie_gap_analysis_constraints_result.code</code>.
     */
    public String getCode() {
        return (String) get(1);
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
     * Create a detached BieGapAnalysisConstraintsResultRecord
     */
    public BieGapAnalysisConstraintsResultRecord() {
        super(BieGapAnalysisConstraintsResult.BIE_GAP_ANALYSIS_CONSTRAINTS_RESULT);
    }

    /**
     * Create a detached, initialised BieGapAnalysisConstraintsResultRecord
     */
    public BieGapAnalysisConstraintsResultRecord(Long resultId, String code) {
        super(BieGapAnalysisConstraintsResult.BIE_GAP_ANALYSIS_CONSTRAINTS_RESULT);

        setResultId(resultId);
        setCode(code);
        resetChangedOnNotNull();
    }
}