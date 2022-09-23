package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import uysal.iddaa.iddaaDb.model.Team;
import uysal.iddaa.iddaaDb.service.TeamRepository;

@RestController
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;

	@GetMapping(value = "/teams")
	public List<Object> getLeagues() {
		return teamRepository.findAllTeamSumarry();
	}

	@GetMapping(value = "/team/{id}")
	public Team getLeagueById(@PathVariable Long id) {
		return teamRepository.findById(id).get();
	}

	@GetMapping(value = "/teamName/{name}")
	public Team getLeagueByName(@PathVariable String name) {
		return teamRepository.findByName(name);
	}
}
