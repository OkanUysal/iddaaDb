package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import uysal.iddaa.iddaaDb.models.team.Team;
import uysal.iddaa.iddaaDb.services.team.TeamService;

@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;

	@GetMapping(value = "/teams")
	public List<Object> getLeagues() {
		return teamService.findAllTeamSumarry();
	}

	@GetMapping(value = "/team/{id}")
	public Team getLeagueById(@PathVariable Long id) {
		return teamService.findById(id).get();
	}

	@GetMapping(value = "/teamName/{name}")
	public Team getLeagueByName(@PathVariable String name) {
		return teamService.findByName(name);
	}
}
