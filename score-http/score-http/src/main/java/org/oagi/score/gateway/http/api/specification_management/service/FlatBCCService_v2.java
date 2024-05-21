package org.oagi.score.gateway.http.api.specification_management.service;

import org.jooq.types.ULong;
import org.oagi.score.gateway.http.api.specification_management.data.FlatBcc;
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
public class FlatBCCService_v2 {
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
    List<String> relatedACCPathMap;

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
        relatedACCPathMap = new ArrayList<>();
        //get the list of the latest ACCs
        List<AccManifestRecord> accManifestList = accReadRepo.getAllLatestACCs();
        accManifestMap = accManifestList.stream().collect(Collectors.toMap(AccManifestRecord::getAccManifestId, acc -> acc));
        List<AccRecord> accList = accReadRepo.getAllACCs();
        accMap = accList.stream().collect(Collectors.toMap(AccRecord::getAccId, acc -> acc));

        //get the list of the latest BCCPs and BCCs
        List<BccpManifestRecord> bccpList = bccpReadRepo.getAllLatestBCCPs();
        bccpMap = bccpList.stream().collect(Collectors.toMap(BccpManifestRecord::getBccpManifestId, bccp -> bccp));

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

        //get the list of the latest ASCCPs and ASCCs
        List<AsccpManifestRecord> asccpList = asccpReadRepo.getAllLatestASCCPs();
        asccpMap = asccpList.stream().collect(Collectors.toMap(AsccpManifestRecord::getAsccpManifestId, asccp -> asccp));

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

        //get the list of the latest DTSCs
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


        List<FlatBccRecord> flatBccRecords = new ArrayList<>();

