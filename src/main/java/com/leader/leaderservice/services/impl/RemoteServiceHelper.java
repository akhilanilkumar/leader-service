package com.leader.leaderservice.services.impl;

import com.leader.leaderservice.client.DevelopmentClient;
import com.leader.leaderservice.client.PartyClient;
import com.leader.leaderservice.model.DevelopmentDTO;
import com.leader.leaderservice.model.PartyDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Log4j2
public class RemoteServiceHelper {

    @Autowired
    private PartyClient partyClient;

    @Autowired
    private DevelopmentClient developmentClient;

    public List<DevelopmentDTO> getDevelopmentDetails(Long partyId, Long leaderId) {
        List<DevelopmentDTO> developmentDetails = developmentClient.getDevelopmentDetails(partyId, leaderId);
        log.debug("Response from Development Service {}", developmentDetails);
        return developmentDetails;
    }


    public Optional<PartyDTO> findPartyById(Long partyId) {
        Optional<PartyDTO> partyDetailsById = Optional.ofNullable(partyClient.getPartyDetailsById(partyId));
        log.debug("Response from Party Service {}", partyDetailsById);
        return partyDetailsById;
    }
}
