package com.sportsit.playermarket.player.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CalculateDateDto {
    private Long age;
    private Long monthsOfExperience;
}
