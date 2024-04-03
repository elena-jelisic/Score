package org.oagi.score.repo.component.specification;

import org.jooq.DSLContext;
import org.oagi.score.repo.api.base.ScoreDataAccessException;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.*;
import org.oagi.score.repo.api.specification.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
            SpecificationAggregateComponent aggregateWithID = insertAggregate(aggregate, specification.getSpecificationId());
            if (aggregate.getSpecificationBasicsList() != null) {
                aggregate.getSpecificationBasicsList().forEach(basic -> {
                    insertBasic(basic, aggregateWithID);
                });
            }
            if (aggregate.getSpecificationAssociationsList() != null) {
                aggregate.getSpecificationAssociationsList().forEach(association -> {
                    insertAssociation(association, aggregateWithID);
                });
            }
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
            StatusCodeRecord statusCodeRecord = getStatusByStatusCodeName("Staged");
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
            StatusCodeRecord statusCodeRecord = getStatusByStatusCodeName("Staged");
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
        SpecificationDataTypeRecord dtRecord = new SpecificationDataTypeRecord();
        dtRecord.setDataTypeName(dataType.getDataTypeName());
        dtRecord.setDefinition(dataType.getDefinition());
        dtRecord.setSpecificationId(specificationID.longValue());
        StatusCodeRecord statusCodeRecord = getStatusByStatusCodeName("Staged");
        dtRecord.setStatusCodeId(statusCodeRecord.getStatusCodeId());
        dtRecord.setGapAnalysisCodeId(null);
        dtRecord.setDtId(null);
        dtRecord.setIsApproved(null);

        dataType.setDataTypeId(BigInteger.valueOf(dslContext.insertInto(SPECIFICATION_DATA_TYPE)
                .set(dtRecord)
                .returning(SPECIFICATION_DATA_TYPE.DATA_TYPE_ID).fetchOne()
                .getDataTypeId()));
        return dataType;
    }

    private SpecificationAssociationComponent insertAssociation(SpecificationAssociationComponent association, SpecificationAggregateComponent fromAggregate) {
        SpecificationAssociationComponentRecord associationRecord = findSpecificationAssociation(association, fromAggregate);
        if (associationRecord == null) {
            associationRecord = new SpecificationAssociationComponentRecord();
            associationRecord.setAssociationName(association.getAssociationName());
            associationRecord.setDefinition(association.getDefinition());
            associationRecord.setFromAggregateComponent(fromAggregate.getComponentId().longValue());
            StatusCodeRecord statusCodeRecord = getStatusByStatusCodeName("Staged");
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
                        .join(SPECIFICATION_AGGREGATE_COMPONENT.as("FIRST"))
                        .on(SPECIFICATION_AGGREGATE_COMPONENT.COMPONENT_ID.eq(SPECIFICATION_ASSOCIATION_COMPONENT.TO_AGGREGATE_COMPONENT))
                        .where(
                                SPECIFICATION_ASSOCIATION_COMPONENT.ASSOCIATION_NAME.eq(association.getAssociationName()).and(
                                        SPECIFICATION_ASSOCIATION_COMPONENT.FROM_AGGREGATE_COMPONENT.eq(fromAggregate.getComponentId().longValue())
                                                .and(SPECIFICATION_AGGREGATE_COMPONENT.as("FIRST").COMPONENT_NAME.eq(association.getToAggregateComponent().getComponentName()))
                                )
                        ).fetchOneInto(SpecificationAssociationComponentRecord.class);
        return associationRecord;
    }

    private StatusCodeRecord getStatusByStatusCodeName(String statusCodeName) {
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
}
