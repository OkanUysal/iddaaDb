package uysal.iddaa.iddaaDb.models.sportotoPrize;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SporTotoPrizeRepository extends JpaRepository<SporTotoPrize, Long>{

	public List<SporTotoPrize> findByWeekNumber(int weekNumber);

}
