package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import uysal.iddaa.iddaaDb.model.League;
import uysal.iddaa.iddaaDb.service.LeagueRepository;

@RestController
public class LeagueController {

	@Autowired
	private LeagueRepository leagueRepository;

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
}
