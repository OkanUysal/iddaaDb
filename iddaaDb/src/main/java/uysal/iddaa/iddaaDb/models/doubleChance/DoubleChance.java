package uysal.iddaa.iddaaDb.models.doubleChance;

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
@Table(name = "doubleChance", uniqueConstraints = { @UniqueConstraint(columnNames = { "matchDetailId" }) })
@JsonView(View.Public.class)
public class DoubleChance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "matchDetailId")
	@JsonIgnoreProperties(value = { "doubleChance" })
	private MatchDetail matchDetail;

	private float doubleChanceRate1X;

	private float doubleChanceRate12;

	private float doubleChanceRate2X;

	private float doubleChancePercentage1X;

	private float doubleChancePercentage12;

	private float doubleChancePercentage2X;

	public DoubleChance() {
		super();
	}

	public DoubleChance(Long id, MatchDetail matchDetail, float doubleChanceRate1X, float doubleChanceRate12,
			float doubleChanceRate2X, float doubleChancePercentage1X, float doubleChancePercentage12,
			float doubleChancePercentage2X) {
		super();
		this.id = id;
		this.matchDetail = matchDetail;
		this.doubleChanceRate1X = doubleChanceRate1X;
		this.doubleChanceRate12 = doubleChanceRate12;
		this.doubleChanceRate2X = doubleChanceRate2X;
		this.doubleChancePercentage1X = doubleChancePercentage1X;
		this.doubleChancePercentage12 = doubleChancePercentage12;
		this.doubleChancePercentage2X = doubleChancePercentage2X;
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

	public float getDoubleChanceRate1X() {
		return doubleChanceRate1X;
	}

	public void setDoubleChanceRate1X(float doubleChanceRate1X) {
		this.doubleChanceRate1X = doubleChanceRate1X;
	}

	public float getDoubleChanceRate12() {
		return doubleChanceRate12;
	}

	public void setDoubleChanceRate12(float doubleChanceRate12) {
		this.doubleChanceRate12 = doubleChanceRate12;
	}

	public float getDoubleChanceRate2X() {
		return doubleChanceRate2X;
	}

	public void setDoubleChanceRate2X(float doubleChanceRate2X) {
		this.doubleChanceRate2X = doubleChanceRate2X;
	}

	public float getDoubleChancePercentage1X() {
		return doubleChancePercentage1X;
	}

	public void setDoubleChancePercentage1X(float doubleChancePercentage1X) {
		this.doubleChancePercentage1X = doubleChancePercentage1X;
	}

	public float getDoubleChancePercentage12() {
		return doubleChancePercentage12;
	}

	public void setDoubleChancePercentage12(float doubleChancePercentage12) {
		this.doubleChancePercentage12 = doubleChancePercentage12;
	}

	public float getDoubleChancePercentage2X() {
		return doubleChancePercentage2X;
	}

	public void setDoubleChancePercentage2X(float doubleChancePercentage2X) {
		this.doubleChancePercentage2X = doubleChancePercentage2X;
	}

}
