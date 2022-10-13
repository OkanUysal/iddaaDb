package uysal.iddaa.iddaaDb.services.currentsportoto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.currentsportoto.CurrentSporToto;
import uysal.iddaa.iddaaDb.models.currentsportoto.CurrentSporTotoRepository;

@Service
public class CurrentSporTotoServiceImp implements CurrentSporTotoService{

	@Autowired
	private CurrentSporTotoRepository currentSporTotoRepository;
	
	@Override
	public List<CurrentSporToto> findAll() {
		return currentSporTotoRepository.findAll();
	}

	@Override
	public List<CurrentSporToto> findByWeekNumber(int weekNumber) {
		return currentSporTotoRepository.findByWeekNumber(weekNumber);
	}
	
	@Override
	public CurrentSporToto save(CurrentSporToto currentSporToto) {
		return currentSporTotoRepository.save(currentSporToto);
	}

}
