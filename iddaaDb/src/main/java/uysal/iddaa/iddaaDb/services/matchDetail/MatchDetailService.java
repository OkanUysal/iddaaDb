package uysal.iddaa.iddaaDb.services.matchDetail;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;
import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailDTO;
import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailSummaryDTO;

public interface MatchDetailService {
	
	List<MatchDetail> findAll();
	
	List<Object> findAllIds();
	
	List<MatchDetailDTO> findAllHomeMatchWithDate(Long id, Date fromDate, Date toDate);
	
	List<MatchDetailDTO> findAllAwayMatchWithDate(Long id, Date fromDate, Date toDate);
	
	List<MatchDetailSummaryDTO> findAllMatchesforDate(Date date);

	Optional<MatchDetail> findById(Long id);
	
	MatchDetail addNewMatchDetail(MatchDetail matchDetail);
	
	void insertWithQuery(MatchDetail matchDetail);
	
	boolean isExistById(Long id);

}
