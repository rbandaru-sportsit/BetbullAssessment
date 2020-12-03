package com.betbull.playermarket.service;

import com.betbull.playermarket.dto.Player;
import com.betbull.playermarket.dto.PlayerContractFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class PlayerService {


    @Autowired
    EntityManager em;


    public void addPlayerFee(Optional<Player> playerData, PlayerContractFee playerContractFee, int playerAge, int monthsofExperience, double transferFee, double commission, double contractFee) {
        playerContractFee.setPlayerId(playerData.get().getPlayerId());
        playerContractFee.setPlayerName(playerData.get().getPlayerName());
        playerContractFee.setTeamId(playerData.get().getTeamId());
        playerContractFee.setDateOfBirth(playerData.get().getDateOfBirth());
        playerContractFee.setDateOfJoin(playerData.get().getExperienceFrom());
        playerContractFee.setContractFee(contractFee +" "+playerData.get().getCurrency());
        playerContractFee.setTransferFee(transferFee);
        playerContractFee.setCommission(commission);
        playerContractFee.setAge(playerAge);
        playerContractFee.setMonthsOfExperience(monthsofExperience);
    }

    public Period ageCalculator(Instant instant) {
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        //Calculating the difference between given date to current date.
        return Period.between(givenDate, LocalDate.now());
    }

    public static java.util.Date StringToDate(String dob) throws ParseException {
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Parsing the given String to Date object
        java.util.Date date = formatter.parse(dob);
        System.out.println("Date object value: "+date);
        return date;
    }



}
