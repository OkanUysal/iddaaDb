package uysal.iddaa.iddaaDb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import uysal.iddaa.iddaaDb.models.goalRange.GoalRange;
import uysal.iddaa.iddaaDb.services.goalRange.GoalRangeService;

@RestController
public class GoalRangeController {

	@Autowired
	private GoalRangeService goalRangeService;
	
	@PostMapping(value = "/addGoalRange")
	public GoalRange addNewGoalRange(@RequestBody GoalRange goalRange) {
		Optional<GoalRange> check = goalRangeService.findById(goalRange.getMatchDetail().getId());
		if(check.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Goal Range is already exists!");
		}
		return goalRangeService.save(goalRange);
	}
}
