package com.betbull.playermarket.controller;

import com.betbull.playermarket.dao.PlayerRepository;
import com.betbull.playermarket.dao.PlayerTeamsRepository;
import com.betbull.playermarket.dto.Player;
import com.betbull.playermarket.dto.PlayerContractFee;
import com.betbull.playermarket.dto.PlayerTeams;
import com.betbull.playermarket.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class PlayerControllerTest {

    @InjectMocks
    private PlayerMarketController playerMarketController;

    @Mock
    PlayerRepository playerRepository;

    @Mock
    PlayerTeamsRepository playerTeamsRepository;

    private Logger log = LogManager.getLogger(getClass().getName());

    @Test
    public void testAddPlayerMarket() {
        Player player = new Player();
        player.setPlayerName("messi");
        /*player.setDateOfBirth(Date.valueOf("1987-06-24"));
        player.setDateOfJoin(Date.valueOf("2018-05-24"));*/
        player.setCurrency("dollar");
        player.setTeamId(3);
        playerRepository.save(new Player());

        assertThat(player.getTeamId()).isGreaterThan(0);
        log.info("player added successfully");
    }

    @Test
    public void testGetPlayerByName() {
        String playerName = "Messi";
        // GIVEN
        List<Player> playerList = new ArrayList<>();
        when(playerRepository.findByPlayerName(playerName)).thenReturn(playerList);
        // WHEN
        playerMarketController.getAllPlayers(playerName);
        log.info("getPlayer is called");
        // THEN
        verify(playerRepository).findByPlayerName(playerName);
        log.info("player verified successfully");
    }
    @Test
    public void testUpdatePlayer() {
        long playerId = 18;
        String playerName = "Ronaldo";
        String currency = "Euro";
        Player playerDetails = new Player();
        playerDetails.setTeamId(playerId);
        playerDetails.setPlayerName(playerName);
        playerDetails.setCurrency(currency);

        when(playerRepository.save(playerDetails)).thenReturn(playerDetails);

        // WHEN
        playerMarketController.updatePlayerDetails(playerId, playerDetails);
        log.info("player updated successfully");
        // THEN
       // verify(playerRepository).findById(playerId);
        assertThat(playerDetails.getCurrency()).isEqualTo("Euro");
        log.info("player update verified");
    }

    @Test
    public void givenInputParameter_whenDeleteTeamIsCalled_thenDeleteTeamIsCalled() {
        long playerId = 18;
        // GIVEN
        // WHEN
        playerMarketController.deletePlayer(playerId);
        // THEN
        verify(playerRepository).deleteById(playerId);
        log.info("player deleted successfully");
    }
    @Test
    public void testGetPlayerTeam() {
        long playerId = 18;
        List<PlayerTeams> playerList = new ArrayList<>();
        // GIVEN
        when(playerTeamsRepository.findByPlayerId(playerId)).thenReturn(playerList);
        // WHEN
        playerMarketController.getPlayerTeams(playerId);
        log.info("getPlayerTeams is called");
        // THEN
        verify(playerTeamsRepository).findByPlayerId(playerId);

    }
    @Test
    public void testGetPlayerContractFee() {

        long id = 18;
        Optional<Player> playerData = playerRepository.findById(id);
        PlayerContractFee playerContractFee = new PlayerContractFee();
        int monthsofExperience=19;
        int playerAge=25;
        double transferFee= monthsofExperience * 100000/playerAge;
        double commission= transferFee*10/100;
        double contractFee= transferFee + commission;

        // GIVEN
        when(playerRepository.findById(id)).thenReturn(playerData);
        // THEN
       verify(playerRepository).findById(id);


    }



}


