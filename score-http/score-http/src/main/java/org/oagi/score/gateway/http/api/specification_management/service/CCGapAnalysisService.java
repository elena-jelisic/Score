package org.oagi.score.gateway.http.api.specification_management.service;

import org.jooq.types.ULong;
import org.oagi.score.gateway.http.api.cc_management.data.CcAccCreateRequest;
import org.oagi.score.gateway.http.api.cc_management.data.CcAsccpCreateRequest;
import org.oagi.score.gateway.http.api.cc_management.data.CcBccpCreateRequest;
import org.oagi.score.gateway.http.api.cc_management.data.node.CcBdtPriRestri;
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
import org.oagi.score.repo.component.bcc.BccReadRepository;
import org.oagi.score.repo.component.bccp.BccpReadRepository;
import org.oagi.score.repo.component.bccp.BccpWriteRepository;
import org.oagi.score.repo.component.bccp.UpdateBccpPropertiesRepositoryRequest;
import org.oagi.score.repo.component.bccp.UpdateBccpPropertiesRepositoryResponse;
import org.oagi.score.repo.component.dt.*;
import org.oagi.score.repo.component.release.ReleaseRepository;
import org.oagi.score.repo.component.specification.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
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
    @Autowired
    DtWriteRepository dtWriteRepo;
    @Autowired
    BdtReadRepository dtReadRepo;
    @Autowired
    BccpReadRepository bccpReadRepo;
    @Autowired
    BccpWriteRepository bccpWriteRepo;
    @Autowired
    BccReadRepository bccReadRepo;
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
        CcGapAnalysisResultCodeRecord ccGHapAnalysisCode;
        basicsRecordList = specRepo.getAllSpecificationBasics(specRecord.getSpecificationId());
        StatusCodeRecord status = specRepo.getStatusByStatusCodeName(SpecComponentState.ANALYZED.toString());
        for (SpecificationBasicComponentRecord bcc : basicsRecordList) {
            bcc.setStatusCodeId(status.getStatusCodeId());
            BccRecord existingBCC = ccService.findBCCByDefinitionAndSpecification(bcc.getDefinition(), bcc.getComponentName(), specRecord.getSpecificationName());
            if (existingBCC != null){
                if (specificationBasicsSame(bcc, existingBCC)){
                    ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName (CCGapAnalysisResult.REUSE_EXISTING.toString());
                    bcc.setBccId(existingBCC.getBccId());
                } else {
                    ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName (CCGapAnalysisResult.REUSE_EXISTING_WITH_UPDATE.toString());
                }
            } else {
                ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName (CCGapAnalysisResult.NEW.toString());
            }
            ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName(CCGapAnalysisResult.NEW.toString());
            bcc.setGapAnalysisCodeId(ccGHapAnalysisCode.getCodeId());
            specRepo.updateSpecificationBasicComponent(bcc);
        }
    }

    private boolean specificationBasicsSame(SpecificationBasicComponentRecord bcc, BccRecord existingBCC) {
        return true;
    }

    private void dtComponentGapAnalysis() {
        CcGapAnalysisResultCodeRecord ccGHapAnalysisCode;
        dtRecordList = specRepo.getAllSpecificationDataTypes(specRecord.getSpecificationId());
        StatusCodeRecord status = specRepo.getStatusByStatusCodeName(SpecComponentState.ANALYZED.toString());
        for (SpecificationDataTypeRecord dt : dtRecordList) {
            dt.setStatusCodeId(status.getStatusCodeId());
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
            ccGHapAnalysisCode = specRepo.getCCGapAnalysisCodeByCodeName(CCGapAnalysisResult.NEW.toString());
            dt.setGapAnalysisCodeId(ccGHapAnalysisCode.getCodeId());
            specRepo.updateSpecificationDTComponent(dt);
        }
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

        BigInteger asccManifestId = ccService.appendAsccp(user, release.getReleaseId().toBigInteger(), fromAccManifestId, asccpManifestID, -1);
        AsccRecord asccRecord = asccReadRepo.getAsccByManifestId (asccManifestId);
        ascc.setAsccId(ULong.valueOf(asccRecord.getAsccId().longValue()));
        specRepo.updateSpecificationAssociationComponent(ascc);
    }

    public void insertNewDT (SpecificationDataTypeRecord dt, AuthenticatedPrincipal user){
        BigInteger basedDt = dtReadRepo.getManifestIdByDTId(BigInteger.valueOf(20));
        ReleaseRecord release = releaseRepo.findReleaseBySpecification (specRecord.getSpecificationId());
        CreateBdtRepositoryRequest createBdtRequest = new CreateBdtRepositoryRequest(user, basedDt, BigInteger.valueOf(release.getReleaseId().longValue()), BigInteger.valueOf(0));

        CreateBdtRepositoryResponse createBdtResponse = dtWriteRepo.createBdt(createBdtRequest);
        UpdateDtPropertiesRepositoryRequest updateDtProperties = new UpdateDtPropertiesRepositoryRequest(user, createBdtResponse.getBdtManifestId());
        updateDtProperties.setDefinition(dt.getDefinition());
        Namespace namespace = namespaceService.getNamespace(user, release.getNamespaceId().toBigInteger());
        updateDtProperties.setNamespaceId(namespace.getNamespaceId());
        updateDtProperties.setQualifier(dt.getDataTypeName());
        SourceRecord source = specRepo.getSourceByID(specRecord.getSourceId());
        updateDtProperties.setDefinitionSource(source.getSourceName());
        //CcBdtPriRestri restr = new CcBdtPriRestri();
        //List<CcBdtPriRestri> restriList = new ArrayList<>();
        //updateDtProperties.setBdtPriRestriList(restriList);
        //dtWriteRepo.updateDtProperties(updateDtProperties);
        StatusCodeRecord status = specRepo.getStatusByStatusCodeName(SpecComponentState.PROCESSED.toString());

        dt.setStatusCodeId(status.getStatusCodeId());
        dt.setDtId(ULong.valueOf(dtReadRepo.getDtByDtManifestId(createBdtResponse.getBdtManifestId()).getDtId().longValue()));
        specRepo.updateSpecificationDTComponent(dt);
    }
    public void insertNewBCCP (SpecificationBasicComponentRecord bcc, AuthenticatedPrincipal user){
        CcBccpCreateRequest bccpCreateRequest = new CcBccpCreateRequest();
        ReleaseRecord release = releaseRepo.findReleaseBySpecification (specRecord.getSpecificationId());
        bccpCreateRequest.setReleaseId(release.getReleaseId().toBigInteger());
        bccpCreateRequest.setBdtManifestId(BigInteger.valueOf(100000266));

        BigInteger bccpManifestId = ccService.createBccp(user, bccpCreateRequest);
        BccpRecord bccpRecord = bccpReadRepo.getBccpByManifestId(bccpManifestId);
        bcc.setIsApproved((byte) 1);
        StatusCodeRecord status = specRepo.getStatusByStatusCodeName(SpecComponentState.PROCESSED.toString());
        bcc.setStatusCodeId(status.getStatusCodeId());

        UpdateBccpPropertiesRepositoryRequest updateBccp = new UpdateBccpPropertiesRepositoryRequest(user, bccpManifestId);
        updateBccp.setDefinition(bcc.getDefinition());
        updateBccp.setPropertyTerm(bcc.getComponentName());
        updateBccp.setDefinitionSource(specRecord.getSpecificationName());
        Namespace namespace = namespaceService.getNamespace(user, release.getNamespaceId().toBigInteger());
        updateBccp.setNamespaceId(BigInteger.valueOf(namespace.getNamespaceId().longValue()));
        bccpWriteRepo.updateBccpProperties(updateBccp);

        SpecificationAggregateComponentRecord fromAcc = specRepo.getSpecificationAggregate(bcc.getAggregateComponentId());
        BigInteger fromAccManifestId = accReadRepo.getAccManifestByAccIDReleaseId(fromAcc.getAccId(), release.getReleaseId());

        BigInteger bccManifestId = ccService.appendBccp(user, release.getReleaseId().toBigInteger(), fromAccManifestId, bccpManifestId, -1);
        BccRecord bccRecord = bccReadRepo.getBccByManifestId (bccManifestId);
        bcc.setBccId(ULong.valueOf(bccRecord.getBccId().longValue()));
        specRepo.updateSpecificationBasicComponent(bcc);
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
        specRecord = specRepo.getSpecificationByName(spec.getSpecificationName());
        dtRecordList = specRepo.getAnalyzedSpecificationDTs(specRecord.getSpecificationId());
        for (SpecificationDataTypeRecord dt: dtRecordList){
            CcGapAnalysisResultCodeRecord ccGapAnalysis = specRepo.getCCGapAnalysisCodeByID(dt.getGapAnalysisCodeId());
            if (ccGapAnalysis.getCode().equals(CCGapAnalysisResult.NEW.toString())){
                insertNewDT(dt, user);
            }
            if (ccGapAnalysis.getCode().equals(CCGapAnalysisResult.REUSE_EXISTING_WITH_UPDATE.toString())){

            }
        }
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
        specRecord = specRepo.getSpecificationByName(spec.getSpecificationName());
        basicsRecordList = specRepo.getAnalyzedSpecificationBasics(specRecord.getSpecificationId());
        for (SpecificationBasicComponentRecord basic: basicsRecordList){
            CcGapAnalysisResultCodeRecord ccGapAnalysis = specRepo.getCCGapAnalysisCodeByID(basic.getGapAnalysisCodeId());
            if (ccGapAnalysis.getCode().equals(CCGapAnalysisResult.NEW.toString())){
                insertNewBCCP(basic, user);
            }
            if (ccGapAnalysis.getCode().equals(CCGapAnalysisResult.REUSE_EXISTING_WITH_UPDATE.toString())){

            }
        }
    }
}
