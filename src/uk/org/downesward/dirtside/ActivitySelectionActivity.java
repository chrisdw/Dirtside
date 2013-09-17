package uk.org.downesward.dirtside;

import uk.org.downesward.dirtside.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivitySelectionActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activityselection);
		Button butGen = (Button) this.findViewById(R.id.butResolveCombat);
		
		butGen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ActivitySelectionActivity.this,
						CombatResolutionActivity.class);
				startActivity(intent);
			}         
		});
		
		butGen = (Button) this.findViewById(R.id.butListInfantry);
		
		butGen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ActivitySelectionActivity.this,
						InfantryTeamListActivity.class);
				startActivity(intent);
			}         
		});		
	}
}
