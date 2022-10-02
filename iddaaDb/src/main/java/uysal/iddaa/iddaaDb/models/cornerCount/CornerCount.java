package uysal.iddaa.iddaaDb.models.cornerCount;

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
@Table(name = "cornerCount", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "matchDetailId", "cornerNumber" }) })
@JsonView(View.Public.class)
public class CornerCount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "matchDetailId")
	@JsonIgnoreProperties(value = { "cornerCount" })
	private MatchDetail matchDetail;

	private int cornerNumber;

	private float cornerCountOverRate;

	private float cornerCountUnderRate;

	private float cornerCountOverPercentage;

	private float cornerCountUnderPercentage;

	public CornerCount() {
		super();
	}

	public CornerCount(Long id, MatchDetail matchDetail, int cornerNumber, float cornerCountOverRate,
			float cornerCountUnderRate, float cornerCountOverPercentage, float cornerCountUnderPercentage) {
		super();
		this.id = id;
		this.matchDetail = matchDetail;
		this.cornerNumber = cornerNumber;
		this.cornerCountOverRate = cornerCountOverRate;
		this.cornerCountUnderRate = cornerCountUnderRate;
		this.cornerCountOverPercentage = cornerCountOverPercentage;
		this.cornerCountUnderPercentage = cornerCountUnderPercentage;
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

	public int getCornerNumber() {
		return cornerNumber;
	}

	public void setCornerNumber(int cornerNumber) {
		this.cornerNumber = cornerNumber;
	}

	public float getCornerCountOverRate() {
		return cornerCountOverRate;
	}

	public void setCornerCountOverRate(float cornerCountOverRate) {
		this.cornerCountOverRate = cornerCountOverRate;
	}

	public float getCornerCountUnderRate() {
		return cornerCountUnderRate;
	}

	public void setCornerCountUnderRate(float cornerCountUnderRate) {
		this.cornerCountUnderRate = cornerCountUnderRate;
	}

	public float getCornerCountOverPercentage() {
		return cornerCountOverPercentage;
	}

	public void setCornerCountOverPercentage(float cornerCountOverPercentage) {
		this.cornerCountOverPercentage = cornerCountOverPercentage;
	}

	public float getCornerCountUnderPercentage() {
		return cornerCountUnderPercentage;
	}

	public void setCornerCountUnderPercentage(float cornerCountUnderPercentage) {
		this.cornerCountUnderPercentage = cornerCountUnderPercentage;
	}

}
