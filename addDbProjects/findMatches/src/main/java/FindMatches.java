import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.jsoup.Jsoup;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

public class FindMatches {

	public static String date = "16/09/2022";

	public static void main(String[] args) {

//		Date d = new Date();
//		String pattern = "dd/MM/yyyy";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//
////		d.setTime(d.getTime() - (long) ((long) 86400000 * 4910)); // 1 Ocak 2012
//		d.setTime(d.getTime() - (long)((long)86400000));

		try {
			String result = Jsoup.connect("https://goapi.mackolik.com/livedata?date=" + date).get().select("body")
					.text();
			
			JsonObject json = new JsonParser().parse(result).getAsJsonObject();
			
			JsonArray matches = json.get("m").getAsJsonArray();
			
			matches.forEach((n) -> {
				JsonArray match = n.getAsJsonArray();
				if(match.get(5).getAsInt() == 4 && !match.get(6).getAsString().equals("Ert.")) { // Is a football match and Is a cancel?					
//					System.out.println(match.get(0).getAsInt() + ": " + match.get(2).getAsString() + "-" + match.get(4).getAsString());
					
					try {
						DbInfo dbInfo = new DbInfo(match.get(36).getAsJsonArray().get(0).getAsInt(),
								match.get(36).getAsJsonArray().get(1).getAsString(), match.get(36).getAsJsonArray().get(2).getAsInt(),
								match.get(36).getAsJsonArray().get(3).getAsString(), match.get(36).getAsJsonArray().get(4).getAsInt(),
								match.get(36).getAsJsonArray().get(5).getAsString(), match.get(0).getAsInt(), match.get(1).getAsInt(),
								match.get(2).getAsString(), match.get(3).getAsInt(), match.get(4).getAsString(), match.get(29).getAsInt(),
								match.get(30).getAsInt(), match.get(31).getAsInt(), match.get(32).getAsInt(),
								match.get(35).getAsString() + " " + match.get(16).getAsString());
						dbInfo.addDb();
//						System.exit(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
