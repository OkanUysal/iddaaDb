package uysal.iddaa.iddaaDb.models.underover;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnderOverRepository extends JpaRepository<UnderOver, Long>{

	Optional<UnderOver> findById(Long id);

}
