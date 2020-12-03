package com.sportsit.betbulldemo.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Player {

    private Integer playerId;
    private String playerName;
    private Integer age;
    private Integer experienceInMonths;
    private String currentClub;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date datoOfBirth;

    public Date getDatoOfBirth() {
        return datoOfBirth;
    }

    public void setDatoOfBirth(Date datoOfBirth) {
        this.datoOfBirth = datoOfBirth;
    }

    public Player(Integer playerId, String playerName, Integer age, Integer experienceInMonths, String currentClub, Date datoOfBirth) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.age = age;
        this.experienceInMonths = experienceInMonths;
        this.currentClub = currentClub;
        this.datoOfBirth = datoOfBirth;
    }

    public Player() {

    }

    public Player(Integer playerId, String playerName, Integer age, Integer experienceInMonths, String currentClub) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.age = age;
        this.experienceInMonths = experienceInMonths;
        this.currentClub = currentClub;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getExperienceInMonths() {
        return experienceInMonths;
    }

    public void setExperienceInMonths(Integer experienceInMonths) {
        this.experienceInMonths = experienceInMonths;
    }

    public String getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(String currentClub) {
        this.currentClub = currentClub;
    }

}
