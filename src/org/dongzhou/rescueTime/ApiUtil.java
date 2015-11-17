package org.dongzhou.rescueTime;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.log4j.Logger;

public class ApiUtil {

	private static Logger logger = Logger.getLogger(ApiUtil.class.getName());

	private static final String KEY = "B63vb_55nMqMqfDXcvoNJQqYcavyMlGnClMPRLeT";

	public static final String ANALYTIC_DATA_API = "https://www.rescuetime.com/anapi/data";
	public static final String DAILY_SUMMARY_API = "https://www.rescuetime.com/anapi/daily_summary_feed";

	// https://www.rescuetime.com/anapi/setup/documentation#daily-summary-feed-reference
	public static HttpUriRequest createAnalyticDataApiRequest() {
		return createRequest(createURI(ANALYTIC_DATA_API));
	}

	// https://www.rescuetime.com/anapi/setup/documentation#daily-summary-feed-reference
	public static HttpUriRequest createDailySummaryFeedRequest() {
		return createRequest(createURI(DAILY_SUMMARY_API));
	}

	private static HttpUriRequest createRequest(URI uri) {
		logger.info("build request with uri: " + uri);
		return RequestBuilder.get().setUri(uri).addParameter("key", KEY).build();
	}

	private static URI createURI(String uri) {
		try {
			return new URI(uri);
		} catch (URISyntaxException e) {
			logger.error(e);
			return null;
		}
	}

	public static void close(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException e) {
			logger.error(e);
		}
	}
}
