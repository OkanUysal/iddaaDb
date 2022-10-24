import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FindSporTotoMatches {

	public static ArrayList<String> matchesList = new ArrayList<>();

	public static void main(String[] args) {
		try {
			for (int i = 153; i < 154; i++) {
				Document doc = Jsoup.connect("https://www.nesine.com/sportoto/mac-sonuclari?pNo=" + i + "_1").get();

				Elements table = doc.select("div.table-responsive");
				Elements matches = table.get(0).select("tr");
				for (int j = 1; j < matches.size(); j++) {
					JsonObject data = new JsonObject();
					Elements tds = matches.get(j).select("td");
					System.out.println(tds.get(2).text() + "    " + tds.get(3).text() + ": " + tds.get(4).text());
					if (!tds.get(4).text().equals("Noter") && !tds.get(3).text().equals("Şili-Bolivya")) {

						for (int x = 2; x < 6; x++) {
							int count = 0;
							String date = tds.get(2).text().split(" ")[0];
							date = date.split("\\.")[2] + "-" + date.split("\\.")[1] + "-" + date.split("\\.")[0];
							JsonArray matchesForDate = new JsonParser()
									.parse(Utils.getRequest("http://localhost:8080/matchDetailsforDate/" + date))
									.getAsJsonArray();
							String myDate = date + " " + tds.get(2).text().split(" ")[1];

							System.out.println(tds.get(4).text());
							System.out.println(tds.get(2).text());
							for (JsonElement ele : matchesForDate) {
								JsonObject o = ele.getAsJsonObject();

								if (tds.get(4).text().equals(
										o.get("homeMatchScore").getAsInt() + " - " + o.get("awayMatchScore").getAsInt())
										&& myDate.equals(o.get("date").getAsString()) ||
								/* Bug fix */ (date.equals("2020-09-27")
										&& o.get("homeName").getAsString().equals("Barcelona"))
										|| (date.equals("2020-12-13")
												&& o.get("homeName").getAsString().equals("Başakşehir FK"))
										|| (date.equals("2020-12-28")
												&& o.get("homeName").getAsString().equals("Antalyaspor"))
										|| (date.equals("2021-01-30")
												&& o.get("homeName").getAsString().equals("Alanyaspor"))
										|| (date.equals("2021-02-15")
												&& o.get("homeName").getAsString().equals("Gençlerbirliği"))
										|| (date.equals("2021-02-14")
												&& o.get("homeName").getAsString().equals("Galatasaray"))
										|| (date.equals("2021-06-06")
												&& o.get("homeName").getAsString().equals("Santos"))
										|| (date.equals("2021-06-05")
												&& o.get("homeName").getAsString().equals("Orlando Pirates"))
										|| (date.equals("2021-07-18")
												&& o.get("homeName").getAsString().equals("Göteborg"))
										|| (date.equals("2022-02-27")
												&& o.get("homeName").getAsString().equals("Lazio"))
										|| (date.equals("2022-03-18")
												&& o.get("homeName").getAsString().equals("Çaykur Rizespor"))
										|| (date.equals("2022-05-15")
												&& o.get("homeName").getAsString().equals("Atletico Madrid"))
										|| (date.equals("2022-05-15")
												&& o.get("homeName").getAsString().equals("Villarreal"))
										|| (date.equals("2022-05-28")
												&& o.get("homeName").getAsString().equals("Liverpool"))
										|| (date.equals("2022-05-29")
												&& o.get("homeName").getAsString().equals("IFK Mariehamn"))
										|| (date.equals("2022-05-28")
												&& o.get("homeName").getAsString().equals("HamKam"))
										|| (date.equals("2022-06-06")
												&& o.get("homeName").getAsString().equals("Avusturya"))
										|| (date.equals("2022-06-19") && o.get("homeName").getAsString().equals("Jerv"))
										|| (date.equals("2022-06-26")
												&& o.get("homeName").getAsString().equals("HamKam"))
										|| (date.equals("2022-07-02") && o.get("homeName").getAsString().equals("Oulu"))
										|| (date.equals("2022-07-09") && o.get("homeName").getAsString().equals("VPS"))
										|| (date.equals("2022-07-16")
												&& o.get("homeName").getAsString().equals("HamKam"))
										|| (date.equals("2022-07-24")
												&& o.get("homeName").getAsString().equals("Stromsgodset II"))) {

									boolean find = false;
									boolean find2 = false;
									for (int k = 0; k < o.get("homeName").getAsString().length() - x; k++) {
										if (tds.get(3).text().split("-")[0]
												.contains(o.get("homeName").getAsString().substring(k, k + x + 1))) {
											find = true;
											break;
										}
									}
									for (int k = 0; k < o.get("awayName").getAsString().length() - x; k++) {
										if (tds.get(3).text().split("-")[1]
												.contains(o.get("awayName").getAsString().substring(k, k + x + 1))) {
											find2 = true;
											break;
										}
									}
									if ((find && find2)
											|| (find && date.equals("2021-06-05")
													&& o.get("homeName").getAsString().equals("Orlando Pirates"))
											|| (find && date.equals("2022-05-29")
													&& o.get("awayName").getAsString().equals("VPS"))
											|| (find2 && date.equals("2022-05-28")
													&& o.get("awayName").getAsString().equals("Odds BK"))
											|| (find && date.equals("2022-06-19")
													&& o.get("awayName").getAsString().equals("HamKam"))
											|| (find2 && date.equals("2022-06-26")
													&& o.get("awayName").getAsString().equals("Sarpsborg 08"))
											|| (find && date.equals("2022-07-02")
													&& o.get("awayName").getAsString().equals("VPS"))
											|| (find2 && date.equals("2022-07-09")
													&& o.get("awayName").getAsString().equals("IFK Mariehamn"))
											|| (find2 && date.equals("2022-07-16")
													&& o.get("awayName").getAsString().equals("Bodo / Glimt"))
											|| (find && date.equals("2022-07-24")
													&& o.get("awayName").getAsString().equals("HamKam"))) {
										System.out.println(o.get("homeName").getAsString() + "-"
												+ o.get("awayName").getAsString());
										data = new JsonObject();
										data.addProperty("weekNumber", i);
										JsonObject o2 = new JsonObject();
										o2.addProperty("id", o.get("id").getAsInt());
										data.add("matchDetail", o2);
										count++;
									}
								}
							}
							if (count != 1) {
								System.out.println("Hata!!! " + i);
								if (x == 5) {
									System.exit(1);
								}
							} else {
								System.out.println(data.toString());
								Utils.sendPost("http://localhost:8080/addSporTotoMatch", data.toString());
								System.out.println();
								System.out.println();
								break;
							}

						}

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
