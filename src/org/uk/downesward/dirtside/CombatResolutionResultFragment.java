package org.uk.downesward.dirtside;

import java.util.ArrayList;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CombatResolutionResultFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment
		
		View view = inflater
				.inflate(R.layout.combatresolutionconfig, container, false);
		
		DatabaseHelper dbh = new DatabaseHelper(this.getActivity());
		Cursor wpn = dbh.getWeapons();
		ArrayList<String> wpnType = new ArrayList<String>();
		
		while (wpn.moveToNext()) {
			wpnType.add(wpn.getString(0));
		}
		Spinner spinner = (Spinner)view.findViewById(R.id.spnWeapon);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
				android.R.layout.simple_list_item_1, wpnType);

		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		return view;
	}
}
