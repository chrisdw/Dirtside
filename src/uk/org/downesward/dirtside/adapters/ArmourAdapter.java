package uk.org.downesward.dirtside.adapters;

import java.util.ArrayList;

import uk.org.downesward.dirtside.domain.Armour;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ArmourAdapter  extends ArrayAdapter<Armour> {
	private ArrayList<Armour> items;

	public ArmourAdapter(Context context, int textViewResourceId,
			ArrayList<Armour> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv = new TextView(getContext());
		Armour o = items.get(position);
		tv.setText(o.getDescription());
		return tv;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(getContext());
		Armour o = items.get(position);
		label.setText(o.getDescription());

		return label;
	}	
}
