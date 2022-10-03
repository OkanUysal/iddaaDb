package uysal.iddaa.iddaaDb.services.team;

import java.util.List;
import java.util.Optional;

import uysal.iddaa.iddaaDb.models.team.Team;
import uysal.iddaa.iddaaDb.models.team.TeamDTO;

public interface TeamService {

	List<Team> findAll();
	
	List<TeamDTO> findAllSummary();

	Optional<Team> findById(Long id);

	Team findByName(String name);
	
	Team addNewTeam(Team team);

}
