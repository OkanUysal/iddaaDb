package uysal.iddaa.iddaaDb.services.season;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.season.Season;
import uysal.iddaa.iddaaDb.models.season.SeasonRepository;
import uysal.iddaa.iddaaDb.services.league.LeagueService;

@Service
public class SeasonServiceImp implements SeasonService{
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	@Autowired
	private LeagueService leagueService;
	
	@Override
	public Optional<Season> findById(Long id) {
		return seasonRepository.findById(id);
	}

	@Override
	public Season findByName(String name) {
		return seasonRepository.findByName(name);
	}

	@Override
	public List<Season> findAll() {
		return seasonRepository.findAll();
	}

	@Override
	public Season addNewSeason(Season season) {
//		season.setLeague(leagueService.findById(season.getLeague().getId()).get());
		return seasonRepository.save(season);
	}

}
