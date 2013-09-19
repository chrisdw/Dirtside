package uk.org.downesward.dirtside;

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
				"SELECT WeaponType, Description FROM WeaponType ORDER BY WeaponType", null);
		return res;
	}
	
	public Cursor getWeaponSizes(String weaponType) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db.rawQuery(
				"SELECT WeaponSize FROM WeaponTypeSize WHERE WeaponType = ?", new String[] {weaponType});
		return res;
	}
	
	public Cursor getFireControl() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db.rawQuery(
				"SELECT FireControl FROM FireControl ORDER BY Points", null);
		return res;
	}
}
