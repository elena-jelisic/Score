package org.oagi.score.gateway.http.api.specification_management.service;

import org.checkerframework.checker.units.qual.A;
import org.jooq.types.ULong;
import org.oagi.score.gateway.http.api.cc_management.data.CcAccCreateRequest;
import org.oagi.score.gateway.http.api.cc_management.data.CcAsccpCreateRequest;
import org.oagi.score.gateway.http.api.cc_management.service.CcNodeService;
import org.oagi.score.gateway.http.api.namespace_management.data.Namespace;
import org.oagi.score.gateway.http.api.namespace_management.service.NamespaceService;
import org.oagi.score.gateway.http.api.specification_management.data.CCGapAnalysisResult;
import org.oagi.score.gateway.http.api.specification_management.data.SpecComponentState;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.*;
import org.oagi.score.gateway.http.api.specification_management.data.Specification;
import org.oagi.score.repo.component.acc.AccReadRepository;
import org.oagi.score.repo.component.acc.AccWriteRepository;
import org.oagi.score.repo.component.acc.UpdateAccBasedAccRepositoryRequest;
import org.oagi.score.repo.component.acc.UpdateAccPropertiesRepositoryRequest;
import org.oagi.score.repo.component.ascc.AsccReadRepository;
import org.oagi.score.repo.component.asccp.AsccpWriteRepository;
import org.oagi.score.repo.component.asccp.UpdateAsccpPropertiesRepositoryRequest;
import org.oagi.score.repo.component.release.ReleaseRepository;
import org.oagi.score.repo.component.specification.SpecificationRepository;
import org.oagi.score.repository.ASCCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CCGapAnalysisService {
    @Autowired
    CcNodeService ccService;
    @Autowired
    SpecificationRepository specRepo;
    @Autowired
    ReleaseRepository releaseRepo;
    @Autowired
    AccReadRepository accReadRepo;
    @Autowired
    AccWriteRepository accWriteRepo;
    @Autowired
    NamespaceService namespaceService;
    @Autowired
    AsccReadRepository asccReadRepo;
    @Autowired
    AsccpWriteRepository asccpWriteRepo;
    List<SpecificationAggregateComponentRecord> aggregatesRecordList;
    List<SpecificationAssociationComponentRecord> associationsRecordList;
    List<SpecificationBasicComponentRecord> basicsRecordList;
    List<SpecificationDataTypeRecord> dtRecordList;
    SpecificationRecord specRecord;

    @Transactional
    public void analyzeCoreComponents(AuthenticatedPrincipal user, Specification spec) {
        specRecord = specRepo.getSpecificationByName(spec.getSpecificationName());
        aggregateComponentGapAnalysis();
        dtComponentGapAnalysis();
        basicComponentGapAnalysis();
        associationComponentGapAnalysis();
    }

    private void associationComponentGapAnalysis() {
        CcGapAnalysisResultCodeRecord ccGHapAnalysisCode;
        associationsRecordList = specRepo.getAllSpecificationAssociations(specRecord.getSpecificationId());
        StatusCodeRecord status = specRepo.getStatusByStatusCodeName(SpecComponentState.ANALYZED.toString());
        for (SpecificationAssociationComponentRecord ascc: associationsRecordList){
            ascc.setStatusCodeId(status.getStatusCodeId());
            //AccRecord existingACC = ccService.findACCByDefinitionAndSpecification(ascc.getDefinition(), ascc.getComponentName(), specRecord.getSpecificationName());
            /*if (existingACC != null){
                if (specificationAggregatesSame(acc, existingACC)){
                    ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName (CCGapAnalysisResult.REUSE_EXISTING.toString());
                    acc.setAccId(existingACC.getAccId());
                } else {
                    ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName (CCGapAnalysisResult.REUSE_EXISTING_WITH_UPDATE.toString());
                }
            } else {
                ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName (CCGapAnalysisResult.NEW.toString());
            }*/
            ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName (CCGapAnalysisResult.NEW.toString());
            ascc.setGapAnalysisCodeId(ccGHapAnalysisCode.getCodeId());
            specRepo.updateSpecificationAssociationComponent(ascc);
        }
    }

    private void basicComponentGapAnalysis() {

    }

    private void dtComponentGapAnalysis() {

    }

    @Transactional
    public void approveACCAnalysisResults(List<SpecificationAggregateComponentRecord> aggregates, AuthenticatedPrincipal user, Specification spec){
        specRecord = specRepo.getSpecificationByName(spec.getSpecificationName());
        aggregatesRecordList = specRepo.getAnalyzedSpecificationAggregates(specRecord.getSpecificationId());
        for (SpecificationAggregateComponentRecord acc: aggregatesRecordList){
            CcGapAnalysisResultCodeRecord ccGapAnalysis = specRepo.getCCGapAnalysisCodeByID(acc.getGapAnalysisCodeId());
            if (ccGapAnalysis.getCode().equals(CCGapAnalysisResult.NEW.toString())){
                AccRecord basis = insertNewACC(acc, user);
            }
            if (ccGapAnalysis.getCode().equals(CCGapAnalysisResult.REUSE_EXISTING_WITH_UPDATE.toString())){

            }
        }
    }

    public AccRecord insertNewACC (SpecificationAggregateComponentRecord acc, AuthenticatedPrincipal user){
        AccRecord basedAccRecord = new AccRecord();
        CcAccCreateRequest accCreateRequest = new CcAccCreateRequest();
        ReleaseRecord release = releaseRepo.findReleaseBySpecification (specRecord.getSpecificationId());
        accCreateRequest.setReleaseId(release.getReleaseId().toBigInteger());
        BigInteger accManifestID = ccService.createAcc(user, accCreateRequest);
        AccRecord accRecord = accReadRepo.getAccByManifestId(accManifestID);
        acc.setIsApproved((byte) 1);
        StatusCodeRecord status = specRepo.getStatusByStatusCodeName(SpecComponentState.PROCESSED.toString());
        acc.setStatusCodeId(status.getStatusCodeId());
        acc.setAccId(ULong.valueOf(accRecord.getAccId().longValue()));
        specRepo.updateSpecificationAggregateComponent(acc);
        UpdateAccPropertiesRepositoryRequest updateAcc = new UpdateAccPropertiesRepositoryRequest(user, accManifestID);
        updateAcc.setDefinition(acc.getDefinition());
        updateAcc.setObjectClassTerm(acc.getComponentName());
        updateAcc.setDefinitionSource(specRecord.getSpecificationName());
        Namespace namespace = namespaceService.getNamespace(user, release.getNamespaceId().toBigInteger());
        updateAcc.setNamespaceId(BigInteger.valueOf(namespace.getNamespaceId().longValue()));
        accWriteRepo.updateAccProperties(updateAcc);
        if (acc.getBasedAggregateComponentId() != null){
            SpecificationAggregateComponentRecord basedAcc = specRepo.getSpecificationAggregate(acc.getBasedAggregateComponentId());
            basedAccRecord = insertNewACC(basedAcc, user);
            BigInteger basedAccManifestID = accReadRepo.getAccManifestByAccIDReleaseId(basedAccRecord.getAccId(), release.getReleaseId());
            UpdateAccBasedAccRepositoryRequest updateAccBased = new UpdateAccBasedAccRepositoryRequest(user, accManifestID, basedAccManifestID);
            accWriteRepo.updateAccBasedAcc(updateAccBased);
        }
        return accRecord;
    }
    public void insertNewASCCP (SpecificationAssociationComponentRecord ascc, AuthenticatedPrincipal user){
        CcAsccpCreateRequest asccpCreateRequest = new CcAsccpCreateRequest();
        ReleaseRecord release = releaseRepo.findReleaseBySpecification (specRecord.getSpecificationId());
        asccpCreateRequest.setReleaseId(release.getReleaseId().toBigInteger());
        SpecificationAggregateComponentRecord associatedAggregate = specRepo.getSpecificationAggregate(ascc.getToAggregateComponent());
        BigInteger basedACCManifestID = accReadRepo.getAccManifestByAccIDReleaseId(associatedAggregate.getAccId(), release.getReleaseId());
        asccpCreateRequest.setRoleOfAccManifestId(basedACCManifestID);
        BigInteger asccpManifestID = ccService.createAsccp(user, asccpCreateRequest);
        AsccpRecord asccpRecord = asccpWriteRepo.getAsccpByManifest(asccpManifestID);
        ascc.setIsApproved((byte) 1);
        StatusCodeRecord status = specRepo.getStatusByStatusCodeName(SpecComponentState.PROCESSED.toString());
        ascc.setStatusCodeId(status.getStatusCodeId());

        UpdateAsccpPropertiesRepositoryRequest updateAsccp = new UpdateAsccpPropertiesRepositoryRequest(user, asccpManifestID);
        updateAsccp.setDefinition(ascc.getDefinition());
        updateAsccp.setPropertyTerm(ascc.getAssociationName());
        updateAsccp.setDefinitionSource(specRecord.getSpecificationName());
        Namespace namespace = namespaceService.getNamespace(user, release.getNamespaceId().toBigInteger());
        updateAsccp.setNamespaceId(BigInteger.valueOf(namespace.getNamespaceId().longValue()));
        asccpWriteRepo.updateAsccpProperties(updateAsccp);

        SpecificationAggregateComponentRecord fromAcc = specRepo.getSpecificationAggregate(ascc.getFromAggregateComponent());
        BigInteger fromAccManifestId = accReadRepo.getAccManifestByAccIDReleaseId(fromAcc.getAccId(), release.getReleaseId());

        BigInteger asccManifestId = ccService.appendAsccp(user, release.getReleaseId().toBigInteger(), fromAccManifestId, asccpManifestID ,1);
        AsccRecord asccRecord = asccReadRepo.getAsccByManifestId (asccManifestId);
        ascc.setAsccId(ULong.valueOf(asccRecord.getAsccId().longValue()));
        specRepo.updateSpecificationAssociationComponent(ascc);
    }

    private void aggregateComponentGapAnalysis() {
        CcGapAnalysisResultCodeRecord ccGHapAnalysisCode;
        aggregatesRecordList = specRepo.getAllSpecificationAggregates(specRecord.getSpecificationId());
        StatusCodeRecord status = specRepo.getStatusByStatusCodeName(SpecComponentState.ANALYZED.toString());
        for (SpecificationAggregateComponentRecord acc: aggregatesRecordList){
            acc.setStatusCodeId(status.getStatusCodeId());
            AccRecord existingACC = ccService.findACCByDefinitionAndSpecification(acc.getDefinition(), acc.getComponentName(), specRecord.getSpecificationName());
            if (existingACC != null){
                if (specificationAggregatesSame(acc, existingACC)){
                    ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName (CCGapAnalysisResult.REUSE_EXISTING.toString());
                    acc.setAccId(existingACC.getAccId());
                } else {
                    ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName (CCGapAnalysisResult.REUSE_EXISTING_WITH_UPDATE.toString());
                }
            } else {
                ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName (CCGapAnalysisResult.NEW.toString());
            }
            acc.setGapAnalysisCodeId(ccGHapAnalysisCode.getCodeId());
            specRepo.updateSpecificationAggregateComponent(acc);
        }
    }

    private boolean specificationAggregatesSame(SpecificationAggregateComponentRecord specAggregate, AccRecord acc){
        return true;
    }

    public void approveDTAnalysisResults(AuthenticatedPrincipal user, Specification spec) {

    }

    public void approveAssociationAnalysisResults(AuthenticatedPrincipal user, Specification spec) {
        specRecord = specRepo.getSpecificationByName(spec.getSpecificationName());
        associationsRecordList = specRepo.getAnalyzedSpecificationAssociations(specRecord.getSpecificationId());
        for (SpecificationAssociationComponentRecord ascc: associationsRecordList){
            CcGapAnalysisResultCodeRecord ccGapAnalysis = specRepo.getCCGapAnalysisCodeByID(ascc.getGapAnalysisCodeId());
            if (ccGapAnalysis.getCode().equals(CCGapAnalysisResult.NEW.toString())){
                insertNewASCCP(ascc, user);
            }
            if (ccGapAnalysis.getCode().equals(CCGapAnalysisResult.REUSE_EXISTING_WITH_UPDATE.toString())){

            }
        }
    }

    public void approveBasicAnalysisResults(AuthenticatedPrincipal user, Specification spec) {
    }
}
