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
	private static final String PATTERN = "^.*#YoureWelcome%s$";

	@Test
	public void testResponseNameFirstOnly() {
		String input = "Barack";
		String expected = String.format(PATTERN, "Barack");
		ObamaTweetModifier modifier = new ObamaTweetModifier();

		User user = mock(User.class);
		when(user.getName()).thenReturn(input);

		String response = modifier.respond(user, "");
		assertThat(response.matches(expected)).isTrue();
	}

	@Test
	public void testResponseNameFirstAndLast() {
		String input = "Barack Obama";
		String expected = String.format(PATTERN, "Barack");
		ObamaTweetModifier modifier = new ObamaTweetModifier();

		User user = mock(User.class);
		when(user.getName()).thenReturn(input);

		String response = modifier.respond(user, "");
		assertThat(response.matches(expected)).isTrue();
	}

	@Test
	public void testResponseNameEmpty() {
		String input = "";
		String expected = String.format(PATTERN, "");
		ObamaTweetModifier modifier = new ObamaTweetModifier();

		User user = mock(User.class);
		when(user.getName()).thenReturn(input);

		String response = modifier.respond(user, "");
		assertThat(response.matches(expected)).isTrue();
	}

	@Test
	public void testResponseNameWhitespace() {
		String input = "\t  ";
		String expected = String.format(PATTERN, "");
		ObamaTweetModifier modifier = new ObamaTweetModifier();

		User user = mock(User.class);
		when(user.getName()).thenReturn(input);

		String response = modifier.respond(user, "");
		assertThat(response.matches(expected)).isTrue();
	}

	@Test
	public void testResponseNameBeginsWithWhitespace() {
		String input = "\t Barack";
		String expected = String.format(PATTERN, "Barack");
		ObamaTweetModifier modifier = new ObamaTweetModifier();

		User user = mock(User.class);
		when(user.getName()).thenReturn(input);

		String response = modifier.respond(user, "");
		assertThat(response.matches(expected)).isTrue();
	}
	
	@Test
	public void testResponseNameLowercase() {
		String input = "\t barack";
		String expected = String.format(PATTERN, "Barack");
		ObamaTweetModifier modifier = new ObamaTweetModifier();
		
		User user = mock(User.class);
		when(user.getName()).thenReturn(input);
		
		String response = modifier.respond(user, "");
		assertThat(response.matches(expected)).isTrue();
	}
	
	@Test
	public void testResponseNameUppercase() {
		String input = "\t BARACK";
		String expected = String.format(PATTERN, "BARACK");
		ObamaTweetModifier modifier = new ObamaTweetModifier();
		
		User user = mock(User.class);
		when(user.getName()).thenReturn(input);
		
		String response = modifier.respond(user, "");
		assertThat(response.matches(expected)).isTrue();
	}
}
