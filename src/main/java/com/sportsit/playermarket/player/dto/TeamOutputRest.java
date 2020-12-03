package com.sportsit.playermarket.player.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class TeamOutputRest {

    private Long teamId;
    private String name;
    private String currencyCode;
    private BigDecimal teamTransferFee;
    private BigDecimal teamCommission;
    private BigDecimal contractFee;
}
