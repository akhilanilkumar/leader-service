package com.leader.leaderservice.controller;

import com.leader.leaderservice.exception.NoSuchLeaderExistException;
import com.leader.leaderservice.exception.NoSuchPartyExistException;
import com.leader.leaderservice.model.LeaderDTO;
import com.leader.leaderservice.model.LeaderDevDTO;
import com.leader.leaderservice.services.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "leader")
public class LeaderController {

    @Autowired
    LeaderService leaderService;

    @PostMapping(value = "register")
    public LeaderDTO registerLeader(@Valid @RequestBody LeaderDTO leaderDTO) throws NoSuchPartyExistException {
        return leaderService.registerALeader(leaderDTO);
    }

    @GetMapping(value = "assigned-works/{partyId}/{leaderId}")
    public LeaderDevDTO getDevelopmentWorks(@PathVariable Long partyId, @PathVariable Long leaderId) throws NoSuchLeaderExistException {
        return leaderService.getDevWorksAssigned(partyId, leaderId);
    }

    @GetMapping(value = "get-all-leaders/{partyId}")
    public List<LeaderDTO> getLeadersByPartyId(@PathVariable Long partyId) {
        return leaderService.getLeadersByPartyId(partyId);
    }

    @GetMapping(value = "find/{partyId}/{leaderId}")
    public LeaderDTO getLeaderByPartyIdAndLeaderId(@PathVariable Long partyId, @PathVariable Long leaderId) {
        return leaderService.getLeaderByPartyIdAndLeaderId(partyId, leaderId);
    }

    @GetMapping(value = "find/{leaderId}")
    public LeaderDTO getLeaderByLeaderId(@PathVariable Long leaderId) {
        return leaderService.getLeaderById(leaderId);
    }

    @DeleteMapping(value = "delete/{partyId}/{leaderId}")
    public boolean deleteLeaderByPartyIdAndLeaderId(@PathVariable Long partyId, @PathVariable Long leaderId) {
        return leaderService.deleteLeaderByPartyIdAndLeaderId(partyId, leaderId);
    }
}
