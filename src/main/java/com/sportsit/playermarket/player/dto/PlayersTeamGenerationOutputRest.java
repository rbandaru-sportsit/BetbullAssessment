package com.sportsit.playermarket.player.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlayersTeamGenerationOutputRest {

    private UserRequestOutput insertTeam;
    private UserRequestOutput updateTeam;
    private UserRequestOutput deleteTeam;
}
