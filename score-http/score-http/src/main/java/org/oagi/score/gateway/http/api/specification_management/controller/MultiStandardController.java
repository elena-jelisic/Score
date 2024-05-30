package org.oagi.score.gateway.http.api.specification_management.controller;

import org.oagi.score.gateway.http.api.bie_management.data.BieCreateRequest;
import org.oagi.score.gateway.http.api.specification_management.data.*;
import org.oagi.score.gateway.http.api.specification_management.service.*;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.SpecificationAggregateComponentRecord;
import org.oagi.score.service.common.data.PageRequest;
import org.oagi.score.service.common.data.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class MultiStandardController {

    @Autowired
    private MultiStandardService importSpecService;
    @Autowired
    private CCGapAnalysisService ccGapAnalysisService;
    @Autowired
    private FlatBCCService_v2 flatBccService;
    @Autowired
    private SpecificationService specService;

    @RequestMapping(value = "/import_specification", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insertNewSpecification(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                         @RequestBody BieCreateRequest bieCreateRequest) {
        importSpecService.insertNewSpecification(user);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/specifications_list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SimpleSpecification> getAllSpecifications(@AuthenticationPrincipal AuthenticatedPrincipal user) {
        return specService.getAllSpecifications(user);
    }

    @RequestMapping(value = "/source_list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SimpleSource> getAllSourceNames(@AuthenticationPrincipal AuthenticatedPrincipal user) {
        return specService.getAllSources(user);
    }

    @RequestMapping(value = "/cc_gap_analysis", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity coreComponentGapAnalysis(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                         @RequestBody BieCreateRequest bieCreateRequest) {
        Specification spec = new Specification();
        spec.setSpecificationName("QIF 3.0.0");
        ccGapAnalysisService.analyzeCoreComponents(user, spec);
        return ResponseEntity.accepted().build();
    }
    @RequestMapping(value = "/specification_list", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<SpecificationList> getSpecificationComponents(
                @RequestParam(name = "specificationId", required = false) Long specificationId,
                @RequestParam(name = "sourceId", required = false) Long sourceId,
                @RequestParam(name = "sortActive") String sortActive,
                @RequestParam(name = "sortDirection") String sortDirection,
                @RequestParam(name = "pageIndex") int pageIndex,
                @RequestParam(name = "pageSize") int pageSize) {

            SpecificationListRequest request = new SpecificationListRequest();
            request.setSpecificationId(specificationId);
            request.setSourceId(sourceId);

            PageRequest pageRequest = new PageRequest();
            pageRequest.setSortActive(sortActive);
            pageRequest.setSortDirection(sortDirection);
            pageRequest.setPageIndex(pageIndex);
            pageRequest.setPageSize(pageSize);
            request.setPageRequest(pageRequest);

            return specService.getSpecificationComponents(request);
    }

    @RequestMapping(value = "/approve_cc_gap_analysis", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity coreComponentGapAnalysisApproval(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                           @RequestBody BieCreateRequest bieCreateRequest) {
        Specification spec = new Specification();
        spec.setSpecificationName("QIF 3.0.0");
        List<SpecificationAggregateComponentRecord> aggregates = new ArrayList<>();
        ccGapAnalysisService.approveACCAnalysisResults(aggregates, user, spec);
        ccGapAnalysisService.approveDTAnalysisResults (user, spec);
        ccGapAnalysisService.approveAssociationAnalysisResults (user, spec);
        ccGapAnalysisService.approveBasicAnalysisResults (user, spec);
        return ResponseEntity.accepted().build();
    }
    @RequestMapping(value = "/flat_bcc", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity flatBccUpdate(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                        @RequestBody BieCreateRequest bieCreateRequest) {
        flatBccService.updateFlatBccTable();
        return ResponseEntity.accepted().build();
    }

}
