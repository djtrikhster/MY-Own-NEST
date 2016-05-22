import java.io.*;
import java.net.*;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "https://developer-api.nest.com/devices/thermostats/jUtUxGfk_NZ6ayV6po80wJTQQlw1CT7W/target_temperature_f";
		String auth = "c.82OAAGKPzxs9eoXUpEra5IfSVArHladxPS6mvRazpmQ6sfH0Bl1qBEan2WHUiQpjzSN9jvFYRjEErTez9VceNt4YQo3Dnp2OBRJgYirZ0DvmT5otjNd05zql82UgMu8AFnyHkCtuxa8A5O";
		String data = "70";
		excutePut(url, data, auth);
	}

	public static String excutePut(String targetURL, String urlParameters, String authKey) {
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Authorization", "Bearer " + authKey);


			//connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
		    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		    writer.append(urlParameters);
		    writer.flush();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if
															// not Java 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
