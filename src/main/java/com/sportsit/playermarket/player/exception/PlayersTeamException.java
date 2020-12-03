package com.sportsit.playermarket.player.exception;

/**
 * Basic class for run-time exceptions in the application.
 */
public class PlayersTeamException extends RuntimeException implements HasTranslatedMessage {

	private static final long serialVersionUID = 1L;

	private String translatedMessage;

	/**
	 * Constructor.
	 *
	 */
	public PlayersTeamException(String translatedMessage) {
		super(translatedMessage);
		this.translatedMessage = translatedMessage;
	}

	public PlayersTeamException(Throwable throwable) {
		super(throwable);
	}

	@Override
	public String getTranslatedMessage() {
		return translatedMessage;
	}

}
