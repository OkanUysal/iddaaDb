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

	@ManyToOne(cascade = CascadeType.ALL)
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

	public float getHandicapRate1() {
		return handicapRate1;
	}

	public void setHandicapRate1(int handicapRate1) {
		this.handicapRate1 = handicapRate1;
	}

	public float getHandicapRate0() {
		return handicapRateX;
	}

	public void setHandicapRate0(int handicapRate0) {
		this.handicapRateX = handicapRate0;
	}

	public float getHandicapRate2() {
		return handicapRate2;
	}

	public void setHandicapRate2(int handicapRate2) {
		this.handicapRate2 = handicapRate2;
	}

	public float getHandicapRatePercentage1() {
		return handicapPercentage1;
	}

	public void setHandicapRatePercentage1(float handicapRatePercentage1) {
		this.handicapPercentage1 = handicapRatePercentage1;
	}

	public float getHandicapRatePercentage0() {
		return handicapPercentageX;
	}

	public void setHandicapRatePercentage0(float handicapRatePercentage0) {
		this.handicapPercentageX = handicapRatePercentage0;
	}

	public float getHandicapRatePercentage2() {
		return handicapPercentage2;
	}

	public void setHandicapRatePercentage2(float handicapRatePercentage2) {
		this.handicapPercentage2 = handicapRatePercentage2;
	}

}
