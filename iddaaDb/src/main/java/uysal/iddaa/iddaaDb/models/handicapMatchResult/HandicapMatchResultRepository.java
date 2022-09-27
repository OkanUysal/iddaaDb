package uysal.iddaa.iddaaDb.models.handicapMatchResult;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandicapMatchResultRepository extends JpaRepository<HandicapMatchResult, Long>{
	
	Optional<HandicapMatchResult> findById(Long id);

}
