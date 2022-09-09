package com.leader.leaderservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class DevelopmentDTO implements Serializable {
    private Long id;

    private Long leaderId;

    private Long partyId;

    private String title;

    private Set<ActivityDTO> activity;

    private Double budget;

    private String state;

    private int activityMonth;

    private int activityYear;
}
