import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Http_Interface {
	private static String link, param, auth;
	public static boolean end = false;
	public static boolean timer, hvac;

	public static void main(String[] args) {
		int temp = 70;
		link = "https://developer-api.nest.com/devices/thermostats/jUtUxGfk_NZ6ayV6po80wJTQQlw1CT7W/";
		// System.out.println("link");
		param = Integer.toString(temp);
		// System.rrlr
		auth = "c.82OAAGKPzxs9eoXUpEra5IfSVArHladLGxPS6mvRazpmQ6sfH0Bl1qBEan2WHUiQpjzSN9jvFYRjEErTez9VceNt4YQo3Dnp2OBRJgYirZ0DvmT5otjNd05zql82UgMu8AFnyHkCtuxa8A5O";
		// System.out.println("auth");
		// System.out.println("temp");
		Guesture(temp);
	}

	public static boolean keepGoing(char c, int t) {
		return end;
	}

	public static int Guesture(int t) {
		Scanner sc = new Scanner(System.in);
		char c;
		do {

			c = sc.next().charAt(0);
			if (c == 'r') { // increase
				t = TempUp(t);
				System.out.println(t);
			}
			if (c == 'l') {
				t = TempDown(t); // decrease
				System.out.println(t);
			}
			if (c == 'q') { // quit

				Scanner sc2 = new Scanner(System.in);
				char c2= sc.next().charAt(0);
				if (c2 == 'q') {
					continue;
				}else{
					end = true;
					System.out.println("Terminated");
				}

				sc2.close();
			}
			/*
			 * if (c == 't')// timer { if (timer == true) { hasTimer("false");
			 * timer = false; System.out.println(timer); // break; } else {
			 * hasTimer("true"); timer = true; System.out.println(timer);
			 * //break; }
			 * 
			 * }
			 */
			if (c == 'h') // hvac
			{
				if (hvac == true) {
					hvacToggle("\"cool\"");
					hvac = false;
					System.out.println(hvac);
					// break;
				} else {
					hvacToggle("\"heat\"");
					hvac = true;
					System.out.println(hvac);
					// break;
				}

			}

			System.out.println(t);
		} while (keepGoing(c, t) == false);
		return t;
	}

	public static int TempUp(int temp) {
		Http_Interface.update("target_temperature_f", temp + 1);
		return temp + 1;
	}

	public static int TempDown(int temp) {
		Http_Interface.update("target_temperature_f", temp - 1);
		return temp - 1;
	}

	public static boolean hasTimer(String h) {
		Http_Interface.update("fan_timer_active", h);
		return timer;
	}

	public static boolean hvacToggle(String c) {
		Http_Interface.update("hvac_mode", c);
		return hvac;
	}

	public static String getLink() {
		return link;
	}

	public static void setLink(String link) {
		Http_Interface.link = link;
	}

	public static String getParam() {
		return param;
	}

	public static void setParam(String param) {
		Http_Interface.param = param;
	}

	public static String getAuth() {
		return auth;
	}

	public static void setAuth(String auth) {
		Http_Interface.auth = auth;
	}

	public static String update(String var, int data) {
		String newUrl = link + var;
		HttpURLConnection connection = null;
		try {
			String line;
			URL url = new URL(newUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Authorization", "Bearer " + auth);
			connection.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.append(Integer.toString(data));
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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			System.out.println("HTTP");
		}
	}

	public static String update(String var, String h) {
		String newUrl = link + var;
		HttpURLConnection connection = null;
		try {
			String line;
			URL url = new URL(newUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Authorization", "Bearer " + auth);
			connection.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.append(h);
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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			System.out.println("HTTP");
		}
	}
}
