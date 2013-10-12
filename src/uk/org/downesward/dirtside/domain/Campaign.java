package uk.org.downesward.dirtside.domain;

import android.database.Cursor;

public class Campaign {
	private int campaignId;
	private String description;
	private String notes;
	
	public Campaign(Cursor cursor) {
		int colIndex;
		colIndex = cursor.getColumnIndex("CampaignId");
		if (colIndex != -1) {
			this.setCampaignId(cursor.getInt(colIndex));
		}
		colIndex = cursor.getColumnIndex("Description");
		if (colIndex != -1) {
			this.setDescription(cursor.getString(colIndex));
		}
		colIndex = cursor.getColumnIndex("Notges");
		if (colIndex != -1) {
			this.setNotes(cursor.getString(colIndex));
		}		
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		
		if (this == other) return true;
		
		if (other instanceof Campaign) {
			return this.getCampaignId() == ((Campaign)other).getCampaignId();
		} else {
			return false;
		}
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
