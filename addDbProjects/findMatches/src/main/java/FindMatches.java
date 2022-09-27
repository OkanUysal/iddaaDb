import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FindMatches {

	public static String startDate = "01/09/2022";
	public static String endDate = "26/09/2022";

	public static void main(String[] args) {

		Date d = null;
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		try {
			d = simpleDateFormat.parse(startDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (!endDate.equals(simpleDateFormat.format(d))) {
			try {
				String result = Jsoup.connect("https://goapi.mackolik.com/livedata?date=" + simpleDateFormat.format(d))
						.get().select("body").text();

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
