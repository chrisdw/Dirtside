package uk.org.downesward.dirtside.domain;

import uk.org.downesward.dirtside.DatabaseHelper;
import android.content.Context;
import android.database.Cursor;

public class Utilities {
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
