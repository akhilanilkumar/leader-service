package com.leader.leaderservice.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
public class LeaderDTO {
    private Long id;

    @NotNull
    @Digits(integer = 999999999, fraction = 0, message = "Only numbers are allowed!!")
    private Long partyId;

    @NotNull
    @Length(min = 5, max = 100, message = "Leader Name length should be with in 5 to 100 characters!")
    private String leaderName;

    @NotNull
    @Length(min = 5, max = 100, message = "Leader State length should be with in 5 to 100 characters!")
    private String leaderState;
}
