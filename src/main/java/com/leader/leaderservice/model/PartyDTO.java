package com.leader.leaderservice.model;

import lombok.Data;

@Data
public class PartyDTO {
    private Long id;

    private String name;

    private String founderName;

    private int founderYear;
}
