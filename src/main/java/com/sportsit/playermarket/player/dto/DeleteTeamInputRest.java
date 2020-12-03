package com.sportsit.playermarket.player.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class DeleteTeamInputRest {

    @NotNull
    private Long teamId;

}
