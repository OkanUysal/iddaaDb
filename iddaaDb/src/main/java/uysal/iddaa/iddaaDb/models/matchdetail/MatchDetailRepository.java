package uysal.iddaa.iddaaDb.models.matchdetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchDetailRepository extends JpaRepository<MatchDetail, Long> {
	
	Optional<MatchDetail> findById(Long id);
	
}
