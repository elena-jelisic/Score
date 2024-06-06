/*
 * This file is generated by jOOQ.
 */
package org.oagi.score.repo.api.impl.jooq.entity.routines;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.oagi.score.repo.api.impl.jooq.entity.Oagi;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProcessBccList extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>oagi.process_bcc_list.accManifestID</code>.
     */
    public static final Parameter<Integer> ACCMANIFESTID = Internal.createParameter("accManifestID", SQLDataType.INTEGER, false, false);

    /**
     * Create a new routine call instance
     */
    public ProcessBccList() {
        super("process_bcc_list", Oagi.OAGI);

        addInParameter(ACCMANIFESTID);
    }

    /**
     * Set the <code>accManifestID</code> parameter IN value to the routine
     */
    public void setAccmanifestid(Integer value) {
        setValue(ACCMANIFESTID, value);
    }
}
