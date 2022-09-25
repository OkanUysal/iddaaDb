package uysal.iddaa.iddaaDb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uysal.iddaa.iddaaDb.models.matchresult.MatchResult;
import uysal.iddaa.iddaaDb.services.matchresult.MatchResultService;

@RestController
public class MatchResultController {
	
	@Autowired
	private MatchResultService matchResultService;
	
	@PostMapping(value = "/addMatchResult")
	public MatchResult addNewCountry(@RequestBody MatchResult matchResult) {
		Optional<MatchResult> check = matchResultService.findById(matchResult.getMatch_detail().getId());
		if(check.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match Result is already exists!");
		}
		return matchResultService.save(matchResult);
	}

}
