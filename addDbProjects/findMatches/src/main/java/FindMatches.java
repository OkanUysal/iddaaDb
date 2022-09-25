import java.util.ArrayList;

import org.jsoup.Jsoup;

import com.google.gson.GsonBuilder;
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

			GsonBuilder builder = new GsonBuilder();
			Object obj = builder.create().fromJson(result, Object.class);

			@SuppressWarnings("unchecked")
			LinkedTreeMap<String, Object> linkedTreeMap = (LinkedTreeMap<String, Object>) obj;

			@SuppressWarnings("unchecked")
			ArrayList<Object> matches = (ArrayList<Object>) linkedTreeMap.get("m");

			matches.forEach((n) -> {
				@SuppressWarnings("unchecked")
				ArrayList<Object> t = (ArrayList<Object>) n;
				System.out.println(((Double) t.get(0)).intValue() + ": " + t.get(2) + "-" + t.get(4));
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
