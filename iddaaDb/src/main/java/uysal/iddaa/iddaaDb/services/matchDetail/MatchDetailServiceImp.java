package uysal.iddaa.iddaaDb.services.matchDetail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;
import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailDTO;
import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailInsertRepository;
import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailRepository;
import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetailSummaryDTO;
import uysal.iddaa.iddaaDb.models.season.Season;
import uysal.iddaa.iddaaDb.services.season.SeasonService;
import uysal.iddaa.iddaaDb.services.team.TeamService;

@Service
public class MatchDetailServiceImp implements MatchDetailService {

	@Autowired
	private MatchDetailRepository matchDetailRepository;
	
	@Autowired
	private MatchDetailInsertRepository matchDetailInsertRepository;

	@Autowired
	private TeamService teamService;
	
	@Autowired
	private SeasonService seasonService;
	
	@Override
	public List<MatchDetail> findAll() {
		return matchDetailRepository.findAll();
	}

	@Override
	public Optional<MatchDetail> findById(Long id) {
		return matchDetailRepository.findById(id);
	}
	
	@Override
	public List<Object> findAllIds() {
		return matchDetailRepository.findAllIds();
	}

	@Override
	public MatchDetail addNewMatchDetail(MatchDetail matchDetail) {
//		matchDetail.setHome(teamService.findById(matchDetail.getHome().getId()).get());
//		matchDetail.setAway(teamService.findById(matchDetail.getAway().getId()).get());
//		matchDetail.setSeason(seasonService.findById(matchDetail.getSeason().getId()).get());
		return matchDetailRepository.save(matchDetail);
	}

	@Override
	public void insertWithQuery(MatchDetail matchDetail) {
		matchDetailInsertRepository.insertWithQuery(matchDetail);	
	}

	@Override
	public boolean isExistById(Long id) {
		return matchDetailRepository.existsById(id);
	}

	@Override
	public List<MatchDetailDTO> findAllHomeMatchWithDate(Long id, Date fromDate, Date toDate) {
		return matchDetailRepository.findAllHomeMatchWithDate(id, fromDate, toDate);
	}

	@Override
	public List<MatchDetailDTO> findAllAwayMatchWithDate(Long id, Date fromDate, Date toDate) {
		return matchDetailRepository.findAllAwayMatchWithDate(id, fromDate, toDate);
	}

	@Override
	public List<MatchDetailSummaryDTO> findAllMatchesforDate(Date date) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        cal.add(Calendar.DATE, 2); //minus number would decrement the days
		return matchDetailRepository.findAllMatchforDate(date, cal.getTime());
	}

}
