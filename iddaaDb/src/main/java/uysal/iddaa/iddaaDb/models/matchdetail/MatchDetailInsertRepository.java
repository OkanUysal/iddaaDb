package uysal.iddaa.iddaaDb.models.matchdetail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class MatchDetailInsertRepository {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
	public void insertWithQuery(MatchDetail matchDetail) {
	    entityManager.createNativeQuery("INSERT INTO match_detail (id, away_id, home_id, season_id, away_half_time_score, home_half_time_score, home_match_score, away_match_score, date) VALUES (?,?,?,?,?,?,?,?,?)")
	      .setParameter(1, matchDetail.getId())
	      .setParameter(2, matchDetail.getAway().getId())
	      .setParameter(3, matchDetail.getHome().getId())
	      .setParameter(4, matchDetail.getSeason().getId())
	      .setParameter(5, matchDetail.getAwayHalfTimeScore())
	      .setParameter(6, matchDetail.getHomeHalfTimeScore())
	      .setParameter(7, matchDetail.getHomeMatchScore())
	      .setParameter(8, matchDetail.getAwayMatchScore())
	      .setParameter(9, matchDetail.getDate())
	      .executeUpdate();
	}
}
