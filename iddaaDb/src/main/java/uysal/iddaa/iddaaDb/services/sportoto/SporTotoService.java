package uysal.iddaa.iddaaDb.services.sportoto;

import java.util.List;

import uysal.iddaa.iddaaDb.models.sportoto.SporToto;
import uysal.iddaa.iddaaDb.models.sportoto.SporTotoDTO;

public interface SporTotoService {

	List<SporTotoDTO> findAllMatches();
	
	List<SporTotoDTO> findWithWeekNumber(int weekNumber);
	
	SporToto addNewMatch(SporToto sporToto);
	
}
