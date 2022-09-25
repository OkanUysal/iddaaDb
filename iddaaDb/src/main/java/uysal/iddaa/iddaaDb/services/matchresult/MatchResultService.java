package uysal.iddaa.iddaaDb.services.matchresult;

import java.util.Optional;

import uysal.iddaa.iddaaDb.models.matchresult.MatchResult;

public interface MatchResultService {
	Optional<MatchResult> findById(Long id);
	
	MatchResult save(MatchResult matchResult);
}
