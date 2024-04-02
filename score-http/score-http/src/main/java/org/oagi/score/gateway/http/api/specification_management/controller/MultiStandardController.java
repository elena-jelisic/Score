package org.oagi.score.gateway.http.api.specification_management.controller;

import org.oagi.score.gateway.http.api.specification_management.service.MultiStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiStandardController {

    @Autowired
    MultiStandardService service;

    @RequestMapping(value = "/import_specification", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSimpleNamespaces() {
        service.insertNewSpecification();
        return "Success";
    }

}
