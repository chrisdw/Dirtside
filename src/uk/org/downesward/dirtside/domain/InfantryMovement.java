package uk.org.downesward.dirtside.domain;

import android.database.Cursor;

public class InfantryMovement {
	private int infantryMovementId;
	private int movement;
	private int cost;
	private String description;
	private boolean HWAllowed;
	private boolean engineeringAllowed;
	
	public InfantryMovement(Cursor cursor) {
		int columnIndex;
		columnIndex = cursor.getColumnIndex("InfantryMovementId");
		if (columnIndex != -1) {
			setInfantryMovementId(cursor.getInt(columnIndex));
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
		columnIndex = cursor.getColumnIndex("Movement");
		if (columnIndex != -1) {
			setMovement(cursor.getInt(columnIndex));
		}
		columnIndex = cursor.getColumnIndex("HWAllowed");
		if (columnIndex != -1) {
			setHWAllowed(cursor.getInt(columnIndex) == 1);
		}	
		columnIndex = cursor.getColumnIndex("EngineeringAllowed");
		if (columnIndex != -1) {
			setEngineeringAllowed(cursor.getInt(columnIndex) == 1);
		}		
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		
		if (this == other) return true;
		
		if (other instanceof InfantryMovement) {
			return this.getInfantryMovementId() == ((InfantryMovement)other).getInfantryMovementId();
		} else {
			return false;
		}
	}
	
	public Integer getInfantryMovementId() {
		return infantryMovementId;
	}
	public void setInfantryMovementId(Integer infantryMovementId) {
		this.infantryMovementId = infantryMovementId;
	}
	public Integer getMovement() {
		return movement;
	}
	public void setMovement(Integer movement) {
		this.movement = movement;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isHWAllowed() {
		return HWAllowed;
	}
	public void setHWAllowed(boolean HWAllowed) {
		this.HWAllowed = HWAllowed;
	}
	public boolean isEngineeringAllowed() {
		return engineeringAllowed;
	}
	public void setEngineeringAllowed(boolean engineeringAllowed) {
		this.engineeringAllowed = engineeringAllowed;
	}
}
