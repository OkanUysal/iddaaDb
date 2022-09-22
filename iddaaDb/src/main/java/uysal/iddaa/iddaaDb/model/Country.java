package uysal.iddaa.iddaaDb.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Contry")
public class Country {

	@Id
	private Long id;

	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "country", cascade = CascadeType.ALL)
	private Set<League> leagues;

	public Country() {
		super();
	}

	public Country(Long id, String name, Set<League> leagues) {
		super();
		this.id = id;
		this.name = name;
		this.leagues = leagues;
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

	public Set<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(Set<League> leagues) {
		this.leagues = leagues;
	}

}
