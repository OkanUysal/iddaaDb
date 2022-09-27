package uysal.iddaa.iddaaDb.models.bothTeamToScore;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BothTeamToScoreRepository extends JpaRepository<BothTeamToScore, Long>{
	
	Optional<BothTeamToScore> findById(Long id);

}
