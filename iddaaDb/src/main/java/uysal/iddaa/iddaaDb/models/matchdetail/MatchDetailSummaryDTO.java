package uysal.iddaa.iddaaDb.models.matchdetail;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import uysal.iddaa.iddaaDb.utils.View;

@JsonView(View.Public.class)
public class MatchDetailSummaryDTO {

	private Long id;
	private Long homeId;
	private Long awayId;
	private String homeName;
	private String awayName;
	private int homeMatchScore;
	private int awayMatchScore;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date date;

	public MatchDetailSummaryDTO() {
		super();
	}

	public MatchDetailSummaryDTO(Long id, Long homeId, Long awayId, String homeName, String awayName,
			int homeMatchScore, int awayMatchScore, Date date) {
		super();
		this.id = id;
		this.homeId = homeId;
		this.awayId = awayId;
		this.homeName = homeName;
		this.awayName = awayName;
		this.homeMatchScore = homeMatchScore;
		this.awayMatchScore = awayMatchScore;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHomeId() {
		return homeId;
	}

	public void setHomeId(Long homeId) {
		this.homeId = homeId;
	}

	public Long getAwayId() {
		return awayId;
	}

	public void setAwayId(Long awayId) {
		this.awayId = awayId;
	}

	public String getHomeName() {
		return homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

	public String getAwayName() {
		return awayName;
	}

	public void setAwayName(String awayName) {
		this.awayName = awayName;
	}

	public int getHomeMatchScore() {
		return homeMatchScore;
	}

	public void setHomeMatchScore(int homeMatchScore) {
		this.homeMatchScore = homeMatchScore;
	}

	public int getAwayMatchScore() {
		return awayMatchScore;
	}

	public void setAwayMatchScore(int awayMatchScore) {
		this.awayMatchScore = awayMatchScore;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
