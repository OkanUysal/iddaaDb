package uysal.iddaa.iddaaDb.services.league;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.leaugue.League;
import uysal.iddaa.iddaaDb.models.leaugue.LeagueRepository;
import uysal.iddaa.iddaaDb.services.country.CountryService;

@Service
public class LeagueServiceImp implements LeagueService {

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private CountryService countryService;

	@Override
	public List<Object> findAllLeagueSumarry() {
		return leagueRepository.findAllLeagueSumarry();
	}

	@Override
	public Optional<League> findById(Long id) {
		return leagueRepository.findById(id);
	}

	@Override
	public League findByName(String name) {
		return leagueRepository.findByName(name);
	}
	
	@Override
	public League addNewLeague(League league) {
//		league.setCountry(countryService.findById(league.getCountry().getId()).get());
		return leagueRepository.save(league);
	}

	@Override
	public List<League> findAll() {
		return leagueRepository.findAll();
	}

}
