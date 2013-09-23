package uk.org.downesward.dirtside;

import java.util.ArrayList;

import uk.org.downesward.dirtside.R;

import uk.org.downesward.dirtside.adapters.ArmourAdapter;
import uk.org.downesward.dirtside.adapters.WeaponAdapter;
import uk.org.downesward.dirtside.domain.Armour;
import uk.org.downesward.dirtside.domain.CombatResolutionConfig;
import uk.org.downesward.dirtside.domain.Weapon;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

public class CombatResolutionConfigFragment extends Fragment {

	public interface ResolveCombat {
		Integer resolveNormalCombat(CombatResolutionConfig config);
		void resolveSpillover(CombatResolutionConfig config, Integer range, Integer probability);
	}
	
	private final class WeaponTypeSelectedListner implements
			OnItemSelectedListener {
		private final DatabaseHelper dbh;

		private WeaponTypeSelectedListner(DatabaseHelper dbh) {
			this.dbh = dbh;
		}

		@Override
		public void onItemSelected(AdapterView<?> parentView,
				View selectedItemView, int position, long id) {
			weaponSize.clear();
			Weapon wp = weapons.get(position);
			Cursor wpn = dbh.getWeaponSizes(wp.getType());
			while (wpn.moveToNext()) {
				weaponSize.add((wpn.getString(0)));
			}
			weaponSize.notifyDataSetChanged();
			
			if (mslPanel != null) {
				if (wp.getType().equals("GMS") || wp.getType().equals("HVMS")) {
					mslPanel.setVisibility(View.VISIBLE);
					View pds = mslPanel.findViewById(R.id.tableRowPDS);
					View ads = mslPanel.findViewById(R.id.tableRowADS);
					if (pds != null && ads != null) {
						if (wp.getType().equals("HVMS")) {
							pds.setVisibility(View.GONE);
							ads.setVisibility(View.GONE);
						} else {
							pds.setVisibility(View.VISIBLE);
							ads.setVisibility(View.VISIBLE);
						}
					}
				}
				else {
					mslPanel.setVisibility(View.GONE);
				}
			}
			if (wp.getType().equals("SLAM")) {
				if (spilloverLong != null) {
					spilloverLong.setEnabled(true);
				}
				if (spilloverMedium != null) {
					spilloverMedium.setEnabled(true);
				}				
			}
			else {
				if (spilloverLong != null) {
					spilloverLong.setEnabled(false);
				}
				if (spilloverMedium != null) {
					spilloverMedium.setEnabled(false);
				}					
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parentView) {
			weaponSize.clear();
			weaponSize.notifyDataSetChanged();
		}
	}

	private ArrayAdapter<Weapon> weaponType;
	private ArrayAdapter<String> weaponSize;
	private ArrayAdapter<String> fireControl;
	private ArrayAdapter<Armour> armourType;
	private ArrayAdapter<CharSequence> targetState;
	private ArrayAdapter<Integer> armourRating;
	private ArrayAdapter<Integer> htk;
	private ArrayAdapter<String> ecm;
	private ArrayAdapter<CharSequence> pds;
	private ArrayAdapter<CharSequence> ads;
	private ArrayAdapter<Integer> targetSize;
	
	private ArrayList<Weapon> weapons;
	private ArrayList<Armour> armours;

	private View infPanel;
	private View armPanel;
	private View mslPanel;
	private MenuItem spilloverLong;
	private MenuItem spilloverMedium;
    private ResolveCombat resolver;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment

		View view = inflater.inflate(R.layout.combatresolutionconfig,
				container, false);

		final DatabaseHelper dbh = new DatabaseHelper(this.getActivity());
		Cursor wpn = dbh.getWeapons();

		weapons = new ArrayList<Weapon>();

		while (wpn.moveToNext()) {
			Weapon aWpn = new Weapon(wpn.getString(0), wpn.getString(1));
			weapons.add(aWpn);
		}

		weaponType = new WeaponAdapter(this.getActivity(),
				R.layout.weaponlistitem, weapons);

		Spinner spinner = (Spinner) view.findViewById(R.id.spnWeapon);
		spinner.setOnItemSelectedListener(new WeaponTypeSelectedListner(dbh));

		// Apply the adapter to the spinner
		spinner.setAdapter(weaponType);

		// Set up the weapon size spinner
		spinner = (Spinner) view.findViewById(R.id.spnWeaponSize);

		ArrayList<String> wpnSize = new ArrayList<String>();
		weaponSize = new ArrayAdapter<String>(this.getActivity(),
				android.R.layout.simple_list_item_1, wpnSize);
		weaponSize
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(weaponSize);

		// Set up the fire control spinner
		spinner = (Spinner) view.findViewById(R.id.spnFireControl);
		ArrayList<String> fireControls = new ArrayList<String>();

		Cursor fcs = dbh.getFireControl();
		while (fcs.moveToNext()) {
			fireControls.add(fcs.getString(0));
		}

		fireControl = new ArrayAdapter<String>(this.getActivity(),
				android.R.layout.simple_list_item_1, fireControls);
		fireControl
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(fireControl);

		// Set up the armour type
		spinner = (Spinner) view.findViewById(R.id.spnArmourType);
		Cursor armour = dbh.getArmour();
		armours = new ArrayList<Armour>();

		while (armour.moveToNext()) {
			Armour anArmour = new Armour();
			anArmour.setArmourTypeId(armour.getInt(0));
			anArmour.setDescription(armour.getString(1));
			anArmour.setFactor(armour.getFloat(1));

			armours.add(anArmour);
		}
		armourType = new ArmourAdapter(this.getActivity(),
				android.R.layout.simple_spinner_item, armours);
		// Apply the adapter to the spinner
		spinner.setAdapter(armourType);

		// Set up the armour size
		spinner = (Spinner) view.findViewById(R.id.spnArmourSize);
		Integer[] items = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		armourRating = new ArrayAdapter<Integer>(this.getActivity(),
				android.R.layout.simple_spinner_item, items);
		// Apply the adapter to the spinner
		spinner.setAdapter(armourRating);

		// Set up HTK
		spinner = (Spinner) view.findViewById(R.id.spnHTK);
		items = new Integer[] { 3, 4, 5, 6 };
		htk = new ArrayAdapter<Integer>(this.getActivity(),
				android.R.layout.simple_spinner_item, items);
		// Apply the adapter to the spinner
		spinner.setAdapter(htk);

		// Set up the target spinner used for working out the secondary die
		spinner = (Spinner) view.findViewById(R.id.spnTargetState);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		targetState = ArrayAdapter.createFromResource(this.getActivity(),
				R.array.valTargetState, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		targetState
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(targetState);

		// Set up the target size
		spinner = (Spinner) view.findViewById(R.id.spnTargetSize);
		items = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		targetSize = new ArrayAdapter<Integer>(this.getActivity(),
				android.R.layout.simple_spinner_item, items);
		// Apply the adapter to the spinner
		spinner.setAdapter(targetSize);
		
		// Set up the ECM spinner
		spinner = (Spinner) view.findViewById(R.id.spnECM);
		ArrayList<String> ecmList = new ArrayList<String>();

		Cursor ecms = dbh.getECM();
		while (ecms.moveToNext()) {
			ecmList.add(ecms.getString(0));
		}

		ecm = new ArrayAdapter<String>(this.getActivity(),
				android.R.layout.simple_list_item_1, ecmList);
		ecm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(ecm);		
		
		// Setup the PDS spinner
		spinner = (Spinner) view.findViewById(R.id.spnPDS);
		pds = ArrayAdapter.createFromResource(this.getActivity(),
				R.array.valPDS, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		pds
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(pds);
		
		// Setup the ADS spinner
		spinner = (Spinner) view.findViewById(R.id.spnADS);
		ads = ArrayAdapter.createFromResource(this.getActivity(),
				R.array.valADS, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		ads
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(ads);
		
		// hide the missile panel
		mslPanel = view.findViewById(R.id.pnlMissiles);
		mslPanel.setVisibility(View.GONE);
		
		// hide the infantry panel
		infPanel = view.findViewById(R.id.pnlInfantry);
		infPanel.setVisibility(View.GONE);
		armPanel = view.findViewById(R.id.pnlArmour);

		// Wire up the button group
		RadioGroup opt = (RadioGroup) view.findViewById(R.id.rgType);
		opt.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (checkedId == R.id.rbInfantry) {
					armPanel.setVisibility(View.GONE);
					infPanel.setVisibility(View.VISIBLE);
				} else if (checkedId == R.id.rbVehicle) {
					armPanel.setVisibility(View.VISIBLE);
					infPanel.setVisibility(View.GONE);
				}

			}

		});

		setHasOptionsMenu(true);

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.combatresolution, menu);
		super.onCreateOptionsMenu(menu, inflater);
		spilloverLong = menu.findItem(R.id.mnu_spilloverlong);
		spilloverMedium = menu.findItem(R.id.mnu_spillovermedium);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		CombatResolutionConfig config = new CombatResolutionConfig();
		
