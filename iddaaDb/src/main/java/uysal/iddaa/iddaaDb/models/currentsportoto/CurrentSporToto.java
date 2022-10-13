package uysal.iddaa.iddaaDb.models.currentsportoto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "currentSporToto")
public class CurrentSporToto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int weekNumber;

	private int matchNumber;

	private int homeId;

	private int awayId;

	private float handicapPercentage1;

	private float handicapPercentageX;

	private float handicapPercentage2;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date date;

	public CurrentSporToto() {
		super();
	}

	public CurrentSporToto(Long id, int weekNumber, int matchNumber, int homeId, int awayId, float handicapPercentage1,
			float handicapPercentageX, float handicapPercentage2, Date date) {
		super();
		this.id = id;
		this.weekNumber = weekNumber;
		this.matchNumber = matchNumber;
		this.homeId = homeId;
		this.awayId = awayId;
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

	public int getMatchNumber() {
		return matchNumber;
	}

	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}

	public int getHomeId() {
		return homeId;
	}

	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}

	public int getAwayId() {
		return awayId;
	}

	public void setAwayId(int awayId) {
		this.awayId = awayId;
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
