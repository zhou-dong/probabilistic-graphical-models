package org.dongzhou.rescueTime;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class RescueTime {

	private static Logger logger = Logger.getLogger(RescueTime.class.getName());

	private static CloseableHttpClient httpclient = null;

	private static DBObject getDailySummaryFeed() throws IOException {
		CloseableHttpResponse response = null;
		HttpUriRequest request = null;
		try {
			httpclient = HttpClients.createDefault();
			request = ApiUtil.createDailySummaryFeedRequest();
			response = httpclient.execute(request);
			logger.info(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity);
			System.out.println(content);
			return (DBObject) JSON.parse(content);
		} finally {
			ApiUtil.close(response);
			ApiUtil.close(httpclient);
		}
	}

	public final static void main(String[] args) throws Exception {
		System.out.println(getDailySummaryFeed());
	}

}
