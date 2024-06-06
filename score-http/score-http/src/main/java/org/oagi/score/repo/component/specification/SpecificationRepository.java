package org.oagi.score.repo.component.specification;

import org.jooq.*;
import org.jooq.Record;
import org.jooq.types.ULong;
import org.oagi.score.gateway.http.api.specification_management.data.*;
import org.oagi.score.gateway.http.api.specification_management.data.Source;
import org.oagi.score.repo.api.base.ScoreDataAccessException;
import org.oagi.score.repo.api.impl.jooq.entity.tables.AppUser;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.*;
import org.oagi.score.service.common.data.PageRequest;
import org.oagi.score.service.common.data.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.*;
import static org.jooq.impl.DSL.*;
import static org.oagi.score.repo.api.impl.jooq.entity.Tables.*;
import static org.oagi.score.repo.api.impl.jooq.entity.Tables.SPECIFICATION_DATA_TYPE;

@Repository
public class SpecificationRepository {

    @Autowired
    private DSLContext dslContext;

    public CreateSpecificationResponse createSpecification(CreateSpecificationRequest request) throws ScoreDataAccessException {

        List<SpecificationAggregateComponent> aggregates = new ArrayList<>();
        Source source = insertSource(request);

        SpecificationType specificationType = getSpecificationType(request.getSpecificationType());

        Specification specification = insertSpecification(request,
                source.getSourceId(), specificationType.getSpecificationTypeId());

        request.getSpecificationAggregatesList().forEach(aggregate -> {
            insertAggregate(aggregate, specification.getSpecificationId());
            aggregates.add(aggregate);
        });

        return new CreateSpecificationResponse(aggregates);
    }

    private Source insertSource(CreateSpecificationRequest request) {
        Source source = request.getSource();
        SourceRecord sourceRecord = new SourceRecord();
        sourceRecord.setSourceName(request.getSource().getSourceName());

        source.setSourceId(BigInteger.valueOf(dslContext.insertInto(SOURCE)
                .set(sourceRecord)
                .returning(SOURCE.SOURCE_ID).fetchOne()
                .getSourceId()));

        return source;
    }

    private SpecificationType getSpecificationType(String specificationType) {
        SpecificationTypeRecord specificationTypeRecord =
                dslContext.selectFrom(SPECIFICATION_TYPE.where
                        (SPECIFICATION_TYPE.SPECIFICATION_TYPE_NAME.eq(specificationType))).fetchOne();
        return mapSpecificationTypeRecord(specificationTypeRecord);
    }

    private Specification insertSpecification(CreateSpecificationRequest request, BigInteger sourceID, BigInteger specificationTypeId) {
        Specification specification = request.getSpecification();

        SpecificationRecord specificationRecord = new SpecificationRecord();
        specificationRecord.setSpecificationName(specification.getSpecificationName());
        specificationRecord.setBasedSpecificationId(specification.getBasedSpecificationId() != null
                ? specification.getBasedSpecificationId().longValue() : null);
        specificationRecord.setSpecificationTypeId(specificationTypeId.longValue());
        specificationRecord.setSourceId(sourceID.longValue());

        specification.setSpecificationId(BigInteger.valueOf(dslContext.insertInto(SPECIFICATION)
                .set(specificationRecord)
                .returning(SPECIFICATION.SPECIFICATION_ID).fetchOne()
                .getSpecificationId()));
        return specification;
    }

    private SpecificationAggregateComponent insertAggregate(SpecificationAggregateComponent aggregate, BigInteger specificationID) {
        SpecificationAggregateComponentRecord foundRecord = findSpecificationAggregate(aggregate, specificationID);
        if (foundRecord == null) {
            SpecificationAggregateComponentRecord specAggregateRecord = new SpecificationAggregateComponentRecord();
            specAggregateRecord.setComponentName(aggregate.getComponentName());
            specAggregateRecord.setDefinition(aggregate.getDefinition());
            specAggregateRecord.setSpecificationid(specificationID.longValue());
            StatusCodeRecord statusCodeRecord = getStatusByStatusCodeName(SpecComponentState.STAGED.toString());
            specAggregateRecord.setStatusCodeId(statusCodeRecord.getStatusCodeId());
            specAggregateRecord.setGapAnalysisCodeId(null);
            specAggregateRecord.setAccId(null);
            specAggregateRecord.setIsApproved(null);

            if (aggregate.getBasedAggregateComponent() != null) {
                SpecificationAggregateComponentRecord basedAggregateRecord = findSpecificationAggregate(aggregate.getBasedAggregateComponent(), specificationID);
                if (basedAggregateRecord == null) {
                    SpecificationAggregateComponent basedAggregate = insertAggregate(aggregate.getBasedAggregateComponent(), specificationID);
                    specAggregateRecord.setBasedAggregateComponentId(basedAggregate.getComponentId().longValue());
                } else {
                    specAggregateRecord.setBasedAggregateComponentId(basedAggregateRecord.getComponentId());
                }
            }
            aggregate.setSpecificationId(specificationID);
            aggregate.setComponentId(BigInteger.valueOf(dslContext.insertInto(SPECIFICATION_AGGREGATE_COMPONENT)
                    .set(specAggregateRecord)
                    .returning(SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_ID).fetchOne()
                    .getComponentId()));
            if (aggregate.getSpecificationBasicsList() != null) {
                aggregate.getSpecificationBasicsList().forEach(basic -> {
                    insertBasic(basic, aggregate);
                });
            }
            if (aggregate.getSpecificationAssociationsList() != null) {
                aggregate.getSpecificationAssociationsList().forEach(association -> {
                    insertAssociation(association, aggregate);
                });
            }
        } else {
            aggregate.setComponentId(BigInteger.valueOf(foundRecord.getComponentId()));
            aggregate.setSpecificationId(specificationID);
        }
        return aggregate;
    }

