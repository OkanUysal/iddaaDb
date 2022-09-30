package uysal.iddaa.iddaaDb.services.doubleChance;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.doubleChance.DoubleChance;
import uysal.iddaa.iddaaDb.models.doubleChance.DoubleChanceRepository;

@Service
public class DoubleChanceServiceImp implements DoubleChanceService {

	@Autowired
	private DoubleChanceRepository doubleChanceRepository;

	@Override
	public Optional<DoubleChance> findById(Long id) {
		return doubleChanceRepository.findById(id);
	}

	@Override
	public DoubleChance save(DoubleChance doubleChance) {
		return doubleChanceRepository.save(doubleChance);
	}

}
