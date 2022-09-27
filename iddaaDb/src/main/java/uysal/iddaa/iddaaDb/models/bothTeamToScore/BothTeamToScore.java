package uysal.iddaa.iddaaDb.models.bothTeamToScore;

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
@Table(name = "bothTeamToScore", uniqueConstraints = { @UniqueConstraint(columnNames = { "matchDetailId" }) })
@JsonView(View.Public.class)
public class BothTeamToScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "matchDetailId")
	@JsonIgnoreProperties(value = { "handicap" })
	private MatchDetail matchDetail;

	private float bttsRate_t;

	private float bttsRate_f;

	private float bttsPercentage_t;

	private float bttsPercentage_f;

	public BothTeamToScore() {
		super();
	}

	public BothTeamToScore(Long id, MatchDetail matchDetail, float bttsRate_t, float bttsRate_f, float bttsPercentage_t,
			float bttsPercentage_f) {
		super();
		this.id = id;
		this.matchDetail = matchDetail;
		this.bttsRate_t = bttsRate_t;
		this.bttsRate_f = bttsRate_f;
		this.bttsPercentage_t = bttsPercentage_t;
		this.bttsPercentage_f = bttsPercentage_f;
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

	public float getBttsRate_t() {
		return bttsRate_t;
	}

	public void setBttsRate_t(float bttsRate_t) {
		this.bttsRate_t = bttsRate_t;
	}

	public float getBttsRate_f() {
		return bttsRate_f;
	}

	public void setBttsRate_f(float bttsRate_f) {
		this.bttsRate_f = bttsRate_f;
	}

	public float getBttsPercentage_t() {
		return bttsPercentage_t;
	}

	public void setBttsPercentage_t(float bttsPercentage_t) {
		this.bttsPercentage_t = bttsPercentage_t;
	}

	public float getBttsPercentage_f() {
		return bttsPercentage_f;
	}

	public void setBttsPercentage_f(float bttsPercentage_f) {
		this.bttsPercentage_f = bttsPercentage_f;
	}

}
