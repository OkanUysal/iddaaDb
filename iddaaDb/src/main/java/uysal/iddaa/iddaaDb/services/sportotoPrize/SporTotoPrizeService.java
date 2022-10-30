package uysal.iddaa.iddaaDb.services.sportotoPrize;

import java.util.List;

import uysal.iddaa.iddaaDb.models.sportotoPrize.SporTotoPrize;

public interface SporTotoPrizeService {

	public List<SporTotoPrize> findAll();
	
	public List<SporTotoPrize> findByWeekNumber(int weekNumber);
	
	public SporTotoPrize save(SporTotoPrize sporTotoPrize);
}
