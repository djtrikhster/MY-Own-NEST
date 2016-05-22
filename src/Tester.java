

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Tester {
    public static void main(String[] args) {
        String authKey = "c.82OAAGKPzxs9eoXUpEra5IfSVArHladLGxPS6mvRazpmQ6sfH0Bl1qBEan2WHUiQpjzSN9jvFYRjEErTez9VceNt4YQo3Dnp2OBRJgYirZ0DvmT5otjNd05zql82UgMu8AFnyHkCtuxa8A5O";
        String text = "https://developer-api.nest.com/devices/thermostats/jUtUxGfk_NZ6ayV6po80wJTQQlw1CT7W/target_temperature_f";
        Tester.excutePost(text, " ", authKey);
    }

    public static String excutePost(String targetURL, String urlParameters, String authK) {
        HttpURLConnection connection = null;
        try {
            String line;
            URL url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "Bearer " + authK);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.append(Integer.toString(70));
            writer.flush();
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            String string = response.toString();
            return string;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}