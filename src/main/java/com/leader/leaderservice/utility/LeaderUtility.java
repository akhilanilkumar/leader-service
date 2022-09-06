package com.leader.leaderservice.utility;

import com.leader.leaderservice.entity.Leader;
import com.leader.leaderservice.model.LeaderDTO;

public final class LeaderUtility {

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

}
