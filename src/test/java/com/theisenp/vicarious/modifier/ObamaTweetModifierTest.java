package com.theisenp.vicarious.modifier;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import twitter4j.User;

/**
 * Unit tests for {@link ObamaTweetModifier}
 * 
 * @author patrick.theisen
 */
public class ObamaTweetModifierTest {

	// Constants
	private static final String NAME = "Patrick Theisen";
	private static final String PATTERN = "^.*#yourewelcomepatricktheisen$";

	@Test
	public void testResponse() {
		ObamaTweetModifier modifier = new ObamaTweetModifier();

		User user = mock(User.class);
		when(user.getName()).thenReturn(NAME);

		for(int i = 0; i < 10000; i++) {
			String response = modifier.respond(user, "");
			assertThat(response.matches(PATTERN)).isTrue();
		}
	}
}
