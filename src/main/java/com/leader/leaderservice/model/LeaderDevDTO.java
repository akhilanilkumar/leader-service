package com.leader.leaderservice.model;

import lombok.Data;

import java.util.List;

@Data
public class LeaderDevDTO {

    private LeaderDTO leaderDTO;

    private List<DevelopmentDTO> developmentDTO;
}
