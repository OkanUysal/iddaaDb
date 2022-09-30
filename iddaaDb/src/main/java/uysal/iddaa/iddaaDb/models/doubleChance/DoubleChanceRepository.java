package uysal.iddaa.iddaaDb.models.doubleChance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoubleChanceRepository extends JpaRepository<DoubleChance, Long>{

	Optional<DoubleChance> findById(Long id);
}
