package uysal.iddaa.iddaaDb.services.country;

import java.util.List;
import java.util.Optional;

import uysal.iddaa.iddaaDb.models.country.Country;

public interface CountryService {
	
	List<Country> findAll();

	List<Object> findAllCountrySumarry();

	Optional<Country> findById(Long id);

	Country findByName(String name);
	
	Country save(Country country);
}
