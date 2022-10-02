package uysal.iddaa.iddaaDb.services.cornerCount;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.cornerCount.CornerCount;
import uysal.iddaa.iddaaDb.models.cornerCount.CornerCountRepository;

@Service
public class CornerCountServiceImp implements CornerCountService{

	@Autowired
	private CornerCountRepository cornerCountRepository;
	
	@Override
	public Optional<CornerCount> findById(Long id) {
		return cornerCountRepository.findById(id);
	}

	@Override
	public CornerCount save(CornerCount cornerCount) {
		return cornerCountRepository.save(cornerCount);
	}

}
