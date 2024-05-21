package org.oagi.score.gateway.http.api.specification_management.controller;

import org.checkerframework.checker.units.qual.A;
import org.oagi.score.gateway.http.api.bie_management.data.BieCreateRequest;
import org.oagi.score.gateway.http.api.specification_management.service.CCGapAnalysisService;
import org.oagi.score.gateway.http.api.specification_management.service.FlatBCCService;
import org.oagi.score.gateway.http.api.specification_management.service.FlatBCCService_v2;
import org.oagi.score.gateway.http.api.specification_management.service.MultiStandardService;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.SpecificationAggregateComponentRecord;
import org.oagi.score.gateway.http.api.specification_management.data.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MultiStandardController {

    @Autowired
    private MultiStandardService importSpecService;
    @Autowired
    private CCGapAnalysisService ccGapAnalysisService;
    @Autowired
    private FlatBCCService_v2 flatBccService;

    @RequestMapping(value = "/import_specification", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertNewSpecification(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                         @RequestBody BieCreateRequest bieCreateRequest) {
        importSpecService.insertNewSpecification(user);
        return "Success";
    }

    @RequestMapping(value = "/cc_gap_analysis", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String coreComponentGapAnalysis(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                         @RequestBody BieCreateRequest bieCreateRequest) {
        Specification spec = new Specification();
        spec.setSpecificationName("QIF 3.0.0");
        ccGapAnalysisService.analyzeCoreComponents(user, spec);
        return "Success";
    }

    @RequestMapping(value = "/approve_cc_gap_analysis", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String coreComponentGapAnalysisApproval(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                           @RequestBody BieCreateRequest bieCreateRequest) {
        Specification spec = new Specification();
        spec.setSpecificationName("QIF 3.0.0");
        List<SpecificationAggregateComponentRecord> aggregates = new ArrayList<>();
        ccGapAnalysisService.approveACCAnalysisResults(aggregates, user, spec);
        ccGapAnalysisService.approveDTAnalysisResults (user, spec);
        ccGapAnalysisService.approveAssociationAnalysisResults (user, spec);
        ccGapAnalysisService.approveBasicAnalysisResults (user, spec);
        return "Success";
    }
    @RequestMapping(value = "/flat_bcc", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String flatBccUpdate(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                                   @RequestBody BieCreateRequest bieCreateRequest) {
        flatBccService.updateFlatBccTable();
        return "Success";
    }

}
