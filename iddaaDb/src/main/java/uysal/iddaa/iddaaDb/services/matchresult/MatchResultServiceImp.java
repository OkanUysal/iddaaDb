package uysal.iddaa.iddaaDb.services.matchresult;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.matchresult.MatchResult;
import uysal.iddaa.iddaaDb.models.matchresult.MatchResultRepository;

@Service
public class MatchResultServiceImp implements MatchResultService{

	@Autowired
	private MatchResultRepository matchResultRepository;
	
	@Override
	public Optional<MatchResult> findById(Long id) {
		return matchResultRepository.findById(id);
	}

}
