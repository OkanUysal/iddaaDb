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
import com.fasterxml.jackson.annotation.JsonView;

import uysal.iddaa.iddaaDb.models.leaugue.League;
import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;
import uysal.iddaa.iddaaDb.utils.View;

@Entity
@Table(name = "season")
public class Season {

	@Id
	@JsonView(View.Public.class)
	private Long id;

	@JsonView(View.Public.class)
	private String name;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "league_id")
	@JsonIgnoreProperties(value = { "seasons" })
	@JsonView(View.Public.class)
	private League league;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "season", cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "season", "home", "away" })
	@JsonView(View.Internal.class)
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
