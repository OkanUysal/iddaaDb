package uysal.iddaa.iddaaDb.models.sportoto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import uysal.iddaa.iddaaDb.models.matchdetail.MatchDetail;

@Entity
@Table(name = "sporToto")
public class SporToto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int weekNumber;

	@OneToOne(cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = { "season", "underOver", "goalRange", "bothTeamToScore", "doubleChance" })
	private MatchDetail matchDetail;

	public SporToto() {
		super();
	}

	public SporToto(Long id, int weekNumber, MatchDetail matchDetail) {
		super();
		this.id = id;
		this.weekNumber = weekNumber;
		this.matchDetail = matchDetail;
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

	public MatchDetail getMatchDetail() {
		return matchDetail;
	}

	public void setMatchDetail(MatchDetail matchDetail) {
		this.matchDetail = matchDetail;
	}

}
