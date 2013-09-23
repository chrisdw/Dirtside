package uk.org.downesward.dirtside.domain;

import java.io.Serializable;

public class CombatResolutionConfig implements Serializable {

	private String weaponType;
	private String weaponSize;
	private boolean moving;
	private int armourTypeId;
	private boolean infantry;
	private String fireControl;
	private Integer armourRating;
	private Integer targetSize;
	private Integer htk;
	private Integer targetState;
	private String ecm;
	private String pds;
	private String ads;
	private Integer range;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1862013591562316178L;
	
	public boolean isMoving() {
		return moving;
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	public String getWeaponType() {
		return weaponType;
	}
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
	public String getWeaponSize() {
		return weaponSize;
	}
	public void setWeaponSize(String weaponSize) {
		this.weaponSize = weaponSize;
	}
	public int getArmourTypeId() {
		return armourTypeId;
	}
	public void setArmourTypeId(int armourTypeId) {
		this.armourTypeId = armourTypeId;
	}
	public boolean isInfantry() {
		return infantry;
	}
	public void setInfantry(boolean infantry) {
		this.infantry = infantry;
	}
	public String getFireControl() {
		return fireControl;
	}
	public void setFireControl(String fireControl) {
		this.fireControl = fireControl;
	}
	public Integer getArmourRating() {
		return armourRating;
	}
	public void setArmourRating(Integer armourRating) {
		this.armourRating = armourRating;
	}
	public Integer getTargetSize() {
		return targetSize;
	}
	public void setTargetSize(Integer targetSize) {
		this.targetSize = targetSize;
	}
	public Integer getHtk() {
		return htk;
	}
	public void setHtk(Integer htk) {
		this.htk = htk;
	}
	public Integer getTargetState() {
		return targetState;
	}
	public void setTargetState(Integer targetState) {
		this.targetState = targetState;
	}
	public String getEcm() {
		return ecm;
	}
	public void setEcm(String ecm) {
		this.ecm = ecm;
	}
	public String getPds() {
		return pds;
	}
	public void setPds(String pds) {
		this.pds = pds;
	}
	public String getAds() {
		return ads;
	}
	public void setAds(String ads) {
		this.ads = ads;
	}
	public Integer getRange() {
		return range;
	}
	public void setRange(Integer range) {
		this.range = range;
	}

}
