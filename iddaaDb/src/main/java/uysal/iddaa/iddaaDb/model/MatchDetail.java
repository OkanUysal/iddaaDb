package uysal.iddaa.iddaaDb.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "match_datail")
public class MatchDetail {

	@Id
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "home_id")
	private Team home;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "away_id")
	private Team away;

	private int home_half_time_score;

	private int away_half_time_score;

	private int home_match_score;

	private int away_match_score;

	private String season;

	private Date date;

	public MatchDetail() {
		super();
	}

	public MatchDetail(Long id, Team home, Team away, int home_half_time_score, int away_half_time_score,
			int home_match_score, int away_match_score, String season, Date date) {
		super();
		this.id = id;
		this.home = home;
		this.away = away;
		this.home_half_time_score = home_half_time_score;
		this.away_half_time_score = away_half_time_score;
		this.home_match_score = home_match_score;
		this.away_match_score = away_match_score;
		this.season = season;
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

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
