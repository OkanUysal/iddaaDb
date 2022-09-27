package uysal.iddaa.iddaaDb.services.bothTeamToScore;

import java.util.Optional;

import uysal.iddaa.iddaaDb.models.bothTeamToScore.BothTeamToScore;

public interface BothTeamToScoreService {

	Optional<BothTeamToScore> findById(Long id);
	
	BothTeamToScore save(BothTeamToScore bothTeamToScore);
}
