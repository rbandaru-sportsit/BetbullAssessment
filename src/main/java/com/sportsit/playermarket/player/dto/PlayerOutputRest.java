package com.sportsit.playermarket.player.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class PlayerOutputRest {

    private Long playerId;
    private String fullName;
    private Long age;
    private Long monthsOfExperience;
    private Long currentTeamId;
    private BigDecimal transferFee;
    private List<PlayerInputRest> previousTeams;
}