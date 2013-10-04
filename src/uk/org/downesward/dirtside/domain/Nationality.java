package uk.org.downesward.dirtside.domain;

import android.database.Cursor;

public class Nationality {
	private int nationalityId;
	private String description;

	public Nationality(Cursor cursor) {
		int colIndex;
		colIndex = cursor.getColumnIndex("NationalityId");
		if (colIndex != -1) {
			this.setNationalityId(cursor.getInt(colIndex));
		}
		colIndex = cursor.getColumnIndex("Description");
		if (colIndex != -1) {
			this.setDescription(cursor.getString(colIndex));
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(int campaignId) {
		this.nationalityId = campaignId;
	}

}
