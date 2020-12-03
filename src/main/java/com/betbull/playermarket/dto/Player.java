package com.betbull.playermarket.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "player")
@SequenceGenerator(name = "player_id_seq", schema = "public", sequenceName = "player_seq_id", allocationSize = 1)

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_id_seq")
    @Column(name="player_id")
    private long playerId;

    @Column(name="player_name")
    private String playerName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name="date_of_birth")
    private Date dateOfBirth;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name="date_of_join")
    private Date experienceFrom;

    @Column(name="currency")
    private  String currency;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public long getTeamId() {
        return teamId;
    }

    public Date getExperienceFrom() {
        return experienceFrom;
    }

    public void setExperienceFrom(Date experienceFrom) {
        this.experienceFrom = experienceFrom;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }


    @Column(name="team_id")
    private long teamId;



}
