package uysal.iddaa.iddaaDb.services.cornerCount;

import java.util.Optional;

import uysal.iddaa.iddaaDb.models.cornerCount.CornerCount;

public interface CornerCountService {

	Optional<CornerCount> findById(Long id);
	
	CornerCount save(CornerCount cornerCount);
}
