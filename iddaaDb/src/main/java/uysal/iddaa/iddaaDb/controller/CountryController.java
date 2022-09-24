package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uysal.iddaa.iddaaDb.models.country.Country;
import uysal.iddaa.iddaaDb.services.country.CountryService;

@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping(value = "/countries")
	public List<Object> getCountries() {
		return countryService.findAllCountrySumarry();
	}

	@GetMapping(value = "/country/{id}")
	public Country getCountryById(@PathVariable Long id) {
		return countryService.findById(id).get();
	}

	@GetMapping(value = "/countryName/{name}")
	public Country getCountryByName(@PathVariable String name) {
		return countryService.findByName(name);
	}
	
	@PostMapping(value = "/addCountry")
	public Country addNewCountry(@RequestBody Country country) {
		return countryService.save(country);
	}
}
