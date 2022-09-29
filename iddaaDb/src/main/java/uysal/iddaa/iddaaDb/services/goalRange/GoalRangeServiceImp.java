package uysal.iddaa.iddaaDb.services.goalRange;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.goalRange.GoalRange;
import uysal.iddaa.iddaaDb.models.goalRange.GoalRangeRepository;
import uysal.iddaa.iddaaDb.services.matchDetail.MatchDetailService;

@Service
public class GoalRangeServiceImp implements GoalRangeService{
	
	@Autowired
	private GoalRangeRepository goalRangeRepository;
	
	@Autowired
	private MatchDetailService matchDetailService;

	@Override
	public Optional<GoalRange> findById(Long id) {
		return goalRangeRepository.findById(id);
	}

	@Override
	public GoalRange save(GoalRange goalRange) {
//		goalRange.setMatchDetail(matchDetailService.findById(goalRange.getMatchDetail().getId()).get());
		return goalRangeRepository.save(goalRange);
	}

}
