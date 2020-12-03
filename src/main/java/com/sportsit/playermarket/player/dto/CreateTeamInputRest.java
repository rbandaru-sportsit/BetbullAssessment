package com.sportsit.playermarket.player.dto;


import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class CreateTeamInputRest {

    @NotNull(message = "Name may not be null")
    @NotEmpty
    private String  name;

    @NotNull(message = "currencyCode may not be null")
    @NotEmpty
    private String currencyCode;

}
