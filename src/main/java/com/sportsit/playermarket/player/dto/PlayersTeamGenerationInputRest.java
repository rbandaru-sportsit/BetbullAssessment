package com.sportsit.playermarket.player.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlayersTeamGenerationInputRest{

    private CreateTeamInputRest insertTeam;
    private UpdateTeamInputRest updateTeam;
    private DeleteTeamInputRest deleteTeam;
}
