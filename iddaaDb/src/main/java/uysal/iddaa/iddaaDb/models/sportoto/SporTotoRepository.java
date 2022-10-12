package uysal.iddaa.iddaaDb.models.sportoto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SporTotoRepository extends JpaRepository<SporToto, Long> {

	@Query("select new uysal.iddaa.iddaaDb.models.sportoto.SporTotoDTO(st.id, st.weekNumber, md.home.id, md.away.id, md.homeMatchScore,"
			+ " md.awayMatchScore, hmr.handicapPercentage1, hmr.handicapPercentageX, hmr.handicapPercentage2, md.date)"
			+ " from SporToto as st inner join st.matchDetail as md inner join st.matchDetail.handicap as hmr "
			+ "where hmr.handicapNum = 0 and st.weekNumber = :weekNumber")
	List<SporTotoDTO> findWithWeekNumber(int weekNumber);
	
	@Query("select new uysal.iddaa.iddaaDb.models.sportoto.SporTotoDTO(st.id, st.weekNumber, md.home.id, md.away.id, md.homeMatchScore,"
			+ " md.awayMatchScore, hmr.handicapPercentage1, hmr.handicapPercentageX, hmr.handicapPercentage2, md.date)"
			+ " from SporToto as st inner join st.matchDetail as md inner join st.matchDetail.handicap as hmr "
			+ "where hmr.handicapNum = 0")
	List<SporTotoDTO> findAllMatches();
}
