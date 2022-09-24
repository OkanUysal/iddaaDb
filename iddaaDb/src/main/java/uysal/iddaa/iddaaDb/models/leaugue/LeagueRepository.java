package uysal.iddaa.iddaaDb.models.leaugue;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long>{

	@Query("select l.id, l.name from League l")
	List<Object> findAllLeagueSumarry();
	
	Optional<League> findById(Long id);
	
	League findByName(String name);
}
