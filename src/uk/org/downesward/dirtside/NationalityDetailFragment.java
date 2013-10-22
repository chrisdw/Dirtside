package uk.org.downesward.dirtside;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import uk.org.downesward.dirtside.domain.Nationality;

/**
 * A fragment representing a single Nationality detail screen. This fragment is
 * either contained in a {@link NationalityListActivity} in two-pane mode (on
 * tablets) or a {@link NationalityDetailActivity} on handsets.
 */
public class NationalityDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private Nationality mItem;

	private int campaignId;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public NationalityDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			Integer nationalityId = Integer.parseInt(getArguments().getString(
					ARG_ITEM_ID));
			DatabaseHelper dbh = new DatabaseHelper(this.getActivity());
			Cursor cur = dbh.getNationality(nationalityId);
			if (cur.moveToNext()) {
				Nationality inf = new Nationality(cur);
				mItem = inf;
			}
		}
		if (getArguments().containsKey(Constants.CAMPAIGN_ID)) {
			campaignId = getArguments().getInt(Constants.CAMPAIGN_ID);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_nationality_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.txtNationalityDescription))
					.setText(mItem.getDescription());

			// Grab the infantry list fragment
			CampaignNationalityFiltered inf = (CampaignNationalityFiltered) this.getFragmentManager()
					.findFragmentById(R.id.national_infantryteam_list);
			if (inf != null) {
				inf.setCampaignId(campaignId);
				inf.setNationalityId(mItem.getNationalityId());
			}
		}

		return rootView;
	}
}
