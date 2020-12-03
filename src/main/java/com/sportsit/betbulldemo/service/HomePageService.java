package com.sportsit.betbulldemo.service;

import com.sportsit.betbulldemo.dao.HomePageRepository;
import com.sportsit.betbulldemo.dto.Commission;
import com.sportsit.betbulldemo.dto.Player;
import com.sportsit.betbulldemo.dto.ResponseTO;
import com.sportsit.betbulldemo.dto.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class HomePageService {

    @Autowired
    private HomePageRepository homePageRepository;


    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }


    public ResponseTO  insertPlayer(Player player){

        ResponseTO responseTO = new ResponseTO();

        LocalDate date = player.getDatoOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lt
                = LocalDate.now();
        player.setAge(calculateAge(date,lt));

        String result = homePageRepository.getPlayerName(player);

        if(result == "null") {
            int i = homePageRepository.insertPlayer(player);
            if (i > 0) {
                responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
                responseTO.setData("Player inserted successfully");
                responseTO.setStatus(true);
                responseTO.getStatusCode(HttpStatus.OK.value());
                return responseTO;
            } else {
                responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
                responseTO.setData("Player Not Created");
                responseTO.setStatus(false);
                responseTO.getStatusCode(HttpStatus.OK.value());
                return responseTO;
            }
        }else {
            responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
            responseTO.setData("Player already Created");
            responseTO.setStatus(false);
            responseTO.getStatusCode(HttpStatus.OK.value());
            return responseTO;
        }
    }

    public ResponseTO updatePlayerByName(Player player){
        return homePageRepository.updatePlayerByName(player);
    }

    public ResponseTO deletePlayer(int player){
        ResponseTO responseTO = new ResponseTO();
        homePageRepository.deletePlayer(player);
        responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
        responseTO.setData("Player removed");
        responseTO.setStatus(true);
        responseTO.getStatusCode(HttpStatus.OK.value());
        return responseTO;
    }


    public List<Player> allPlayers(){
        return homePageRepository.getAllPlayers();
    }


    public List<Team> allTeams(){
        return homePageRepository.getTeams();
    }

    public ResponseTO createTeam(Team team){
        ResponseTO responseTO = new ResponseTO();
        String s= homePageRepository.insertTeamName(team);
        if(s.equalsIgnoreCase("Team Exists")){
            responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
            responseTO.setData("Team Exists");
            responseTO.setStatus(false);
            responseTO.getStatusCode(HttpStatus.OK.value());
            return responseTO;
        }else{
            responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
            responseTO.setData("Team created");
            responseTO.setStatus(true);
            responseTO.getStatusCode(HttpStatus.OK.value());
            return responseTO;
        }
    }


    public ResponseTO addTeamPlayers(Team team){
        ResponseTO responseTO = new ResponseTO();
        String s = homePageRepository.insertTeamPlayers(team);
        if(s.equalsIgnoreCase("Player added")){
            responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
            responseTO.setData("Player added");
            responseTO.setStatus(true);
            responseTO.getStatusCode(HttpStatus.OK.value());
            return responseTO;
        }else{
            responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
            responseTO.setData("Player not added");
            responseTO.setStatus(false);
            responseTO.getStatusCode(HttpStatus.OK.value());
            return responseTO;
        }
    }


    public List<Commission> getPlayerFee(){
        return homePageRepository.getPlayerFee();
    }


    public void getPlayerTeams(int playerId){
        homePageRepository.getPlayerTeams(playerId);
    }


    public Team getTeamName(int playerName){
        String s = homePageRepository.getTeamName(playerName);
        int i = homePageRepository.getTeamId(s);
        Team t = new Team();
        t.setTeamName(s);
        t.setTeamId(i);
        return t;
    }


    public ResponseTO deleteTeamPlayer(String playerName, String team){
        ResponseTO responseTO = new ResponseTO();
        try {
            String s = homePageRepository.deleteTeamPlayer(playerName, team);
            responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
            responseTO.setData("Player removed from " + team + " team");
            responseTO.setStatus(true);
            responseTO.getStatusCode(HttpStatus.OK.value());
            return responseTO;
        }catch (Exception e){
            responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
            responseTO.setData("Player not removed from " + team + " team");
            responseTO.setStatus(false);
            responseTO.getStatusCode(HttpStatus.OK.value());
            return responseTO;
        }
    }

    public List<Commission> getPlayerFeeCommission(int playerId) {
         return homePageRepository.getPlayerCommission(playerId);
    }
}


