import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DbInfo {

	public int countryId;
	public String countryName;
	public int leaugeId;
	public String leagueName;
	public int seasonId;
	public String seasonName;
	public int matchId;
	public int homeTeamId;
	public String homeTeamName;
	public int awayTeamId;
	public String awayTeamName;
	public int homeScore;
	public int awayScore;
	public int homeHalfScore;
	public int awayHalfScore;
	public String date;

	public DbInfo(int countryId, String countryName, int leaugeId, String leagueName, int seasonId, String seasonName,
			int matchId, int homeTeamId, String homeTeamName, int awayTeamId, String awayTeamName, int homeScore,
			int awayScore, int homeHalfScore, int awayHalfScore, String date) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.leaugeId = leaugeId;
		this.leagueName = leagueName;
		this.seasonId = seasonId;
		this.seasonName = seasonName;
		this.matchId = matchId;
		this.homeTeamId = homeTeamId;
		this.homeTeamName = homeTeamName;
		this.awayTeamId = awayTeamId;
		this.awayTeamName = awayTeamName;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.homeHalfScore = homeHalfScore;
		this.awayHalfScore = awayHalfScore;
		this.date = date;
	}

	public void addDb() {
		if(Utils.matchDetails.contains(this.matchId)) {
			return;
		} 
		
		// add country
		JsonObject country = new JsonObject();
		country.addProperty("id", this.countryId);
		country.addProperty("name", this.countryName);
		if (!Utils.countries.contains(this.countryId)) {
			if (Utils.sendPost("http://localhost:8080/addCountry", country.toString())) {
				Utils.countries.add(this.countryId);
			}
		}

		// add league
		JsonObject league = new JsonObject();
		league.addProperty("id", this.leaugeId);
		league.addProperty("name", this.leagueName);
		league.add("country", country);
		if (!Utils.leagues.contains(this.leaugeId)) {
			if (Utils.sendPost("http://localhost:8080/addLeague", league.toString())) {
				Utils.leagues.add(this.leaugeId);
			}
		}

		// add season
		JsonObject season = new JsonObject();
		season.addProperty("id", this.seasonId);
		season.addProperty("name", this.seasonName);
		season.add("league", league);
		if (!Utils.seasons.contains(this.seasonId)) {
			if (Utils.sendPost("http://localhost:8080/addSeason", season.toString())) {
				Utils.seasons.add(this.seasonId);
			}
		}

		// add home team
		JsonObject homeTeam = new JsonObject();
		homeTeam.addProperty("id", this.homeTeamId);
		homeTeam.addProperty("name", this.homeTeamName);
		if (!Utils.teams.contains(this.homeTeamId)) {
			if (Utils.sendPost("http://localhost:8080/addTeam", homeTeam.toString())) {
				Utils.teams.add(this.homeTeamId);
			}
		}

		// add away team
		JsonObject awayTeam = new JsonObject();
		awayTeam.addProperty("id", this.awayTeamId);
		awayTeam.addProperty("name", this.awayTeamName);
		if (!Utils.teams.contains(this.awayTeamId)) {
			if (Utils.sendPost("http://localhost:8080/addTeam", awayTeam.toString())) {
				Utils.teams.add(this.awayTeamId);
			}
		}

		// add match detail
		JsonObject matchDetail = new JsonObject();
		matchDetail.addProperty("id", this.matchId);
		matchDetail.add("home", homeTeam);
		matchDetail.add("away", awayTeam);
		matchDetail.add("season", season);
		matchDetail.addProperty("homeHalfTimeScore", this.homeHalfScore);
		matchDetail.addProperty("awayHalfTimeScore", this.awayHalfScore);
		matchDetail.addProperty("homeMatchScore", this.homeScore);
		matchDetail.addProperty("awayMatchScore", this.awayScore);
		matchDetail.addProperty("date", this.date);
//		if (!Utils.matchDetails.containsKey(this.matchId)) {
//			if (Utils.sendPost("http://localhost:8080/addMatchDetail", matchDetail.toString())) {
//				Utils.matchDetails.put(this.matchId, "");
//			}
//		}
		Utils.sendPost("http://localhost:8080/addMatchDetail", matchDetail.toString());

		getBetDetails();

	}

	private void getBetDetails() {
		try {
			Date d1, d2;
			
			d1 = new Date();
			Document doc = Jsoup.connect("http://arsiv.mackolik.com/Match/Default.aspx?id=" + matchId).get();
			d2 = new Date();
			
			System.out.println("Match details request: " + matchId + " - Time: " + (d2.getTime() - d1.getTime()) + " ns.");

			Elements betsTable = doc.select("div#compare-left-coll");

			Elements bets = betsTable.get(0).select("div.md");

			bets.forEach((b) -> {
				String text = b.select("div.detail-title").get(0).text();
				if ((text.startsWith("Maç Sonucu") && !text.startsWith("Maç Sonucu ve"))
						|| text.startsWith("Handikaplı Maç Sonucu")) {
					getHandicapMatchResult(text.substring(0, text.lastIndexOf(" ")), b.select("div.sgoutcome-name"),
							b.select("div.sgoutcome-value"));
//					System.exit(0);
				}
				if ((text.contains("Alt/Üst") && text.substring(0, text.lastIndexOf(" ")).length() < 14)) {
					getUnderOverResult(text.substring(0, text.lastIndexOf(" ")), b.select("div.sgoutcome-name"),
							b.select("div.sgoutcome-value"));
//					System.exit(0);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getHandicapMatchResult(String betName, Elements betNames, Elements betValues) {
		int handicap = 0;
		float ms1 = 1;
		float msx = 1;
		float ms2 = 1;
		float ms1p = 0;
		float msxp = 0;
		float ms2p = 0;
		float totalp = 0;

		if (betName.equals("Maç Sonucu")) {
			handicap = 0;
		} else {
			handicap = Integer.valueOf(betName.split("[():]")[1]) - Integer.valueOf(betName.split("[():]")[2]);
		}

//		if (matchDetails != null && !matchDetails.equals("")) {
//			JsonObject matchResults = new JsonParser().parse(matchDetails).getAsJsonObject();
//
//			for (int i = 0; i < matchResults.get("handicap").getAsJsonArray().size(); i++) {
//				if (matchResults.get("handicap").getAsJsonArray().get(i).getAsJsonObject().get("handicapNum")
//						.getAsInt() == handicap) {
//					return;
//				}
//			}
//		}

		for (int i = 0; i < betNames.size(); i++) {
			if (betNames.get(i).text().equals("1")) {
				try {
					ms1 = Float.valueOf(betValues.get(i).text());
				} catch (Exception e) {
				}
			} else if (betNames.get(i).text().equals("X")) {
				try {
					msx = Float.valueOf(betValues.get(i).text());
				} catch (Exception e) {
				}
			} else if (betNames.get(i).text().equals("2")) {
				try {
					ms2 = Float.valueOf(betValues.get(i).text());
				} catch (Exception e) {
				}
			}
		}

		ms1p = 1 / ms1 * 100;
		msxp = 1 / msx * 100;
		ms2p = 1 / ms2 * 100;

		totalp = ms1p + msxp + ms2p;

		ms1p = ms1p / totalp * 100;
		msxp = msxp / totalp * 100;
		ms2p = ms2p / totalp * 100;

		JsonObject matchResult = new JsonObject();
		matchResult.addProperty("handicapNum", handicap);
		matchResult.addProperty("handicapRate1", ms1);
		matchResult.addProperty("handicapRateX", msx);
		matchResult.addProperty("handicapRate2", ms2);
		matchResult.addProperty("handicapPercentage1", ms1p);
		matchResult.addProperty("handicapPercentageX", msxp);
		matchResult.addProperty("handicapPercentage2", ms2p);

		JsonObject matchDetail = new JsonObject();
		matchDetail.addProperty("id", matchId);

		matchResult.add("matchDetail", matchDetail);

		Utils.sendPost("http://localhost:8080/addHandicapMatchResult", matchResult.toString());
	}
	
	private void getUnderOverResult(String betName, Elements betNames, Elements betValues) {
		float underOverNum = 0;
		float under = 1;
		float over = 1;
		float underp = 0;
		float overp = 0;
		float totalp = 0;

		underOverNum = Float.valueOf(betName.trim().split(" ")[0].replace(",", "."));
		
//		if (!matchDetails.equals("")) {
//			JsonObject matchResults = new JsonParser().parse(matchDetails).getAsJsonObject();
//
//			for (int i = 0; i < matchResults.get("under_over").getAsJsonArray().size(); i++) {
//				if (matchResults.get("under_over").getAsJsonArray().get(i).getAsJsonObject().get("underOverNum")
//						.getAsFloat() == underOverNum) {
//					return;
//				}
//			}
//		}

		for (int i = 0; i < betNames.size(); i++) {
			if (betNames.get(i).text().equals("Alt")) {
				try {
					under = Float.valueOf(betValues.get(i).text());
				} catch (Exception e) {
				}
			} else if (betNames.get(i).text().equals("Üst")) {
				try {
					over = Float.valueOf(betValues.get(i).text());
				} catch (Exception e) {
				}
			}
		}

		underp = 1 / under * 100;
		overp = 1 / over * 100;

		totalp = underp + overp;

		underp = underp / totalp * 100;
		overp = overp / totalp * 100;

		JsonObject underOver = new JsonObject();
		underOver.addProperty("underOverNum", underOverNum);
		underOver.addProperty("underRate", under);
		underOver.addProperty("underPercentage", underp);
		underOver.addProperty("overRate", over);
		underOver.addProperty("overPercentage", overp);

		JsonObject matchDetail = new JsonObject();
		matchDetail.addProperty("id", matchId);

		underOver.add("matchDetail", matchDetail);

		Utils.sendPost("http://localhost:8080/addUnderOver", underOver.toString());
	}

}
