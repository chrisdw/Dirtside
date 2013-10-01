package uk.org.downesward.dirtside.domain;

import android.content.Context;
import android.database.Cursor;
import uk.org.downesward.dirtside.DatabaseHelper;

/**
 * Model a weapon by type and size.
 * @author chrisdw
 *
 */
public class Weapon {
	private String type;
	private String size;
	private String description;
	
	/**
	 * These weapons have special characteristics, that trigger
	 * logic flow changes
	 */
	public static final String GUIDED_MISSILE = "GMS";
	public static final String HIGH_VELOCITY_MISSILE = "HVMS";
	public static final String SLAM = "SLAM";
	public static final String IAVR = "IAVR";
	public static final String APSW = "APSW";
	
	public class ChitConfig
	{
		public String chits;
		public Float factor;
	}
	
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
	public Integer range(Integer range, Context context) {
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
	
	/**
	 * Calculate the effective size for pulling chits
	 * @return
	 */
	public Integer effectiveSize() {
		if (this.type.equals(GUIDED_MISSILE) || this.type.equals(HIGH_VELOCITY_MISSILE)) {
			if (this.size.equals("L")) {
				return 3;
			}
			else {
				return 5;
			}
		} else {
			return Integer.parseInt(this.size);
		}
	}
	
	/**
	 * Work out which chits are valid when calculating damage using this
	 * weapon
	 * @param range
	 * @param armour
	 * @param context
	 * @return
	 */
	public ChitConfig validChits(Integer range, Integer armour, Context context) {
		ChitConfig config = new ChitConfig();
		// TODO: Add appropriate logic		
		return config;
	}
}
