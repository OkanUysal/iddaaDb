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

import com.fasterxml.jackson.annotation.JsonView;

import uysal.iddaa.iddaaDb.models.team.Team;
import uysal.iddaa.iddaaDb.services.team.TeamService;
import uysal.iddaa.iddaaDb.utils.View;

@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;

	@GetMapping(value = "/teams")
	@JsonView(View.Public.class)
	public List<Object> getLeagues() {
		return teamService.findAllTeamSumarry();
	}

	@GetMapping(value = "/team/{id}")
	@JsonView(View.Internal.class)
	public Team getLeagueById(@PathVariable Long id) {
		return teamService.findById(id).get();
	}

	@GetMapping(value = "/teamName/{name}")
	@JsonView(View.Internal.class)
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
