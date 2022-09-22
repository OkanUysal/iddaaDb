package uysal.iddaa.iddaaDb.model;

import javax.persistence.*;

@Entity
@Table(name = "Team")
public class Team {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "league_id")
	private League league;

	public Team(Long id, String name, League league) {
		super();
		this.id = id;
		this.name = name;
		this.league = league;
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
	
	
}
