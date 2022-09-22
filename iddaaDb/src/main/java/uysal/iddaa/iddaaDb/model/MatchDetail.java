package uysal.iddaa.iddaaDb.model;

import javax.persistence.*;

@Entity
@Table(name = "match_datail")
public class MatchDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "home_id")
	private Team home;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "away_id")
	private Team away;

	public MatchDetail() {
		super();
	}

	public MatchDetail(Long id, Team home, Team away) {
		super();
		this.id = id;
		this.home = home;
		this.away = away;
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

}
