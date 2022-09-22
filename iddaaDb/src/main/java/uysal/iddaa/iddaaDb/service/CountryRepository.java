package uysal.iddaa.iddaaDb.service;

import org.springframework.stereotype.Repository;

import uysal.iddaa.iddaaDb.model.Country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	@Query("select c.id, c.name from Country c")
	List<Object> findAllCountrySumarry();
}
