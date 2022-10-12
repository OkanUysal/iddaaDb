import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Utils {

	public static Set<Integer> countries = new HashSet<Integer>();
	public static Set<Integer> leagues = new HashSet<Integer>();
	public static Set<Integer> seasons = new HashSet<Integer>();
	public static Set<Integer> teams = new HashSet<Integer>();
	public static HashMap<Integer, String> matchDetails = new HashMap<>();

	public static boolean sendPost(String uri, String json) {
		Date d, d2;
		d = new Date();
		try {
			URL url = new URL(uri);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);

			try (OutputStream os = con.getOutputStream()) {
				byte[] input = json.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
//				System.out.println(response.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
//			System.exit(0);
			return false;
		}
		d2 = new Date();
		System.out.println("Post request: " + uri + "-" + json + " - Time: " + (d2.getTime() - d.getTime()) + " ns.");
		return true;
	}

	public static String getRequest(String uri) {
		Date d, d2;
		d = new Date();
		StringBuilder response = new StringBuilder();
		try {
			URL url = new URL(uri);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);

			try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {

				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		d2 = new Date();
//		System.out.println("Get request: " + uri + " - Time: " + (d2.getTime() - d.getTime()) + " ns.");
		return response.toString();
	}

}
