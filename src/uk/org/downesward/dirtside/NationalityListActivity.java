package uk.org.downesward.dirtside;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * An activity representing a list of Nationalities. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link NationalityDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link NationalityListFragment} and the item details (if present) is a
 * {@link NationalityDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link NationalityListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class NationalityListActivity extends FragmentActivity implements
		NationalityListFragment.Callbacks {

	private int campaignId;
	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nationality_list);

		Bundle b = getIntent().getExtras();
		if (b != null && b.containsKey("CampaignId")) {
			campaignId = b.getInt("CampaignId");
		}
		if (findViewById(R.id.nationality_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((NationalityListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.nationality_list))
					.setActivateOnItemClick(true);

		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	/**
	 * Callback method from {@link NationalityListFragment.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(NationalityDetailFragment.ARG_ITEM_ID, id);
			NationalityDetailFragment fragment = new NationalityDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.nationality_detail_container, fragment)
					.commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this,
					NationalityDetailActivity.class);
			detailIntent.putExtra(NationalityDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
