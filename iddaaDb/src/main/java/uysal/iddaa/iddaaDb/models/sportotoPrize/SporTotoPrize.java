package uysal.iddaa.iddaaDb.models.sportotoPrize;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sporTotoPrize")
public class SporTotoPrize {

	@Id
	private int weekNumber;

	private int guessed_15;
	private int guessed_14;
	private int guessed_13;
	private int guessed_12;

	public SporTotoPrize() {
		super();
	}
	
	public SporTotoPrize(int weekNumber, int guessed_15, int guessed_14, int guessed_13, int guessed_12) {
		super();
		this.weekNumber = weekNumber;
		this.guessed_15 = guessed_15;
		this.guessed_14 = guessed_14;
		this.guessed_13 = guessed_13;
		this.guessed_12 = guessed_12;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	public int getGuessed_15() {
		return guessed_15;
	}

	public void setGuessed_15(int guessed_15) {
		this.guessed_15 = guessed_15;
	}

	public int getGuessed_14() {
		return guessed_14;
	}

	public void setGuessed_14(int guessed_14) {
		this.guessed_14 = guessed_14;
	}

	public int getGuessed_13() {
		return guessed_13;
	}

	public void setGuessed_13(int guessed_13) {
		this.guessed_13 = guessed_13;
	}

	public int getGuessed_12() {
		return guessed_12;
	}

	public void setGuessed_12(int guessed_12) {
		this.guessed_12 = guessed_12;
	}

}
