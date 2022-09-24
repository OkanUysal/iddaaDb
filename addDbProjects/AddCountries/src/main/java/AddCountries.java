import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddCountries {

	public static void main(String[] args) {

		try {
			Document doc = Jsoup.connect("https://arsiv.mackolik.com/Puan-Durumu").get();

			Elements eles = doc.select("div.news-select-temp");
			Elements eles2 = eles.get(0).select("select");

			Elements eles3 = eles2.get(1).select("option");

			eles3.forEach((n) -> {
				sendPost(n.attr("value"), n.text());
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void sendPost(String id, String name) {

		try {
			URL url = new URL("http://localhost:8080/addCountry");

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);

			JsonObject obj = new JsonObject();
			obj.addProperty("id", Integer.valueOf(id));
			obj.addProperty("name", name);

			String json = obj.toString();

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
				System.out.println(response.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
