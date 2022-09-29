package uysal.iddaa.iddaaDb.services.bothTeamToScore;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.bothTeamToScore.BothTeamToScore;
import uysal.iddaa.iddaaDb.models.bothTeamToScore.BothTeamToScoreRepository;
import uysal.iddaa.iddaaDb.services.matchDetail.MatchDetailService;

@Service
public class BothTeamToScoreServiceImp implements BothTeamToScoreService{

	@Autowired
	private BothTeamToScoreRepository bothTeamToScoreRepository;
	
	@Autowired
	private MatchDetailService matchDetailService;

	@Override
	public Optional<BothTeamToScore> findById(Long id) {
		return bothTeamToScoreRepository.findById(id);
	}

	@Override
	public BothTeamToScore save(BothTeamToScore bothTeamToScore) {
//		bothTeamToScore.setMatchDetail(matchDetailService.findById(bothTeamToScore.getMatchDetail().getId()).get());
		return bothTeamToScoreRepository.save(bothTeamToScore);
	}
}
