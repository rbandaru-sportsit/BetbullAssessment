package com.sportsit.playermarket.player.it;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.sportsit.playermarket.player.AbstractRestOperationIT;
import com.sportsit.playermarket.player.dto.CreateTeamInputRest;
import com.sportsit.playermarket.util.RestConstants;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.validation.constraints.Null;

import static com.jayway.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DirtiesContext
public class HomeController_CreateTeamIT extends AbstractRestOperationIT{

	private static final String NAME = "Gustava Adamec";
	private static final String CURRENCY_CODE = "USD";
	
	@Override
	protected String getRestOperationPath() {
		return RestConstants.PATH_ADD_TEAM;
	}

	@Test
	public void createTeam_givenNewTeamInput_ShouldStatusCode200() {
		// GIVEN
		CreateTeamInputRest createTeamInputRest = createTeamInputRest(CURRENCY_CODE, NAME);
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.body(createTeamInputRest)
				.when()
				.post(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_OK);
		// @formatter:on
	}

	@Test
	public void createTeam_givenEmptyName_ShouldStatusCode400() {
		// GIVEN
		CreateTeamInputRest createTeamInputRest = createTeamInputRest(CURRENCY_CODE, "");
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.body(createTeamInputRest)
				.when()
				.post(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_BAD_REQUEST);
		// @formatter:on
	}

	@Test
	public void createTeam_givenEmptyCurrencyCode_ShouldStatusCode400() {
		// GIVEN
		CreateTeamInputRest createTeamInputRest = createTeamInputRest("", NAME);
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.body(createTeamInputRest)
				.when()
				.post(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_BAD_REQUEST);
		// @formatter:on
	}

	@Test
	public void createTeam_givenEmptyInputParameters_ShouldStatusCode400() {
		// GIVEN
		CreateTeamInputRest createTeamInputRest = createTeamInputRest("", "");
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.body(createTeamInputRest)
				.when()
				.post(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_BAD_REQUEST);
		// @formatter:on
	}

	@Test
	public void createTeam_givenExistingName_ShouldStatusCode400() {
		// GIVEN
		CreateTeamInputRest createTeamInputRest = createTeamInputRest(CURRENCY_CODE, NAME);
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.body(createTeamInputRest)
				.when()
				.post(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_BAD_REQUEST);
		// @formatter:on
	}

	private CreateTeamInputRest createTeamInputRest(String currencyCode, String name) {
		// @formatter:off
		return new CreateTeamInputRest()
				.setCurrencyCode(currencyCode)
				.setName(name);
		// @formatter:on
	}


}
