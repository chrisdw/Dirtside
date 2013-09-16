package org.uk.downesward.dirtside;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {

	private static String DB_NAME = "toe";

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	public Cursor getWeapons() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db.rawQuery(
				"SELECT WeaponType FROM WeaponType ORDER BY WeaponType", null);
		return res;
	}
}
