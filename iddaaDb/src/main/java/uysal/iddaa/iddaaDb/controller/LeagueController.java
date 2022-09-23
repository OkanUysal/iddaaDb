package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import uysal.iddaa.iddaaDb.model.League;
import uysal.iddaa.iddaaDb.service.CountryRepository;
import uysal.iddaa.iddaaDb.service.LeagueRepository;

@RestController
public class LeagueController {

	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private CountryRepository countryRepository;

	@GetMapping(value = "/leagues")
	public List<Object> getLeagues() {
		return leagueRepository.findAllLeagueSumarry();
	}

	@GetMapping(value = "/league/{id}")
	public League getLeagueById(@PathVariable Long id) {
		return leagueRepository.findById(id).get();
	}

	@GetMapping(value = "/leagueName/{name}")
	public League getLeagueByName(@PathVariable String name) {
		return leagueRepository.findByName(name);
	}
	
	@PostMapping(value = "addLeague")
	public League addNewLeague(@RequestBody String input) {
		JsonObject object = new JsonParser().parse(input).getAsJsonObject();
		League league = new League();
		league.setName(object.get("name").getAsString());
		league.setCountry(countryRepository.findById(object.get("country_id").getAsLong()).get());
		
		return leagueRepository.save(league);
	}
}
