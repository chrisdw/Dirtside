package uk.org.downesward.dirtside.domain;

import android.database.Cursor;

public class InfantryFirepower {
	private int infantryFPId;
	private String description;
	private int cost;
	private int chits;
	
	public InfantryFirepower(Cursor cursor) {
		int columnIndex;
		columnIndex = cursor.getColumnIndex("InfantryFPId");
		if (columnIndex != -1) {
			setInfantryFPId(cursor.getInt(columnIndex));
		}		
		columnIndex = cursor.getColumnIndex("Description");
		if (columnIndex != -1) {
			setDescription(cursor.getString(columnIndex));
		} else {
			setDescription("");
		}
		columnIndex = cursor.getColumnIndex("Cost");
		if (columnIndex != -1) {
			setCost(cursor.getInt(columnIndex));
		}
		columnIndex = cursor.getColumnIndex("Chrits");
		if (columnIndex != -1) {
			setChits(cursor.getInt(columnIndex));
		}		
	}
	
	public Integer getInfantryFPId() {
		return infantryFPId;
	}
	public void setInfantryFPId(Integer infantryFPId) {
		this.infantryFPId = infantryFPId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public Integer getChits() {
		return chits;
	}
	public void setChits(Integer chits) {
		this.chits = chits;
	}
}
