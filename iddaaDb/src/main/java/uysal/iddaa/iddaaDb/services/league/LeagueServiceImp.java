package uysal.iddaa.iddaaDb.services.league;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	public League addNewLeague(String data) {
		JsonObject object = new JsonParser().parse(data).getAsJsonObject();

		League league = new League();

		league.setName(object.get("name").getAsString());
		league.setCountry(countryService.findById(object.get("country_id").getAsLong()).get());

		return leagueRepository.save(league);
	}

}
