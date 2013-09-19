package uk.org.downesward.dirtside;

import java.util.ArrayList;

import uk.org.downesward.dirtside.R;

import uk.org.downesward.dirtside.adapters.WeaponAdapter;
import uk.org.downesward.dirtside.domain.Weapon;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CombatResolutionConfigFragment extends Fragment {

	private ArrayAdapter<Weapon> weaponType;
	private ArrayAdapter<String> weaponSize;	
	private ArrayAdapter<String> fireControl;
	
	private ArrayList<Weapon> weapons;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment

		View view = inflater.inflate(R.layout.combatresolutionconfig,
				container, false);

		final DatabaseHelper dbh = new DatabaseHelper(this.getActivity());
		Cursor wpn = dbh.getWeapons();
		ArrayList<String> wpnType = new ArrayList<String>();

		weapons = new ArrayList<Weapon>();
		
		while (wpn.moveToNext()) {
			Weapon aWpn = new Weapon(wpn.getString(0), wpn.getString(1));
			weapons.add(aWpn);
			wpnType.add(aWpn.getLongName());
		}
		
		weaponType = new WeaponAdapter(this.getActivity(), R.layout.weaponlistitem, weapons);
		
		Spinner spinner = (Spinner) view.findViewById(R.id.spnWeapon);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
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
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				weaponSize.clear();
				weaponSize.notifyDataSetChanged();
			}

		});

		// Apply the adapter to the spinner
		spinner.setAdapter(weaponType);
		
		// Set up the weapon size spinner
		spinner = (Spinner) view.findViewById(R.id.spnWeaponSize);
		
		ArrayList<String> wpnSize = new ArrayList<String>();	
		weaponSize = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_list_item_1,
				wpnSize);
		weaponSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(weaponSize);
		
		// Set up the fire control spinner
		spinner = (Spinner) view.findViewById(R.id.spnFireControl);
		
		ArrayList<String> fireControls = new ArrayList<String>();	
		
		Cursor fcs = dbh.getFireControl();
		while (wpn.moveToNext()) {
			fireControls.add(fcs.getString(0));
		}
		
		fireControl = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_list_item_1,
				fireControls);
		fireControl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(fireControl);
		
		return view;
	}
}