    private SpecificationBasicComponent insertBasic(SpecificationBasicComponent basic, SpecificationAggregateComponent fromAggregate) {
        SpecificationBasicComponentRecord basicRecord =
                findSpecificationBasic(basic, fromAggregate);
        if (basicRecord == null) {
            basicRecord = new SpecificationBasicComponentRecord();
            basicRecord.setComponentName(basic.getComponentName());
            basicRecord.setDefinition(basic.getDefinition());
            basicRecord.setAggregateComponentId(fromAggregate.getComponentId().longValue());
            basicRecord.setMinCardinality(basic.getMinCardinality());
            basicRecord.setMaxCardinality(basic.getMaxCardinality());
            basicRecord.setValueConstraint(basic.getValueConstraint());
            StatusCodeRecord statusCodeRecord = getStatusByStatusCodeName(SpecComponentState.STAGED.toString());
            basicRecord.setStatusCodeId(statusCodeRecord.getStatusCodeId());
            basicRecord.setGapAnalysisCodeId(null);
            basicRecord.setBccId(null);
            basicRecord.setIsApproved(null);
            basicRecord.setSpecificationId(fromAggregate.getSpecificationId().longValue());
            SpecificationDataTypeRecord dtRecord = getSpecificationDataType(basic.getDataType(), fromAggregate.getSpecificationId());
            if (dtRecord == null) {
                SpecificationDataType dataType = insertDataType(basic.getDataType(), fromAggregate.getSpecificationId());
                basicRecord.setDataTypeId(dataType.getDataTypeId().longValue());
            } else {
                basicRecord.setDataTypeId(dtRecord.getDataTypeId());
            }
            basic.setComponentId(BigInteger.valueOf(dslContext.insertInto(SPECIFICATION_BASIC_COMPONENT)
                    .set(basicRecord)
                    .returning(SPECIFICATION_BASIC_COMPONENT.COMPONENT_ID).fetchOne()
                    .getComponentId()));
        } else {
            basic.setComponentId(BigInteger.valueOf(basicRecord.getComponentId()));
            basic.setSpecificationId(BigInteger.valueOf(basicRecord.getSpecificationId()));
        }

        return basic;
    }

    private SpecificationDataType insertDataType(SpecificationDataType dataType, BigInteger specificationID) {
        SpecificationDataType basedDT = new SpecificationDataType();
        SpecificationDataTypeRecord dtRecord;
        dtRecord = dslContext.selectFrom(SPECIFICATION_DATA_TYPE).where(SPECIFICATION_DATA_TYPE.DATA_TYPE_NAME.eq(dataType.getDataTypeName())).fetchOne();
        if (dtRecord == null) {
            dtRecord = new SpecificationDataTypeRecord();
            if (dataType.getBasedDT() != null){
                basedDT = insertDataType(dataType.getBasedDT(), specificationID);
                dtRecord.setBasedDtId(basedDT.getDataTypeId());
            }
            dtRecord.setDataTypeName(dataType.getDataTypeName());
            dtRecord.setDefinition(dataType.getDefinition());
            dtRecord.setSpecificationId(specificationID.longValue());
            StatusCodeRecord statusCodeRecord = getStatusByStatusCodeName(SpecComponentState.STAGED.toString());
            dtRecord.setStatusCodeId(statusCodeRecord.getStatusCodeId());
            dtRecord.setConstraint(dataType.getConstraint());
            dtRecord.setConstraintType(dataType.getConstraintType());
            dtRecord.setGapAnalysisCodeId(null);
            dtRecord.setDtId(null);
            dtRecord.setIsApproved(null);

            dataType.setDataTypeId(Long.valueOf(dslContext.insertInto(SPECIFICATION_DATA_TYPE)
                    .set(dtRecord)
                    .returning(SPECIFICATION_DATA_TYPE.DATA_TYPE_ID).fetchOne()
                    .getDataTypeId()));
        } else{
            dataType.setDataTypeId(dtRecord.getDataTypeId());
        }

        return dataType;
    }

