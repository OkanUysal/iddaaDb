package uysal.iddaa.iddaaDb.models.country;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import uysal.iddaa.iddaaDb.models.leaugue.League;
import uysal.iddaa.iddaaDb.utils.View;

@Entity
@Table(name = "Contry")
public class Country {

	@Id
	@JsonView(View.Public.class)
	private Long id;

	@JsonView(View.Public.class)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "country", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "country", "seasons" })
	@JsonView(View.Internal.class)
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
