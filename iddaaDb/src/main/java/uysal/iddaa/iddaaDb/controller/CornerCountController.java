package uysal.iddaa.iddaaDb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uysal.iddaa.iddaaDb.models.cornerCount.CornerCount;
import uysal.iddaa.iddaaDb.services.cornerCount.CornerCountService;

@RestController
public class CornerCountController {

	@Autowired
	private CornerCountService cornerCountService;
	
	@PostMapping(value = "/addCornerCount")
	public CornerCount addNewHandicap(@RequestBody CornerCount cornerCount) {
		Optional<CornerCount> check = cornerCountService.findById(cornerCount.getMatchDetail().getId());
		if(check.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Corner Count is already exists!");
		}
		return cornerCountService.save(cornerCount);
	}
}
