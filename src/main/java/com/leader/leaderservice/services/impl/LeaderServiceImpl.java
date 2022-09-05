package com.leader.leaderservice.services.impl;

import com.leader.leaderservice.entity.Leader;
import com.leader.leaderservice.exception.NoSuchLeaderExistException;
import com.leader.leaderservice.exception.NoSuchPartyExistException;
import com.leader.leaderservice.model.DevelopmentDTO;
import com.leader.leaderservice.model.LeaderDTO;
import com.leader.leaderservice.model.LeaderDevDTO;
import com.leader.leaderservice.repository.LeaderRepository;
import com.leader.leaderservice.services.LeaderService;
import com.leader.leaderservice.services.LeaderUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderServiceImpl implements LeaderService {

    @Autowired
    private LeaderRepository leaderRepository;

    /**
     * List all the development works assigned.
     *
     * @param partyId
     * @param leaderId
     * @return LeaderDevDTO
     * @throws NoSuchLeaderExistException
     */
    @Override
    public LeaderDevDTO getDevWorksAssigned(Long partyId, Long leaderId) throws NoSuchLeaderExistException {
        LeaderDevDTO leaderDevDTO = new LeaderDevDTO();
//        Find Leader details
        Leader leader = leaderRepository.findById(leaderId)
                .orElseThrow(() -> new NoSuchLeaderExistException("No matching records found for Leader: " + leaderId));
        leaderDevDTO.setLeaderDTO(LeaderUtility.convertToDTO(leader));

//        Find the development work details
        List<DevelopmentDTO> developmentDTOS = LeaderUtility.getDevelopmentDetails(partyId, leaderId);
        leaderDevDTO.setDevelopmentDTO(developmentDTOS);
        return leaderDevDTO;
    }

    /**
     * Register with a Party
     *
     * @param leaderDTO
     * @return
     * @throws NoSuchPartyExistException
     */
    @Override
    public LeaderDTO registerALeader(LeaderDTO leaderDTO) throws NoSuchPartyExistException {
        // Check if party is already existing, otherwise throw exception
        LeaderUtility.findPartyById(leaderDTO.getPartyId())
                .orElseThrow(() -> new NoSuchPartyExistException("No matching records found for a Party: " + leaderDTO.getPartyId()));
        Leader leader = LeaderUtility.convertToEntity(leaderDTO);
        Leader savedLeader = leaderRepository.save(leader);
        return LeaderUtility.convertToDTO(savedLeader);
    }

    /**
     * Get leader details using party id
     *
     * @param partyId
     * @return
     */
    @Override
    public List<LeaderDTO> getLeadersByPartyId(Long partyId) {
        List<Leader> leaders = leaderRepository.findByPartyId(partyId).orElseGet(List::of);
        return leaders.stream().map(LeaderUtility::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Get leader details using party id and leader id
     *
     * @param partyId
     * @param id
     * @return
     */
    @Override
    public LeaderDTO getLeaderByPartyIdAndLeaderId(Long partyId, Long id) {
        return leaderRepository.findByIdAndPartyId(id, partyId).map(LeaderUtility::convertToDTO).orElse(null);
    }

    /**
     * Delete leader using party id and leader id
     *
     * @param partyId
     * @param id
     * @return
     */
    @Override
    public boolean deleteLeaderByPartyIdAndLeaderId(Long partyId, Long id) {
        leaderRepository.deleteAllByIdAndPartyId(id, partyId);
        return true;
    }

    /**
     * Get leader details using leader id
     *
     * @param id
     * @return
     */
    @Override
    public LeaderDTO getLeaderById(Long id) {
        return leaderRepository.findById(id).map(LeaderUtility::convertToDTO).orElse(null);
    }

}
