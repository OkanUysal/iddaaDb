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
	
	@PostMapping(value = "/addTeam")
	public Team addNewTeam(@RequestBody Team team) {
		Optional<Team> check = teamService.findById(team.getId());
		if(check.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team already exists!");
		}
		return teamService.addNewTeam(team);
	}
}
