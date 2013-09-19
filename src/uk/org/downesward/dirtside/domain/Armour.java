package uk.org.downesward.dirtside.domain;

public class Armour {
	private int armourTypeId;
	private String description;
	private float factor;
	
	public int getArmourTypeId() {
		return armourTypeId;
	}
	public void setArmourTypeId(int armoutTypeId) {
		this.armourTypeId = armoutTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getFactor() {
		return factor;
	}
	public void setFactor(float factor) {
		this.factor = factor;
	}
	
}
