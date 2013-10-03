package uk.org.downesward.dirtside.domain;

import android.content.Context;
import android.database.Cursor;
import uk.org.downesward.dirtside.DatabaseHelper;

/**
 * Model a weapon by type and size.
 * 
 * @author chrisdw
 * 
 */
public class Weapon {
	private String type;
	private String size;
	private String description;
	private boolean energyUsing;
	private Integer points;
	private boolean biological;
	
	/**
	 * These weapons have special characteristics, that trigger logic flow
	 * changes
	 */
	public static final String GUIDED_MISSILE = "GMS";
	public static final String HIGH_VELOCITY_MISSILE = "HVMS";
	public static final String SLAM = "SLAM";
	public static final String IAVR = "IAVR";
	public static final String APSW = "APSW";
	public static final String HIGH_ENERGY_LASER = "HEL";
	public static final String POWER_GUN = "PNG";

	public class ChitConfig {
		public String chits;
		public Float factor;
	}

	public Weapon(Cursor cursor) {
		int colIndex;
		colIndex = cursor.getColumnIndex("WeaponType");
		if (colIndex != -1) {
			this.setType(cursor.getString(colIndex));
		}
		colIndex = cursor.getColumnIndex("WeaponSize");
		if (colIndex != -1) {
			this.setSize(cursor.getString(colIndex));
		}			
		colIndex = cursor.getColumnIndex("Description");
		if (colIndex != -1) {
			this.setDescription(cursor.getString(colIndex));
		}
		colIndex = cursor.getColumnIndex("Biological");
		if (colIndex != -1) {
			this.setBiological(cursor.getInt(colIndex) == 1);
		}
		colIndex = cursor.getColumnIndex("EnergyUsing");
		if (colIndex != -1) {
			this.setEnergyUsing(cursor.getInt(colIndex) == 1);
		}
		colIndex = cursor.getColumnIndex("Points");
		if (colIndex != -1) {
			this.setPoints(cursor.getInt(colIndex));
		}		
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
		return this.type + "(" + this.description + ")";
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

	public boolean isEnergyUsing() {
		return energyUsing;
	}

	public void setEnergyUsing(boolean energyUsing) {
		this.energyUsing = energyUsing;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public boolean isBiological() {
		return biological;
	}

	public void setBiological(boolean biological) {
		this.biological = biological;
	}

	/**
	 * Convert a range in MU's into a range band
	 * 
	 * @param range
	 *            Range in MU's
	 * @return Range band the range falls in, -1 represents out of range
	 */
	public Integer range(Integer range, Context context) {
		Integer whichRange = -1;
		DatabaseHelper dbh = new DatabaseHelper(context);
		Cursor ranges = dbh.getRanges();
		int rangeIdCol = ranges.getColumnIndex("RangeId");
		while (ranges.moveToNext()) {
			Cursor bands = dbh.getRangeForWeaponTypeRangeBand(this.type,
					this.size, ranges.getString(rangeIdCol));
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
	 * 
	 * @return
	 */
	public Integer effectiveSize() {
		if (this.type.equals(GUIDED_MISSILE)
				|| this.type.equals(HIGH_VELOCITY_MISSILE)) {
			if (this.size.equals("L")) {
				return 3;
			} else {
				return 5;
			}
		} else {
			return Integer.parseInt(this.size);
		}
	}

	/**
	 * Work out which chits are valid when calculating damage using this weapon
	 * 
	 * @param range
	 * @param armour
	 * @param context
	 * @return
	 */
	public ChitConfig validChits(Integer range, Integer armour, Context context) {
		ChitConfig config = new ChitConfig();

		DatabaseHelper dbh = new DatabaseHelper(context);
		Cursor chits = dbh.getChitsForWeaponTypeRange(this.type,
				range.toString());
		if (chits.moveToNext()) {
			config.chits = this.getChits(chits, armour);
			if (armour == 4) {
				// Iridium armour reduces a power gun's factor to 1.0
				if (this.type.equals(POWER_GUN)) {
					config.factor = 1.0f;
				} else {
					config.factor = chits.getFloat(chits
							.getColumnIndex("Factor"));
				}
			} else {
				config.factor = chits.getFloat(chits.getColumnIndex("Factor"));
			}
		}
		return config;
	}

	private String getChits(Cursor chits, Integer armour) {
		String validChits = "";

		switch (armour) {
		case -1: // Infantry
			validChits = Utilities.chitLookup(chits.getInt(chits
					.getColumnIndex("Infantry")));
			break;
		case 1:
		case 4:
		case 5:
			validChits = Utilities.chitLookup(chits.getInt(chits
					.getColumnIndex("Normal")));
			break;
		case 2:
		case 6:
			validChits = Utilities.chitLookup(chits.getInt(chits
					.getColumnIndex("Ablative")));
			break;
		case 3:
			validChits = Utilities.chitLookup(chits.getInt(chits
					.getColumnIndex("Reactive")));
			break;
		case 7:
			if (this.type.equals(GUIDED_MISSILE)) {
				validChits = Utilities.chitLookup(chits.getInt(chits
						.getColumnIndex("Reactive")));
			} else if (this.type.equals(HIGH_ENERGY_LASER)) {
				validChits = Utilities.chitLookup(chits.getInt(chits
						.getColumnIndex("Ablative")));
			} else {
				validChits = Utilities.chitLookup(chits.getInt(chits
						.getColumnIndex("Normal")));
			}
			break;
		default:
			validChits = Utilities.chitLookup(chits.getInt(chits
					.getColumnIndex("Normal")));
			break;
		}
		return validChits;
	}
}
