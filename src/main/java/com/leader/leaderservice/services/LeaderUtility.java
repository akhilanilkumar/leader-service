package com.leader.leaderservice.services;

import com.leader.leaderservice.entity.Leader;
import com.leader.leaderservice.model.DevelopmentDTO;
import com.leader.leaderservice.model.LeaderDTO;
import com.leader.leaderservice.model.PartyDTO;
import org.bouncycastle.util.Arrays;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class LeaderUtility {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String DEV_SERVICE_URI = "http://localhost:9093/development/";

    private static final String PARTY_SERVICE_URI = "http://localhost:9091/party/";

    private LeaderUtility() {
//        Left blank intentionally
    }

    /**
     * Convert Leader entity to Leader DTO
     *
     * @param leaderDTO
     * @return
     */
    public static Leader convertToEntity(LeaderDTO leaderDTO) {
        Leader leader = new Leader();
        leader.setId(leaderDTO.getId());
        leader.setPartyId(leaderDTO.getPartyId());
        leader.setLeaderName(leaderDTO.getLeaderName());
        leader.setLeaderState(leaderDTO.getLeaderState());
        return leader;
    }

    /**
     * Convert Leader DTO to Leader entity
     *
     * @param leader
     * @return
     */
    public static LeaderDTO convertToDTO(Leader leader) {
        LeaderDTO leaderDTO = new LeaderDTO();
        leaderDTO.setId(leader.getId());
        leaderDTO.setPartyId(leader.getPartyId());
        leaderDTO.setLeaderName(leader.getLeaderName());
        leaderDTO.setLeaderState(leader.getLeaderState());
        return leaderDTO;
    }

    public static List<DevelopmentDTO> getDevelopmentDetails(Long partyId, Long leaderId) {
        final String URI = DEV_SERVICE_URI + "works/" + partyId + "/" + leaderId;
        ResponseEntity<DevelopmentDTO[]> devTemplateObj = restTemplate.getForEntity(URI, DevelopmentDTO[].class);
        List<DevelopmentDTO> developmentDTOs = new ArrayList<>();
        if (!Arrays.isNullOrEmpty(devTemplateObj.getBody())) {
            developmentDTOs = List.of(devTemplateObj.getBody());
        }
        return developmentDTOs;
    }


    public static Optional<PartyDTO> findPartyById(Long partyId) {
        return Optional.ofNullable(restTemplate.getForObject(PARTY_SERVICE_URI + partyId, PartyDTO.class));
    }
}
