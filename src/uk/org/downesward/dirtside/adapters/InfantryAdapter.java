package uk.org.downesward.dirtside.adapters;

import java.util.ArrayList;

import uk.org.downesward.dirtside.domain.Infantry;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class InfantryAdapter  extends ArrayAdapter<Infantry> {
	private ArrayList<Infantry> items;

	public InfantryAdapter(Context context, int textViewResourceId,
			ArrayList<Infantry> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv = new TextView(getContext());
		Infantry o = items.get(position);
		tv.setText(o.getDescription());
		return tv;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(getContext());
		Infantry o = items.get(position);
		label.setText(o.getDescription());

		return label;
	}	
}
