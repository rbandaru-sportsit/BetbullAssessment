package com.betbull.playermarket.controller;


import com.betbull.playermarket.dao.PlayerRepository;
import com.betbull.playermarket.dao.PlayerTeamsRepository;
import com.betbull.playermarket.dao.TeamsRepository;
import com.betbull.playermarket.dto.Player;
import com.betbull.playermarket.dto.PlayerContractFee;
import com.betbull.playermarket.dto.PlayerTeams;
import com.betbull.playermarket.dto.UserStatus;
import com.betbull.playermarket.service.PlayerService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//import java.time.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Date;

@RestController
@RequestMapping("/bet-bull/players")
public class PlayerMarketController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerTeamsRepository playerTeamsRepository;

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    PlayerService playerService;

    @Autowired
    EntityManager em;

    private Logger log = LogManager.getLogger(getClass().getName());

    @PostMapping("/addplayer")
    @Transactional
    public ResponseEntity<Player> addPlayerMarket(@RequestBody Player player) {
        log.info("addPlayerMarket method called");
        try {
            Player playerDetails = new Player();
            if(player.getPlayerName()!=null && !player.getPlayerName().isEmpty()) {
                if (player.getPlayerName() != playerDetails.getPlayerName()) {
                    playerRepository.save(player);
                    String getTeamName = "select team_name from teams where team_id=?";
                    Query teamQuery = em.createNativeQuery(getTeamName);
                    teamQuery.setParameter(1, player.getTeamId());
                   String name = String.valueOf(teamQuery.getResultList());

                   String nativeQuery = "insert into player_teams(player_id,team_id, team_name) values(?,?,?)";
                    Query query = em.createNativeQuery(nativeQuery);
                    query.setParameter(1, player.getPlayerId());
                    query.setParameter(2, player.getTeamId());
                    query.setParameter(3,name);
                    query.executeUpdate();

                }else {
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            else{
                return new ResponseEntity<>(player,HttpStatus.NO_CONTENT);
            }
            log.info("addPlayerMarket method completed");
            return new ResponseEntity<>(player,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPlayers")
    public ResponseEntity<List<Player>> getAllPlayers(@RequestParam(required = false) String playerName) {
        log.info("getAllPlayers method called");
        try {
            List<Player> players = new ArrayList<Player>();

            if (playerName == null)
                playerRepository.findAll().forEach(players::add);
            else
                playerRepository.findByPlayerName(playerName).forEach(players::add);

            if (players.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("getAllPlayers method completed");
            System.out.println(players);
            return new ResponseEntity<>(players, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPlayer/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable("id") long id) {
        log.info("getPlayerById method called");
        UserStatus userStatus= new UserStatus();
        Optional<Player> playerData = playerRepository.findById(id);

        if (playerData.isPresent()) {
            return new ResponseEntity<>(playerData.get(), HttpStatus.OK);
        } else {
            userStatus.setMessage("Player id "+id +" not exists");
            userStatus.setResponseCode(200);
            userStatus.setResponseStatus("OK");
            return new ResponseEntity<>(userStatus,HttpStatus.NOT_FOUND);
        }

    }


    @PutMapping("/updatePlayer/{id}")
    public ResponseEntity<Player> updatePlayerDetails(@PathVariable("id") long id, @RequestBody Player player) {
        log.info("updatePlayerDetails method called");
        Optional<Player> playerData = playerRepository.findById(id);

        if (playerData.isPresent()) {
            Player playerDetails = playerData.get();
           if(player.getPlayerName()!=null && !player.getPlayerName().isEmpty())
               playerDetails.setPlayerName(player.getPlayerName());
            if(player.getDateOfBirth()!=null)
            playerDetails.setDateOfBirth(player.getDateOfBirth());
            if(player.getExperienceFrom()!=null)
            playerDetails.setExperienceFrom(player.getExperienceFrom());
            if(player.getTeamId()!=0)
            playerDetails.setTeamId(player.getTeamId());
            if(player.getCurrency()!=null && !player.getCurrency().isEmpty())
            playerDetails.setCurrency(player.getCurrency());
            log.info("updatePlayerDetails method completed");
            return new ResponseEntity<>(playerRepository.save(playerDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/deletePlayer/{id}")
    @Transactional
    public ResponseEntity<UserStatus> deletePlayer(@PathVariable("id") long id) {

            UserStatus userStatus = new UserStatus();
           log.info("deletePlayer method called");
        try {
            if(playerRepository.existsById(id)) {
                playerRepository.deleteById(id);

                userStatus.setMessage("Player id "+id +" deleted succesfully");
                userStatus.setResponseCode(200);
                userStatus.setResponseStatus("OK");
                log.info("Player id "+id +"deleted succesfully");

                String nativeQuery = "delete from player_teams where player_id=?";
                Query query = em.createNativeQuery(nativeQuery);
                query.setParameter(1, id);
                query.executeUpdate();

            }
            else {
                userStatus.setMessage("Player id "+id +" not exists");
                userStatus.setResponseCode(200);
                userStatus.setResponseStatus("OK");
                log.info("Player id "+id +" not exists");

            }
            log.info("deletePlayer method completed");
            return new ResponseEntity<>(userStatus,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTeams")
    public ResponseEntity<List<PlayerTeams>> getPlayerTeams(@RequestParam(required = false) long playerId ) {
        log.info("getPlayerTeams method called");
        try {
            List<PlayerTeams> playerTeams = playerTeamsRepository.findByPlayerId(playerId);

            if (playerTeams.isEmpty()) {
                return new ResponseEntity<>(playerTeams,HttpStatus.OK);
            }
            log.info("getPlayerTeams method completed");
            return new ResponseEntity<>(playerTeams, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getContractFee/{id}")
    public ResponseEntity<PlayerContractFee> getContractFee(@PathVariable("id") long id) {
        log.info("getContractFee method called");
        Optional<Player> playerData = playerRepository.findById(id);

        PlayerContractFee playerContractFee = new PlayerContractFee();
        if (playerData.isPresent()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date age = null;
            java.util.Date months = null;
            try {
                age = formatter.parse(String.valueOf(playerData.get().getDateOfBirth()));
                months = formatter.parse(String.valueOf(playerData.get().getExperienceFrom()));

                //Converting obtained Date object to LocalDate object
                Instant instant = age.toInstant();
                Period period = playerService.ageCalculator(instant);
                int playerAge=period.getYears();
                //Converting date to months
                Instant instant1 = months.toInstant();
                Period period1 = playerService.ageCalculator(instant1);
                int monthsofExperience=(period1.getYears()*12)+period1.getMonths();

                double transferFee= monthsofExperience * 100000/playerAge;
                double commission= transferFee*10/100;
                double contractFee= transferFee + commission;

                playerService.addPlayerFee(playerData, playerContractFee, playerAge, monthsofExperience, transferFee, commission, contractFee);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            log.info("getContractFee method completed");
            return new ResponseEntity<>(playerContractFee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }







}
