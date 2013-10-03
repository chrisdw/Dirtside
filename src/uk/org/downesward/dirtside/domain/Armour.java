package uk.org.downesward.dirtside.domain;

import android.database.Cursor;

/**
 * Model an armour type
 * @author chrisdw
 *
 */
public class Armour {
	private int armourTypeId;
	private String description;
	private float factor;
	private String shortCode;
	private boolean biological;
	
	public Armour(Cursor cursor) {
		int colIndex;
		colIndex = cursor.getColumnIndex("ArmourTypeId");
		if (colIndex != -1) {
			this.setArmourTypeId(cursor.getInt(colIndex));
		}
		colIndex = cursor.getColumnIndex("Description");
		if (colIndex != -1) {
			this.setDescription(cursor.getString(colIndex));
		}
		colIndex = cursor.getColumnIndex("ShortCode");
		if (colIndex != -1) {
			this.setShortCode(cursor.getString(colIndex));
		}
		colIndex = cursor.getColumnIndex("Factor");
		if (colIndex != -1) {
			this.setFactor(cursor.getFloat(colIndex));
		}	
		colIndex = cursor.getColumnIndex("Biological");
		if (colIndex != -1) {
			this.setBiological(cursor.getInt(colIndex) == 1);
		}			
	}
	public int getArmourTypeId() {
		return armourTypeId;
	}
	public void setArmourTypeId(int armoutTypeId) {
		this.armourTypeId = armoutTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getFactor() {
		return factor;
	}
	public void setFactor(float factor) {
		this.factor = factor;
	}
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	public boolean isBiological() {
		return biological;
	}
	public void setBiological(boolean biological) {
		this.biological = biological;
	}
	
}
