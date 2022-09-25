package uysal.iddaa.iddaaDb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uysal.iddaa.iddaaDb.models.underover.UnderOver;
import uysal.iddaa.iddaaDb.services.underover.UnderOverService;

@RestController
public class UnderOverController {

	@Autowired
	private UnderOverService underOverService;
	
	@PostMapping(value = "/addUnderOver")
	public UnderOver addNewCountry(@RequestBody UnderOver underOver) {
		Optional<UnderOver> check = underOverService.findById(underOver.getMatch_detail().getId());
		if(check.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Under Over is already exists!");
		}
		return underOverService.save(underOver);
	}
}