    private SpecificationAssociationComponent insertAssociation(SpecificationAssociationComponent association, SpecificationAggregateComponent fromAggregate) {
        SpecificationAssociationComponentRecord associationRecord = findSpecificationAssociation(association, fromAggregate);
        if (associationRecord == null) {
            associationRecord = new SpecificationAssociationComponentRecord();
            associationRecord.setAssociationName(association.getAssociationName());
            associationRecord.setDefinition(association.getDefinition());
            associationRecord.setFromAggregateComponent(fromAggregate.getComponentId().longValue());
            StatusCodeRecord statusCodeRecord = getStatusByStatusCodeName(SpecComponentState.STAGED.toString());
            associationRecord.setStatusCodeId(statusCodeRecord.getStatusCodeId());
            associationRecord.setGapAnalysisCodeId(null);
            associationRecord.setAsccId(null);
            associationRecord.setMinCardinality(association.getMinCardinality());
            associationRecord.setMaxCardinality(association.getMaxCardinality());
            associationRecord.setIsApproved(null);
            associationRecord.setSpecificationId(fromAggregate.getSpecificationId().longValue());
            SpecificationAggregateComponentRecord toAggregateRecord = findSpecificationAggregate(association.getToAggregateComponent(), fromAggregate.getSpecificationId());
            if (toAggregateRecord == null) {
                SpecificationAggregateComponent toAggregate = insertAggregate(association.getToAggregateComponent(), fromAggregate.getSpecificationId());
                associationRecord.setToAggregateComponent(toAggregate.getComponentId().longValue());
            } else {
                associationRecord.setToAggregateComponent(toAggregateRecord.getComponentId());
            }

            association.setComponentId(BigInteger.valueOf(dslContext.insertInto(SPECIFICATION_ASSOCIATION_COMPONENT)
                    .set(associationRecord)
                    .returning(SPECIFICATION_ASSOCIATION_COMPONENT.COMPONENT_ID).fetchOne()
                    .getComponentId()));
        } else {
            association.setComponentId(BigInteger.valueOf(associationRecord.getComponentId()));
            association.setSpecificationId(BigInteger.valueOf(associationRecord.getSpecificationId()));
        }

        return association;
    }

    private SpecificationType mapSpecificationTypeRecord(SpecificationTypeRecord specificationTypeRecord) {
        SpecificationType specificationType = new SpecificationType();
        specificationType.setSpecificationTypeId(BigInteger.valueOf(specificationTypeRecord.getSpecificationTypeId()));
        specificationType.setSpecificationTypeName(specificationType.getSpecificationTypeName());
        return specificationType;
    }

    private SpecificationAggregateComponentRecord findSpecificationAggregate(SpecificationAggregateComponent aggregate, BigInteger specificationID) {
        SpecificationAggregateComponentRecord aggregateRecord =
                dslContext.select(SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_ID,
                                SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_NAME).
                        from(SPECIFICATION_AGGREGATE_COMPONENT).where(SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_NAME.
                                eq(aggregate.getComponentName()).and(SPECIFICATION_AGGREGATE_COMPONENT.SPECIFICATIONID.eq(
                                        specificationID.longValue()
                                ))).fetchOneInto(SpecificationAggregateComponentRecord.class);
        return aggregateRecord;
    }

    private SpecificationBasicComponentRecord findSpecificationBasic(SpecificationBasicComponent basic, SpecificationAggregateComponent fromAggregate) {
        SpecificationBasicComponentRecord basicRecord = dslContext.selectFrom(SPECIFICATION_BASIC_COMPONENT.
                where(SPECIFICATION_BASIC_COMPONENT.COMPONENT_NAME.eq(basic.getComponentName()).and(
                        SPECIFICATION_BASIC_COMPONENT.AGGREGATE_COMPONENT_ID.eq(fromAggregate.getComponentId().longValue())
                ))).fetchOne();
        return basicRecord;
    }

