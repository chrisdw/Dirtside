package uk.org.downesward.dirtside.domain;

public class Weapon {
	private String type;
	private String size;
	private String description;
	
	public Weapon(String type, String desription) {
		this.type = type;
		this.description = desription;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public String getLongName() {
		return this.type +  "(" + this.description + ")";
	}
	
	public String getName() {
		return this.type + "/" + this.size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
