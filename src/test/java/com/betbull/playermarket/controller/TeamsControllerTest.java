package com.betbull.playermarket.controller;


import com.betbull.playermarket.dao.TeamsRepository;
import com.betbull.playermarket.dto.Teams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class TeamsControllerTest {


    @InjectMocks
    private TeamsController teamsController;

    @Mock
    TeamsRepository teamsRepository;

    private Logger log = LogManager.getLogger(getClass().getName());

    @Test
    public void givenInputParameters_whenAddTeamIsCalled_thenSaveIsCalled() {

        // GIVEN
        Teams teams = new Teams();
        teams.setTeamName("Fc Barcelona");
        teams.setTeamColour("white");

        when(teamsRepository.save(teams)).thenReturn(teams);

        // WHEN
        teamsController.addteam(teams);
        log.info("team added successfully");
        // THEN
        verify(teamsRepository).save(any());
    }



    @Test
    public void givenInputParameter_whenGetAllTeamsIsCalled_thenGetAllTeamsIsCalled() {
        String teamName = "Fc Barcelona";
        // GIVEN
        List<Teams> teamsList = new ArrayList<>();
        when(teamsRepository.findByTeamName(teamName)).thenReturn(teamsList);
        // WHEN
        teamsController.getAllTeams(teamName);
        log.info("getAllTeams called");
        // THEN
        verify(teamsRepository).findByTeamName(teamName);

    }

    @Test
    public void givenInputParameter_whenDeleteTeamIsCalled_thenDeleteTeamIsCalled() {
        long teamId = 1;
        // GIVEN
        // WHEN
        teamsController.deleteTeam(teamId);
        log.info("team deleted successfully");
        // THEN
        verify(teamsRepository).deleteById(teamId);
    }

    @Test
    public void givenInputParameter_whenUpdateTeamDetailsIsCalled_thenUpdateTeamDetailsIsCalled() {


        long id = 4;
        String teamName = "Baranga";
        String colour = "green";
        Teams teamDetails = new Teams();
        teamDetails.setTeamId(id);
        teamDetails.setTeamName(teamName);
        teamDetails.setTeamColour(colour);

        when(teamsRepository.save(teamDetails)).thenReturn(teamDetails);

        // WHEN
        teamsController.updateTeamDetails(id, teamDetails);
        log.info("team updated successfully");
        // THEN
        verify(teamsRepository).findById(id);
        log.info("team update verified");
    }
}





