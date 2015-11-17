package org.dongzhou.rescueTime;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class RescueTime {

	private static Logger logger = Logger.getLogger(RescueTime.class.getName());

	public static final String ANALYTIC_DATA_API = "https://www.rescuetime.com/anapi/data";
	public static final String DAILY_SUMMARY_API = "https://www.rescuetime.com/anapi/daily_summary_feed";

	/**
	 * @param begin:
	 *            format: 2015-11-12
	 * 
	 * @param end:
	 *            format: 2015-11-17
	 * 
	 * @param type:
	 *            csv or json
	 */
	public static String getAnalyticData(String begin, String end, String type)
			throws ClientProtocolException, IOException {
		RequestBuilder requestBuilder = ApiUtil.createRequestBuilder(ANALYTIC_DATA_API);
		requestBuilder.addParameter("perspective", "rank");
		requestBuilder.addParameter("restrict_kind", "overview");
		requestBuilder.addParameter("restrict_begin", begin);
		requestBuilder.addParameter("restrict_end", end);
		requestBuilder.addParameter("format", type);
		HttpUriRequest request = requestBuilder.build();
		return getContent(request);
	}

	public static String getAnalyticData() throws ClientProtocolException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(System.currentTimeMillis());
		return getAnalyticData("2015-11-12", today, "csv");
	}

	public static DBObject getDailySummary() throws IOException {
		HttpUriRequest request = ApiUtil.createRequestBuilder(DAILY_SUMMARY_API).build();
		String content = getContent(request);
		return (DBObject) JSON.parse(content);
	}

	private static String getContent(HttpUriRequest request)
			throws ClientProtocolException, IOException {
		logger.info(request);
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClients.createDefault();
			response = httpclient.execute(request);
			logger.info(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} finally {
			ApiUtil.close(response);
			ApiUtil.close(httpclient);
		}
	}

	public final static void main(String[] args) throws Exception {
		String analyze = getAnalyticData();
		logger.info(analyze);
		DBObject dailySummary = getDailySummary();
		logger.info(dailySummary);
	}

}
