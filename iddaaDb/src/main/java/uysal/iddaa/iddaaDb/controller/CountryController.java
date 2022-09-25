package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import uysal.iddaa.iddaaDb.models.country.Country;
import uysal.iddaa.iddaaDb.services.country.CountryService;
import uysal.iddaa.iddaaDb.utils.View;

@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping(value = "/countries")
	@JsonView(View.Public.class)
	public List<Country> getCountries() {
		return countryService.findAll();
	}

	@GetMapping(value = "/country/{id}")
	@JsonView(View.Internal.class)
	public Country getCountryById(@PathVariable Long id) {
		return countryService.findById(id).get();
	}

	@GetMapping(value = "/countryName/{name}")
	@JsonView(View.Internal.class)
	public Country getCountryByName(@PathVariable String name) {
		return countryService.findByName(name);
	}
	
	@PostMapping(value = "/addCountry")
	public Country addNewCountry(@RequestBody Country country) {
		Optional<Country> check = countryService.findById(country.getId());
		if(check.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country already exists!");
		}
		return countryService.save(country);
	}
}
