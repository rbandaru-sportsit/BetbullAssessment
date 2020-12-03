package com.sportsit.playermarket.player.it;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.sportsit.playermarket.player.AbstractRestOperationIT;
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
public class HomeController_DeleteTeamIT extends AbstractRestOperationIT{

	private static final Long TEAM_ID = 953L;
	
	@Override
	protected String getRestOperationPath() {
		return RestConstants.PATH_ADD_TEAM;
	}

	@Test
	public void deleteTeam_givenEmptyInputParameters_ShouldStatusCode405() {
		// GIVEN
		Long teamId = 953L;
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.when()
				.delete(getRestOperationPath())
				.then()
				.statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
		// @formatter:on
	}

}
