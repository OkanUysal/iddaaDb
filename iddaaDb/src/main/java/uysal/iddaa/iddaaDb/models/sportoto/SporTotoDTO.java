package uysal.iddaa.iddaaDb.models.sportoto;

import java.util.Date;

public class SporTotoDTO {

	private Long id;
	private int weekNumber;
	private Long homeId;
	private Long awayId;
	private int homeMatchScore;
	private int awayMatchScore;
	private float handicapPercentage1;
	private float handicapPercentageX;
	private float handicapPercentage2;
	private Date date;

	public SporTotoDTO() {
		super();
	}

	public SporTotoDTO(Long id, int weekNumber, Long homeId, Long awayId, int homeMatchScore, int awayMatchScore,
			float handicapPercentage1, float handicapPercentageX, float handicapPercentage2, Date date) {
		super();
		this.id = id;
		this.weekNumber = weekNumber;
		this.homeId = homeId;
		this.awayId = awayId;
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

	public int getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
