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
		Cursor res = db
				.rawQuery(
						"SELECT WeaponType, Description FROM WeaponType ORDER BY WeaponType",
						null);
		return res;
	}

	public Cursor getWeaponSizes(String weaponType) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db
				.rawQuery(
						"SELECT WeaponSize FROM WeaponTypeSize WHERE WeaponType = ? ORDER BY WeaponSize",
						new String[] { weaponType });
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
		Cursor res = db
				.rawQuery(
						"SELECT ArmourTypeId, Description, Factor FROM Armour ORDER BY ArmourTypeId",
						null);
		return res;
	}

	public Cursor getArmour(Integer armourTypeId) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db
				.rawQuery(
						"SELECT ArmourTypeId, Description, Factor, ShortCode, Biological FROM Armour WHERE ArmourTypeId = ?",
						new String[] { armourTypeId.toString() });
		return res;
	}

	public Cursor getInfantry() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db
				.rawQuery(
						"SELECT InfantryId, Description FROM Infantry ORDER BY Description",
						null);
		return res;
	}

	public Cursor getInfantry(Integer infantryId) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db
				.rawQuery(
						"SELECT InfantryId, Description, Size, CampaignId, Nationality, Notes, PersonnelCount, Cost FROM Infantry WHERE InfantryId = ?",
						new String[] { infantryId.toString() });
		return res;
	}

	public Cursor getECM() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db.rawQuery("SELECT Rating FROM ECM ORDER BY Cost", null);
		return res;
	}

	public Cursor getDiceForECM(String ecmRating) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db.rawQuery("SELECT Die FROM ECM WHERE Rating = ?",
				new String[] { ecmRating });
		return res;
	}

	public Cursor getSignatureForSize(String size) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db.rawQuery(
				"SELECT Signature FROM VehicleSize WHERE Size = ?",
				new String[] { size });
		return res;
	}

	public Cursor getRanges() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db.rawQuery("SELECT RangeId FROM Range ORDER BY RangeId",
				null);
		return res;
	}

	public Cursor getRangeForWeaponTypeRangeBand(String weaponTypeId,
			String weaponSizeId, String rangeBand) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db
				.rawQuery(
						"SELECT Range FROM WeaponTypeSizeRange WHERE WeaponTypeID = ? AND WeaponSizeId = ? AND RangeBand = ?",
						new String[] { weaponTypeId, weaponSizeId, rangeBand });
		return res;
	}

	public Cursor getChitsForWeaponTypeRange(String weaponType, String rangeId) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db
				.rawQuery(
						"SELECT Ablative, Reactive, Normal, Infantry, Factor FROM WeaponTypeRangeChits WHERE WeaponType = ? AND RangeId = ?",
						new String[] { weaponType, rangeId });
		return res;
	}

	public Cursor getDieForGuidance(String guidance) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db.rawQuery(
				"SELECT Die FROM MissileGuidance WHERE Guidance = ?",
				new String[] { guidance });
		return res;
	}

	public Cursor getDieForFireControlAndRange(String fireContol,
			Integer rangeId) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db
				.rawQuery(
						"SELECT Die FROM FireControlRange WHERE FireControl = ? AND RangeId = ?",
						new String[] { fireContol, rangeId.toString() });
		return res;
	}

	public Cursor getSizeDetails(Integer unitSizeId) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db
				.rawQuery(
						"SELECT Symbol, Description FROM UnitSizes WHERE UnitSizeId = ?",
						new String[] { unitSizeId.toString() });
		return res;
	}

	public Cursor getCampaigns() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db
				.rawQuery(
						"SELECT CampaignId, Description, Notes FROM Campaign ORDER BY Description",
						null);
		return res;
	}

	public Cursor getNationalities() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor res = db
				.rawQuery(
						"SELECT NationalityId, Description FROM Nationality ORDER BY Description",
						null);
		return res;
	}
}
