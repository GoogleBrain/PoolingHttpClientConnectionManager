import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

public class TTT {

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpgets = new HttpPost("http://localhost:8080/Server/one");
		httpgets.addHeader("User-Agent", "imgfornote");

		JSONObject obj = new JSONObject();
		obj.put("age", "1234");
		obj.put("name", "你大爷的。。。");
		StringEntity entity1 = new StringEntity(obj.toString(), "utf-8");
		entity1.setContentEncoding("UTF-8");
		entity1.setContentType("application/json");
		httpgets.setEntity(entity1);

		HttpResponse response = httpclient.execute(httpgets);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instreams = entity.getContent();
			String str = convertStreamToString(instreams);
			System.out.println(str);
		}
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}