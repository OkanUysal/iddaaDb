package uysal.iddaa.iddaaDb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;
import uysal.iddaa.iddaaDb.services.matchDetail.MatchDetailService;

@RestController
public class MatchDetailController {

	@Autowired
	private MatchDetailService matchDetailService;

	@GetMapping(value = "/matchDetails")
	public List<Object> getLeagues() {
		return matchDetailService.findAllMatchDetailSumarry();
	}

	@GetMapping(value = "/matchDetail/{id}")
	public MatchDetail getLeagueById(@PathVariable Long id) {
		return matchDetailService.findById(id).get();
	}
}
