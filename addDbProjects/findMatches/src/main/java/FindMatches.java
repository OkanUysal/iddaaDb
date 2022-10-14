import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FindMatches {

	public static String startDate = "01/06/2022";
	public static String endDate = "13/10/2022";

	public static void main(String[] args) {

		Date d = null;
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		try {
			JsonArray countriesJsonArray = new JsonParser().parse(Utils.getRequest("http://localhost:8080/countries")).getAsJsonArray();
			countriesJsonArray.forEach( (c) -> {
				Utils.countries.add(c.getAsJsonObject().get("id").getAsInt());
			});
			
			JsonArray leagueJsonArray = new JsonParser().parse(Utils.getRequest("http://localhost:8080/leagues")).getAsJsonArray();
			leagueJsonArray.forEach( (l) -> {
				Utils.leagues.add(l.getAsJsonObject().get("id").getAsInt());
			});
			
			JsonArray seasonsJsonArray = new JsonParser().parse(Utils.getRequest("http://localhost:8080/seasons")).getAsJsonArray();
			seasonsJsonArray.forEach( (s) -> {
				Utils.seasons.add(s.getAsJsonObject().get("id").getAsInt());
			});
			
			JsonArray teamsJsonArray = new JsonParser().parse(Utils.getRequest("http://localhost:8080/teams")).getAsJsonArray();
			teamsJsonArray.forEach( (t) -> {
				Utils.teams.add(t.getAsJsonObject().get("id").getAsInt());
			});
			
			JsonArray matchDetailsJsonArray = new JsonParser().parse(Utils.getRequest("http://localhost:8080/matchDetailIds")).getAsJsonArray();
			matchDetailsJsonArray.forEach( (m) -> {
				Utils.matchDetails.add(m.getAsInt());
			});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			d = simpleDateFormat.parse(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (!endDate.equals(simpleDateFormat.format(d))) {
			try {
				Date d1, d2;
				d1 = new Date();
				String result = Jsoup.connect("https://goapi.mackolik.com/livedata?date=" + simpleDateFormat.format(d))
						.get().select("body").text();
				d2 = new Date();
//				System.out.println("Goapi request: " + simpleDateFormat.format(d) + " - Time: " + (d2.getTime() - d1.getTime()) + " ns.");

				JsonObject json = new JsonParser().parse(result).getAsJsonObject();

				JsonArray matches = json.get("m").getAsJsonArray();

				matches.forEach((n) -> {
					JsonArray match = n.getAsJsonArray();
					if (match.get(5).getAsInt() == 4 && !match.get(6).getAsString().equals("Ert.")) { // Is a football
																										// match and Is
																										// a cancel?
//						System.out.println(match.get(0).getAsInt() + ": " + match.get(2).getAsString() + "-" + match.get(4).getAsString());

						try {
							DbInfo dbInfo = new DbInfo(match.get(36).getAsJsonArray().get(0).getAsInt(),
									match.get(36).getAsJsonArray().get(1).getAsString(),
									match.get(36).getAsJsonArray().get(2).getAsInt(),
									match.get(36).getAsJsonArray().get(3).getAsString(),
									match.get(36).getAsJsonArray().get(4).getAsInt(),
									match.get(36).getAsJsonArray().get(5).getAsString(), match.get(0).getAsInt(),
									match.get(1).getAsInt(), match.get(2).getAsString(), match.get(3).getAsInt(),
									match.get(4).getAsString(), match.get(29).getAsInt(), match.get(30).getAsInt(),
									match.get(31).getAsInt(), match.get(32).getAsInt(),
									match.get(35).getAsString() + " " + match.get(16).getAsString());
							dbInfo.addDb();
//							System.exit(0);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			} catch (Exception e) {
				e.printStackTrace();
			}

			d.setTime(d.getTime() + (long) ((long) 86400000));
		}

	}

}
