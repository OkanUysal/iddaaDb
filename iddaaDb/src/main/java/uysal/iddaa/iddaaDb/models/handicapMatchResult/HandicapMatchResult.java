package uysal.iddaa.iddaaDb.models.handicapMatchResult;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;
import uysal.iddaa.iddaaDb.utils.View;

@Entity
@Table(name = "handicapMatchResult", uniqueConstraints = { @UniqueConstraint(columnNames = { "matchDetailId", "handicapNum" }) })
@JsonView(View.Public.class)
public class HandicapMatchResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "matchDetailId")
	@JsonIgnoreProperties(value = { "handicap" })
	private MatchDetail matchDetail;

	private int handicapNum;

	private float handicapRate1;

	private float handicapRateX;

	private float handicapRate2;

	private float handicapPercentage1;

	private float handicapPercentageX;

	private float handicapPercentage2;

	public HandicapMatchResult() {
		super();
	}

	public HandicapMatchResult(Long id, MatchDetail matchDetail, int handicapNum, int handicapRate1, int handicapRateX,
			int handicapRate2, float handicapPercentage1, float handicapPercentageX,
			float handicapPercentage2) {
		super();
		this.id = id;
		this.matchDetail = matchDetail;
		this.handicapNum = handicapNum;
		this.handicapRate1 = handicapRate1;
		this.handicapRateX = handicapRateX;
		this.handicapRate2 = handicapRate2;
		this.handicapPercentage1 = handicapPercentage1;
		this.handicapPercentageX = handicapPercentageX;
		this.handicapPercentage2 = handicapPercentage2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MatchDetail getMatchDetail() {
		return matchDetail;
	}

	public void setMatchDetail(MatchDetail matchDetail) {
		this.matchDetail = matchDetail;
	}

	public int getHandicapNum() {
		return handicapNum;
	}

	public void setHandicapNum(int handicapNum) {
		this.handicapNum = handicapNum;
	}

	public float gethandicapRate1() {
		return handicapRate1;
	}

	public void setHandicapRate1(float handicapRate1) {
		this.handicapRate1 = handicapRate1;
	}

	public float gethandicapRateX() {
		return handicapRateX;
	}

	public void sethandicapRateX(float handicapRateX) {
		this.handicapRateX = handicapRateX;
	}

	public float gethandicapRate2() {
		return handicapRate2;
	}

	public void sethandicapRate2(float handicapRate2) {
		this.handicapRate2 = handicapRate2;
	}

	public float gethandicapPercentage1() {
		return handicapPercentage1;
	}

	public void sethandicapPercentage1(float handicapPercentage1) {
		this.handicapPercentage1 = handicapPercentage1;
	}

	public float gethandicapPercentageX() {
		return handicapPercentageX;
	}

	public void sethandicapPercentageX(float handicapPercentageX) {
		this.handicapPercentageX = handicapPercentageX;
	}

	public float gethandicapPercentage2() {
		return handicapPercentage2;
	}

	public void sethandicapPercentage2(float handicapPercentage2) {
		this.handicapPercentage2 = handicapPercentage2;
	}

}
