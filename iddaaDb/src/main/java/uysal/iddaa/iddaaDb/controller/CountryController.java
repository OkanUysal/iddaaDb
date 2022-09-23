package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import uysal.iddaa.iddaaDb.model.Country;
import uysal.iddaa.iddaaDb.service.CountryRepository;

@RestController
public class CountryController {

	@Autowired
	private CountryRepository countryRepository;

	@GetMapping(value = "/countries")
	public List<Object> getCountries() {
		return countryRepository.findAllCountrySumarry();
	}

	@GetMapping(value = "/country/{id}")
	public Country getCountryById(@PathVariable Long id) {
		return countryRepository.findById(id).get();
	}

	@GetMapping(value = "/countryName/{name}")
	public Country getCountryByName(@PathVariable String name) {
		return countryRepository.findByName(name);
	}
}
