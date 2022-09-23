package uysal.iddaa.iddaaDb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uysal.iddaa.iddaaDb.model.League;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long>{

	@Query("select l.id, l.name from League l")
	List<Object> findAllLeagueSumarry();
	
	Optional<League> findById(Long id);
	
	League findByName(String name);
}
