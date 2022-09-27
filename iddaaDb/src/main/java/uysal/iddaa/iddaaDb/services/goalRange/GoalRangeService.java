package uysal.iddaa.iddaaDb.services.goalRange;

import java.util.Optional;

import uysal.iddaa.iddaaDb.models.goalRange.GoalRange;

public interface GoalRangeService {

	Optional<GoalRange> findById(Long id);
	
	GoalRange save(GoalRange goalRange);
}
