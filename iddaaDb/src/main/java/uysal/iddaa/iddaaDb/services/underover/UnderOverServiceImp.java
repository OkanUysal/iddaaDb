package uysal.iddaa.iddaaDb.services.underover;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.underover.UnderOver;
import uysal.iddaa.iddaaDb.models.underover.UnderOverRepository;
import uysal.iddaa.iddaaDb.services.matchDetail.MatchDetailService;

@Service
public class UnderOverServiceImp implements UnderOverService{
	
	@Autowired
	private UnderOverRepository underOverRepository;
	
	@Autowired
	private MatchDetailService matchDetailService;

	@Override
	public Optional<UnderOver> findById(Long id) {
		return underOverRepository.findById(id);
	}

	@Override
	public UnderOver save(UnderOver underOver) {
		underOver.setMatch_detail(matchDetailService.findById(underOver.getMatch_detail().getId()).get());
		return underOverRepository.save(underOver);
	}

}
