package uysal.iddaa.iddaaDb.services.season;

import java.util.List;
import java.util.Optional;

import uysal.iddaa.iddaaDb.models.season.Season;

public interface SeasonService {

	Optional<Season> findById(Long id);

	Season findByName(String name);

	List<Season> findAll();
	
	Season addNewSeason(Season season);

}
