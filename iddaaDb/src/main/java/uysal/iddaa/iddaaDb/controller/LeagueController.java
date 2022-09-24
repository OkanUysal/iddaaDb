package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uysal.iddaa.iddaaDb.models.leaugue.League;
import uysal.iddaa.iddaaDb.services.league.LeagueService;

@RestController
public class LeagueController {

	@Autowired
	private LeagueService leagueService;

	@GetMapping(value = "/leagues")
	public List<Object> getLeagues() {
		return leagueService.findAllLeagueSumarry();
	}

	@GetMapping(value = "/league/{id}")
	public League getLeagueById(@PathVariable Long id) {
		return leagueService.findById(id).get();
	}

	@GetMapping(value = "/leagueName/{name}")
	public League getLeagueByName(@PathVariable String name) {
		return leagueService.findByName(name);
	}
	
	@PostMapping(value = "addLeague")
	public League addNewLeague(@RequestBody League league) {
		return leagueService.addNewLeague(league);
	}
}
