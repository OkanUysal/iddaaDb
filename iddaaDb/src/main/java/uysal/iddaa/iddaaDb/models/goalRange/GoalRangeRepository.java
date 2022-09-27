package uysal.iddaa.iddaaDb.models.goalRange;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRangeRepository extends JpaRepository<GoalRange, Long>{

	Optional<GoalRange> findById(Long id);
}
