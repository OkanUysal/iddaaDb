package uysal.iddaa.iddaaDb.models.matchdetail;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchDetailRepository extends JpaRepository<MatchDetail, Long> {

	@Query("select md.id, md.home_half_time_score, md.away_half_time_score, md.home_match_score, md.away_match_score, md.season, md.date from MatchDetail md")
	List<Object> findAllMatchDetailSumarry();
	
	Optional<MatchDetail> findById(Long id);
	
}
