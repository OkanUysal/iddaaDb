package uysal.iddaa.iddaaDb.models.team;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{

	@Query("select t.id, t.name from Team t")
	List<Object> findAllTeamSumarry();
	
	Optional<Team> findById(Long id);
	
	Team findByName(String name);
}
