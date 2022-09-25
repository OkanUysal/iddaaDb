package uysal.iddaa.iddaaDb.models.season;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import uysal.iddaa.iddaaDb.models.leaugue.League;
import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;

@Entity
@Table(name = "season")
public class Season {

	@Id
	private Long id;

	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "league_id")
	@JsonIgnoreProperties(value = { "seasons" })
	private League league;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "season", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "home", "away" })
	private Set<MatchDetail> matchDetails;

	public Season() {
		super();
	}

	public Season(Long id, String name, League league, Set<MatchDetail> matchDetails) {
		super();
		this.id = id;
		this.name = name;
		this.league = league;
		this.matchDetails = matchDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Set<MatchDetail> getMatchDetails() {
		return matchDetails;
	}

	public void setMatchDetails(Set<MatchDetail> matchDetails) {
		this.matchDetails = matchDetails;
	}

}
