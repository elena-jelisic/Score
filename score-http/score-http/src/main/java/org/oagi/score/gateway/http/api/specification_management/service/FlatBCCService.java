package org.oagi.score.gateway.http.api.specification_management.service;

import org.jooq.types.ULong;
import org.oagi.score.gateway.http.api.specification_management.data.FlatBcc;
import org.oagi.score.repo.api.corecomponent.model.AccManifest;
import org.oagi.score.repo.api.impl.jooq.entity.tables.records.*;
import org.oagi.score.repo.component.acc.AccReadRepository;
import org.oagi.score.repo.component.ascc.AsccReadRepository;
import org.oagi.score.repo.component.asccp.AsccpReadRepository;
import org.oagi.score.repo.component.bcc.BccReadRepository;
import org.oagi.score.repo.component.bccp.BccpReadRepository;
import org.oagi.score.repo.component.dt.BdtReadRepository;
import org.oagi.score.repo.component.flat_bcc.FlatBccRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class FlatBCCService {
    @Autowired
    private FlatBccRepo flatBccRepo;
    @Autowired
    private AccReadRepository accReadRepo;
    @Autowired
    private BccReadRepository bccReadRepo;
    @Autowired
    private BdtReadRepository dtRepository;
    @Autowired
    private AsccReadRepository asccReadRepo;
    @Autowired
    private AsccpReadRepository asccpReadRepo;
    @Autowired
    private BccpReadRepository bccpReadRepo;
    List<AccManifestRecord> accRelatedAccList;
    Map<ULong, List<DtScManifestRecord>> dtDTSCMap;
    Map<ULong, AccManifestRecord> accManifestMap;
    Map<ULong, AccRecord> accMap;
    Map<ULong, List<AsccManifestRecord>> accASCCMap;
    Map<ULong, List<BccManifestRecord>> accBCCMap;
    Map<ULong, AsccpManifestRecord> asccpMap;
    Map<ULong, BccpManifestRecord> bccpMap;
    Map<AccManifestRecord, String> relatedACCPathMap;

    @Transactional
    public void updateFlatBccTable() {
        FlatBcc flatBcc = new FlatBcc();
        accASCCMap = new HashMap<>();
        accBCCMap = new HashMap<>();
        accMap = new HashMap<>();
        accManifestMap = new HashMap<>();
        asccpMap = new HashMap<>();
        bccpMap = new HashMap<>();
        dtDTSCMap = new HashMap<>();
        relatedACCPathMap = new HashMap<>();
        List<AccManifestRecord> accManifestList = accReadRepo.getAllLatestACCs();
        accManifestMap = accManifestList.stream().collect(Collectors.toMap(AccManifestRecord::getAccManifestId, acc -> acc));
        List<AccRecord> accList = accReadRepo.getAllACCs();
        accMap = accList.stream().collect(Collectors.toMap(AccRecord::getAccId, acc -> acc));
        List<BccpManifestRecord> bccpList = bccpReadRepo.getAllLatestBCCPs();
        bccpMap = bccpList.stream().collect(Collectors.toMap(BccpManifestRecord::getBccpManifestId, bccp -> bccp));
        List<AsccManifestRecord> asccList = asccReadRepo.getAllLatestASCCs();
        for (AsccManifestRecord ascc : asccList) {
            if (accASCCMap.containsKey(ascc.getFromAccManifestId())) {
                accASCCMap.get(ascc.getFromAccManifestId()).add(ascc);
            } else {
                ArrayList<AsccManifestRecord> list = new ArrayList<>();
                list.add(ascc);
                accASCCMap.put(ascc.getFromAccManifestId(), list);
            }
        }
        List<BccManifestRecord> bccList = bccReadRepo.getAllLatestBCCs();
        for (BccManifestRecord bcc : bccList) {
            if (accBCCMap.containsKey(bcc.getFromAccManifestId())) {
                accBCCMap.get(bcc.getFromAccManifestId()).add(bcc);
            } else {
                ArrayList<BccManifestRecord> list = new ArrayList<>();
                list.add(bcc);
                accBCCMap.put(bcc.getFromAccManifestId(), list);
            }
        }
        List<AsccpManifestRecord> asccpList = asccpReadRepo.getAllLatestASCCPs();
        asccpMap = asccpList.stream().collect(Collectors.toMap(AsccpManifestRecord::getAsccpManifestId, asccp -> asccp));
        List<DtScManifestRecord> dtSCList = dtRepository.getLatestDTSCList();
        for (DtScManifestRecord dtsc : dtSCList) {
            if (dtDTSCMap.containsKey(dtsc.getOwnerDtManifestId())) {
                dtDTSCMap.get(dtsc.getOwnerDtManifestId()).add(dtsc);
            } else {
                ArrayList<DtScManifestRecord> list = new ArrayList<>();
                list.add(dtsc);
                dtDTSCMap.put(dtsc.getOwnerDtManifestId(), list);
            }
        }
        for (AccManifestRecord accManifest : accManifestList) {
            AccRecord processed = accMap.get(accManifest.getAccId());
            accRelatedAccList = new ArrayList<>();
            getAllRelatedACCs(accManifest);
            if (accBCCMap.get(accManifest.getAccManifestId()) != null) {
                for (BccManifestRecord bcc : accBCCMap.get(accManifest.getAccManifestId())) {
                    flatBcc.setAccId(processed.getAccId().toBigInteger());
                    flatBcc.setBccID(bcc.getBccId().toBigInteger());
                    flatBcc.setPath(processed.getObjectClassTerm());
                    flatBcc.setDtSCId(null);
                    flatBccRepo.insertFlatBcc(flatBcc);
                    BccpManifestRecord bccp = bccpMap.get(bcc.getToBccpManifestId());
                    if (dtDTSCMap.get(bccp.getBdtManifestId()) != null) {
                        for (DtScManifestRecord dtSC : dtDTSCMap.get(bccp.getBdtManifestId())) {
                            flatBcc.setAccId(processed.getAccId().toBigInteger());
                            flatBcc.setBccID(bcc.getBccId().toBigInteger());
                            flatBcc.setPath(processed.getObjectClassTerm());
                            flatBcc.setDtSCId(dtSC.getDtScId().toBigInteger());
                            flatBccRepo.insertFlatBcc(flatBcc);
                        }
                    }
                }
            }
            if (!accRelatedAccList.isEmpty()) {
                for (AccManifestRecord relatedACCManifest : accRelatedAccList) {
                    String path = relatedACCPathMap.get(relatedACCManifest);
                    if (accBCCMap.get(relatedACCManifest.getAccManifestId()) != null) {
                        for (BccManifestRecord bcc : accBCCMap.get(relatedACCManifest.getAccManifestId())) {
                            BccpManifestRecord bccp = bccpMap.get(bcc.getToBccpManifestId());
                            flatBcc.setAccId(processed.getAccId().toBigInteger());
                            flatBcc.setBccID(bcc.getBccId().toBigInteger());
                            flatBcc.setDtSCId(null);
                            flatBcc.setPath(path);
                            flatBccRepo.insertFlatBcc(flatBcc);
                            if (dtDTSCMap.get(bccp.getBdtManifestId()) != null) {
                                for (DtScManifestRecord dtSC : dtDTSCMap.get(bccp.getBdtManifestId())) {
                                    flatBcc.setAccId(processed.getAccId().toBigInteger());
                                    flatBcc.setBccID(bcc.getBccId().toBigInteger());
                                    flatBcc.setPath(path);
                                    flatBcc.setDtSCId(dtSC.getDtScId().toBigInteger());
                                    flatBccRepo.insertFlatBcc(flatBcc);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void getAllRelatedACCs(AccManifestRecord accManifest) {
        String path = relatedACCPathMap.get(accManifest);
        if (accManifest.getBasedAccManifestId() != null) {
            AccManifestRecord basedAccManifest = accManifestMap.get(accManifest.getBasedAccManifestId());
            if (basedAccManifest != null) {
                accRelatedAccList.add(basedAccManifest);
                AccRecord basedAcc = accMap.get(basedAccManifest.getAccId());
                if (path == null) {
                    relatedACCPathMap.put(basedAccManifest, basedAcc.getObjectClassTerm());
                } else {
                    relatedACCPathMap.put(basedAccManifest, path.concat(". ").concat(basedAcc.getObjectClassTerm()));
                }
                getAllRelatedACCs(basedAccManifest);
            }
        }
        List<AsccManifestRecord> asccList = accASCCMap.get(accManifest.getAccManifestId());
        if (asccList != null) {
            for (AsccManifestRecord ascc : asccList) {
                AccManifestRecord associatedAccManifest = accManifestMap.get(asccpMap.get(ascc.getToAsccpManifestId()).getRoleOfAccManifestId());
                getAllRelatedACCs(associatedAccManifest);
                AccRecord associatedACC = accMap.get(associatedAccManifest.getAccId());
                if (associatedACC != null) {
                    accRelatedAccList.add(associatedAccManifest);
                    if (path == null) {
                        relatedACCPathMap.put(associatedAccManifest, associatedACC.getObjectClassTerm());
                    } else {
                        relatedACCPathMap.put(associatedAccManifest, path.concat(". ").concat(associatedACC.getObjectClassTerm()));
                    }

                }
            }
        }
    }
}
