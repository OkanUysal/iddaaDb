package uysal.iddaa.iddaaDb.services.matchDetail;

import java.util.List;
import java.util.Optional;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;

public interface MatchDetailService {
	List<Object> findAllMatchDetailSumarry();

	Optional<MatchDetail> findById(Long id);
	
	MatchDetail addNewMatchDetail(MatchDetail matchDetail);

}
