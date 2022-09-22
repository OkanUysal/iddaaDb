package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import uysal.iddaa.iddaaDb.service.CountryRepository;

@RestController
public class CountryController {

	@Autowired
	private CountryRepository countryRepository;

	@GetMapping(value = "/countries")
	public String getCounries() {
		List<Object> countries = countryRepository.findAllCountrySumarry();
		return new Gson().toJson(countries);
	}
}
