package com.yichudu.yichuutil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class PoolingHttpClient {
	// thread-safe
	private static CloseableHttpClient httpClient;
	private static PoolingHttpClientConnectionManager cm;
	static {
		init();
		closeExpiredConnectionsPeriodTask(60);
	}

	static void init() {
		cm = new PoolingHttpClientConnectionManager();
		// max connections
		cm.setMaxTotal(200);
		// max connections per route
		cm.setDefaultMaxPerRoute(20);
		// set max connections for a specified route
		cm.setMaxPerRoute(new HttpRoute(new HttpHost("locahost", 80)), 50);

		final RequestConfig requestConfig = RequestConfig.custom()
		// the socket timeout (SO_TIMEOUT) in milliseconds
				.setSocketTimeout(5000)
				// the timeout in milliseconds until a connection is
				// established.
				.setConnectTimeout(5000)
				// the timeout in milliseconds used when requesting a connection
				// from the connection pool.
				.setConnectionRequestTimeout(5000).build();
		httpClient = HttpClients.custom().setConnectionManager(cm)
				.setDefaultRequestConfig(requestConfig).build();
	}

	public static String get(String url) throws ClientProtocolException,
			IOException {
		CloseableHttpResponse response1 = null;
		System.out.println(httpClient);
		Scanner scanner = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			response1 = httpClient.execute(httpGet);
			HttpEntity entity1 = response1.getEntity();
			InputStream instream = entity1.getContent();
			StringBuilder sb = new StringBuilder();
			scanner = new Scanner(instream, "utf-8");
			while (scanner.hasNextLine())
				sb.append(scanner.nextLine() + "\n");
			EntityUtils.consume(entity1);
			httpGet.releaseConnection();
			return sb.toString();
		} finally {
			if (scanner != null)
				scanner.close();
			if (response1 != null)
				response1.close();
		}
	}

	private static void closeExpiredConnectionsPeriodTask(
			final int timeUnitBySecond) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					try {
						TimeUnit.SECONDS.sleep(timeUnitBySecond);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					cm.closeExpiredConnections();
				}

			}
		}).start();
	}

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		for (int i = 0; i < 100; i++) {
			new Thread() {
				public void run() {
					try {
						System.out.print(get("http://www.baidu.com"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				};
			}.start();
		}
	}
}