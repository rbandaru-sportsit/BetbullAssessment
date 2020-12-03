package com.sportsit.playermarket.player.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRequestOutput {

	private boolean isRequestCreated;
	private String message;
	private int responseCode;
	private String responseStatus;
	private String responseErrorMessage;
	
}