		// Create the config object to communicate with the
		// activity
		View view = this.getView();
		
		// Weapon Type
		Spinner wpn = (Spinner)view.findViewById(R.id.spnWeapon);
		config.setWeaponType(((Weapon)wpn.getSelectedItem()).getType());
		
		// Weapon Size
		Spinner siz = (Spinner)view.findViewById(R.id.spnWeaponSize);
		config.setWeaponSize((String)siz.getSelectedItem());

		// Moving
		CheckBox moving = (CheckBox)view.findViewById(R.id.chkMoving);
		config.setMoving(moving.isChecked());

		// Infantry Or Vehicle target
		RadioGroup opt = (RadioGroup) view.findViewById(R.id.rgType);
		if (opt.getCheckedRadioButtonId() == R.id.rbInfantry) {
			config.setInfantry(true);
		}
		
		// Fire Control
		Spinner fc = (Spinner)view.findViewById(R.id.spnFireControl);
		config.setFireControl((String)fc.getSelectedItem());
		
		// Armour Type
		Spinner armt = (Spinner)view.findViewById(R.id.spnArmourType);
		config.setArmourTypeId(((Armour)armt.getSelectedItem()).getArmourTypeId());
		
		// Armour Rating
		Spinner armr = (Spinner)view.findViewById(R.id.spnArmourSize);
		config.setArmourRating((Integer)armr.getSelectedItem());
		
