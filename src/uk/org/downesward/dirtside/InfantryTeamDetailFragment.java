package uk.org.downesward.dirtside;

import java.util.ArrayList;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import uk.org.downesward.dirtside.R;

import uk.org.downesward.dirtside.adapters.CampaignAdapter;
import uk.org.downesward.dirtside.adapters.NationalityAdapter;
import uk.org.downesward.dirtside.domain.Infantry;
import uk.org.downesward.dirtside.domain.Campaign;
import uk.org.downesward.dirtside.domain.Nationality;

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
				Infantry inf = new Infantry(cur);
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
			((TextView) rootView.findViewById(R.id.txtInfantryDescription))
					.setText(mItem.getDescription());
			
			((TextView) rootView.findViewById(R.id.txtInfantryNotes))
			.setText(mItem.getNotes());
			
			((TextView) rootView.findViewById(R.id.txtInfantryCost))
			.setText(mItem.getCost().toString());
			
			((TextView) rootView.findViewById(R.id.txtInfantryPersonnelCount))
			.setText(mItem.getPersonnelCount().toString());			
			
			DatabaseHelper dbh = new DatabaseHelper(this.getActivity());
			
			// Campaigns
			Cursor campaigns = dbh.getCampaigns();
			ArrayList<Campaign> mCampaigns = new ArrayList<Campaign>();
			Campaign thisCampaign = null;
					
			while (campaigns.moveToNext()) {
				Campaign campaign = new Campaign(campaigns);
				if (campaign.getCampaignId() == mItem.getCampaignId()) {
					thisCampaign = campaign;
				}
				mCampaigns.add(campaign);
			}
			Spinner spinner = (Spinner) rootView.findViewById(R.id.spnCampaign);
			CampaignAdapter campaignAdapter = new CampaignAdapter(this.getActivity(),
					android.R.layout.simple_spinner_item, mCampaigns);	

			spinner.setAdapter(campaignAdapter);
			
			int spinnerPosition = campaignAdapter.getPosition(thisCampaign);
			spinner.setSelection(spinnerPosition);
			
			// Nationality
			Cursor nationalities = dbh.getNationalities();
			ArrayList<Nationality> mNationalities = new ArrayList<Nationality>();
			Nationality thisNationality = null;		
					
			while (nationalities.moveToNext()) {
				Nationality nationality = new Nationality(nationalities);
				if (nationality.getNationalityId() == mItem.getNationalityId()) {
					thisNationality = nationality;
				}
				mNationalities.add(nationality);
			}
			spinner = (Spinner) rootView.findViewById(R.id.spnNationality);
			NationalityAdapter nationalityAdapter = new NationalityAdapter(this.getActivity(),
					android.R.layout.simple_spinner_item, mNationalities);	
			
			spinner.setAdapter(nationalityAdapter);
			
			spinnerPosition = nationalityAdapter.getPosition(thisNationality);
			spinner.setSelection(spinnerPosition);			
			
		}

		return rootView;
	}
}
