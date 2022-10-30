package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uysal.iddaa.iddaaDb.models.sportotoPrize.SporTotoPrize;
import uysal.iddaa.iddaaDb.services.sportotoPrize.SporTotoPrizeService;

@RestController
public class SporTotoPrizeController {

	@Autowired 
	SporTotoPrizeService sporTotoPrizeService;
	
	@GetMapping(value = "/sporTotoPrizeList")
	public List<SporTotoPrize> getMatches() {
		return sporTotoPrizeService.findAll();
	}
	
	@GetMapping(value = "/sporTotoPrizeList/{weekNumber}")
	public List<SporTotoPrize> getMatches(@PathVariable int weekNumber) {
		return sporTotoPrizeService.findByWeekNumber(weekNumber);
	}
	
	@PostMapping(value = "/addSporTotoPrize")
	public SporTotoPrize addNewMatch(@RequestBody SporTotoPrize sporTotoPrize) {
		return sporTotoPrizeService.save(sporTotoPrize);
	}
}
