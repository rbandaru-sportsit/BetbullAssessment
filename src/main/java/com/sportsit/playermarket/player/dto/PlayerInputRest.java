package com.sportsit.playermarket.player.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class PlayerInputRest {

    @NotNull
    private Long id;

}
