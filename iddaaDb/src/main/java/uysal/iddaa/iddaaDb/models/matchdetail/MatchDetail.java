package uysal.iddaa.iddaaDb.models.matchdetail;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import uysal.iddaa.iddaaDb.models.bothTeamToScore.BothTeamToScore;
import uysal.iddaa.iddaaDb.models.doubleChance.DoubleChance;
import uysal.iddaa.iddaaDb.models.goalRange.GoalRange;
import uysal.iddaa.iddaaDb.models.handicapMatchResult.HandicapMatchResult;
import uysal.iddaa.iddaaDb.models.season.Season;
import uysal.iddaa.iddaaDb.models.team.Team;
import uysal.iddaa.iddaaDb.models.underover.UnderOver;
import uysal.iddaa.iddaaDb.utils.View;

@Entity
@Table(name = "matchDetail")
public class MatchDetail {

	@Id
	@JsonView(View.Public.class)
	private Long id;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "homeId")
	@JsonIgnoreProperties(value = { "homeMatches", "awayMatches" })
	@JsonView(View.Public.class)
	private Team home;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "awayId")
	@JsonIgnoreProperties(value = { "homeMatches", "awayMatches" })
	@JsonView(View.Public.class)
	private Team away;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "seasonId")
	@JsonIgnoreProperties(value = { "matchDetails" })
	@JsonView(View.Internal.class)
	private Season season;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "matchDetail", cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "matchDetail" })
	@JsonView(View.Internal.class)
	private Set<UnderOver> underOver;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "matchDetail", cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "matchDetail" })
	@JsonView(View.Internal.class)
	private Set<HandicapMatchResult> handicap;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "matchDetail", cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "matchDetail" })
	@JsonView(View.Internal.class)
	private Set<GoalRange> goalRange;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "matchDetail", cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "matchDetail" })
	@JsonView(View.Internal.class)
	private Set<BothTeamToScore> bothTeamToScore;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "matchDetail", cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "matchDetail" })
	@JsonView(View.Internal.class)
	private Set<DoubleChance> doubleChance;

	@JsonView(View.Public.class)
	private int homeHalfTimeScore;

	@JsonView(View.Public.class)
	private int awayHalfTimeScore;

	@JsonView(View.Public.class)
	private int homeMatchScore;

	@JsonView(View.Public.class)
	private int awayMatchScore;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	@JsonView(View.Public.class)
	private Date date;

	public MatchDetail() {
		super();
	}

	public MatchDetail(Long id, Team home, Team away, Season season, Set<UnderOver> underOver,
			Set<HandicapMatchResult> handicap, Set<GoalRange> goalRange, Set<BothTeamToScore> bothTeamToScore,
			int homeHalfTimeScore, int awayHalfTimeScore, int homeMatchScore, int awayMatchScore, Date date) {
		super();
		this.id = id;
		this.home = home;
		this.away = away;
		this.season = season;
		this.underOver = underOver;
		this.handicap = handicap;
		this.goalRange = goalRange;
		this.bothTeamToScore = bothTeamToScore;
		this.homeHalfTimeScore = homeHalfTimeScore;
		this.awayHalfTimeScore = awayHalfTimeScore;
		this.homeMatchScore = homeMatchScore;
		this.awayMatchScore = awayMatchScore;
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

	public Set<UnderOver> getUnderOver() {
		return underOver;
	}

	public void setUnderOver(Set<UnderOver> underOver) {
		this.underOver = underOver;
	}

	public Set<HandicapMatchResult> getHandicap() {
		return handicap;
	}

	public void setHandicap(Set<HandicapMatchResult> handicap) {
		this.handicap = handicap;
	}

	public Set<GoalRange> getGoalRange() {
		return goalRange;
	}

	public void setGoalRange(Set<GoalRange> goalRange) {
		this.goalRange = goalRange;
	}

	public Set<BothTeamToScore> getBothTeamToScore() {
		return bothTeamToScore;
	}

	public void setBothTeamToScore(Set<BothTeamToScore> bothTeamToScore) {
		this.bothTeamToScore = bothTeamToScore;
	}

	public int getHomeHalfTimeScore() {
		return homeHalfTimeScore;
	}

	public void setHomeHalfTimeScore(int homeHalfTimeScore) {
		this.homeHalfTimeScore = homeHalfTimeScore;
	}

	public int getAwayHalfTimeScore() {
		return awayHalfTimeScore;
	}

	public void setAwayHalfTimeScore(int awayHalfTimeScore) {
		this.awayHalfTimeScore = awayHalfTimeScore;
	}

	public int getHomeMatchScore() {
		return homeMatchScore;
	}

	public void setHomeMatchScore(int homeMatchScore) {
		this.homeMatchScore = homeMatchScore;
	}

	public int getAwayMatchScore() {
		return awayMatchScore;
	}

	public void setAwayMatchScore(int awayMatchScore) {
		this.awayMatchScore = awayMatchScore;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
