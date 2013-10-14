package uk.org.downesward.dirtside.domain;

import android.database.Cursor;

public class InfantryRange {
	private int infantryRangeId;
	private String description;
	private float cost;
	private int range;
	
	public InfantryRange(Cursor cursor) {
		int columnIndex;
		columnIndex = cursor.getColumnIndex("InfantryRangeId");
		if (columnIndex != -1) {
			setInfantryRangeId(cursor.getInt(columnIndex));
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
		columnIndex = cursor.getColumnIndex("Range");
		if (columnIndex != -1) {
			setRange(cursor.getInt(columnIndex));
		}		
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		
		if (this == other) return true;
		
		if (other instanceof InfantryRange) {
			return this.getInfantryRangeId() == ((InfantryRange)other).getInfantryRangeId();
		} else {
			return false;
		}
	}	
	
	public Integer getInfantryRangeId() {
		return infantryRangeId;
	}

	public void setInfantryRangeId(Integer infantryRangeId) {
		this.infantryRangeId = infantryRangeId;
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

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
		
}
