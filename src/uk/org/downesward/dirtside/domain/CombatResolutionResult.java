package uk.org.downesward.dirtside.domain;

import java.io.Serializable;

public class CombatResolutionResult implements Serializable {

	private String outcome;
	private String dieRolls;
	private String chits;
	private Integer state;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -43841248176896931L;
	
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getDieRolls() {
		return dieRolls;
	}
	public void setDieRolls(String dieRolls) {
		this.dieRolls = dieRolls;
	}
	public String getChits() {
		return chits;
	}
	public void setChits(String chits) {
		this.chits = chits;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

}
