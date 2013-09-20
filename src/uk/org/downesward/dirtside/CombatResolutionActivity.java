package uk.org.downesward.dirtside;

import uk.org.downesward.dirtside.R;
import uk.org.downesward.dirtside.domain.CombatResolutionConfig;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void resolveSpillover(CombatResolutionConfig config, Integer range,
			Integer probability) {
		// TODO Auto-generated method stub
		
	}
}
