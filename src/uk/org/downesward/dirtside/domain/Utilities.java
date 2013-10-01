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
	public static String RangeDie(String weaponType, String fireCon, String guidance, int range, Context context) {
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
}
