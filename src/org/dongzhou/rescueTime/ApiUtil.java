package org.dongzhou.rescueTime;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.RequestBuilder;
import org.apache.log4j.Logger;

public class ApiUtil {

	private static Logger logger = Logger.getLogger(ApiUtil.class.getName());

	private static final String KEY = "B63vb_55nMqMqfDXcvoNJQqYcavyMlGnClMPRLeT";

	public static RequestBuilder createRequestBuilder(String uri) {
		return createRequestBuilder(createURI(uri));
	}

	private static RequestBuilder createRequestBuilder(URI uri) {
		return RequestBuilder.get().setUri(uri).addParameter("key", KEY);
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