		// HTK
		Spinner htk = (Spinner)view.findViewById(R.id.spnHTK);
		config.setHtk((Integer)htk.getSelectedItem());
		
		// Target State
		Spinner tstate = (Spinner)view.findViewById(R.id.spnTargetState);
		config.setTargetState(tstate.getSelectedItemPosition());
		
		// Target Size
		Spinner tsize = (Spinner)view.findViewById(R.id.spnTargetSize);
		config.setTargetSize((Integer)tsize.getSelectedItem());
		
		// ECM
		Spinner ecm = (Spinner)view.findViewById(R.id.spnECM);
		config.setEcm((String)ecm.getSelectedItem());
		
		// PDS
		Spinner pds = (Spinner)view.findViewById(R.id.spnPDS);
		config.setPds((String)pds.getSelectedItem());
		
		// ADS
		Spinner ads = (Spinner)view.findViewById(R.id.spnADS);
		config.setAds((String)ads.getSelectedItem());		
		
		switch (item.getItemId()) {
		case R.id.mnu_resolve:
			// resolve standard (tells us whether to enable spillover)
			Integer result = resolver.resolveNormalCombat(config);
			switch (result) {
			case 1:
				spilloverMedium.setEnabled(true);
			case 2:
				spilloverLong.setEnabled(true);				
			}
			return true;
		case R.id.mnu_spillovermedium:
			// resolve medium spillover
			resolver.resolveSpillover(config, 2, 5);
			return true;
		case R.id.mnu_spilloverlong:
			// resolved long range spillover
			resolver.resolveSpillover(config, 3, 6);
			return true;
		}
		return false;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof ResolveCombat)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		resolver = (ResolveCombat) activity;
	}
}
