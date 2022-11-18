package com.leader.leaderservice.services.impl;

import com.leader.leaderservice.entity.Leader;
import com.leader.leaderservice.exception.NoSuchLeaderExistException;
import com.leader.leaderservice.model.DevelopmentDTO;
import com.leader.leaderservice.model.LeaderDevDTO;
import com.leader.leaderservice.repository.LeaderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LeaderServiceImplTest {

    @Mock
    private LeaderRepository leaderRepository;

    @Mock
    private RemoteServiceHelper remoteServiceHelper;

    @InjectMocks
    private LeaderServiceImpl leaderService;


    private Leader leader;

    @BeforeAll
    public void setup() {
        leader = new Leader();
        leader.setId(1L);
        leader.setPartyId(1L);
        leader.setLeaderName("Test Leader");
        leader.setLeaderState("Test State");

    }

    @Test
    @DisplayName("JUnit for get all development work assigned")
    void getDevWorksAssigned() throws NoSuchLeaderExistException {
        DevelopmentDTO developmentDTO = mock(DevelopmentDTO.class);
        //        Repository call
        given(leaderRepository.findById(anyLong())).willReturn(Optional.of(leader));
        //        Rest Template call
        given(remoteServiceHelper.getDevelopmentDetails(anyLong(), anyLong())).willReturn(List.of(developmentDTO));

        LeaderDevDTO devWorksAssigned = leaderService.getDevWorksAssigned(anyLong(), 2L);

        assertNotNull("Leader information should be present", devWorksAssigned.getLeaderDTO());
        assertNotNull("Assigned works should be available", devWorksAssigned.getDevelopmentDTO());
        verify(remoteServiceHelper, times(1)).getDevelopmentDetails(anyLong(), anyLong());
        assertFalse(devWorksAssigned.getDevelopmentDTO().isEmpty());
    }

    @Test
    void registerALeader() {
    }

    @Test
    void getLeadersByPartyId() {
    }

    @Test
    void getLeaderByPartyIdAndLeaderId() {
    }

    @Test
    void deleteLeaderByPartyIdAndLeaderId() {
    }

    @Test
    void getLeaderById() {
    }
}