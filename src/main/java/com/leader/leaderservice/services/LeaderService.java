package com.leader.leaderservice.services;

import com.leader.leaderservice.exception.NoSuchLeaderExistException;
import com.leader.leaderservice.exception.NoSuchPartyExistException;
import com.leader.leaderservice.model.LeaderDTO;
import com.leader.leaderservice.model.LeaderDevDTO;

import java.util.List;

public interface LeaderService {

    /**
     * List all the development works assigned.
     *
     * @param partyId
     * @param leaderId
     * @return
     */
    LeaderDevDTO getDevWorksAssigned(Long partyId, Long leaderId) throws NoSuchLeaderExistException;

    /**
     * Register a leader
     *
     * @param leaderDTO
     * @return
     */
    LeaderDTO registerALeader(LeaderDTO leaderDTO) throws NoSuchPartyExistException;

    /**
     * Get leader details using party id
     *
     * @param partyId
     * @return
     */
    List<LeaderDTO> getLeadersByPartyId(Long partyId);

    /**
     * Get leader details using leader id
     *
     * @param id
     * @return
     */
    LeaderDTO getLeaderById(Long id);

    /**
     * Get leader details using party id and leader id
     *
     * @param partyId
     * @param leaderId
     * @return
     */
    LeaderDTO getLeaderByPartyIdAndLeaderId(Long partyId, Long leaderId);

    /**
     * Delete leader using party id and leader id
     *
     * @param partyId
     * @param leaderId
     * @return
     */
    boolean deleteLeaderByPartyIdAndLeaderId(Long partyId, Long leaderId);
}
