package uysal.iddaa.iddaaDb.services.underover;

import java.util.Optional;

import uysal.iddaa.iddaaDb.models.underover.UnderOver;

public interface UnderOverService {
	
	Optional<UnderOver> findById(Long id);
	
	UnderOver save(UnderOver underOver);

}
