package uysal.iddaa.iddaaDb.models.underover;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;
import uysal.iddaa.iddaaDb.utils.View;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "under_over")
@JsonView(View.Public.class)
public class UnderOver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "match_detail_id")
	@JsonIgnoreProperties(value = { "under_over" })
	private MatchDetail match_detail;

	private float underRatio;

	private float underPercentage;

	private float overPercentage;

	private float overRatio;

	private float underOverNum;

	public UnderOver() {
		super();
	}

	public UnderOver(Long id, MatchDetail match_detail, float underRatio, float overRatio, float underOverNum) {
		super();
		this.id = id;
		this.match_detail = match_detail;
		this.underRatio = underRatio;
		this.overRatio = overRatio;
		this.underOverNum = underOverNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MatchDetail getMatch_detail() {
		return match_detail;
	}

	public void setMatch_detail(MatchDetail match_detail) {
		this.match_detail = match_detail;
	}

	public float getUnderRatio() {
		return underRatio;
	}

	public void setUnderRatio(float underRatio) {
		this.underRatio = underRatio;
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

	public float getOverRatio() {
		return overRatio;
	}

	public void setOverRatio(float overRatio) {
		this.overRatio = overRatio;
	}

	public float getUnderOverNum() {
		return underOverNum;
	}

	public void setUnderOverNum(float underOverNum) {
		this.underOverNum = underOverNum;
	}

}
