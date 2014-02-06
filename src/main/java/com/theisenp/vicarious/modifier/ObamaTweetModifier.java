package com.theisenp.vicarious.modifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import twitter4j.User;

/**
 * Extension of {@link BaseTweetReplyModifier} that responds gratefully to
 * tweets thank Obama
 * 
 * @author patrick.theisen
 */
public class ObamaTweetModifier extends BaseTweetReplyModifier {

	// Constant
	private static final Random RANDOM = new Random();
	private static final String PATTERN = "%s, %s! #YoureWelcome%s";
	private static final List<String> OPENINGS = new ArrayList<String>();
	private static final List<String> CLOSINGS = new ArrayList<String>();
	static {
		OPENINGS.add("All in a day's work");
		OPENINGS.add("Any time");
		OPENINGS.add("Don't mention it");
		OPENINGS.add("Happy to hear it");
		OPENINGS.add("I'm glad I could help");
		OPENINGS.add("It's all part of the job");
		OPENINGS.add("My pleasure");
		OPENINGS.add("No problem");
		OPENINGS.add("Of course");
		OPENINGS.add("You're welcome");

		CLOSINGS.add("enjoy");
		CLOSINGS.add("have a great day");
		CLOSINGS.add("I couldn't do it without folks like you");
		CLOSINGS.add("I'm grateful to have your support");
		CLOSINGS.add("I'm thrilled to have your support");
		CLOSINGS.add("I appreciate the support");
		CLOSINGS.add("I've got your back");
		CLOSINGS.add("thanks for the support");
		CLOSINGS.add("your support means a lot to me");
		CLOSINGS.add("let me know if you need anything else");
	}

	@Override
	protected String respond(User user, String text) {
		// Choose an opening at random
		int openingIndex = RANDOM.nextInt(OPENINGS.size());
		String opening = OPENINGS.get(openingIndex);

		// Choose a closing at random
		int closingIndex = RANDOM.nextInt(CLOSINGS.size());
		String closing = CLOSINGS.get(closingIndex);

		// Camel case the user's first name
		String name = formatName(user.getName());

		return String.format(PATTERN, opening, closing, name);
	}

	/**
	 * @param name
	 * The name to format
	 * @return The first name in the given name, with the first letter
	 * capitalized
	 */
	private static String formatName(String name) {
		// Take the first non-empty token
		String firstName = name.replaceAll("\\s", "");
		for(String token : name.split("\\s")) {
			if(token.length() > 0) {
				firstName = token;
				break;
			}
		}

		// Uppercase only the first letter
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < firstName.length(); i++) {
			char c = firstName.charAt(i);
			builder.append(i == 0 ? Character.toUpperCase(c) : c);
		}

		return builder.toString();
	}
}
