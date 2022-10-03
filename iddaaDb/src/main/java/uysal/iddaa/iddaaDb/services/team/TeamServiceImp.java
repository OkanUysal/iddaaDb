package uysal.iddaa.iddaaDb.services.team;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.team.Team;
import uysal.iddaa.iddaaDb.models.team.TeamDTO;
import uysal.iddaa.iddaaDb.models.team.TeamRepository;

@Service
public class TeamServiceImp implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	@Override
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

	@Override
	public Optional<Team> findById(Long id) {
		return teamRepository.findById(id);
	}

	@Override
	public Team findByName(String name) {
		return teamRepository.findByName(name);
	}

	@Override
	public Team addNewTeam(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public List<TeamDTO> findAllSummary() {
		List<TeamDTO> a = teamRepository.findAllSummary();
		return a;
	}

}