    private SpecificationAssociationComponentRecord findSpecificationAssociation(SpecificationAssociationComponent association, SpecificationAggregateComponent fromAggregate) {
        SpecificationAssociationComponentRecord associationRecord =
                dslContext.select(SPECIFICATION_ASSOCIATION_COMPONENT.COMPONENT_ID,
                                SPECIFICATION_ASSOCIATION_COMPONENT.SPECIFICATION_ID,
                                SPECIFICATION_ASSOCIATION_COMPONENT.ASSOCIATION_NAME)
                        .from(SPECIFICATION_ASSOCIATION_COMPONENT)
                        .join(SPECIFICATION_AGGREGATE_COMPONENT)
                        .on(SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_ID.eq(SPECIFICATION_ASSOCIATION_COMPONENT.TO_AGGREGATE_COMPONENT))
                        .where(
                                SPECIFICATION_ASSOCIATION_COMPONENT.ASSOCIATION_NAME.eq(association.getAssociationName()).and(
                                        SPECIFICATION_ASSOCIATION_COMPONENT.FROM_AGGREGATE_COMPONENT.eq(fromAggregate.getComponentId().longValue())
                                                .and(SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_NAME.eq(association.getToAggregateComponent().getComponentName()))
                                )
                        ).fetchOneInto(SpecificationAssociationComponentRecord.class);
        return associationRecord;
    }

    public StatusCodeRecord getStatusByStatusCodeName(String statusCodeName) {
        StatusCodeRecord statusCodeRecord =
                dslContext.selectFrom(STATUS_CODE.where(STATUS_CODE.CODE.eq(statusCodeName))).fetchOne();
        return statusCodeRecord;
    }

    private SpecificationDataTypeRecord getSpecificationDataType(SpecificationDataType dataType, BigInteger specificationID) {
        SpecificationDataTypeRecord dtRecord = dslContext.selectFrom(SPECIFICATION_DATA_TYPE.
                where(SPECIFICATION_DATA_TYPE.DATA_TYPE_NAME.
                        eq(dataType.getDataTypeName()).and(SPECIFICATION_DATA_TYPE.SPECIFICATION_ID.
                                eq(specificationID.longValue())))).fetchOne();
        return dtRecord;
    }

    public SpecificationRecord getSpecificationByName(String name) {
        SpecificationRecord specificationRecord =
                dslContext.selectFrom(SPECIFICATION.where(SPECIFICATION.SPECIFICATION_NAME.eq(name))).fetchOne();
        return specificationRecord;
    }

    public SourceRecord getSourceByID(Long sourceId) {
        SourceRecord sourceRecord =
                dslContext.selectFrom(SOURCE.where(SOURCE.SOURCE_ID.eq(sourceId))).fetchOne();
        return sourceRecord;
    }

    public List<SpecificationAggregateComponentRecord> getAllSpecificationAggregates(Long specificationId) {
        List<SpecificationAggregateComponentRecord> aggregatesRecords =
                dslContext.selectFrom(SPECIFICATION_AGGREGATE_COMPONENT
                        .where(SPECIFICATION_AGGREGATE_COMPONENT.SPECIFICATIONID.eq(specificationId))).stream().toList();
        return aggregatesRecords;
    }

    public CcGapAnalysisResultCodeRecord getCCGapAnalysisCodeByCodeName(String code) {
        CcGapAnalysisResultCodeRecord codeRecord =
                dslContext.selectFrom(CC_GAP_ANALYSIS_RESULT_CODE.
                        where(CC_GAP_ANALYSIS_RESULT_CODE.CODE.eq(code))).fetchOne();
        return codeRecord;
    }

    public void updateSpecificationAggregateComponent(SpecificationAggregateComponentRecord acc) {
        dslContext.update(SPECIFICATION_AGGREGATE_COMPONENT)
                .set(SPECIFICATION_AGGREGATE_COMPONENT.STATUS_CODE_ID, acc.getStatusCodeId())
                .set(SPECIFICATION_AGGREGATE_COMPONENT.GAP_ANALYSIS_CODE_ID, acc.getGapAnalysisCodeId())
                .set(SPECIFICATION_AGGREGATE_COMPONENT.ACC_ID, acc.getAccId())
                .set(SPECIFICATION_AGGREGATE_COMPONENT.IS_APPROVED, acc.getIsApproved())
                .where(SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_ID.eq(acc.getComponentId())).execute();
    }

