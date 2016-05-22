import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SendText {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("To", "+7326093292");
		vars.put("From", "+8482602719");

		vars.put("Url", "http://www.xxx.com/assistedliving/redirect1");
		vars.put("Url", "https://myappengineapp.appspot.com/secure/twilio");

		String accountSid = "AC76a619b6e59471eff5d41f79355bb3d6";
		String authToken = "9051030c47a1cca92ad0c008c333e154";
		makeCall(vars, accountSid, authToken);

	}

	public static void makeCall(Map<String, String> vars, String accountSid, String authToken) {

		try {

			String encoded = "";
			if (vars != null) {
				for (String key : vars.keySet()) {
					try {
						encoded += "&" + key + "=" + URLEncoder.encode(vars.get(key), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
				encoded = encoded.substring(1);
			}

			URL url = new URL("https://api.twilio.com/2010-04-01/Accounts/" + accountSid + "/Calls");

			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

			String userpass = accountSid + ":" + authToken;

			httpURLConnection.setRequestProperty("Authorization", "");

			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("POST");

			OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
			out.write(encoded);
			out.close();

			BufferedReader in = null;
			try {
				if (httpURLConnection.getInputStream() != null) {
					in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
				}
			} catch (IOException e) {
				e.printStackTrace();
				if (httpURLConnection.getErrorStream() != null) {
					in = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
				}
			}

			if (in == null) {
				System.out.println("Unable to read response from server");
			}

			StringBuffer decodedString = new StringBuffer();
			String line;
			while ((line = in.readLine()) != null) {
				decodedString.append(line);
			}
			in.close();

			// get result code
			int responseCode = httpURLConnection.getResponseCode();
			System.out.println("response code is " + responseCode);

		} catch (Exception e) {

		}
	}

}