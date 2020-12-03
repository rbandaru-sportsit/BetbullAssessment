package com.sportsit.playermarket.player.service;

import com.sportsit.playermarket.player.dto.*;

import java.io.IOException;
import java.util.List;

public interface PlayersTeamGenerationService {

	UserRequestOutput createTeam(CreateTeamInputRest insertTeam );

	UserRequestOutput updateTeam(UpdateTeamInputRest updateTeam);

	UserRequestOutput addPlayer(CreatePlayerInputRest createPlayerInputRest );

	UserRequestOutput updatePlayer(UpdatePlayerInputRest updatePlayerInputRest);

	UserRequestOutput deleteTeam(Long teamId);

	UserRequestOutput deletePlayer(Long playerId);

	List<PlayerListOutputRest> playersList();

	TeamOutputRest getPlayerTeamDetails(Long playerId);

	PlayerOutputRest getPlayerChargedDetails(Long playerId);
}