package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uysal.iddaa.iddaaDb.models.sportoto.SporToto;
import uysal.iddaa.iddaaDb.models.sportoto.SporTotoDTO;
import uysal.iddaa.iddaaDb.services.sportoto.SporTotoService;

@RestController
public class SporTotoController {
	
	@Autowired
	private SporTotoService sporTotoService;
	
	@GetMapping(value = "/sporTotomatches")
	public List<SporTotoDTO> getSporTotoMatches() {
		return sporTotoService.findAllMatches();
	}
	
	@GetMapping(value = "/sporTotoWeek/{week}")
	public List<SporTotoDTO> getSporTotoWeek(@PathVariable int week) {
		return sporTotoService.findWithWeekNumber(week);
	}
	
	@PostMapping(value = "/addSporTotoMatch")
	public SporToto addNetMatch(@RequestBody SporToto sporToto) {
		return sporTotoService.addNewMatch(sporToto);
	}

}
