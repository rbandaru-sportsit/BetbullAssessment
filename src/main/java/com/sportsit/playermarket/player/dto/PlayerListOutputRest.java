package com.sportsit.playermarket.player.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Accessors(chain = true)
public class PlayerListOutputRest {

    private Long playerId;
    private String fullName;
    private String currencyCode;
    private Date dateOfBirth;
    private Date playingStartedDate;
    private Long currentTeamId;
}
