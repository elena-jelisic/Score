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
public class ProcessAsccList extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>oagi.process_ascc_list.originalACCManifestID</code>.
     */
    public static final Parameter<Integer> ORIGINALACCMANIFESTID = Internal.createParameter("originalACCManifestID", SQLDataType.INTEGER, false, false);

    /**
     * The parameter
     * <code>oagi.process_ascc_list.associatingACCManifestID</code>.
     */
    public static final Parameter<Integer> ASSOCIATINGACCMANIFESTID = Internal.createParameter("associatingACCManifestID", SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>oagi.process_ascc_list.initialPath</code>.
     */
    public static final Parameter<byte[]> INITIALPATH = Internal.createParameter("initialPath", SQLDataType.BLOB, false, false);

    /**
     * Create a new routine call instance
     */
    public ProcessAsccList() {
        super("process_ascc_list", Oagi.OAGI);

        addInParameter(ORIGINALACCMANIFESTID);
        addInParameter(ASSOCIATINGACCMANIFESTID);
        addInParameter(INITIALPATH);
    }

    /**
     * Set the <code>originalACCManifestID</code> parameter IN value to the
     * routine
     */
    public void setOriginalaccmanifestid(Integer value) {
        setValue(ORIGINALACCMANIFESTID, value);
    }

    /**
     * Set the <code>associatingACCManifestID</code> parameter IN value to the
     * routine
     */
    public void setAssociatingaccmanifestid(Integer value) {
        setValue(ASSOCIATINGACCMANIFESTID, value);
    }

    /**
     * Set the <code>initialPath</code> parameter IN value to the routine
     */
    public void setInitialpath(byte[] value) {
        setValue(INITIALPATH, value);
    }
}