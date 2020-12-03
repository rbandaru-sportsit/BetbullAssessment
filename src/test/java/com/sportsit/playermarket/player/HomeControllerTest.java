package com.sportsit.playermarket.player;

import com.sportsit.playermarket.player.dto.CreatePlayerInputRest;
import com.sportsit.playermarket.player.dto.CreateTeamInputRest;
import com.sportsit.playermarket.player.dto.UserRequestOutput;
import com.sportsit.playermarket.player.service.PlayersTeamGenerationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.sportsit.playermarket.player.dto.*;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    private static final Long PLAYER_ID = 957L;

    @InjectMocks
    private HomeController homeController;

    @Mock
    PlayersTeamGenerationService playersTeamGenerationService;

    private Logger log = LogManager.getLogger(getClass().getName());

    @Test
    public void givenInputParameters_whenCreateTeamIsCalled_thenCreateTeamIsCalled() {
        // GIVEN
        UserRequestOutput userRequestOutput = teamOutput();
        when(playersTeamGenerationService.createTeam(createTeamInputRest())).thenReturn(userRequestOutput);

        // WHEN
        homeController.createTeam(createTeamInputRest());
        log.info("");
        // THEN
        verify(playersTeamGenerationService).createTeam(any());
    }

    @Test
    public void givenInputParameters_whenCreatePlayerIsCalled_thenCreatePlayerIsCalled() {
        // GIVEN
        UserRequestOutput userRequestOutput = playerOutput();
        when(playersTeamGenerationService.addPlayer(createPlayerInputRest())).thenReturn(userRequestOutput);

        // WHEN
        homeController.createPlayer(createPlayerInputRest());

        // THEN
        verify(playersTeamGenerationService).addPlayer(any());
    }

    @Test
    public void givenInputParameters_whenUpdateTeamIsCalled_thenUpdateTeamIsCalled() {
        // GIVEN
        UserRequestOutput userRequestOutput = teamOutput();
        when(playersTeamGenerationService.updateTeam(updateTeamInputRest())).thenReturn(userRequestOutput);

        // WHEN
        homeController.updateTeam(updateTeamInputRest());

        // THEN
        verify(playersTeamGenerationService).updateTeam(any());
    }

    @Test
    public void givenInputParameters_whenUpdatePlayerIsCalled_thenUpdatePlayerIsCalled() {
        // GIVEN
        UserRequestOutput userRequestOutput = playerOutput();
        when(playersTeamGenerationService.updatePlayer(updatePlayerInputRest())).thenReturn(userRequestOutput);

        // WHEN
        homeController.updatePlayer(updatePlayerInputRest());

        // THEN
        verify(playersTeamGenerationService).updatePlayer(any());
    }

    @Test
    public void givenInputParameters_whenDeleteTeamIsCalled_thenDeleteTeamIsCalled() {

        DeleteTeamInputRest deleteTeamInputRest = new DeleteTeamInputRest().setTeamId(957L);

        // GIVEN
        UserRequestOutput userRequestOutput = teamOutput();
        when(playersTeamGenerationService.deleteTeam(deleteTeamInputRest.getTeamId())).thenReturn(userRequestOutput);

        // WHEN
        homeController.deleteTeam(deleteTeamInputRest);

        // THEN
        verify(playersTeamGenerationService).deleteTeam(any());
    }

    @Test
    public void givenInputParameters_whenDeletePlayerIsCalled_thenDeletePlayerIsCalled() {

        PlayerInputRest deletePlayerInputRest = new PlayerInputRest().setId(957L);

        // GIVEN
        UserRequestOutput userRequestOutput = playerOutput();
        when(playersTeamGenerationService.deletePlayer(deletePlayerInputRest.getId())).thenReturn(userRequestOutput);

        // WHEN
        homeController.deletePlayer(deletePlayerInputRest);

        // THEN
        verify(playersTeamGenerationService).deletePlayer(any());
    }

    @Test
    public void givenInputParameter_whenGetPlayerTeamsDetailsIsCalled_thenGetPlayerTeamsDetailsIsCalled() throws IOException {

        // GIVEN
        TeamOutputRest teamOutputRest = getTeamDetails();
        when(playersTeamGenerationService.getPlayerTeamDetails(PLAYER_ID)).thenReturn(teamOutputRest);

        // WHEN
        homeController.getPlayerTeamsDetails(PLAYER_ID);

        // THEN
        verify(playersTeamGenerationService).getPlayerTeamDetails(any());
    }

    @Test
    public void givenInputParameter_whenPlayersListIsCalled_thenPlayerListIsCalled() {

        // GIVEN
        List<PlayerListOutputRest> getPlayerList = getPlayerList();
        when(playersTeamGenerationService.playersList()).thenReturn(getPlayerList);

        // WHEN
        homeController.playersList();

        // THEN
        verify(playersTeamGenerationService).playersList();

    }

    @Test
    public void givenInputParameter_whenGetPlayerChargedIsCalled_thenGetPlayerChargedIsCalled() throws IOException {

        // GIVEN
        PlayerOutputRest playerOutputRest = getPlayerDetails();
        when(playersTeamGenerationService.getPlayerChargedDetails(PLAYER_ID)).thenReturn(playerOutputRest);

        // WHEN
        homeController.getPlayerCharged(PLAYER_ID);

        // THEN
        verify(playersTeamGenerationService).getPlayerChargedDetails(any());
    }

    private List<PlayerListOutputRest> getPlayerList() {
        return Arrays.asList(new PlayerListOutputRest()
                        .setPlayerId(15L)
                        .setCurrentTeamId(1L)
                        .setFullName("John Doile")
                        .setDateOfBirth(new Date())
                        .setPlayingStartedDate(new Date()),

                new PlayerListOutputRest()
                        .setPlayerId(1L)
                        .setCurrentTeamId(1L)
                        .setFullName("John Doile")
                        .setDateOfBirth(new Date())
                        .setPlayingStartedDate(new Date()));
    }

    private PlayerOutputRest getPlayerDetails(){
        // @formatter:off
        return new PlayerOutputRest()
                .setPlayerId(1L)
                .setAge(16L)
                .setFullName("John Ahmed Simi")
                .setTransferFee(BigDecimal.TEN)
                .setCurrentTeamId(1L)
                .setMonthsOfExperience(1L);
        // @formatter:on
    }

    private TeamOutputRest getTeamDetails() {
        // @formatter:off
        return new TeamOutputRest()
                .setTeamId(1L)
                .setCurrencyCode("USD")
                .setName("John Ahmed Simi")
                .setTeamTransferFee(BigDecimal.TEN)
                .setTeamCommission(BigDecimal.ONE)
                .setContractFee(BigDecimal.valueOf(11));
        // @formatter:on
    }

    private CreateTeamInputRest createTeamInputRest() {
        // @formatter:off
        return new CreateTeamInputRest()
                .setName("John Ahmed Simi")
                .setCurrencyCode("USD");
        // @formatter:on
    }

    private UpdatePlayerInputRest updatePlayerInputRest() {
        // @formatter:off
        return new UpdatePlayerInputRest()
                .setFullName("John Ahmed Simi")
                .setDateOfBirth(new Date())
                .setPlayingStartedDate(new Date())
                .setCurrentTeamId(1L)
                .setPreviousTeams(null)
                .setPlayerId(1L);
        // @formatter:on
    }

    private CreatePlayerInputRest createPlayerInputRest() {
        // @formatter:off
        return new CreatePlayerInputRest()
                .setFullName("John Ahmed Simi")
                .setDateOfBirth(new Date())
                .setPlayingStartedDate(new Date())
                .setCurrentTeamId(952L)
                .setPreviousTeams(null);
        // @formatter:on
    }

    private UpdateTeamInputRest updateTeamInputRest() {
        // @formatter:off
        return new UpdateTeamInputRest()
                .setName("John Ahmed Simi")
                .setCurrencyCode("USD")
                .setTeamId(957L);
        // @formatter:on
    }

    private UserRequestOutput teamOutput() {
        // @formatter:off
        return new UserRequestOutput()
                .setRequestCreated(true)
                .setMessage("Team request created successfully")
                .setResponseStatus("OK")
                .setResponseCode(HttpStatus.OK.value());
        // @formatter:on
    }

    private UserRequestOutput playerOutput() {
        // @formatter:off
        return new UserRequestOutput()
                .setRequestCreated(true)
                .setMessage("Player created successfully")
                .setResponseStatus("OK")
                .setResponseCode(HttpStatus.OK.value());
        // @formatter:on
    }

}
