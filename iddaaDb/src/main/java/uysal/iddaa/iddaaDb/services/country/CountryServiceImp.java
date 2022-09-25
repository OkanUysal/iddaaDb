package uysal.iddaa.iddaaDb.services.country;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.country.Country;
import uysal.iddaa.iddaaDb.models.country.CountryRepository;

@Service
public class CountryServiceImp implements CountryService{
	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<Object> findAllCountrySumarry() {
		return countryRepository.findAllCountrySumarry();
	}

	@Override
	public Optional<Country> findById(Long id) {
		return countryRepository.findById(id);
	}

	@Override
	public Country findByName(String name) {
		return countryRepository.findByName(name);
	}

	@Override
	public Country save(Country country) {
		return countryRepository.save(country);
	}

	@Override
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

}
