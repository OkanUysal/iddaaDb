package uysal.iddaa.iddaaDb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import uysal.iddaa.iddaaDb.model.Country;
import uysal.iddaa.iddaaDb.service.CountryRepository;

@RestController
public class CountryController {

	@Autowired
	private CountryRepository countryRepository;

	@GetMapping(value = "/countries")
	public String getCountries() {
		List<Object> countries = countryRepository.findAllCountrySumarry();
		return new Gson().toJson(countries);
	}
	
	@GetMapping(value = "/country/{id}")
	public String getCountryById(@PathVariable Long id) {
		return new Gson().toJson(countryRepository.findById(id).get());
	}
	
	@GetMapping(value = "/countryName/{name}")
	public String getCountryByName(@PathVariable String name) {
		return new Gson().toJson(countryRepository.findByName(name));
	}
}
