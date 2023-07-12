package org.oagi.score.gateway.http.api.oas_management.service;

import org.jooq.DSLContext;
import org.jooq.types.ULong;
import org.oagi.score.gateway.http.api.application_management.service.ApplicationConfigurationService;
import org.oagi.score.gateway.http.api.oas_management.data.BieForOasDocListRequest;
import org.oagi.score.gateway.http.api.oas_management.data.BieForOasDocUpdateRequest;
import org.oagi.score.gateway.http.api.oas_management.data.BieForOasDocUpdateResponse;
import org.oagi.score.gateway.http.configuration.security.SessionService;
import org.oagi.score.repo.BusinessInformationEntityRepository;
import org.oagi.score.repo.CoreComponentRepository;
import org.oagi.score.repo.OasDocRepository;
import org.oagi.score.repo.PaginationResponse;
import org.oagi.score.repo.api.ScoreRepositoryFactory;
import org.oagi.score.repo.api.businesscontext.model.GetBusinessContextListRequest;
import org.oagi.score.repo.api.businesscontext.model.GetBusinessContextListResponse;
import org.oagi.score.repo.api.businessterm.model.UpdateBusinessTermResponse;
import org.oagi.score.repo.api.openapidoc.model.*;
import org.oagi.score.repo.component.top_level_asbiep.UpdateTopLevelAsbiepRequest;
import org.oagi.score.service.authentication.AuthenticationService;
import org.oagi.score.service.businesscontext.BusinessContextService;
import org.oagi.score.service.common.data.AccessPrivilege;
import org.oagi.score.service.common.data.AppUser;
import org.oagi.score.service.common.data.PageRequest;
import org.oagi.score.service.common.data.PageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OpenAPIDocService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private ScoreRepositoryFactory scoreRepositoryFactory;
    @Autowired
    private CoreComponentRepository ccRepository;
    @Autowired
    private BusinessInformationEntityRepository bieRepository;
    @Autowired
    private BusinessContextService businessContextService;
    @Autowired
    private OasDocRepository oasDocRepository;
    @Autowired
    private ApplicationConfigurationService applicationConfigurationService;
    @Autowired
    private DSLContext dslContext;

    public GetOasDocResponse getOasDoc(GetOasDocRequest request) {
        GetOasDocResponse response = scoreRepositoryFactory.createOasDocReadRepository().getOasDoc(request);
        return response;
    }

    public GetOasDocListResponse getOasDocList(GetOasDocListRequest request) {
        GetOasDocListResponse response = scoreRepositoryFactory.createOasDocReadRepository().getOasDocList(request);
        return response;
    }

    @Transactional
    public CreateOasDocResponse createOasDoc(CreateOasDocRequest request) {
        CreateOasDocResponse response = scoreRepositoryFactory.createOasDocWriteRepository().createOasDoc(request);
        return response;
    }

    @Transactional
    public UpdateOasDocResponse updateOasDoc(UpdateOasDocRequest request) {
        UpdateOasDocResponse response = scoreRepositoryFactory.createOasDocWriteRepository().updateOasDoc(request);
        return response;
    }

    @Transactional
    public DeleteOasDocResponse DeleteOasDoc(DeleteOasDocRequest request) {
        DeleteOasDocResponse response = scoreRepositoryFactory.createOasDocWriteRepository().deleteOasDoc(request);
        return response;
    }

    public GetBieForOasDocResponse getBieForOasDoc(GetBieForOasDocRequest request) {
        GetBieForOasDocResponse response = scoreRepositoryFactory.createBieForOasDocReadRepository().getBieForOasDoc(request);
        return response;
    }

    public PageResponse<BieForOasDoc> getBieListForOasDoc(AuthenticatedPrincipal user, BieForOasDocListRequest request) {
        PageRequest pageRequest = request.getPageRequest();
        AppUser requester = sessionService.getAppUserByUsername(user);
        PaginationResponse<BieForOasDoc> result = oasDocRepository.selectBieForOasDocLists()
                .setOasDocId(request.getOasDocId())
                .setBusinessContext(request.getBusinessContext())
                .setSort(pageRequest.getSortActive(), pageRequest.getSortDirection())
                .setOffset(pageRequest.getOffset(), pageRequest.getPageSize())
                .fetchInto(BieForOasDoc.class);

        List<BieForOasDoc> bieForOasDocList = result.getResult();
        bieForOasDocList.forEach(bieForOasDoc -> {

            GetBusinessContextListRequest getBusinessContextListRequest =
                    new GetBusinessContextListRequest(authenticationService.asScoreUser(user))
                            .withTopLevelAsbiepIdList(Arrays.asList(bieForOasDoc.getTopLevelAsbiepId()))
                            .withName(request.getBusinessContext());

            getBusinessContextListRequest.setPageIndex(-1);
            getBusinessContextListRequest.setPageSize(-1);

            GetBusinessContextListResponse getBusinessContextListResponse = businessContextService
                    .getBusinessContextList(getBusinessContextListRequest, applicationConfigurationService.isTenantEnabled());

            bieForOasDoc.setBusinessContexts(getBusinessContextListResponse.getResults());
            bieForOasDoc.setAccess(
                    AccessPrivilege.toAccessPrivilege(requester, bieForOasDoc.getOwnerUserId(), bieForOasDoc.getState()).toString()
            );
        });

        PageResponse<BieForOasDoc> response = new PageResponse();
        response.setList(bieForOasDocList);
        response.setPage(pageRequest.getPageIndex());
        response.setSize(pageRequest.getPageSize());
        response.setLength(result.getPageCount());
        return response;
    }


    public PageResponse<BieForOasDoc> selectBieForOasDoc(AuthenticatedPrincipal user, BieForOasDocListRequest request) {
        PageRequest pageRequest = request.getPageRequest();
        AppUser requester = sessionService.getAppUserByUsername(user);
        PaginationResponse<BieForOasDoc> result = oasDocRepository.selectBieForOasDocLists()
                .setOasDocId(request.getOasDocId())
                .setDen(request.getDen())
                .setPropertyTerm(request.getPropertyTerm())
                .setBusinessContext(request.getBusinessContext())
                .setAsccpManifestId(request.getAsccpManifestId())
                .setExcludePropertyTerms(request.getExcludePropertyTerms())
                .setExcludeTopLevelAsbiepIds(request.getExcludeTopLevelAsbiepIds())
                .setStates(request.getStates())
                .setReleaseId(request.getReleaseId())
                .setOwnerLoginIds(request.getOwnerLoginIds())
                .setUpdaterLoginIds(request.getUpdaterLoginIds())
                .setUpdateDate(request.getUpdateStartDate(), request.getUpdateEndDate())
                .setAccess(ULong.valueOf(requester.getAppUserId()), request.getAccess())
                .setOwnedByDeveloper(request.getOwnedByDeveloper())
                .setSort(pageRequest.getSortActive(), pageRequest.getSortDirection())
                .setOffset(pageRequest.getOffset(), pageRequest.getPageSize())
                .fetchInto(BieForOasDoc.class);

        List<BieForOasDoc> bieForOasDocList = result.getResult();
        bieForOasDocList.forEach(bieForOasDoc -> {

            GetBusinessContextListRequest getBusinessContextListRequest =
                    new GetBusinessContextListRequest(authenticationService.asScoreUser(user))
                            .withTopLevelAsbiepIdList(Arrays.asList(bieForOasDoc.getTopLevelAsbiepId()))
                            .withName(request.getBusinessContext());

            getBusinessContextListRequest.setPageIndex(-1);
            getBusinessContextListRequest.setPageSize(-1);

            GetBusinessContextListResponse getBusinessContextListResponse = businessContextService
                    .getBusinessContextList(getBusinessContextListRequest, applicationConfigurationService.isTenantEnabled());

            bieForOasDoc.setBusinessContexts(getBusinessContextListResponse.getResults());
            bieForOasDoc.setVerbs(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
            bieForOasDoc.setMessageBody(Arrays.asList("", "requestBody", "responseBody"));
        });

        PageResponse<BieForOasDoc> response = new PageResponse();
        response.setList(bieForOasDocList);
        response.setPage(pageRequest.getPageIndex());
        response.setSize(pageRequest.getPageSize());
        response.setLength(result.getPageCount());
        return response;
    }

    @Transactional
    public AddBieForOasDocResponse addBieForOasDoc(AuthenticatedPrincipal user, AddBieForOasDocRequest request) {
        BigInteger userId = sessionService.userId(user);
        if (userId == null) {
            throw new IllegalArgumentException("`userId` parameter must not be null.");
        }
        if (request.getOasDocId() == null) {
            throw new IllegalArgumentException("`oasDocId` parameter must not be null.");
        }
        if (request.getTopLevelAsbiepId() == null) {
            throw new IllegalArgumentException("`TopLevelAsbiepId` parameter must not be null.");
        }

        long millis = System.currentTimeMillis();

        ULong oasMessageBodyId = oasDocRepository.insertOasMessageBody()
                .setUserId(userId)
                .setTopLevelAsbiepId(request.getTopLevelAsbiepId())
                .setTimestamp(millis)
                .execute();

        ULong oasResourceId = oasDocRepository.insertOasResource()
                .setUserId(userId)
                .setOasDocId(request.getOasDocId())
                .setPath(request.getPath())
                .setRef(request.getRef())
                .setTimestamp(millis)
                .execute();

        ULong oasOperationId = oasDocRepository.insertOasOperation()
                .setUserId(userId)
                .setOperationId(request.getOperationId())
                .setOasResourceId(oasResourceId)
                .setVerb(request.getVerb())
                .setSummary(request.getSummary())
                .setDescription(request.getDescriptionForOperation())
                .setDeprecated(request.isDeprecatedForOperation())
                .setTimestamp(millis)
                .execute();

        if (request.isOasRequest()) {
            ULong oasRequestId = oasDocRepository.insertOasRequest()
                    .setUserId(userId)
                    .setOasOperationId(oasOperationId)
                    .setOasMessageBodyId(oasMessageBodyId)
                    .setDescription(request.getDescription())
                    .setMakeArrayIndicator(request.isMakeArrayIndicator())
                    .setSuppressRootIndicator(request.isSuppressRootIndicator())
                    .setIncludePaginationIndicator(request.isIncludePaginationIndicator())
                    .setIncludeMetaHeaderIndicator(request.isIncludeMetaHeaderIndicator())
                    .setRequired(request.isRequiredForRequestBody())
                    .setTimestamp(millis)
                    .execute();
        } else {
            ULong oasResponseId = oasDocRepository.insertOasResponse()
                    .setUserId(userId)
                    .setOasOperationId(oasOperationId)
                    .setOasMessageBodyId(oasMessageBodyId)
                    .setDescription(request.getDescription())
                    .setMakeArrayIndicator(request.isMakeArrayIndicator())
                    .setSuppressRootIndicator(request.isSuppressRootIndicator())
                    .setIncludePaginationIndicator(request.isIncludePaginationIndicator())
                    .setIncludeMetaHeaderIndicator(request.isIncludeMetaHeaderIndicator())
                    .setTimestamp(millis)
                    .execute();
        }
        AddBieForOasDocResponse response = scoreRepositoryFactory.createBieForOasDocReadRepository().addBieForOasDoc(request);
        return response;
    }

    public boolean checkOasDocUniqueness(OasDoc oasDoc) {
        return oasDocRepository.checkOasDocUniqueness(oasDoc);
    }

    public boolean checkOasDocTitleUniqueness(OasDoc oasDoc) {
        return oasDocRepository.checkOasDocTitleUniqueness(oasDoc);
    }
    @Transactional
    public UpdateBieForOasDocResponse updateDetails(AuthenticatedPrincipal user, UpdateBieForOasDocRequest request) {

        UpdateBieForOasDocResponse response =
                scoreRepositoryFactory.createBieForOasDocWriteRepository()
                        .updateBieForOasDoc(request);
        return response;
    }
}
