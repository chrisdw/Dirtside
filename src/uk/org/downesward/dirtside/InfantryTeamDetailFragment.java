package uk.org.downesward.dirtside;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import uk.org.downesward.dirtside.R;

import uk.org.downesward.dirtside.domain.Infantry;

/**
 * A fragment representing a single InfantryTeam detail screen. This fragment is
 * either contained in a {@link InfantryTeamListActivity} in two-pane mode (on
 * tablets) or a {@link InfantryTeamDetailActivity} on handsets.
 */
public class InfantryTeamDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private Infantry mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public InfantryTeamDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {

			Integer infantryId = Integer.parseInt(getArguments().getString(
					ARG_ITEM_ID));
			DatabaseHelper dbh = new DatabaseHelper(this.getActivity());
			Cursor cur = dbh.getInfantry(infantryId);
			if (cur.moveToNext()) {
				Infantry inf = new Infantry();
				inf.setInfantryId(cur.getInt(cur.getColumnIndex("InfantryId")));
				inf.setDescription(cur.getString(cur.getColumnIndex("Description")));
				mItem = inf;
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_infantryteam_detail,
				container, false);

		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.infantryteam_detail))
					.setText(mItem.getDescription());
		}

		return rootView;
	}
}
