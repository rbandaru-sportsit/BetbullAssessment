package com.betbull.playermarket.controller;

import com.betbull.playermarket.dao.PlayerRepository;
import com.betbull.playermarket.dao.TeamsRepository;
import com.betbull.playermarket.dto.Player;
import com.betbull.playermarket.dto.Teams;
import com.betbull.playermarket.dto.UserStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bet-bull/teams")
public class TeamsController {

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    PlayerRepository playerRepository;

    private Logger log = LogManager.getLogger(getClass().getName());

    @GetMapping("/getTeams")
    public ResponseEntity<List<Teams>> getAllTeams(@RequestParam(required = false) String teamName) {
        log.info("getAllTeams method called");
        try {
            List<Teams> teams = new ArrayList<Teams>();

            if (teamName == null)
                teamsRepository.findAll().forEach(teams::add);
            else
                teamsRepository.findByTeamName(teamName).forEach(teams::add);

            if (teams.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("getAllTeams method completed");
            return new ResponseEntity<>(teams, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addTeams")
    public ResponseEntity<Teams> addteam(@RequestBody Teams teams) {
        log.info("addteam method called");
        try {
            Teams teamData = teamsRepository
                    .save(teams);
            log.info("addteam method completed");
            return new ResponseEntity<>(teamData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteTeam/{id}")
    public ResponseEntity<UserStatus> deleteTeam(@PathVariable("id") long id) {

        UserStatus userStatus = new UserStatus();
        log.info("deletePlayer method called");

        try {
            if(teamsRepository.existsById(id)) {
                teamsRepository.deleteById(id);
                userStatus.setMessage("team id "+id +" deleted succesfully");
                userStatus.setResponseCode(200);
                userStatus.setResponseStatus("OK");
                log.info("team id "+id +"deleted succesfully");
            }
            else {
                userStatus.setMessage("team id "+id +" not exists");
                userStatus.setResponseCode(200);
                userStatus.setResponseStatus("OK");
                log.info("team id "+id +" not exists");

            }
            log.info("deleteTeam method completed");
            return new ResponseEntity<>(userStatus,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateTeam/{id}")
    public ResponseEntity<Teams> updateTeamDetails(@PathVariable("id") long id, @RequestBody Teams teams) {
        log.info("updateTeamDetails method called");
        Optional<Teams> teamData = teamsRepository.findById(id);

        if (teamData.isPresent()) {
            Teams teamDetails = teamData.get();
            if(teams.getTeamName()!=null && !teams.getTeamName().isEmpty())
                teamDetails.setTeamName(teams.getTeamName());
            if(teams.getTeamColour()!=null && !teams.getTeamColour().isEmpty())
                teamDetails.setTeamColour(teams.getTeamColour());
            log.info("updateTeamDetails method completed");
            return new ResponseEntity<>(teamsRepository.save(teamDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
