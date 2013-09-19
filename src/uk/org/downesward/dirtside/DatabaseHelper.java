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
	
	public Cursor getArmour() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db.rawQuery(
				"SELECT ArmourTypeId, Description, Factor FROM Armour ORDER BY Description", null);
		return res;
	}	
	
	public Cursor getInfantry() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db.rawQuery(
				"SELECT InfantryId, Description FROM Infantry ORDER BY Description", null);
		return res;
	}	
	
	public Cursor getInfantry(Integer infantryId) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db.rawQuery(
				"SELECT InfantryId, Description FROM Infantry WHERE InfantryId = ?", new String[] {infantryId.toString()});
		return res;
	}	
}
