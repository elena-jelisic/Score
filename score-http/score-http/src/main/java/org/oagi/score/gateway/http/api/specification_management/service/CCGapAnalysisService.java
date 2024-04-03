package org.oagi.score.gateway.http.api.specification_management.service;

import org.jooq.types.ULong;
import org.oagi.score.gateway.http.api.cc_management.data.CcAccCreateRequest;
import org.oagi.score.gateway.http.api.cc_management.service.CcNodeService;
import org.oagi.score.gateway.http.api.namespace_management.data.Namespace;
import org.oagi.score.gateway.http.api.namespace_management.service.NamespaceService;
import org.oagi.score.gateway.http.api.specification_management.data.CCGapAnalysisResult;
import org.oagi.score.gateway.http.api.specification_management.data.SpecComponentState;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.*;
import org.oagi.score.gateway.http.api.specification_management.data.Specification;
import org.oagi.score.repo.component.acc.AccReadRepository;
import org.oagi.score.repo.component.acc.AccWriteRepository;
import org.oagi.score.repo.component.acc.UpdateAccPropertiesRepositoryRequest;
import org.oagi.score.repo.component.release.ReleaseRepository;
import org.oagi.score.repo.component.specification.SpecificationRepository;
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
    List<SpecificationAggregateComponentRecord> aggregatesRecordList;
    SpecificationRecord specRecord;

    @Transactional
    public void analyzeCoreComponents(AuthenticatedPrincipal user, Specification spec) {
        specRecord = specRepo.getSpecificationByName(spec.getSpecificationName());
        aggregateComponentGapAnalysis();
    }

    @Transactional
    public void approveACCAnalysisResults(List<SpecificationAggregateComponentRecord> aggregates, AuthenticatedPrincipal user, Specification spec){
        specRecord = specRepo.getSpecificationByName(spec.getSpecificationName());
        aggregatesRecordList = specRepo.getAnalyzedSpecificationAggregates(specRecord.getSpecificationId());
        for (SpecificationAggregateComponentRecord acc: aggregatesRecordList){
            CcGapAnalysisResultCodeRecord ccGapAnalysis = specRepo.getCCGapAnalysisCodeByID(acc.getGapAnalysisCodeId());
            if (ccGapAnalysis.getCode().equals(CCGapAnalysisResult.NEW.toString())){
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
            }
            if (ccGapAnalysis.getCode().equals(CCGapAnalysisResult.REUSE_EXISTING_WITH_UPDATE.toString())){

            }
        }
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
}
