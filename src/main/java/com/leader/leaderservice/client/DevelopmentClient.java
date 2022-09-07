package com.leader.leaderservice.client;

import com.leader.leaderservice.exception.NoSuchLeaderExistException;
import com.leader.leaderservice.exception.NoSuchPartyExistException;
import com.leader.leaderservice.model.DevelopmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(url = "${uri.development-service}", name = "development-service")
public interface DevelopmentClient {

    @GetMapping(value = "works/{partyId}/{leaderId}")
    List<DevelopmentDTO> getDevelopmentDetails(@PathVariable Long partyId, @PathVariable Long leaderId);

    @PostMapping(value = "assign-work")
    DevelopmentDTO assignDevWorks(@Valid @RequestBody DevelopmentDTO developmentDTO) throws NoSuchLeaderExistException, NoSuchPartyExistException;
}
