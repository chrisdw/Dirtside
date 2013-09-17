package uk.org.downesward.dirtside.domain;

public class Weapon {
	private String type;
	private String size;
	
	public Weapon(String type, String size) {
		this.type = type;
		this.size = size;
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
	
	public String getName() {
		return this.type + "/" + this.size;
	}
	
}
