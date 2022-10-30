package uysal.iddaa.iddaaDb.services.sportotoPrize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.sportotoPrize.SporTotoPrize;
import uysal.iddaa.iddaaDb.models.sportotoPrize.SporTotoPrizeRepository;

@Service
public class SporTotoPrizeServiceImp implements SporTotoPrizeService{

	@Autowired
	private SporTotoPrizeRepository sporTotoPrizeRepository;
	
	@Override
	public List<SporTotoPrize> findAll() {
		return sporTotoPrizeRepository.findAll();
	}

	@Override
	public List<SporTotoPrize> findByWeekNumber(int weekNumber) {
		return sporTotoPrizeRepository.findByWeekNumber(weekNumber);
	}

	@Override
	public SporTotoPrize save(SporTotoPrize sporTotoPrize) {
		return sporTotoPrizeRepository.save(sporTotoPrize);
	}

}