    public List<SpecificationAggregateComponentRecord> getAnalyzedSpecificationAggregates(Long specificationId) {
        List<SpecificationAggregateComponentRecord> aggregatesRecords =
                (dslContext.select(SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_ID,
                                SPECIFICATION_AGGREGATE_COMPONENT.DEFINITION,
                                SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_NAME,
                                SPECIFICATION_AGGREGATE_COMPONENT.STATUS_CODE_ID,
                                SPECIFICATION_AGGREGATE_COMPONENT.GAP_ANALYSIS_CODE_ID,
                                SPECIFICATION_AGGREGATE_COMPONENT.IS_APPROVED,
                                SPECIFICATION_AGGREGATE_COMPONENT.BASED_AGGREGATE_COMPONENT_ID,
                                SPECIFICATION_AGGREGATE_COMPONENT.ACC_ID)
                        .from(SPECIFICATION_AGGREGATE_COMPONENT)
                        .join(STATUS_CODE).on(SPECIFICATION_AGGREGATE_COMPONENT.STATUS_CODE_ID.eq(STATUS_CODE.STATUS_CODE_ID))
                        .where(SPECIFICATION_AGGREGATE_COMPONENT.SPECIFICATIONID.eq(specificationId)
                                .and(STATUS_CODE.CODE.eq(SpecComponentState.ANALYZED.toString()))).fetchInto(SpecificationAggregateComponentRecord.class));
        return aggregatesRecords;
    }

    public CcGapAnalysisResultCodeRecord getCCGapAnalysisCodeByID(Long gapAnalysisCodeId) {
        CcGapAnalysisResultCodeRecord codeRecord =
                dslContext.selectFrom(CC_GAP_ANALYSIS_RESULT_CODE.
                        where(CC_GAP_ANALYSIS_RESULT_CODE.CODE_ID.eq(gapAnalysisCodeId))).fetchOne();
        return codeRecord;
    }

    public SpecificationAggregateComponentRecord getSpecificationAggregate(Long componentID) {
        return dslContext.selectFrom(SPECIFICATION_AGGREGATE_COMPONENT).where(SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_ID.eq(componentID)).fetchOne();
    }

    public List<SpecificationAssociationComponentRecord> getAllSpecificationAssociations(Long specificationId) {
        List<SpecificationAssociationComponentRecord> associationsRecords =
                dslContext.selectFrom(SPECIFICATION_ASSOCIATION_COMPONENT
                        .where(SPECIFICATION_ASSOCIATION_COMPONENT.SPECIFICATION_ID.eq(specificationId))).stream().toList();
        return associationsRecords;
    }

    public void updateSpecificationAssociationComponent(SpecificationAssociationComponentRecord ascc) {
        dslContext.update(SPECIFICATION_ASSOCIATION_COMPONENT)
                .set(SPECIFICATION_ASSOCIATION_COMPONENT.STATUS_CODE_ID, ascc.getStatusCodeId())
                .set(SPECIFICATION_ASSOCIATION_COMPONENT.GAP_ANALYSIS_CODE_ID, ascc.getGapAnalysisCodeId())
                .set(SPECIFICATION_ASSOCIATION_COMPONENT.ASCC_ID, ascc.getAsccId())
                .set(SPECIFICATION_ASSOCIATION_COMPONENT.IS_APPROVED, ascc.getIsApproved())
                .where(SPECIFICATION_ASSOCIATION_COMPONENT.COMPONENT_ID.eq(ascc.getComponentId())).execute();
    }

    public List<SpecificationAssociationComponentRecord> getAnalyzedSpecificationAssociations(Long specificationId) {
        List<SpecificationAssociationComponentRecord> associationsRecords =
                (dslContext.select(SPECIFICATION_ASSOCIATION_COMPONENT.COMPONENT_ID,
                                SPECIFICATION_ASSOCIATION_COMPONENT.DEFINITION,
                                SPECIFICATION_ASSOCIATION_COMPONENT.ASSOCIATION_NAME,
                                SPECIFICATION_ASSOCIATION_COMPONENT.STATUS_CODE_ID,
                                SPECIFICATION_ASSOCIATION_COMPONENT.GAP_ANALYSIS_CODE_ID,
                                SPECIFICATION_ASSOCIATION_COMPONENT.IS_APPROVED,
                                SPECIFICATION_ASSOCIATION_COMPONENT.TO_AGGREGATE_COMPONENT,
                                SPECIFICATION_ASSOCIATION_COMPONENT.FROM_AGGREGATE_COMPONENT,
                                SPECIFICATION_ASSOCIATION_COMPONENT.ASCC_ID)
                        .from(SPECIFICATION_ASSOCIATION_COMPONENT)
                        .join(STATUS_CODE).on(SPECIFICATION_ASSOCIATION_COMPONENT.STATUS_CODE_ID.eq(STATUS_CODE.STATUS_CODE_ID))
                        .where(SPECIFICATION_ASSOCIATION_COMPONENT.SPECIFICATION_ID.eq(specificationId)
                                .and(STATUS_CODE.CODE.eq(SpecComponentState.ANALYZED.toString()))).fetchInto(SpecificationAssociationComponentRecord.class));
        return associationsRecords;
    }

