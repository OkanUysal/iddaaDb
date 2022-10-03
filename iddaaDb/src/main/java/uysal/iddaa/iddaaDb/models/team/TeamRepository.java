package uysal.iddaa.iddaaDb.models.team;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{
	
	@Query("select new uysal.iddaa.iddaaDb.models.team.TeamDTO(t.name, t.id) from Team as t")
	List<TeamDTO> findAllSummary();
	
	Optional<Team> findById(Long id);
	
	Team findByName(String name);
}
