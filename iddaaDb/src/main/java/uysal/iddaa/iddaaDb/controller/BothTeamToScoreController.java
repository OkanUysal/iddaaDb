package uysal.iddaa.iddaaDb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uysal.iddaa.iddaaDb.models.bothTeamToScore.BothTeamToScore;
import uysal.iddaa.iddaaDb.services.bothTeamToScore.BothTeamToScoreService;

@RestController
public class BothTeamToScoreController {

	@Autowired
	private BothTeamToScoreService bothTeamToScoreService;
	
	@PostMapping(value = "/addBothTeamToScore")
	public BothTeamToScore addBothTeamToScore(@RequestBody BothTeamToScore bothTeamToScore) {
		Optional<BothTeamToScore> check = bothTeamToScoreService.findById(bothTeamToScore.getMatchDetail().getId());
		if(check.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Both Team To Score is already exists!");
		}
		return bothTeamToScoreService.save(bothTeamToScore);
	}
}
