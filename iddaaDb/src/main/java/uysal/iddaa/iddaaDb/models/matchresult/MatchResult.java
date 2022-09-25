package uysal.iddaa.iddaaDb.models.matchresult;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;
import uysal.iddaa.iddaaDb.utils.View;

@Entity
@Table(name = "match_result")
@JsonView(View.Public.class)
public class MatchResult {

	@Id
	@JsonView(View.Public.class)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	@JsonIgnoreProperties(value = { "match_result" })
	@JoinColumn(name = "id")
	private MatchDetail match_detail;

	private float rate_1;

	private float rate_0;

	private float rate_2;

	private float percentage_1;

	private float percentage_0;

	private float percentage_2;

	public MatchResult() {
		super();
	}

	public MatchResult(Long id, MatchDetail match_detail, float rate_1, float rate_0, float rate_2, float percentage_1,
			float percentage_0, float percentage_2) {
		super();
		this.id = id;
		this.match_detail = match_detail;
		this.rate_1 = rate_1;
		this.rate_0 = rate_0;
		this.rate_2 = rate_2;
		this.percentage_1 = percentage_1;
		this.percentage_0 = percentage_0;
		this.percentage_2 = percentage_2;
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

	public float getRate_1() {
		return rate_1;
	}

	public void setRate_1(float rate_1) {
		this.rate_1 = rate_1;
	}

	public float getRate_0() {
		return rate_0;
	}

	public void setRate_0(float rate_0) {
		this.rate_0 = rate_0;
	}

	public float getRate_2() {
		return rate_2;
	}

	public void setRate_2(float rate_2) {
		this.rate_2 = rate_2;
	}

	public float getPercentage_1() {
		return percentage_1;
	}

	public void setPercentage_1(float percentage_1) {
		this.percentage_1 = percentage_1;
	}

	public float getPercentage_0() {
		return percentage_0;
	}

	public void setPercentage_0(float percentage_0) {
		this.percentage_0 = percentage_0;
	}

	public float getPercentage_2() {
		return percentage_2;
	}

	public void setPercentage_2(float percentage_2) {
		this.percentage_2 = percentage_2;
	}

}
