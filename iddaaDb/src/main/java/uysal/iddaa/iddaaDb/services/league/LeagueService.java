package uysal.iddaa.iddaaDb.services.league;

import java.util.List;
import java.util.Optional;

import uysal.iddaa.iddaaDb.models.leaugue.League;

public interface LeagueService {
	List<Object> findAllLeagueSumarry();

	Optional<League> findById(Long id);

	League findByName(String name);
	
	League addNewLeague(League league);
}
