package uysal.iddaa.iddaaDb.services.doubleChance;

import java.util.Optional;

import uysal.iddaa.iddaaDb.models.doubleChance.DoubleChance;

public interface DoubleChanceService {

	Optional<DoubleChance> findById(Long id);
	
	DoubleChance save(DoubleChance doubleChance);
}
