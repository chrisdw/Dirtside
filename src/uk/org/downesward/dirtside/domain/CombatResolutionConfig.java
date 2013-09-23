package uk.org.downesward.dirtside.domain;

import java.io.Serializable;

public class CombatResolutionConfig implements Serializable {

	private String weaponType;
	private String weaponSize;
	private boolean moving;
	private int armourTypeId;
	private boolean infantry;
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

}
