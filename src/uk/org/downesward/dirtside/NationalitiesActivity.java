package uk.org.downesward.dirtside;

import java.util.ArrayList;


import uk.org.downesward.dirtside.adapters.NationalityAdapter;
import uk.org.downesward.dirtside.domain.Nationality;
import android.os.Bundle;
import android.app.ListActivity;
import android.database.Cursor;
import android.view.Menu;

public class NationalitiesActivity extends ListActivity {

	private ArrayList<Nationality> mNationalities;
	private NationalityAdapter nationalityAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		DatabaseHelper dbh = new DatabaseHelper(this);
		Cursor nationalities;
		if (b != null && b.containsKey("CampaignId")) {
			nationalities = dbh.getNationalitiesForCampaign(b.getInt("CampaignId"));
		} else {
			nationalities = dbh.getNationalities();
		}
		mNationalities = new ArrayList<Nationality>();
		while (nationalities.moveToNext()) {
			Nationality nationality = new Nationality(nationalities);
			mNationalities.add(nationality);
		}
		nationalityAdapter = new NationalityAdapter(
				this, android.R.layout.simple_spinner_item,
				mNationalities);
		
		this.setListAdapter(nationalityAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nationalities, menu);
		return true;
	}

}
