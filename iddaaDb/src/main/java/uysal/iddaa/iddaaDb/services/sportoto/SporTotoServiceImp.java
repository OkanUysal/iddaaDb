package uysal.iddaa.iddaaDb.services.sportoto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.sportoto.SporToto;
import uysal.iddaa.iddaaDb.models.sportoto.SporTotoDTO;
import uysal.iddaa.iddaaDb.models.sportoto.SporTotoRepository;

@Service
public class SporTotoServiceImp implements SporTotoService{
	
	@Autowired
	private SporTotoRepository sporTotoRepository;

	@Override
	public List<SporTotoDTO> findAllMatches() {
		return sporTotoRepository.findAllMatches();
	}

	@Override
	public List<SporTotoDTO> findWithWeekNumber(int weekNumber) {
		return sporTotoRepository.findWithWeekNumber(weekNumber);
	}

	@Override
	public SporToto addNewMatch(SporToto sporToto) {
		return sporTotoRepository.save(sporToto);
	}

}
