package com.sportsit.playermarket.player.it;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.sportsit.playermarket.player.AbstractRestOperationIT;
import com.sportsit.playermarket.player.dto.CreatePlayerInputRest;
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
public class HomeController_CreatePlayerIT extends AbstractRestOperationIT{

	//Date d = Date.from(LocalDateTime.now().minusYears(16).toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
	private static final String NAME = "Gustava Adamec";
	private static final Long CURRENT_TEAM_ID = 952L;
	private static final Date DOB = new Date();
	private static final Date PLAYING_START_DATE= new Date();
	List<PlayerInputRest> PREVIOUS_TEAMS = null;
	
	@Override
	protected String getRestOperationPath() {
		return RestConstants.PATH_ADD_PLAYER;
	}

	@Test
	public void createPlayer_givenPlayerInput_ShouldStatusCode200() {
		// GIVEN
		CreatePlayerInputRest createPlayerInputRest = createPlayerInput(NAME, CURRENT_TEAM_ID, PLAYING_START_DATE, DOB, PREVIOUS_TEAMS);
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.body(createPlayerInputRest)
				.when()
				.post(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_OK);
		// @formatter:on
	}

	private CreatePlayerInputRest createPlayerInput(String name, Long currentTeamId, Date playingStartedDate, Date dob, List<PlayerInputRest> previousTeams) {
		// @formatter:off
		return new CreatePlayerInputRest()
				.setFullName(name)
				.setCurrentTeamId(currentTeamId)
				.setPlayingStartedDate(playingStartedDate)
				.setDateOfBirth(dob)
				.setPreviousTeams(previousTeams);
		// @formatter:on
	}


}
