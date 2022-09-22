package uysal.iddaa.iddaaDb.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Team")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "league_id")
	private League league;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "home_matches", cascade = CascadeType.ALL)
	private Set<Team> homeMatchs;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "away_matches", cascade = CascadeType.ALL)
	private Set<Team> awayMatches;

	public Team() {
		super();
	}

	public Team(Long id, String name, League league, Set<Team> homeMatchs, Set<Team> awayMatches) {
		super();
		this.id = id;
		this.name = name;
		this.league = league;
		this.homeMatchs = homeMatchs;
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

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Set<Team> getHomeMatchs() {
		return homeMatchs;
	}

	public void setHomeMatchs(Set<Team> homeMatchs) {
		this.homeMatchs = homeMatchs;
	}

	public Set<Team> getAwayMatches() {
		return awayMatches;
	}

	public void setAwayMatches(Set<Team> awayMatches) {
		this.awayMatches = awayMatches;
	}

}
