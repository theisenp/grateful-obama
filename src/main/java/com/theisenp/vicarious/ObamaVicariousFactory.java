package com.theisenp.vicarious;

import java.io.File;
import java.io.IOException;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.theisenp.vicarious.logger.CompositeTweetLogger;
import com.theisenp.vicarious.logger.ConsoleTweetLogger;
import com.theisenp.vicarious.logger.FileTweetLogger;
import com.theisenp.vicarious.logger.TweetLogger;
import com.theisenp.vicarious.modifier.ObamaTweetModifier;
import com.theisenp.vicarious.modifier.TweetModifier;
import com.theisenp.vicarious.provider.BaseTweetProvider;
import com.theisenp.vicarious.provider.FileIntervalTweetFetcher;
import com.theisenp.vicarious.provider.QueryTweetFetcher;
import com.theisenp.vicarious.provider.ThrottledTweetProvider;
import com.theisenp.vicarious.provider.TweetFetcher;
import com.theisenp.vicarious.provider.TweetProvider;
import com.theisenp.vicarious.publisher.BlockingTweetPublisher;
import com.theisenp.vicarious.publisher.TweetPublisher;

/**
 * Implementation of {@link VicariousFactory} for GratefulObama
 * 
 * @author patrick.theisen
 */
public class ObamaVicariousFactory implements VicariousFactory {

	// Constants
	private static final String QUERY = "#thanksobama";
	private static final String FILE_PATH = ".lastTweet";

	// Data
	private final TweetProvider provider;
	private final TweetModifier modifier;
	private final TweetPublisher publisher;
	private final TweetLogger logger;

	/**
	 * @throws IOException
	 */
	public ObamaVicariousFactory() throws IOException {
		Twitter twitter = TwitterFactory.getSingleton();
		File file = new File(FILE_PATH);

		// Create the provider
		QueryTweetFetcher queryFetcher = new QueryTweetFetcher(QUERY);
		TweetFetcher fetcher = new FileIntervalTweetFetcher(file, queryFetcher);
		TweetProvider rawProvider = new BaseTweetProvider(twitter, fetcher);
		provider = new ThrottledTweetProvider(rawProvider, 5);

		// Create the modifier
		modifier = new ObamaTweetModifier();

		// Create the publisher
		publisher = new BlockingTweetPublisher(twitter);

		// Create the logger
		TweetLogger fileLogger = new FileTweetLogger(file);
		TweetLogger consoleLogger = new ConsoleTweetLogger();
		logger = new CompositeTweetLogger(fileLogger, consoleLogger);
	}

	@Override
	public TweetProvider getTweetProvider() {
		return provider;
	}

	@Override
	public TweetModifier getTweetModifier() {
		return modifier;
	}

	@Override
	public TweetPublisher getTweetPublisher() {
		return publisher;
	}

	@Override
	public TweetLogger getTweetLogger() {
		return logger;
	}
}
