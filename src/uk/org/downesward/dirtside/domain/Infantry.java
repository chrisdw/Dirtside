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
    private boolean artilleryObserver;
    private boolean engineering;
    private boolean flying;
    private boolean teleport;
    private boolean iavr;
    private boolean lad;
    private boolean biological;
    private int infantryMovementId;
    private int infantryFPId;
    private int infantryHTKId;
    private int infantryRangeId;
    private String weaponTypeId;
    private String weaponSizeId;
    private String weaponGuidanceId;
    
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
		columnIndex = cursor.getColumnIndex("ArtilleryObserver");
		if (columnIndex != -1) {
			setArtilleryObserver(cursor.getInt(columnIndex)==1);
		}
		columnIndex = cursor.getColumnIndex("Flying");
		if (columnIndex != -1) {
			setFlying(cursor.getInt(columnIndex)==1);
		}
		columnIndex = cursor.getColumnIndex("Engineering");
		if (columnIndex != -1) {
			setEngineering(cursor.getInt(columnIndex)==1);
		}
		columnIndex = cursor.getColumnIndex("Teleport");
		if (columnIndex != -1) {
			setTeleport(cursor.getInt(columnIndex)==1);
		}	
		columnIndex = cursor.getColumnIndex("LAD");
		if (columnIndex != -1) {
			setLAD(cursor.getInt(columnIndex)==1);
		}
		columnIndex = cursor.getColumnIndex("IAVR");
		if (columnIndex != -1) {
			setIAVR(cursor.getInt(columnIndex)==1);
		}
		columnIndex = cursor.getColumnIndex("Biological");
		if (columnIndex != -1) {
			setBiological(cursor.getInt(columnIndex)==1);
		}	
		columnIndex = cursor.getColumnIndex("InfantryMovementId");
		if (columnIndex != -1) {
			setInfantryMovementId(cursor.getInt(columnIndex));
		}
		columnIndex = cursor.getColumnIndex("InfantryHTKId");
		if (columnIndex != -1) {
			setInfantryHTKId(cursor.getInt(columnIndex));
		}
		columnIndex = cursor.getColumnIndex("InfantryRangeId");
		if (columnIndex != -1) {
			setInfantryRangeId(cursor.getInt(columnIndex));
		}			
		columnIndex = cursor.getColumnIndex("WeaponTypeId");
		if (columnIndex != -1) {
			setWeaponTypeId(cursor.getString(columnIndex));
		}
		columnIndex = cursor.getColumnIndex("WeaponSizeId");
		if (columnIndex != -1) {
			setWeaponSizeId(cursor.getString(columnIndex));
		}
		columnIndex = cursor.getColumnIndex("WeaponGuidanceId");
		if (columnIndex != -1) {
			setWeaponGuidanceId(cursor.getString(columnIndex));
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

	public boolean isArtilleryObserver() {
		return artilleryObserver;
	}

	public void setArtilleryObserver(boolean artilleryObserver) {
		this.artilleryObserver = artilleryObserver;
	}

	public boolean isEngineering() {
		return engineering;
	}

	public void setEngineering(boolean engineering) {
		this.engineering = engineering;
	}

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	}

	public boolean isTeleport() {
		return teleport;
	}

	public void setTeleport(boolean teleport) {
		this.teleport = teleport;
	}

	public boolean isIAVR() {
		return iavr;
	}

	public void setIAVR(boolean iavr) {
		this.iavr = iavr;
	}

	public boolean isLAD() {
		return lad;
	}

	public void setLAD(boolean lad) {
		this.lad = lad;
	}

	public boolean isBiological() {
		return biological;
	}

	public void setBiological(boolean biological) {
		this.biological = biological;
	}
	public Integer getInfantryMovementId() {
		return infantryMovementId;
	}
	public void setInfantryMovementId(Integer infantryMovementId) {
		this.infantryMovementId = infantryMovementId;
	}

	public Integer getInfantryFPId() {
		return infantryFPId;
	}

	public void setInfantryFPId(Integer infantryFPId) {
		this.infantryFPId = infantryFPId;
	}
	
	public Integer getInfantryHTKId() {
		return infantryHTKId;
	}

	public void setInfantryHTKId(Integer infantryHTKId) {
		this.infantryHTKId = infantryHTKId;
	}
	
	public Integer getInfantryRangeId() {
		return infantryRangeId;
	}

	public void setInfantryRangeId(Integer infantryRangeId) {
		this.infantryRangeId = infantryRangeId;
	}

	public String getWeaponTypeId() {
		return weaponTypeId;
	}

	public void setWeaponTypeId(String weaponTypeId) {
		this.weaponTypeId = weaponTypeId;
	}

	public String getWeaponSizeId() {
		return weaponSizeId;
	}

	public void setWeaponSizeId(String weaponSizeId) {
		this.weaponSizeId = weaponSizeId;
	}

	public String getWeaponGuidanceId() {
		return weaponGuidanceId;
	}

	public void setWeaponGuidanceId(String weaponGuidanceId) {
		this.weaponGuidanceId = weaponGuidanceId;
	}		
}
