package uysal.iddaa.iddaaDb.models.currentsportoto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentSporTotoRepository extends JpaRepository<CurrentSporToto, Long>{
	
	public List<CurrentSporToto> findByWeekNumber(int weekNumber);

}
