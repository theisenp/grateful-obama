package com.theisenp.vicarious;

import java.io.IOException;

/**
 * Entry point for GratefulObama
 * 
 * @author patrick.theisen
 */
public class GratefulObama {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		VicariousRunner.run(new ObamaVicariousFactory());
	}
}
