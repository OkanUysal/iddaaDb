package uysal.iddaa.iddaaDb.services.handicapMatchResult;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.handicapMatchResult.HandicapMatchResult;
import uysal.iddaa.iddaaDb.models.handicapMatchResult.HandicapMatchResultRepository;
import uysal.iddaa.iddaaDb.services.matchDetail.MatchDetailService;

@Service
public class HandicapMatchResultServiceImp implements HandicapMatchResultService{
	
	@Autowired
	private HandicapMatchResultRepository handicapRepository;
	
	@Autowired
	private MatchDetailService matchDetailService;

	@Override
	public Optional<HandicapMatchResult> findById(Long id) {
		return handicapRepository.findById(id);
	}

	@Override
	public HandicapMatchResult save(HandicapMatchResult handicap) {
		handicap.setMatchDetail(matchDetailService.findById(handicap.getMatchDetail().getId()).get());
		return handicapRepository.save(handicap);
	}

}
