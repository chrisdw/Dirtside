package uk.org.downesward.dirtside.domain;

import android.content.Context;
import android.database.Cursor;
import uk.org.downesward.dirtside.DatabaseHelper;

public class Weapon {
	private String type;
	private String size;
	private String description;
	
	public Weapon(String type, String desription) {
		this.type = type;
		this.description = desription;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public String getLongName() {
		return this.type +  "(" + this.description + ")";
	}
	
	public String getName() {
		return this.type + "/" + this.size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Convert a range in MU's into a range band
	 * @param range Range in MU's
	 * @return Range band the range falls in, -1 represents 
	 * out of range
	 */
	public Integer Range(Integer range, Context context) {
		Integer whichRange = -1;
		DatabaseHelper dbh = new DatabaseHelper(context);
		Cursor ranges = dbh.getRanges();
		int rangeIdCol = ranges.getColumnIndex("RangeId");
		while (ranges.moveToNext()) {
			Cursor bands = dbh.getRangeForWeaponTypeRangeBand(this.type, this.size, ranges.getString(rangeIdCol));
			int rangeCol = bands.getColumnIndex("Range");
			while (bands.moveToNext()) {
				if (range <= bands.getInt(rangeCol)) {
					whichRange = Integer.parseInt(ranges.getString(rangeIdCol));
					break;
				}
			}
		}
		return whichRange;
	}
	
}
