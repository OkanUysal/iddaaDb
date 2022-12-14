package uysal.iddaa.iddaaDb.models.underover;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;
import uysal.iddaa.iddaaDb.utils.View;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "underOver", uniqueConstraints = {@UniqueConstraint(columnNames = {"matchDetailId", "underOverNum"})})
@JsonView(View.Public.class)
public class UnderOver {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "matchDetailId")
	@JsonIgnoreProperties(value = { "handicap", "underOver" })
	private MatchDetail matchDetail;
	
	private float underOverNum;

	private float underRate;

	private float underPercentage;

	private float overPercentage;

	private float overRate;



	public UnderOver() {
		super();
	}


	public UnderOver(Long id, MatchDetail matchDetail, float underOverNum, float underRate, float underPercentage,
			float overPercentage, float overRate) {
		super();
		this.id = id;
		this.matchDetail = matchDetail;
		this.underOverNum = underOverNum;
		this.underRate = underRate;
		this.underPercentage = underPercentage;
		this.overPercentage = overPercentage;
		this.overRate = overRate;
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

	public float getUnderRate() {
		return underRate;
	}

	public void setUnderRatio(float underRate) {
		this.underRate = underRate;
	}

	public float getUnderPercentage() {
		return underPercentage;
	}

	public void setUnderPercentage(float underPercentage) {
		this.underPercentage = underPercentage;
	}

	public float getOverPercentage() {
		return overPercentage;
	}

	public void setOverPercentage(float overPercentage) {
		this.overPercentage = overPercentage;
	}

	public float getOverRate() {
		return overRate;
	}

	public void setOverRate(float overRate) {
		this.overRate = overRate;
	}

	public float getUnderOverNum() {
		return underOverNum;
	}

	public void setUnderOverNum(float underOverNum) {
		this.underOverNum = underOverNum;
	}

}
