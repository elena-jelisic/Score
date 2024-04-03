package org.oagi.score.gateway.http.api.specification_management.controller;

import org.oagi.score.gateway.http.api.bie_management.data.BieCreateRequest;
import org.oagi.score.gateway.http.api.specification_management.service.MultiStandardService;
import org.oagi.score.repo.api.user.model.ScoreUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiStandardController {

    @Autowired
    MultiStandardService service;

    @RequestMapping(value = "/import_specification", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String insertNewSpecification(@AuthenticationPrincipal AuthenticatedPrincipal user,
                                         @RequestBody BieCreateRequest bieCreateRequest) {
        service.insertNewSpecification(user);
        return "Success";
    }

}
