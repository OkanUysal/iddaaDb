package uysal.iddaa.iddaaDb.models.season;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

	Optional<Season> findById(Long id);

	Season findByName(String name);
}