    public List<SpecificationDataTypeRecord> getAllSpecificationDataTypes(Long specificationId) {
        List<SpecificationDataTypeRecord> dtRecords =
                dslContext.selectFrom(SPECIFICATION_DATA_TYPE
                        .where(SPECIFICATION_DATA_TYPE.SPECIFICATION_ID.eq(specificationId))).stream().toList();
        return dtRecords;
    }

    public void updateSpecificationDTComponent(SpecificationDataTypeRecord dt) {
        dslContext.update(SPECIFICATION_DATA_TYPE)
                .set(SPECIFICATION_DATA_TYPE.STATUS_CODE_ID, dt.getStatusCodeId())
                .set(SPECIFICATION_DATA_TYPE.GAP_ANALYSIS_CODE_ID, dt.getGapAnalysisCodeId())
                .set(SPECIFICATION_DATA_TYPE.DT_ID, dt.getDtId())
                .set(SPECIFICATION_DATA_TYPE.IS_APPROVED, dt.getIsApproved())
                .where(SPECIFICATION_DATA_TYPE.DATA_TYPE_ID.eq(dt.getDataTypeId())).execute();
    }

    public List<SpecificationDataTypeRecord> getAnalyzedSpecificationDTs(Long specificationId) {
        List<SpecificationDataTypeRecord> dtRecords =
                (dslContext.select(SPECIFICATION_DATA_TYPE.DATA_TYPE_ID,
                                SPECIFICATION_DATA_TYPE.DEFINITION,
                                SPECIFICATION_DATA_TYPE.STATUS_CODE_ID,
                                SPECIFICATION_DATA_TYPE.GAP_ANALYSIS_CODE_ID,
                                SPECIFICATION_DATA_TYPE.IS_APPROVED,
                                SPECIFICATION_DATA_TYPE.DT_ID)
                        .from(SPECIFICATION_DATA_TYPE)
                        .join(STATUS_CODE).on(SPECIFICATION_DATA_TYPE.STATUS_CODE_ID.eq(STATUS_CODE.STATUS_CODE_ID))
                        .where(SPECIFICATION_DATA_TYPE.SPECIFICATION_ID.eq(specificationId)
                                .and(STATUS_CODE.CODE.eq(SpecComponentState.ANALYZED.toString()))).fetchInto(SpecificationDataTypeRecord.class));
        return dtRecords;
    }

    public List<SpecificationBasicComponentRecord> getAllSpecificationBasics(Long specificationId) {
        List<SpecificationBasicComponentRecord> basicRecords =
                dslContext.selectFrom(SPECIFICATION_BASIC_COMPONENT
                        .where(SPECIFICATION_BASIC_COMPONENT.SPECIFICATION_ID.eq(specificationId))).stream().toList();
        return basicRecords;
    }

    public void updateSpecificationBasicComponent(SpecificationBasicComponentRecord basic) {
        dslContext.update(SPECIFICATION_BASIC_COMPONENT)
                .set(SPECIFICATION_BASIC_COMPONENT.STATUS_CODE_ID, basic.getStatusCodeId())
                .set(SPECIFICATION_BASIC_COMPONENT.GAP_ANALYSIS_CODE_ID, basic.getGapAnalysisCodeId())
                .set(SPECIFICATION_BASIC_COMPONENT.BCC_ID, basic.getBccId())
                .set(SPECIFICATION_BASIC_COMPONENT.IS_APPROVED, basic.getIsApproved())
                .where(SPECIFICATION_BASIC_COMPONENT.COMPONENT_ID.eq(basic.getComponentId())).execute();
    }

    public List<SpecificationBasicComponentRecord> getAnalyzedSpecificationBasics(Long specificationId) {
        List<SpecificationBasicComponentRecord> basicsRecords =
                (dslContext.select(SPECIFICATION_BASIC_COMPONENT.COMPONENT_ID,
                                SPECIFICATION_BASIC_COMPONENT.DEFINITION,
                                SPECIFICATION_BASIC_COMPONENT.COMPONENT_NAME,
                                SPECIFICATION_BASIC_COMPONENT.STATUS_CODE_ID,
                                SPECIFICATION_BASIC_COMPONENT.GAP_ANALYSIS_CODE_ID,
                                SPECIFICATION_BASIC_COMPONENT.IS_APPROVED,
                                SPECIFICATION_BASIC_COMPONENT.AGGREGATE_COMPONENT_ID,
                                SPECIFICATION_BASIC_COMPONENT.BCC_ID)
                        .from(SPECIFICATION_BASIC_COMPONENT)
                        .join(STATUS_CODE).on(SPECIFICATION_BASIC_COMPONENT.STATUS_CODE_ID.eq(STATUS_CODE.STATUS_CODE_ID))
                        .where(SPECIFICATION_BASIC_COMPONENT.SPECIFICATION_ID.eq(specificationId)
                                .and(STATUS_CODE.CODE.eq(SpecComponentState.ANALYZED.toString()))).fetchInto(SpecificationBasicComponentRecord.class));
        return basicsRecords;
    }

