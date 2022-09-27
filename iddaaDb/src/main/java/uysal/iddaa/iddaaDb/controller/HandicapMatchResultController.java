package uysal.iddaa.iddaaDb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uysal.iddaa.iddaaDb.models.handicapMatchResult.HandicapMatchResult;
import uysal.iddaa.iddaaDb.services.handicapMatchResult.HandicapMatchResultService;

@RestController
public class HandicapMatchResultController {

	@Autowired
	private HandicapMatchResultService handicapService;
	
	@PostMapping(value = "/addHandicapMatchResult")
	public HandicapMatchResult addNewHandicap(@RequestBody HandicapMatchResult handicap) {
		Optional<HandicapMatchResult> check = handicapService.findById(handicap.getMatchDetail().getId());
		if(check.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Handicap is already exists!");
		}
		return handicapService.save(handicap);
	}
}
