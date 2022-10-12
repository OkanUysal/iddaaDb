import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetCurrentSportTotoMatches {

	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("https://www.nesine.com/sportoto").get();
			Elements table = doc.select("div.table-responsive");
			Elements matches = table.get(0).select("tr");
			for (int j = 2; j < matches.size() - 1; j++) {
				Elements tds = matches.get(j).select("td");
				String home = tds.get(2).select("a").get(0).text().split("-")[0];
				String away = tds.get(2).select("a").get(0).text().split("-")[1];
				System.out.print(tds.get(2).select("a").get(0).text() + ": ");
				String result = Jsoup
						.connect("https://goapi.mackolik.com/livedata?date=" + tds.get(1).text().replaceAll("\\.", "/").split(" ")[0])
						.get().select("body").text();

				JsonObject json = new JsonParser().parse(result).getAsJsonObject();

				JsonArray mackolikMatches = json.get("m").getAsJsonArray();
				
				JsonArray array = getMatch(home, away, mackolikMatches, tds.get(1).text().split(" ")[1]);
				if(array != null) {
					System.out.println("(" + array.get(1).getAsString() + "-" + array.get(3).getAsString() + ")" + array.get(18).getAsString() + "-" + array.get(19).getAsString() + "-" + array.get(20).getAsString());
				} else {
					System.out.println("Error!!!");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static JsonArray getMatch(String home, String away, JsonArray matches, String hour) {
		for (int i = 0; i < matches.size(); i++) {
			if(hour.equals(matches.get(i).getAsJsonArray().get(16).getAsString())) {
				if(home.equals(matches.get(i).getAsJsonArray().get(2).getAsString()) &&
						away.equals(matches.get(i).getAsJsonArray().get(4).getAsString())) {
//					System.out.println(matches.get(i).getAsJsonArray().get(2).getAsString() + "-" + matches.get(i).getAsJsonArray().get(4).getAsString());
					return matches.get(i).getAsJsonArray();
				}
				
				for (int j = 5; j > 1; j--) {
					boolean find = false;
					boolean find2 = false;
					for (int k = 0; k < home.length() - j; k++) {
						if (matches.get(i).getAsJsonArray().get(2).getAsString()
								.contains(home.substring(k, k + j + 1))) {
							find = true;
							break;
						}
					}
					for (int k = 0; k < away.length() - j; k++) {
						if (matches.get(i).getAsJsonArray().get(4).getAsString()
								.contains(away.substring(k, k + j + 1))) {
							find2 = true;
							break;
						}
					}
					if(find && find2) {
//						System.out.println(matches.get(i).getAsJsonArray().get(2).getAsString() + "-" + matches.get(i).getAsJsonArray().get(4).getAsString());
						return matches.get(i).getAsJsonArray();
					}
				}
			}
			
		}

		return null;
	}

}
