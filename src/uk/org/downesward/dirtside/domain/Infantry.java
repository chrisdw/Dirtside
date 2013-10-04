package uk.org.downesward.dirtside.domain;

import android.database.Cursor;

/**
 * Model an infantry team.
 * 
 * @author chrisdw
 * 
 */
public class Infantry {
	private Integer infantryId;
	private String description;
	private Integer size;
	private Integer campaignId;
	private Integer nationalityId;
	private String notes;
    private float cost;
    private Integer personnelCount;
    
	public Infantry() {
	}

	public Infantry(Cursor cursor) {
		int columnIndex;

		columnIndex = cursor.getColumnIndex("InfantryId");
		if (columnIndex != -1) {
			setInfantryId(cursor.getInt(columnIndex));
		}
		columnIndex = cursor.getColumnIndex("Description");
		if (columnIndex != -1) {
			setDescription(cursor.getString(columnIndex));
		} else {
			setDescription("");
		}
		columnIndex = cursor.getColumnIndex("Size");
		if (columnIndex != -1) {
			setSize(cursor.getInt(columnIndex));
		}
		columnIndex = cursor.getColumnIndex("CampaignId");
		if (columnIndex != -1) {
			setCampaignId(cursor.getInt(columnIndex));
		}
		// Annoying DB inconsistency
		columnIndex = cursor.getColumnIndex("Nationality");
		if (columnIndex != -1) {
			setNationalityId(cursor.getInt(columnIndex));
		}
		columnIndex = cursor.getColumnIndex("Notes");
		if (columnIndex != -1) {
			setNotes(cursor.getString(columnIndex));
		} else {
			setNotes("");
		}
		columnIndex = cursor.getColumnIndex("Cost");
		if (columnIndex != -1) {
			setCost(cursor.getFloat(columnIndex));
		}
		columnIndex = cursor.getColumnIndex("PersonnelCount");
		if (columnIndex != -1) {
			setPersonnelCount(cursor.getInt(columnIndex));
		}		
	}

	public Integer getInfantryId() {
		return infantryId;
	}

	public void setInfantryId(Integer infantryId) {
		this.infantryId = infantryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	public Integer getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(Integer nationalityId) {
		this.nationalityId = nationalityId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Integer getPersonnelCount() {
		return personnelCount;
	}

	public void setPersonnelCount(Integer personnelCount) {
		this.personnelCount = personnelCount;
	}

}
