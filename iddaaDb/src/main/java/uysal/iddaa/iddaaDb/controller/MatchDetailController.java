package uysal.iddaa.iddaaDb.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;
import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailDTO;
import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailSummaryDTO;
import uysal.iddaa.iddaaDb.services.matchDetail.MatchDetailService;
import uysal.iddaa.iddaaDb.utils.View;

@RestController
public class MatchDetailController {

	@Autowired
	private MatchDetailService matchDetailService;
	
	@GetMapping(value = "/matchDetails")
	@JsonView(View.Public.class)
	public List<MatchDetail> getMatches() {
		return matchDetailService.findAll();
	}
	
	@GetMapping(value = "/matchDetailIds")
	@JsonView(View.Public.class)
	public List<Object> getMatchIds() {
		return matchDetailService.findAllIds();
	}

	@GetMapping(value = "/matchDetail/{id}")
	@JsonView(View.Internal.class)
	public MatchDetail getLeagueById(@PathVariable Long id) {
		return matchDetailService.findById(id).get();
	}
	
	@GetMapping(value = "/matchDetailHome/{id}/{fromDate}/{toDate}")
	@JsonView(View.Internal.class)
	public List<MatchDetailDTO> getMatchDetailHome(@PathVariable("id") Long id, @PathVariable("fromDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @PathVariable("toDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
		return matchDetailService.findAllHomeMatchWithDate(id, fromDate, toDate);
	}
	
	@GetMapping(value = "/matchDetailAway/{id}/{fromDate}/{toDate}")
	@JsonView(View.Internal.class)
	public List<MatchDetailDTO> getMatchDetailAway(@PathVariable("id") Long id, @PathVariable("fromDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, @PathVariable("toDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
		return matchDetailService.findAllAwayMatchWithDate(id, fromDate, toDate);
	}
	
	@GetMapping(value = "/matchDetailsforDate/{date}")
	@JsonView(View.Internal.class)
	public List<MatchDetailSummaryDTO> getMatchesforDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date ) {
		return matchDetailService.findAllMatchesforDate(date);
	}
	
	@PostMapping(value = "addMatchDetail")
	public MatchDetail addNewMatchDetail(@RequestBody MatchDetail matchDetail) {
		boolean check = matchDetailService.isExistById(matchDetail.getId());
		if(check) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match Detail already exists!");			
		}
		matchDetailService.insertWithQuery(matchDetail);
		return matchDetail;
	}
}
