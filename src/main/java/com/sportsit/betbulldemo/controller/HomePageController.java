package com.sportsit.betbulldemo.controller;

import com.sportsit.betbulldemo.dto.*;
import com.sportsit.betbulldemo.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomePageController {


    @Autowired
    private HomePageService homePageService;


    @PostMapping(value = "/createPlayer")
    public ResponseTO createPlayer(@RequestBody Player player){
       return homePageService.insertPlayer(player);
    }

    @GetMapping(value = "/deletePlayer")
    public ResponseTO deletePlayer(@RequestParam int playerId){
        return homePageService.deletePlayer(playerId);
    }

    @PostMapping(value = "/updatePlayer")
    public ResponseTO updatePlayerByName(@RequestBody Player player){
        return homePageService.updatePlayerByName(player);
    }

    @GetMapping(value = "/allPlayers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Player> playersList(){
        return homePageService.allPlayers();
    }

    @PostMapping(value = "/createTeam")
    public ResponseTO createTeam(@RequestBody Team team){
        return homePageService.createTeam(team);
    }

    @PostMapping(value = "/addTeamPlayers")
    public ResponseTO addPlayersTOTeam(@RequestBody Team team){
        return homePageService.addTeamPlayers(team);
    }


    @GetMapping(value = "/getAllTeams")
    public List<Team> getTeamPlayers(){
        return homePageService.allTeams();
    }

    @GetMapping(value = "/getAllTransferFee")
    public List<Commission> getAllTransferFee(){
        return homePageService.getPlayerFee();
    }

    @GetMapping(value = "/getPlayerTransferFee")
    public List<Commission> getPlayerTransferFee(@RequestParam int playerId){
        return homePageService.getPlayerFeeCommission(playerId);
    }

    @GetMapping(value = "/getPlayerTeamName")
    public Team getTeamName(@RequestParam int playerId){
        return homePageService.getTeamName(playerId);
    }

    @PostMapping(value = "/deleteTeamPlayer")
    public ResponseTO deleteTeamPlayer(@RequestBody RequestTO requestTO){
        return homePageService.deleteTeamPlayer(requestTO.getPlayerName(),requestTO.getTeamName());
    }


}
