package uysal.iddaa.iddaaDb.services.matchresult;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.matchresult.MatchResult;
import uysal.iddaa.iddaaDb.models.matchresult.MatchResultRepository;
import uysal.iddaa.iddaaDb.services.matchDetail.MatchDetailService;

@Service
public class MatchResultServiceImp implements MatchResultService{

	@Autowired
	private MatchResultRepository matchResultRepository;
	
	@Autowired
	private MatchDetailService matchDetailService;
	
	@Override
	public Optional<MatchResult> findById(Long id) {
		return matchResultRepository.findById(id);
	}

	@Override
	public MatchResult save(MatchResult matchResult) {
		matchResult.setMatch_detail(matchDetailService.findById(matchResult.getMatch_detail().getId()).get());
		return matchResultRepository.save(matchResult);
	}

}
