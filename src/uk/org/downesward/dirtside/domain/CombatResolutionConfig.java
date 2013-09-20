package uk.org.downesward.dirtside.domain;

import java.io.Serializable;

public class CombatResolutionConfig implements Serializable {

	private boolean moving;
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

}
