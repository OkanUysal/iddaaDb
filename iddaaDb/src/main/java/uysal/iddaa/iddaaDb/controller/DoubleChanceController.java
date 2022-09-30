package uysal.iddaa.iddaaDb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uysal.iddaa.iddaaDb.models.doubleChance.DoubleChance;
import uysal.iddaa.iddaaDb.services.doubleChance.DoubleChanceService;

@RestController
public class DoubleChanceController {

	@Autowired
	private DoubleChanceService doubleChanceService;
	
	@PostMapping(value = "/addDoubleChance")
	public DoubleChance addNewHandicap(@RequestBody DoubleChance doubleChance) {
		Optional<DoubleChance> check = doubleChanceService.findById(doubleChance.getMatchDetail().getId());
		if(check.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Handicap is already exists!");
		}
		return doubleChanceService.save(doubleChance);
	}
}
