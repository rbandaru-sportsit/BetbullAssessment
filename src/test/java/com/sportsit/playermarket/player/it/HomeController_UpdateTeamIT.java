package com.sportsit.playermarket.player.it;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.sportsit.playermarket.player.AbstractRestOperationIT;
import com.sportsit.playermarket.player.dto.UpdateTeamInputRest;
import com.sportsit.playermarket.util.RestConstants;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static com.jayway.restassured.RestAssured.given;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DirtiesContext
public class HomeController_UpdateTeamIT extends AbstractRestOperationIT{

	private static final Long TEAM_ID = 953L;
	private static final String NAME = "Gustava Adamec";
	private static final String CURRENCY_CODE = "USD";
	
	@Override
	protected String getRestOperationPath() {
		return RestConstants.PATH_UPDATE_TEAM;
	}

	@Test
	public void updateTeam_givenTeamInputParameters_ShouldStatusCode200() {
		// GIVEN
		UpdateTeamInputRest updateTeamInputRest = updateTeamInputRest(TEAM_ID, CURRENCY_CODE, NAME);
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.body(updateTeamInputRest)
				.when()
				.post(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_OK);
		// @formatter:on
	}

	@Test
	public void updateTeam_givenNullId_ShouldStatusCode400() {
		// GIVEN
		UpdateTeamInputRest updateTeamInputRest = updateTeamInputRest(null, CURRENCY_CODE, "");
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.body(updateTeamInputRest)
				.when()
				.post(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_BAD_REQUEST);
		// @formatter:on
	}

	@Test
	public void updateTeam_givenEmptyName_ShouldStatusCode400() {
		// GIVEN
		UpdateTeamInputRest updateTeamInputRest = updateTeamInputRest(TEAM_ID, CURRENCY_CODE, "");
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.body(updateTeamInputRest)
				.when()
				.post(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_BAD_REQUEST);
		// @formatter:on
	}

	@Test
	public void updateTeam_givenEmptyCurrencyCode_ShouldStatusCode400() {
		// GIVEN
		UpdateTeamInputRest updateTeamInputRest = updateTeamInputRest(TEAM_ID,"", NAME);
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.body(updateTeamInputRest)
				.when()
				.post(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_BAD_REQUEST);
		// @formatter:on
	}

	private UpdateTeamInputRest updateTeamInputRest(Long teamId, String currencyCode, String name) {
		// @formatter:off
		return new UpdateTeamInputRest()
				.setTeamId(teamId)
				.setCurrencyCode(currencyCode)
				.setName(name);
		// @formatter:on
	}


}
