package com.sportsit.playermarket.player.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class CreatePlayerInputRest {

    @NotNull
    private String fullName;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    private Date playingStartedDate;

    private Long currentTeamId;

    private List<PlayerInputRest> previousTeams;
}
