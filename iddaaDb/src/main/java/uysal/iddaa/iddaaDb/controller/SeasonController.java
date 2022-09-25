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

import uysal.iddaa.iddaaDb.models.season.Season;
import uysal.iddaa.iddaaDb.services.season.SeasonService;
import uysal.iddaa.iddaaDb.utils.View;

@RestController
public class SeasonController {
	
	@Autowired
	private SeasonService seasonService;
	
	@GetMapping(value = "/seasons")
	@JsonView(View.Public.class)
	public List<Season> getSeasons() {
		return seasonService.findAll();
	}
	
	@GetMapping(value = "/season/{id}")
	@JsonView(View.Internal.class)
	public Season getSeason(@PathVariable Long id) {
		return seasonService.findById(id).get();
	}
	
	@PostMapping(value = "addSeason")
	public Season addNewLeague(@RequestBody Season season) {
		Optional<Season> check = seasonService.findById(season.getId());
		if(check.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "League already exists!");
		}
		return seasonService.addNewSeason(season);
	}

}
