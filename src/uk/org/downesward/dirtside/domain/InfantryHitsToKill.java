package uk.org.downesward.dirtside.domain;

import android.database.Cursor;

public class InfantryHitsToKill {
	private int infantryHTKId;
	private String description;
	private float cost;
	private int htk;
	
	public InfantryHitsToKill(Cursor cursor) {
		int columnIndex;
		columnIndex = cursor.getColumnIndex("InfantryHTKId");
		if (columnIndex != -1) {
			setInfantryHTKId(cursor.getInt(columnIndex));
		}		
		columnIndex = cursor.getColumnIndex("Description");
		if (columnIndex != -1) {
			setDescription(cursor.getString(columnIndex));
		} else {
			setDescription("");
		}
		columnIndex = cursor.getColumnIndex("Cost");
		if (columnIndex != -1) {
			setCost(cursor.getFloat(columnIndex));
		}
		columnIndex = cursor.getColumnIndex("HTK");
		if (columnIndex != -1) {
			setHTK(cursor.getInt(columnIndex));
		}		
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		
		if (this == other) return true;
		
		if (other instanceof InfantryHitsToKill) {
			return this.getInfantryHTKId() == ((InfantryHitsToKill)other).getInfantryHTKId();
		} else {
			return false;
		}
	}	
	
	public Integer getInfantryHTKId() {
		return infantryHTKId;
	}

	public void setInfantryHTKId(Integer infantryHTKId) {
		this.infantryHTKId = infantryHTKId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getHTK() {
		return htk;
	}

	public void setHTK(int htk) {
		this.htk = htk;
	}
	
}
