package uk.org.downesward.dirtside;

import java.util.ArrayList;

import uk.org.downesward.dirtside.adapters.CampaignAdapter;
import uk.org.downesward.dirtside.domain.Campaign;
import android.os.Bundle;
import android.app.ListActivity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class CampaignsActivity extends ListActivity {
	
	private ArrayList<Campaign> mCampaigns;
	private CampaignAdapter campaignAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campaigns);
		
		DatabaseHelper dbh = new DatabaseHelper(this);

		Cursor campaigns = dbh.getCampaigns();
		mCampaigns = new ArrayList<Campaign>();

		while (campaigns.moveToNext()) {
			Campaign campaign = new Campaign(campaigns);
			mCampaigns.add(campaign);
		}
		campaignAdapter = new CampaignAdapter(this,
				android.R.layout.simple_spinner_item, mCampaigns);
		
		this.setListAdapter(campaignAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.campaigns, menu);
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Bundle b = new Bundle();
		Campaign item = (Campaign) getListAdapter().getItem(position);
		b.putInt("CampaignId", item.getCampaignId());
		// TODO: Launch a nationality activity for this campaign
	}

}
