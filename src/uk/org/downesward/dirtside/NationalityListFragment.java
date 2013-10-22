package uk.org.downesward.dirtside;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import uk.org.downesward.dirtside.adapters.NationalityAdapter;
import uk.org.downesward.dirtside.domain.Nationality;

/**
 * A list fragment representing a list of Nationalities. This fragment also
 * supports tablet devices by allowing list items to be given an 'activated'
 * state upon selection. This helps indicate which item is currently being
 * viewed in a {@link NationalityDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class NationalityListFragment extends ListFragment implements NationalitiesLister {
	
	private int campaignId;
	
	private ArrayList<Nationality> mNationalities;
	
	private NationalityAdapter nationalityAdapter;
	
	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private Callbacks mCallbacks = sDummyCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(String id, Integer campaignId);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id, Integer campaignId) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public NationalityListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNationalities = new ArrayList<Nationality>();
		
		DatabaseHelper dbh = new DatabaseHelper(this.getActivity());
		Cursor nationalities;
		if (this.getCampaignId() != 0) {
			nationalities = dbh.getNationalitiesForCampaign(this.getCampaignId());
		} else {
			nationalities = dbh.getNationalities();
		}
		mNationalities = new ArrayList<Nationality>();
		while (nationalities.moveToNext()) {
			Nationality nationality = new Nationality(nationalities);
			mNationalities.add(nationality);
		}
		nationalityAdapter = new NationalityAdapter(
				this.getActivity(), android.R.layout.simple_spinner_item,
				mNationalities);
		
		setListAdapter(nationalityAdapter);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		mCallbacks.onItemSelected(nationalityAdapter.getItem(position).getNationalityId().toString(), this.getCampaignId());
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}

	@Override
	public Integer getCampaignId() {
		return this.campaignId;
	}

	@Override
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
		DatabaseHelper dbh = new DatabaseHelper(this.getActivity());
		Cursor nationalities;
		if (this.getCampaignId() != 0) {
			nationalities = dbh.getNationalitiesForCampaign(this.getCampaignId());
		} else {
			nationalities = dbh.getNationalities();
		}
		nationalityAdapter.clear();
		while (nationalities.moveToNext()) {
			Nationality nationality = new Nationality(nationalities);
			nationalityAdapter.add(nationality);
		}
		nationalityAdapter.notifyDataSetChanged();
	}
}
