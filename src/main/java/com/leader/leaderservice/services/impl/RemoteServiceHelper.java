package com.leader.leaderservice.services.impl;

import com.leader.leaderservice.model.DevelopmentDTO;
import com.leader.leaderservice.model.PartyDTO;
import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoteServiceHelper {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${uri.party-service}")
    private String PARTY_SERVICE_URI;
    @Value("${uri.development-service}")
    private String DEV_SERVICE_URI;

    public List<DevelopmentDTO> getDevelopmentDetails(Long partyId, Long leaderId) {
        final String URI = DEV_SERVICE_URI + "works/" + partyId + "/" + leaderId;
        ResponseEntity<DevelopmentDTO[]> devTemplateObj = restTemplate.getForEntity(URI, DevelopmentDTO[].class);
        List<DevelopmentDTO> developmentDTOs = new ArrayList<>();
        if (!Arrays.isNullOrEmpty(devTemplateObj.getBody())) {
            developmentDTOs = List.of(devTemplateObj.getBody());
        }
        return developmentDTOs;
    }


    public Optional<PartyDTO> findPartyById(Long partyId) {
        return Optional.ofNullable(restTemplate.getForObject(PARTY_SERVICE_URI + partyId, PartyDTO.class));
    }
}
