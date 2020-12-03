package com.sportsit.playermarket.player.it;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.sportsit.playermarket.player.AbstractRestOperationIT;
import com.sportsit.playermarket.player.dto.UpdatePlayerInputRest;
import com.sportsit.playermarket.player.dto.PlayerInputRest;
import com.sportsit.playermarket.util.RestConstants;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Date;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DirtiesContext
public class HomeController_UpdatePlayerIT extends AbstractRestOperationIT{

	private static final Long TEAM_ID = 953L;
	private static final String NAME = "Gustava Adamec";
	private static final Long CURRENT_TEAM_ID = 952L;
	private static final Date DOB = new Date();
	private static final Date PLAYING_START_DATE= new Date();
	List<PlayerInputRest> PREVIOUS_TEAMS = null;
	
	@Override
	protected String getRestOperationPath() {
		return RestConstants.PATH_UPDATE_PLAYER;
	}

	@Test
	public void updatePlayer_givenPlayerInput_ShouldStatusCode400() {
		// GIVEN
		UpdatePlayerInputRest updatePlayerInputRest = updatePlayerInput(TEAM_ID, NAME, CURRENT_TEAM_ID, PLAYING_START_DATE, DOB, PREVIOUS_TEAMS);
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.body(updatePlayerInputRest)
				.when()
				.patch(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_OK);
		// @formatter:on
	}

	private UpdatePlayerInputRest updatePlayerInput(Long playerId, String name, Long currentTeamId, Date playingStartedDate, Date dob, List<PlayerInputRest> previousTeams) {
		// @formatter:off
		return new UpdatePlayerInputRest()
				.setPlayerId(playerId)
				.setFullName(name)
				.setCurrentTeamId(currentTeamId)
				.setPlayingStartedDate(playingStartedDate)
				.setDateOfBirth(dob)
				.setPreviousTeams(previousTeams);
		// @formatter:on
	}


}
