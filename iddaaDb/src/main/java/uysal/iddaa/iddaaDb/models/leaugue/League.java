package uysal.iddaa.iddaaDb.models.leaugue;

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

import uysal.iddaa.iddaaDb.models.country.Country;
import uysal.iddaa.iddaaDb.models.season.Season;
import uysal.iddaa.iddaaDb.utils.View;

@Entity
@Table(name = "League")
public class League {

	@Id
	@JsonView(View.Public.class)
	private Long id;

	@JsonView(View.Public.class)
	private String name;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "country_id")
	@JsonIgnoreProperties(value = { "leagues" })
	@JsonView(View.Public.class)
	private Country country;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "league", cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "league", "matchDetails" })
	@JsonView(View.Internal.class)
	private Set<Season> seasons;

	public League() {
		super();
	}

	public League(Long id, String name, Country country, Set<Season> seasons) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.seasons = seasons;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(Set<Season> seasons) {
		this.seasons = seasons;
	}

}
