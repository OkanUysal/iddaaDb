package uysal.iddaa.iddaaDb.services.matchDetail;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;
import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailRepository;

@Service
public class MatchDetailServiceImp implements MatchDetailService{

	@Autowired
	private MatchDetailRepository matchDetailRepository;
	
	@Override
	public List<Object> findAllMatchDetailSumarry() {
		return matchDetailRepository.findAllMatchDetailSumarry();
	}

	@Override
	public Optional<MatchDetail> findById(Long id) {
		return matchDetailRepository.findById(id);
	}

}
