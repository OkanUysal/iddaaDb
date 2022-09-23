package uysal.iddaa.iddaaDb.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Team")
public class Team {

	@Id
	private Long id;

	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "home", cascade = CascadeType.ALL)
	private Set<MatchDetail> homeMatches;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "away", cascade = CascadeType.ALL)
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
