package uysal.iddaa.iddaaDb.models.leaugue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import uysal.iddaa.iddaaDb.models.country.Country;

@Entity
@Table(name = "League")
public class League {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id")
	@JsonIgnoreProperties(value = { "leagues" })
	private Country country;

	public League() {
		super();
	}

	public League(Long id, String name, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
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

}
