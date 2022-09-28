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

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;
import uysal.iddaa.iddaaDb.services.matchDetail.MatchDetailService;
import uysal.iddaa.iddaaDb.utils.View;

@RestController
public class MatchDetailController {

	@Autowired
	private MatchDetailService matchDetailService;
	
	@GetMapping(value = "/matchDetails")
	@JsonView(View.Public.class)
	public List<MatchDetail> getLeagues() {
		return matchDetailService.findAll();
	}

	@GetMapping(value = "/matchDetail/{id}")
	@JsonView(View.Internal.class)
	public MatchDetail getLeagueById(@PathVariable Long id) {
		return matchDetailService.findById(id).get();
	}
	
	@PostMapping(value = "addMatchDetail")
	public MatchDetail addNewMatchDetail(@RequestBody MatchDetail matchDetail) {
		Optional<MatchDetail> check = matchDetailService.findById(matchDetail.getId());
		if(check.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match Detail already exists!");
		}
		return matchDetailService.addNewMatchDetail(matchDetail);
	}
}
