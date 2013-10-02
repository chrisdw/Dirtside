package uk.org.downesward.dirtside.domain;

import uk.org.downesward.dirtside.DatabaseHelper;
import android.content.Context;
import android.database.Cursor;

/**
 * Utilities for managing items
 * @author chrisdw
 *
 */
public class Utilities {
	public static final Integer RED = 1;
	public static final Integer GREEN = 2;
	public static final Integer YELLOW = 4;
	
	/**
	 * Given a weapon type and a fire control or guidance and a range; work out the dice
	 * to roll for an attack.
	 * 
	 * @param weaponType The type of weapon
	 * @param fireCon The rating of it's fire control
	 * @param guidance The guidance rating of self guiding ordinance
	 * @param range The range
	 * @param context A context
	 * @return A string descriptor of the dice - such as D6 or D8 or null.
	 */
	public static String rangeDie(String weaponType, String fireCon, String guidance, int range, Context context) {
		String item = null;
		DatabaseHelper dbh = new DatabaseHelper(context);
		Cursor res;
		if (weaponType.equals(Weapon.GUIDED_MISSILE)) {
			res = dbh.getDieForGuidance(guidance);
		}
		else {
			res = dbh.getDieForFireControlAndRange(fireCon, range);
		}
		if (res != null) {
			int colIndex = res.getColumnIndexOrThrow("Die");
			if (res.moveToNext()) {
				item = res.getString(colIndex);
			}
		}
		return item;
	}
	
	/**
	 * Convert a bit code represented as an integer into a string
	 * @param chits
	 * @return
	 */
	public static String chitLookup(Integer chits) {
		String chitString = "";
		
		if (chits == 7) {
			chitString = "ALL";
		} else {
			if ((chits & RED) == RED) {
				chitString += "R";
			}
			if ((chits & GREEN) == GREEN) {
				chitString += "G";
			}
			if ((chits & YELLOW) == YELLOW) {
				chitString += "Y";
			}			
		}
		
		return chitString;
	}
}