        for (AccManifestRecord accManifest : accManifestList) {
            AccRecord processed = accMap.get(accManifest.getAccId());
            if (processed.getObjectClassTerm().equals("Test Results")){
                accRelatedAccList = new ArrayList<>();

                getAllRelatedACCs(processed, accManifest, null);

                if (accBCCMap.get(accManifest.getAccManifestId()) != null) {
                    for (int i = 0; i < accBCCMap.get(accManifest.getAccManifestId()).size(); i++) {
                        flatBcc.setAccId(processed.getAccId().toBigInteger());
                        flatBcc.setBccID(accBCCMap.get(accManifest.getAccManifestId()).get(i).getBccId().toBigInteger());
                        flatBcc.setPath(processed.getObjectClassTerm());
                        flatBcc.setDtSCId(null);
                        flatBccRecords.add(flatBccRepo.insertFlatBcc(flatBcc));
                        BccpManifestRecord bccp = bccpMap.get(accBCCMap.get(accManifest.getAccManifestId()).get(i).getToBccpManifestId());
                        if (dtDTSCMap.get(bccp.getBdtManifestId()) != null) {
                            for (int j= 0; j < dtDTSCMap.get(bccp.getBdtManifestId()).size(); j++) {
                                flatBcc.setAccId(processed.getAccId().toBigInteger());
                                flatBcc.setBccID(accBCCMap.get(accManifest.getAccManifestId()).get(i).getBccId().toBigInteger());
                                flatBcc.setPath(processed.getObjectClassTerm());
                                flatBcc.setDtSCId(dtDTSCMap.get(bccp.getBdtManifestId()).get(j).getDtScId().toBigInteger());
                                flatBccRecords.add(flatBccRepo.insertFlatBcc(flatBcc));
                            }
                        }
                    }
                }
                if (!relatedACCPathMap.isEmpty()) {
                    for (String path: relatedACCPathMap) {
                        String associatedObjectClassTerm = getObjectClassTermFromPath (path);
                        ULong associatedAccManifestId = getAccManifestIdByObjectClassTerm (associatedObjectClassTerm);
                        if (accBCCMap.get(associatedAccManifestId) != null) {
                            for (int i = 0; i < accBCCMap.get(associatedAccManifestId).size(); i++) {
                                BccpManifestRecord bccp = bccpMap.get(accBCCMap.get(associatedAccManifestId).get(i).getToBccpManifestId());
                                flatBcc.setAccId(processed.getAccId().toBigInteger());
                                flatBcc.setBccID(accBCCMap.get(associatedAccManifestId).get(i).getBccId().toBigInteger());
                                flatBcc.setDtSCId(null);
                                flatBcc.setPath(path);
                                flatBccRecords.add(flatBccRepo.insertFlatBcc(flatBcc));
                                if (dtDTSCMap.get(bccp.getBdtManifestId()) != null) {
                                    for (int j = 0; j < dtDTSCMap.get(bccp.getBdtManifestId()).size(); j++) {
                                        flatBcc.setAccId(processed.getAccId().toBigInteger());
                                        flatBcc.setBccID(accBCCMap.get(associatedAccManifestId).get(i).getBccId().toBigInteger());
                                        flatBcc.setPath(path);
                                        flatBcc.setDtSCId(dtDTSCMap.get(bccp.getBdtManifestId()).get(j).getDtScId().toBigInteger());
                                        flatBccRecords.add(flatBccRepo.insertFlatBcc(flatBcc));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        flatBccRepo.insertFlatBccList(flatBccRecords);
    }

    private void getAllRelatedACCs(AccRecord processed, AccManifestRecord processedAccManifest, String paths) {
        if (processedAccManifest.getBasedAccManifestId() != null) {
            AccManifestRecord basedAccManifest = accManifestMap.get(processedAccManifest.getBasedAccManifestId());
            if (basedAccManifest != null) {
                accRelatedAccList.add(basedAccManifest);
                AccRecord basedAcc = accMap.get(basedAccManifest.getAccId());
                if (paths == null) {
                    relatedACCPathMap.add(processed.getObjectClassTerm() + ". " + basedAcc.getObjectClassTerm());
                    paths = getTheLatestPath();
                    getAllRelatedACCs(basedAcc, basedAccManifest, paths);
                } else {
                    if (!containedInTheList(paths, basedAcc.getObjectClassTerm())){
                        relatedACCPathMap.add(paths.concat(". ").concat(basedAcc.getObjectClassTerm()));
                        paths = getTheLatestPath();
                        getAllRelatedACCs(basedAcc, basedAccManifest, paths);
                    }
                }
            }
        }

        List<AsccManifestRecord> asccList = accASCCMap.get(processedAccManifest.getAccManifestId());
        if (asccList != null) {
            for (AsccManifestRecord ascc : asccList) {
                AccManifestRecord associatedAccManifest = accManifestMap.get(asccpMap.get(ascc.getToAsccpManifestId()).getRoleOfAccManifestId());
                if (associatedAccManifest != null) {
                    AccRecord associatedACC = accMap.get(associatedAccManifest.getAccId());
                    if (!associatedACC.getObjectClassTerm().equals(processed.getObjectClassTerm())){
                        accRelatedAccList.add(associatedAccManifest);
                        if (paths == null) {
                            relatedACCPathMap.add(processed.getObjectClassTerm() + ". " + associatedACC.getObjectClassTerm());
                            getAllRelatedACCs(associatedACC, associatedAccManifest, processed.getObjectClassTerm() );
                        } else {
                            if (!containedInTheList(paths, associatedACC.getObjectClassTerm())){
                                paths = getSecondToTheLatestPath();
                                relatedACCPathMap.add(paths.concat(". ").concat(associatedACC.getObjectClassTerm()));
                                getAllRelatedACCs(associatedACC, associatedAccManifest, paths);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean containedInTheList (String fullPath, String path){
        String[] pathPortions = fullPath.split("\\.");
        boolean result = false;
        for (int i=0; i<pathPortions.length; i++){
            if (pathPortions[i].trim().equals(path)){
                result = true;
                break;
            } else{
                result = false;
            }
        }
        return result;
    }

    private String getTheLatestPath(){
        return relatedACCPathMap.get(relatedACCPathMap.size()-1);
    }

    private String getSecondToTheLatestPath(){
        return relatedACCPathMap.get(relatedACCPathMap.size()-2);
    }

    private String getObjectClassTermFromPath (String path){
        String[] pathPortions = path.split("\\.");
        return pathPortions[pathPortions.length-1].trim();
    }

    private ULong getAccManifestIdByObjectClassTerm (String objectClassTerm){
        ULong manifestID = ULong.valueOf(0);
        for (ULong accManifestID : accManifestMap.keySet()){
            String currentObjectClassTerm = accManifestMap.get(accManifestID).getDen().substring(0, accManifestMap.get(accManifestID).getDen().indexOf(". Details")).trim();
            if (currentObjectClassTerm.equals(objectClassTerm)){
                manifestID = accManifestID;
                break;
            }
        }
        return manifestID;
    }
}
