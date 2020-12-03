package com.sportsit.playermarket;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class PlayerMarketApplicationTests {

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	@Test
	public void shouldStartSuccessfully() {
		// When
		PlayerMarketApplication.main(new String[] {});
		String output = outputCapture.toString();

		// Then
		assertThat(output, containsString("Tomcat started on port(s):"));
	}

}
