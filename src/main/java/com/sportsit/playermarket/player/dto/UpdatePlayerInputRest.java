package com.sportsit.playermarket.player.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Data
@Accessors(chain = true)
public class UpdatePlayerInputRest {
    @NotNull
    private Long playerId;

    @NotNull
    private String fullName;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    private Date playingStartedDate;

    private Long currentTeamId;

    private List<PlayerInputRest> previousTeams;

}
