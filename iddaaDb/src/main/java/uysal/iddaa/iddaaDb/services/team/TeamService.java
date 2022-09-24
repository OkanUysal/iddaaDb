package uysal.iddaa.iddaaDb.services.team;

import java.util.List;
import java.util.Optional;

import uysal.iddaa.iddaaDb.models.team.Team;

public interface TeamService {

	List<Object> findAllTeamSumarry();

	Optional<Team> findById(Long id);

	Team findByName(String name);

}