    public List<SimpleSpecification> getSpecifications() {
        List<SpecificationRecord> specifications = dslContext.selectFrom(SPECIFICATION).fetch();
        List<SimpleSpecification> specList = new ArrayList<>();
        SimpleSpecification spec;
        for (SpecificationRecord specRecord : specifications){
            spec = mapSpecificationFromEntity(specRecord);
            specList.add(spec);
        }
        return specList;
    }

    public List<SimpleSource> getSources(){
        List<SourceRecord> records = dslContext.selectFrom(SOURCE).fetch();
        List<SimpleSource> sourceList = new ArrayList<>();
        SimpleSource source;
        for (SourceRecord sourceRecord : records){
            source = mapSourceFromEntity (sourceRecord);
            sourceList.add(source);
        }
        return sourceList;
    }

    private SimpleSpecification mapSpecificationFromEntity (SpecificationRecord specRecord){
        SimpleSpecification spec = new SimpleSpecification();
        spec.setSpecificationId(BigInteger.valueOf(specRecord.getSpecificationId()));
        spec.setSpecificationName(specRecord.getSpecificationName());
        return spec;
    }

    private SimpleSource mapSourceFromEntity (SourceRecord sourceRecord){
        SimpleSource source = new SimpleSource();
        source.setSourceId(BigInteger.valueOf(sourceRecord.getSourceId()));
        source.setSourceName(sourceRecord.getSourceName());
        return source;
    }

    public PageResponse<SpecificationList> getSpecificationComponents(SpecificationListRequest request) {

        SelectOrderByStep select = null;

        select = (select != null) ? select.union(getAllSpecificationAggregates(request))
                    : getAllSpecificationAggregates(request);

        select = (select != null) ? select.union(getAllSpecificationBasics(request))
                : getAllSpecificationBasics(request);

        if (select == null) {
            PageResponse response = new PageResponse();
            response.setList(Collections.emptyList());
            response.setPage(request.getPageRequest().getPageIndex());
            response.setSize(request.getPageRequest().getPageSize());
            response.setLength(0);
            return response;
        }

        PageRequest pageRequest = request.getPageRequest();
        Field field = null;
        switch (pageRequest.getSortActive()) {
            case "componentID":
                field = field("componentID");
                break;
            case "status":
                field = field("status");
                break;
            case "componentType":
                field = field("componentType");
                break;
            case "componentName":
                field = field("componentName");
                break;
            case "description":
                field = field("description");
                break;
            case "owner":
                field = field("owner");
                break;
            case "updatedOn":
                field = field("updatedOn");
                break;
        }

        List<SortField> sortFields = new ArrayList<>();
        if (field != null && pageRequest.getSortDirection() != null) {
            switch (pageRequest.getSortDirection()) {
                case "asc":
                    sortFields.add(field.asc());
                    break;
                case "desc":
                    sortFields.add(field.desc());
                    break;
            }
        }
        int count = dslContext.fetchCount(select);

        SelectWithTiesAfterOffsetStep offsetStep = null;
        if (!sortFields.isEmpty()) {
            offsetStep = select.orderBy(sortFields)
                    .limit(pageRequest.getOffset(), pageRequest.getPageSize());
        } else {
            if (pageRequest.getPageIndex() >= 0 && pageRequest.getPageSize() > 0) {
                offsetStep = select
                        .limit(pageRequest.getOffset(), pageRequest.getPageSize());
            }
        }

        List<SpecificationList> result = ((offsetStep != null) ? offsetStep.fetch() : select.fetch())
                .map((RecordMapper<Record, SpecificationList>) row -> {
                    SpecificationList componentsList = new SpecificationList();
                    componentsList.setComponentType(row.getValue("componentType", String.class));
                    componentsList.setComponentId(row.getValue("componentID", ULong.class).toBigInteger());
                    componentsList.setStatus(row.getValue("status", String.class));
                    componentsList.setDescription(row.getValue("description", String.class));
                    componentsList.setComponentName(row.getValue("componentName", String.class));
                    return componentsList;
                });

        PageResponse<SpecificationList> response = new PageResponse();
        response.setList(result);
        response.setPage(pageRequest.getPageIndex());
        response.setSize(pageRequest.getPageSize());
        response.setLength(count);

        return response;
    }

