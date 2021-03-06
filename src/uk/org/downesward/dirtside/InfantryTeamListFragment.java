package uk.org.downesward.dirtside;

import java.util.ArrayList;

import uk.org.downesward.dirtside.adapters.InfantryAdapter;
import uk.org.downesward.dirtside.domain.Infantry;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

/**
 * A list fragment representing a list of InfantryTeams. This fragment also
 * supports tablet devices by allowing list items to be given an 'activated'
 * state upon selection. This helps indicate which item is currently being
 * viewed in a {@link InfantryTeamDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class InfantryTeamListFragment extends ListFragment 
	implements CampaignNationalityFiltered {

	private ArrayList<Infantry> infantryTeams;
	private InfantryAdapter infantryAdapter;
	
	private int nationalityId;
	private int campaignId;
	
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
		public void onItemSelected(String id);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public InfantryTeamListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DatabaseHelper dbh = new DatabaseHelper(this.getActivity());
		Cursor teams = dbh.getInfantry();
		infantryTeams = new ArrayList<Infantry>();
		
		while (teams.moveToNext()) {
			Infantry team = new Infantry(teams);
			infantryTeams.add(team);
		}
		infantryAdapter = new InfantryAdapter(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				infantryTeams);
		
		setListAdapter(infantryAdapter);
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
		mCallbacks.onItemSelected(infantryAdapter.getItem(position).getInfantryId().toString());
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
		return campaignId;
	}

	@Override
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
		refreshList(this.getCampaignId(), this.getNationalityId());
	}

	@Override
	public Integer getNationalityId() {
		return nationalityId;
	}

	@Override
	public void setNationalityId(Integer nationalityId) {
		this.nationalityId = nationalityId;
		refreshList(this.getCampaignId(), this.getNationalityId());
	}	
	
	private void refreshList(Integer campaignId, Integer nationalityId) {
		if (campaignId != 0 && nationalityId != 0) {
			infantryAdapter.clear();
			DatabaseHelper dbh = new DatabaseHelper(this.getActivity());
			Cursor teams = dbh.getInfantryForCampaignNationality(campaignId, nationalityId);
			while (teams.moveToNext()) {
				Infantry team = new Infantry(teams);
				infantryAdapter.add(team);
			}			
			infantryAdapter.notifyDataSetChanged();
		}
	}
}
