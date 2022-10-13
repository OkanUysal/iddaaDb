package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uysal.iddaa.iddaaDb.models.currentsportoto.CurrentSporToto;
import uysal.iddaa.iddaaDb.services.currentsportoto.CurrentSporTotoService;

@RestController
public class CurrentSporTotoController {
	
	@Autowired CurrentSporTotoService currentSporTotoService;
	
	@GetMapping(value = "/currentSporTotoLists")
	public List<CurrentSporToto> getMatches() {
		return currentSporTotoService.findAll();
	}
	
	@GetMapping(value = "/currentSporTotoList/{weekNumber}")
	public List<CurrentSporToto> getMatches(@PathVariable int weekNumber) {
		return currentSporTotoService.findByWeekNumber(weekNumber);
	}
	
	@PostMapping(value = "/addCurrentSporTotoMatch")
	public CurrentSporToto addNewMatch(@RequestBody CurrentSporToto currentSporToto) {
		return currentSporTotoService.save(currentSporToto);
	}

}
