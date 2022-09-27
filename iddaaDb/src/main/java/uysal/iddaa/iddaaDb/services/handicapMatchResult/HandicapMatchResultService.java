package uysal.iddaa.iddaaDb.services.handicapMatchResult;

import java.util.Optional;

import uysal.iddaa.iddaaDb.models.handicapMatchResult.HandicapMatchResult;

public interface HandicapMatchResultService {

	Optional<HandicapMatchResult> findById(Long id);
	
	HandicapMatchResult save(HandicapMatchResult handicap);
}
