package uysal.iddaa.iddaaDb.models.matchdetail;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import uysal.iddaa.iddaaDb.utils.View;

@JsonView(View.Public.class)
public class MatchDetailDTO {
	
	private Long id;
	private Long teamId;
	private String teamName;
	private int homeMatchScore;
	private int awayMatchScore;
	private float handicapPercentage1;
	private float handicapPercentageX;
	private float handicapPercentage2;
	private Date date;
	
	public MatchDetailDTO(Long id, Long teamId, String teamName, int homeMatchScore, int awayMatchScore,
			float handicapPercentage1, float handicapPercentageX, float handicapPercentage2, Date date) {
		super();
		this.id = id;
		this.teamId = teamId;
		this.teamName = teamName;
		this.homeMatchScore = homeMatchScore;
		this.awayMatchScore = awayMatchScore;
		this.handicapPercentage1 = handicapPercentage1;
		this.handicapPercentageX = handicapPercentageX;
		this.handicapPercentage2 = handicapPercentage2;
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
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
	public float getHandicapPercentage1() {
		return handicapPercentage1;
	}
	public void setHandicapPercentage1(float handicapPercentage1) {
		this.handicapPercentage1 = handicapPercentage1;
	}
	public float getHandicapPercentageX() {
		return handicapPercentageX;
	}
	public void setHandicapPercentageX(float handicapPercentageX) {
		this.handicapPercentageX = handicapPercentageX;
	}
	public float getHandicapPercentage2() {
		return handicapPercentage2;
	}
	public void setHandicapPercentage2(float handicapPercentage2) {
		this.handicapPercentage2 = handicapPercentage2;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
}
