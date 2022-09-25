import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.google.gson.JsonObject;

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
		// add country
		JsonObject country = new JsonObject();
		country.addProperty("id", this.countryId);
		country.addProperty("name", this.countryName);
		sendPost("http://localhost:8080/addCountry", country.toString());
//		System.out.println(country.toString());

		// add league
		JsonObject league = new JsonObject();
		league.addProperty("id", this.leaugeId);
		league.addProperty("name", this.leagueName);
		league.add("country", country);
		sendPost("http://localhost:8080/addLeague", league.toString());
//		System.out.println(league.toString());

		// add season
		JsonObject season = new JsonObject();
		season.addProperty("id", this.seasonId);
		season.addProperty("name", this.seasonName);
		season.add("league", league);
		sendPost("http://localhost:8080/addSeason", season.toString());

		// add home team
		JsonObject homeTeam = new JsonObject();
		homeTeam.addProperty("id", this.homeTeamId);
		homeTeam.addProperty("name", this.homeTeamName);
		sendPost("http://localhost:8080/addTeam", homeTeam.toString());

		// add home team
		JsonObject awayTeam = new JsonObject();
		awayTeam.addProperty("id", this.awayTeamId);
		awayTeam.addProperty("name", this.awayTeamName);
		sendPost("http://localhost:8080/addTeam", awayTeam.toString());

		// add home team
		JsonObject matchDetail = new JsonObject();
		matchDetail.addProperty("id", this.matchId);
		matchDetail.add("home", homeTeam);
		matchDetail.add("away", awayTeam);
		matchDetail.add("season", season);
		matchDetail.addProperty("home_half_time_score", this.homeHalfScore);
		matchDetail.addProperty("away_half_time_score", this.awayHalfScore);
		matchDetail.addProperty("home_match_score", this.homeScore);
		matchDetail.addProperty("away_match_score", this.awayScore);
		matchDetail.addProperty("date", this.date);
		sendPost("http://localhost:8080/addMatchDetail", matchDetail.toString());

	}

	private static void sendPost(String uri, String json) {
		System.out.println(json);
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
				System.out.println(response.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
