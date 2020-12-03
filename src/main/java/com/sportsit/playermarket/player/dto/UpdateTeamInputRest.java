package com.sportsit.playermarket.player.dto;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Accessors(chain = true)
public class UpdateTeamInputRest {
    @NotNull
    private Long  teamId;
    @NotNull
    private String  name;
    @NotNull
    private String  currencyCode;
}
