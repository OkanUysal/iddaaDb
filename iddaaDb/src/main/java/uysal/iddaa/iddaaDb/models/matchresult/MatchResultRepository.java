package uysal.iddaa.iddaaDb.models.matchresult;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchResultRepository extends JpaRepository<MatchResult, Long> {

	Optional<MatchResult> findById(Long id);

}
