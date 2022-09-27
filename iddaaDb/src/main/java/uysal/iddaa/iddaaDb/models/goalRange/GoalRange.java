package uysal.iddaa.iddaaDb.models.goalRange;

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
@Table(name = "goalRange", uniqueConstraints = { @UniqueConstraint(columnNames = { "matchDetailId" }) })
@JsonView(View.Public.class)
public class GoalRange {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "matchDetailId")
	@JsonIgnoreProperties(value = { "handicap" })
	private MatchDetail matchDetail;

	private float goalRangeRate01;

	private float goalRangeRate23;

	private float goalRangeRate45;

	private float goalRangeRatePlus6;

	private float goalRangePercentage01;

	private float goalRangePercentage23;

	private float goalRangePercentage45;

	private float goalRangePercentagePlus6;

	public GoalRange() {
		super();
	}

	public GoalRange(Long id, MatchDetail matchDetail, float goalRangeRate01, float goalRangeRate23,
			float goalRangeRate45, float goalRangeRatePlus6, float goalRangePercentage01, float goalRangePercentage23,
			float goalRangePercentage45, float goalRangePercentagePlus6) {
		super();
		this.id = id;
		this.matchDetail = matchDetail;
		this.goalRangeRate01 = goalRangeRate01;
		this.goalRangeRate23 = goalRangeRate23;
		this.goalRangeRate45 = goalRangeRate45;
		this.goalRangeRatePlus6 = goalRangeRatePlus6;
		this.goalRangePercentage01 = goalRangePercentage01;
		this.goalRangePercentage23 = goalRangePercentage23;
		this.goalRangePercentage45 = goalRangePercentage45;
		this.goalRangePercentagePlus6 = goalRangePercentagePlus6;
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

	public float getGoalRangeRate01() {
		return goalRangeRate01;
	}

	public void setGoalRangeRate01(float goalRangeRate01) {
		this.goalRangeRate01 = goalRangeRate01;
	}

	public float getGoalRangeRate23() {
		return goalRangeRate23;
	}

	public void setGoalRangeRate23(float goalRangeRate23) {
		this.goalRangeRate23 = goalRangeRate23;
	}

	public float getGoalRangeRate45() {
		return goalRangeRate45;
	}

	public void setGoalRangeRate45(float goalRangeRate45) {
		this.goalRangeRate45 = goalRangeRate45;
	}

	public float getGoalRangeRatePlus6() {
		return goalRangeRatePlus6;
	}

	public void setGoalRangeRatePlus6(float goalRangeRatePlus6) {
		this.goalRangeRatePlus6 = goalRangeRatePlus6;
	}

	public float getGoalRangePercentage01() {
		return goalRangePercentage01;
	}

	public void setGoalRangePercentage01(float goalRangePercentage01) {
		this.goalRangePercentage01 = goalRangePercentage01;
	}

	public float getGoalRangePercentage23() {
		return goalRangePercentage23;
	}

	public void setGoalRangePercentage23(float goalRangePercentage23) {
		this.goalRangePercentage23 = goalRangePercentage23;
	}

	public float getGoalRangePercentage45() {
		return goalRangePercentage45;
	}

	public void setGoalRangePercentage45(float goalRangePercentage45) {
		this.goalRangePercentage45 = goalRangePercentage45;
	}

	public float getGoalRangePercentagePlus6() {
		return goalRangePercentagePlus6;
	}

	public void setGoalRangePercentagePlus6(float goalRangePercentagePlus6) {
		this.goalRangePercentagePlus6 = goalRangePercentagePlus6;
	}

}
