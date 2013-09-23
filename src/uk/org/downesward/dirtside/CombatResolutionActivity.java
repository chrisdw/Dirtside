package uk.org.downesward.dirtside;

import uk.org.downesward.dirtside.R;
import uk.org.downesward.dirtside.domain.CombatResolutionConfig;
import uk.org.downesward.dirtside.domain.Weapon;

import android.app.Activity;
import android.os.Bundle;

public class CombatResolutionActivity extends Activity implements CombatResolutionConfigFragment.ResolveCombat {
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.combatresolution);

	}

	@Override
	public Integer resolveNormalCombat(CombatResolutionConfig config) {
		Weapon weapon = new Weapon(config.getWeaponType(), "");
		weapon.setSize(config.getWeaponSize());
		Integer weaponType = 0;
		
		if (weapon.getType().equals("IAVR")) {
			weaponType = 1;
		}
		else if (weapon.getType().equals("APSW")) {
			weaponType = 2;
		}
		
		Integer whichRange = weapon.Range(config.getRange(), this);
		if (whichRange > 0) {
			// High signature targets are treated as being closer
			if (config.getTargetSize() == 6) {
				whichRange--;
			}
			else if (config.getTargetSize() == 7) {
				whichRange -= 2;
			}
			if (whichRange < 1) {
				whichRange = 1;
			}
		}
		return 0;
	}

	@Override
	public void resolveSpillover(CombatResolutionConfig config, Integer range,
			Integer probability) {
		// TODO Auto-generated method stub
		
	}
}
