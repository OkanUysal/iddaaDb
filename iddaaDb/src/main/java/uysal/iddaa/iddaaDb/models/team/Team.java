package uysal.iddaa.iddaaDb.models.team;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;

@Entity
@Table(name = "Team")
public class Team {

	@Id
	private Long id;

	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "home", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "match_result" })
	private Set<MatchDetail> homeMatches;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "away", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "match_result" })
	private Set<MatchDetail> awayMatches;

	public Team() {
		super();
	}

	public Team(Long id, String name, Set<MatchDetail> homeMatches, Set<MatchDetail> awayMatches) {
		super();
		this.id = id;
		this.name = name;
		this.homeMatches = homeMatches;
		this.awayMatches = awayMatches;
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

	public Set<MatchDetail> getHomeMatches() {
		return homeMatches;
	}

	public void setHomeMatchs(Set<MatchDetail> homeMatches) {
		this.homeMatches = homeMatches;
	}

	public Set<MatchDetail> getAwayMatches() {
		return awayMatches;
	}

	public void setAwayMatches(Set<MatchDetail> awayMatches) {
		this.awayMatches = awayMatches;
	}

}
