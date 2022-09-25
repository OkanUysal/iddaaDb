package uysal.iddaa.iddaaDb.models.matchdetail;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import uysal.iddaa.iddaaDb.models.matchresult.MatchResult;
import uysal.iddaa.iddaaDb.models.season.Season;
import uysal.iddaa.iddaaDb.models.team.Team;
import uysal.iddaa.iddaaDb.utils.View;

@Entity
@Table(name = "match_datail")
public class MatchDetail {

	@Id
	@JsonView(View.Public.class)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "home_id")
	@JsonIgnoreProperties(value = { "homeMatches", "awayMatches" })
	@JsonView(View.Public.class)
	private Team home;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "away_id")
	@JsonIgnoreProperties(value = { "homeMatches", "awayMatches" })
	@JsonView(View.Public.class)
	private Team away;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	@JsonIgnoreProperties(value = { "matchDetails" })
	@JsonView(View.Public.class)
	private Season season;

	@OneToOne(mappedBy = "match_detail", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	@JsonView(View.Internal.class)
	private MatchResult match_result;

	@JsonView(View.Internal.class)
	private int home_half_time_score;

	@JsonView(View.Internal.class)
	private int away_half_time_score;

	@JsonView(View.Public.class)
	private int home_match_score;

	@JsonView(View.Public.class)
	private int away_match_score;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	@JsonView(View.Public.class)	
	private Date date;

	public MatchDetail() {
		super();
	}

	public MatchDetail(Long id, Team home, Team away, Season season, MatchResult match_result, int home_half_time_score,
			int away_half_time_score, int home_match_score, int away_match_score, Date date) {
		super();
		this.id = id;
		this.home = home;
		this.away = away;
		this.season = season;
		this.match_result = match_result;
		this.home_half_time_score = home_half_time_score;
		this.away_half_time_score = away_half_time_score;
		this.home_match_score = home_match_score;
		this.away_match_score = away_match_score;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Team getHome() {
		return home;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public Team getAway() {
		return away;
	}

	public void setAway(Team away) {
		this.away = away;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public MatchResult getMatch_result() {
		return match_result;
	}

	public void setMatch_result(MatchResult match_result) {
		this.match_result = match_result;
	}

	public int getHome_half_time_score() {
		return home_half_time_score;
	}

	public void setHome_half_time_score(int home_half_time_score) {
		this.home_half_time_score = home_half_time_score;
	}

	public int getAway_half_time_score() {
		return away_half_time_score;
	}

	public void setAway_half_time_score(int away_half_time_score) {
		this.away_half_time_score = away_half_time_score;
	}

	public int getHome_match_score() {
		return home_match_score;
	}

	public void setHome_match_score(int home_match_score) {
		this.home_match_score = home_match_score;
	}

	public int getAway_match_score() {
		return away_match_score;
	}

	public void setAway_match_score(int away_match_score) {
		this.away_match_score = away_match_score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
