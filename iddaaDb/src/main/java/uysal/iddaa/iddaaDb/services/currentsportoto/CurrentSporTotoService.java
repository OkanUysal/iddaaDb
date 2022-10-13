package uysal.iddaa.iddaaDb.services.currentsportoto;

import java.util.List;

import uysal.iddaa.iddaaDb.models.currentsportoto.CurrentSporToto;

public interface CurrentSporTotoService {

	public List<CurrentSporToto> findAll();
	
	public List<CurrentSporToto> findByWeekNumber(int weekNumber);
	
	public CurrentSporToto save(CurrentSporToto currentSporToto);
}
