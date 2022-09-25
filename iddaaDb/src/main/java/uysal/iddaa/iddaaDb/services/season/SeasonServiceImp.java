package uysal.iddaa.iddaaDb.services.season;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.season.Season;
import uysal.iddaa.iddaaDb.models.season.SeasonRepository;

@Service
public class SeasonServiceImp implements SeasonService{
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	@Override
	public Optional<Season> findById(Long id) {
		return seasonRepository.findById(id);
	}

	@Override
	public Season findByName(String name) {
		return seasonRepository.findByName(name);
	}

}
