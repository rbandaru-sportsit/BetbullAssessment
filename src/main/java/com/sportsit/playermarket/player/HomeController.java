package com.sportsit.playermarket.player;

import com.sportsit.playermarket.player.dto.*;
import com.sportsit.playermarket.player.service.PlayersTeamGenerationService;
import com.sportsit.playermarket.util.RestConstants;
import com.sun.istack.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class HomeController {
    @Autowired
    PlayersTeamGenerationService playersTeamGenerationService;

    private Logger log = LogManager.getLogger(getClass().getName());

    @RequestMapping(value = RestConstants.PATH_ADD_TEAM, method = RequestMethod.POST, consumes = RestConstants.CONTENT_TYPE_APPLICATION_JSON)
    public @ResponseBody
    UserRequestOutput createTeam(@RequestBody @Valid @javax.validation.constraints.NotNull CreateTeamInputRest createTeamInputRest) {
        log.info("Add team : request info : "+createTeamInputRest);
        UserRequestOutput userRequestOutput = playersTeamGenerationService.createTeam(createTeamInputRest);
        log.info("Add team : response info : "+userRequestOutput);
        return userRequestOutput;
    }

    @RequestMapping(value = RestConstants.PATH_UPDATE_TEAM, method = RequestMethod.PATCH, consumes = RestConstants.CONTENT_TYPE_APPLICATION_JSON)
    public @ResponseBody
    UserRequestOutput updateTeam(@RequestBody @Valid @javax.validation.constraints.NotNull UpdateTeamInputRest updateTeamInputRest) {
        log.info("Update team : request info : "+updateTeamInputRest);
        UserRequestOutput userRequestOutput = playersTeamGenerationService.updateTeam(updateTeamInputRest);
        log.info("Update team : response info : "+userRequestOutput);
        return userRequestOutput;
    }

    @RequestMapping(value = RestConstants.PATH_DELETE_TEAM, method = RequestMethod.DELETE, consumes = RestConstants.CONTENT_TYPE_APPLICATION_JSON)
    public @ResponseBody
    UserRequestOutput deleteTeam(@RequestBody @Valid @javax.validation.constraints.NotNull DeleteTeamInputRest deleteTeamInputRest) {
        log.info("Delete team : request info : "+deleteTeamInputRest);
        UserRequestOutput userRequestOutput = playersTeamGenerationService.deleteTeam(deleteTeamInputRest.getTeamId());
        log.info("Delete team : response info : "+userRequestOutput);
        return userRequestOutput;
    }

    @RequestMapping(value = RestConstants.PATH_ADD_PLAYER, method = RequestMethod.POST, consumes = RestConstants.CONTENT_TYPE_APPLICATION_JSON)
    public @ResponseBody
    UserRequestOutput createPlayer(@RequestBody @Valid CreatePlayerInputRest createPlayerInputRest) {
        log.info("Add player : request info : "+createPlayerInputRest);
        UserRequestOutput userRequestOutput = playersTeamGenerationService.addPlayer(createPlayerInputRest);
        log.info("Add player : response info : "+userRequestOutput);
        return userRequestOutput;
    }

    @RequestMapping(value = RestConstants.PATH_UPDATE_PLAYER, method = RequestMethod.PATCH, consumes = RestConstants.CONTENT_TYPE_APPLICATION_JSON)
    public @ResponseBody
    UserRequestOutput updatePlayer(@RequestBody @Valid @javax.validation.constraints.NotNull UpdatePlayerInputRest updatePlayerInputRest) {
        log.info("Update player : request info : "+updatePlayerInputRest);
        UserRequestOutput userRequestOutput = playersTeamGenerationService.updatePlayer(updatePlayerInputRest);
        log.info("Update player : response info : "+userRequestOutput);
        return userRequestOutput;
    }

    @RequestMapping(value = RestConstants.PATH_DELETE_PLAYER, method = RequestMethod.DELETE, consumes = RestConstants.CONTENT_TYPE_APPLICATION_JSON)
    public @ResponseBody
    UserRequestOutput deletePlayer(@RequestBody @Valid @javax.validation.constraints.NotNull PlayerInputRest playerInputRest) {
        log.info("Delete player : request info : "+playerInputRest);
        UserRequestOutput userRequestOutput = playersTeamGenerationService.deletePlayer(playerInputRest.getId());
        log.info("Delete player : response info : "+userRequestOutput);
        return userRequestOutput;
    }

    @RequestMapping(value = RestConstants.PATH_PLAYER_LIST, method = RequestMethod.GET, produces = RestConstants.CONTENT_TYPE_APPLICATION_JSON)
    public List<PlayerListOutputRest> playersList() {
        log.info("Get player List Api is called");
       return playersTeamGenerationService.playersList();
    }

    @RequestMapping(value = RestConstants.PATH_PLAYER_TEAMS_DETAILS, method = RequestMethod.GET, produces = RestConstants.CONTENT_TYPE_APPLICATION_JSON)
    public TeamOutputRest getPlayerTeamsDetails(@PathVariable("playerId") Long playerId){
        log.info("Get player Team Details : request info playerId: "+playerId);
        return playersTeamGenerationService.getPlayerTeamDetails(playerId);
    }

    @RequestMapping(value = RestConstants.PATH_PLAYER_CHARGED, method = RequestMethod.GET, produces = RestConstants.CONTENT_TYPE_APPLICATION_JSON)
    public PlayerOutputRest getPlayerCharged(@PathVariable("playerId") Long playerId){
        log.info("Get player charged Dettails: request info playerId: "+playerId);
        return playersTeamGenerationService.getPlayerChargedDetails(playerId);
    }

}