    private SelectOrderByStep getAllSpecificationBasics(SpecificationListRequest request) {
        AppUser appUserOwner = APP_USER.as("owner");
        AppUser appUserUpdater = APP_USER.as("updater");

        List<Condition> conditions = new ArrayList();
        if (request.getSpecificationId() != null){
            conditions.add(SPECIFICATION.SPECIFICATION_ID.eq(request.getSpecificationId()));
        }
        if (request.getSourceId() != null){
            conditions.add(SOURCE.SOURCE_ID.eq(request.getSourceId()));
        }
        List<Field> selectFields = new ArrayList<>();
        selectFields.addAll(Arrays.asList(
                inline("BASIC").as("componentType"),
                SPECIFICATION_BASIC_COMPONENT.COMPONENT_ID.as("componentID"),
                SPECIFICATION_BASIC_COMPONENT.COMPONENT_NAME.as("componentName"),
                SPECIFICATION_BASIC_COMPONENT.DEFINITION.as("description"),
                STATUS_CODE.CODE.as("status")));


        return dslContext.select(selectFields)
                .from(SPECIFICATION_BASIC_COMPONENT)
                .join(SPECIFICATION)
                .on(SPECIFICATION_BASIC_COMPONENT.SPECIFICATION_ID.eq(SPECIFICATION.SPECIFICATION_ID))
                .join(SOURCE)
                .on(SPECIFICATION.SOURCE_ID.eq(SOURCE.SOURCE_ID))
                .join(STATUS_CODE)
                .on(STATUS_CODE.STATUS_CODE_ID.eq(SPECIFICATION_BASIC_COMPONENT.STATUS_CODE_ID))
                .where(conditions);
    }

    private SelectOrderByStep getAllSpecificationAggregates(SpecificationListRequest request) {
        AppUser appUserOwner = APP_USER.as("owner");
        AppUser appUserUpdater = APP_USER.as("updater");

        List<Condition> conditions = new ArrayList();
        if (request.getSpecificationId() != null){
            conditions.add(SPECIFICATION.SPECIFICATION_ID.eq(request.getSpecificationId()));
        }
        if (request.getSourceId() != null){
            conditions.add(SOURCE.SOURCE_ID.eq(request.getSourceId()));
        }
        List<Field> selectFields = new ArrayList<>();
        selectFields.addAll(Arrays.asList(
                inline("AGGREGATE").as("componentType"),
                SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_ID.as("componentID"),
                SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_NAME.as("componentName"),
                SPECIFICATION_AGGREGATE_COMPONENT.DEFINITION.as("description"),
                STATUS_CODE.CODE.as("status")));


        return dslContext.select(selectFields)
                .from(SPECIFICATION_AGGREGATE_COMPONENT)
                .join(SPECIFICATION)
                .on(SPECIFICATION_AGGREGATE_COMPONENT.SPECIFICATIONID.eq(SPECIFICATION.SPECIFICATION_ID))
                .join(SOURCE)
                .on(SPECIFICATION.SOURCE_ID.eq(SOURCE.SOURCE_ID))
                .join(STATUS_CODE)
                .on(STATUS_CODE.STATUS_CODE_ID.eq(SPECIFICATION_AGGREGATE_COMPONENT.STATUS_CODE_ID))
                .where(conditions);
    }

    private Specification findSpecificationById(BigInteger specificationId) {
        Specification specification = new Specification();
        SpecificationRecord specRecord = dslContext.selectFrom(SPECIFICATION).where(SPECIFICATION.SPECIFICATION_ID.eq((Field<Long>) specificationId)).fetchOne();
        specification.setBasedSpecificationId(BigInteger.valueOf(specRecord.getBasedSpecificationId()));
        specification.setSpecificationTypeId(BigInteger.valueOf(specRecord.getSpecificationTypeId()));
        specification.setSpecificationName(specRecord.getSpecificationName());
        specification.setSpecificationId(BigInteger.valueOf(specRecord.getSpecificationId()));
        specification.setSourceId(BigInteger.valueOf(specRecord.getSourceId()));
        return specification;
    }

    private SpecificationList mapper(Record record) {
        SpecificationList specificationList = new SpecificationList();

        return specificationList;
    }

    private SelectOnConditionStep<Record2<String, String>> getSelectOnConditionStep() {
            return dslContext.select(SOURCE.SOURCE_NAME, SPECIFICATION.SPECIFICATION_NAME)
                    .from(SOURCE)
                    .join(SPECIFICATION)
                    .on(SOURCE.SOURCE_ID.eq(SPECIFICATION.SOURCE_ID));

    }
}

