package com.sportsit.betbulldemo.dao;

import com.sportsit.betbulldemo.dto.Commission;
import com.sportsit.betbulldemo.dto.Player;
import com.sportsit.betbulldemo.dto.ResponseTO;
import com.sportsit.betbulldemo.dto.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Repository
public class HomePageRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertPlayer(Player player){
        Random rand = new Random();
        int rand_int1 = rand.nextInt(1000);

        return jdbcTemplate.update("insert into player(playerid,playername,currentclub,age,experiencemonths) values(?,?,?,?,?) ", rand_int1,player.getPlayerName(), player.getCurrentClub(), player.getAge(),player.getExperienceInMonths());
    }

    public String  deletePlayer(int player){
        try{
            jdbcTemplate.update("delete from player where playerid=?", player);
            return "seccess";
        }catch (Exception e){
            return "fail";
        }
    }


    public ResponseTO updatePlayerByName(Player player){
        ResponseTO responseTO = new ResponseTO();
        try {
            jdbcTemplate.update("update player set currentclub=?,age=?,experiencemonths=? where playerid=?",  player.getCurrentClub(), player.getAge(), player.getExperienceInMonths(), player.getPlayerId());
            responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
            responseTO.setData("updated");
            responseTO.setStatus(true);
            responseTO.getStatusCode(HttpStatus.OK.value());
        }catch (Exception e){
            responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
            responseTO.setData("Not Updated");
            responseTO.setStatus(false);
            responseTO.getStatusCode(HttpStatus.OK.value());
        }
        return responseTO;
    }

    public String getPlayerName(Player player){
        try {
            return jdbcTemplate.queryForObject(
                    "select playername from player where playername=?", new Object[]{player.getPlayerName()}, String.class);
        }catch (Exception e){
            return "null";
        }
    }

    public String getTeamName(int playerName){
        try {
            return jdbcTemplate.queryForObject(
                    "select currentclub from player where playerId=?", new Object[]{playerName}, String.class);
        }catch (Exception e){
            return "null";
        }
    }


    public int getTeamId(String teamName){
        try {
            return jdbcTemplate.queryForObject(
                    "select teamid from team where teamname=?", new Object[]{teamName}, Integer.class);
        }catch (Exception e){
            return 0;
        }
    }

    public List<Player> getAllPlayers(){
        //List<Player> customers = new ArrayList<>();
        return this.jdbcTemplate.query("select * from player", new PlayerMapper());
    }

    public String deleteTeamPlayer(String playerName, String team){
        try {
            jdbcTemplate.update("update player set currentclub='' where playername=?", playerName);
            return "success";
        }catch (Exception e){
            return "fail";
        }
    }


    public List<Commission> getPlayerFee(){
        //List<Player> customers = new ArrayList<>();
        return this.jdbcTemplate.query("select * from player", new ComissionMapper());
    }

    public List<Commission> getPlayerCommission(int playerId) {
        return this.jdbcTemplate.query("select * from player where playerid="+playerId+"", new ComissionMapper());
    }

    public void getPlayerTeams(int playerId) {

    }

    private static final class ComissionMapper implements RowMapper<Commission> {
        public Commission mapRow(ResultSet rs, int rowNum) throws SQLException {
            Commission commission = new Commission();
            int age = rs.getInt("age");
            int experiencemonths = rs.getInt("experiencemonths");
            double d = experiencemonths*100000/age;
            commission.setPlayerName(rs.getString("playername"));
            commission.setTransferFee(d);
            commission.setTeamCommission(d*10/100);
            commission.setContractfee((d*10/100)+d);
            commission.setCurrency("USD");
            return commission;
        }
    }


    private static final class PlayerMapper implements RowMapper<Player> {
        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            Player player = new Player();
            player.setPlayerId(rs.getInt("playerid"));
            player.setPlayerName(rs.getString("playername"));
            player.setAge(rs.getInt("age"));
            if(rs.getString("currentclub") == null){
                player.setCurrentClub("");
            }else{
                player.setCurrentClub(rs.getString("currentclub"));
            }
            player.setExperienceInMonths(rs.getInt("experiencemonths"));
            return player;
        }
    }



    public String insertTeamPlayers(Team team){
        try {

            for(Player player : team.getPlayersList()){
                jdbcTemplate.update("insert into playerteams(playerid,teamid) values(?,?)", player.getPlayerId(),team.getTeamId());
            }

            jdbcTemplate.batchUpdate(
                    "update player set currentclub=? where playerid=?",
                    new BatchPreparedStatementSetter() {
                        public void setValues(PreparedStatement ps, int i)
                                throws SQLException {
                            ps.setString(1, team.getTeamName());
                            ps.setInt(2, team.getPlayersList().get(i).getPlayerId());
                        }

                        public int getBatchSize() {
                            return team.getPlayersList().size();
                        }
                    });
            return "Player added";
        }catch (Exception e){
            return "Player not added";
        }

    }

    public String insertTeamName(Team team){
        Random rand = new Random();
        int rand_int1 = rand.nextInt(1000);

        try {
            String i = jdbcTemplate.queryForObject(
                    "select teamname from team where teamname=?", new Object[]{team.getTeamName()}, String.class);
            return "Team Exists";
        }catch (Exception e){
            jdbcTemplate.update("insert into team(teamid,teamname,currencyid) values(?,?,?)", rand_int1,team.getTeamName(),101);

            try {
                jdbcTemplate.batchUpdate(
                        "update player set currentclub=? where playername=?",
                        new BatchPreparedStatementSetter() {
                            public void setValues(PreparedStatement ps, int i)
                                    throws SQLException {
                                ps.setString(1, team.getTeamName());
                                ps.setString(2, team.getPlayersList().get(i).getPlayerName());
                            }

                            public int getBatchSize() {
                                return team.getPlayersList().size();
                            }
                        });

                return "Team created";
            }catch (Exception e1){
                return "Team created";
            }
        }

    }

    public List<Team> getTeams(){
        List<Team> teamList = this.jdbcTemplate.query("select * from team", new TeamMapper());
        for (Team teamname : teamList){
            teamname.getTeamName();
            List<Player> playerList = this.jdbcTemplate.query("select * from player where currentclub='"+teamname.getTeamName()+"'", new PlayerMapper());
            teamname.setPlayersList(playerList);
        }
        return teamList;

    }

    private static final class TeamMapper implements RowMapper<Team> {
        public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
            Team team = new Team();
            team.setTeamName(rs.getString("teamname"));
            team.setCurrencyId(rs.getInt("currencyid"));
            team.setTeamId(rs.getInt("teamid"));
            team.setCurrencyName("USD");
            return team;
        }
    }

}


