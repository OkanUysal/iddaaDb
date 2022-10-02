package uysal.iddaa.iddaaDb.models.cornerCount;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CornerCountRepository extends JpaRepository<CornerCount, Long>{

	Optional<CornerCount> findById(Long id);
}
