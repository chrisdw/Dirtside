package uk.org.downesward.dirtside.adapters;

import java.util.ArrayList;

import uk.org.downesward.dirtside.domain.InfantryFirepower;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class InfantryFirepowerAdapter extends ArrayAdapter<InfantryFirepower> {
	private ArrayList<InfantryFirepower> items;

	public InfantryFirepowerAdapter(Context context, int textViewResourceId,
			ArrayList<InfantryFirepower> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv = new TextView(getContext());
		InfantryFirepower o = items.get(position);
		tv.setText(o.getDescription());
		return tv;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(getContext());
		InfantryFirepower o = items.get(position);
		label.setText(o.getDescription());

		return label;
	}	
}
