package org.oagi.score.gateway.http.api.specification_management.service;

import org.oagi.score.gateway.http.api.specification_management.data.SimpleSource;
import org.oagi.score.gateway.http.api.specification_management.data.SimpleSpecification;
import org.oagi.score.gateway.http.api.specification_management.data.SpecificationList;
import org.oagi.score.gateway.http.api.specification_management.data.SpecificationListRequest;
import org.oagi.score.repo.component.specification.SpecificationRepository;
import org.oagi.score.service.common.data.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SpecificationService {
    @Autowired
    SpecificationRepository specRepo;

    @Transactional
    public List<SimpleSpecification> getAllSpecifications(AuthenticatedPrincipal user) {
        return specRepo.getSpecifications();
    }

    @Transactional
    public List<SimpleSource> getAllSources (AuthenticatedPrincipal user){
        return specRepo.getSources();
    }

    @Transactional
    public PageResponse<SpecificationList> getSpecificationComponents (SpecificationListRequest request){
        return specRepo.getSpecificationComponents(request);
    }
}
