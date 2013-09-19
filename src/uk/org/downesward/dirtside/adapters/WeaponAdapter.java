package uk.org.downesward.dirtside.adapters;

import java.util.ArrayList;

import uk.org.downesward.dirtside.R;
import uk.org.downesward.dirtside.domain.Weapon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WeaponAdapter extends ArrayAdapter<Weapon> {
	private ArrayList<Weapon> items;

	public WeaponAdapter(Context context, int textViewResourceId,
			ArrayList<Weapon> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = LayoutInflater.from(getContext());
			v = vi.inflate(R.layout.weaponlistitem, null);
		}
		Weapon o = items.get(position);
		if (o != null) {
			TextView tt = (TextView) v.findViewById(R.id.txtName);
			TextView bt = (TextView) v.findViewById(R.id.txtDescription);

			if (tt != null) {
				tt.setText(o.getType());
			}
			if (bt != null) {
				bt.setText(o.getDescription());
			}
		}
		return v;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(getContext());
		Weapon o = items.get(position);
		label.setText(o.getType());

		return label;
	}

}
