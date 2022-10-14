package uysal.iddaa.iddaaDb.models.matchdetail;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchDetailRepository extends JpaRepository<MatchDetail, Long> {
	
	boolean existsById(Long id);
	
	Optional<MatchDetail> findById(Long id);
	
	@Query("select md.id from MatchDetail md")
	List<Object> findAllIds();
	
	@Query("select new uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailDTO(md.id, md.home.id, md.home.name, md.homeMatchScore, md.awayMatchScore, hmr.handicapPercentage1, hmr.handicapPercentageX, hmr.handicapPercentage2, md.date) from MatchDetail as md inner join md.handicap as hmr where md.home.id = :teamId and hmr.handicapNum = 0 and md.date >= :fromDate and md.date < :toDate order by md.date")
	List<MatchDetailDTO> findAllHomeMatchWithDate(Long teamId, Date fromDate, Date toDate);
	
	@Query("select new uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailDTO(md.id, md.away.id, md.away.name, md.homeMatchScore, md.awayMatchScore, hmr.handicapPercentage1, hmr.handicapPercentageX, hmr.handicapPercentage2, md.date) from MatchDetail as md inner join md.handicap as hmr where md.away.id = :teamId and hmr.handicapNum = 0 and md.date >= :fromDate and md.date < :toDate order by md.date")
	List<MatchDetailDTO> findAllAwayMatchWithDate(Long teamId, Date fromDate, Date toDate);
	
	@Query("select new uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailSummaryDTO(md.id, md.home.id, md.away.id, md.home.name, md.away.name, md.homeMatchScore, md.awayMatchScore, md.date) from MatchDetail as md where md.date > :fromDate and md.date < :toDate")
	List<MatchDetailSummaryDTO> findAllMatchforDate(Date fromDate, Date toDate);
	
}
