package com.sportsit.betbulldemo.dto;

import org.springframework.stereotype.Component;

@Component
public class Commission {
    private String playerName;
    private double transferFee;
    private double contractfee;
    private double teamCommission;
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Commission(String lionel_messi, int i, double v, double v1) {
    }

    public Commission() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(double transferFee) {
        this.transferFee = transferFee;
    }

    public double getContractfee() {
        return contractfee;
    }

    public void setContractfee(double contractfee) {
        this.contractfee = contractfee;
    }

    public double getTeamCommission() {
        return teamCommission;
    }

    public void setTeamCommission(double teamCommission) {
        this.teamCommission = teamCommission;
    }
}
