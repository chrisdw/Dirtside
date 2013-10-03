package uk.org.downesward.dirtside.adapters;

import java.util.ArrayList;

import uk.org.downesward.dirtside.domain.Campaign;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CampaignAdapter extends ArrayAdapter<Campaign> {
	private ArrayList<Campaign> items;

	public CampaignAdapter(Context context, int textViewResourceId,
			ArrayList<Campaign> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tv = new TextView(getContext());
		Campaign o = items.get(position);
		tv.setText(o.getDescription());
		return tv;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(getContext());
		Campaign o = items.get(position);
		label.setText(o.getDescription());

		return label;
	}	
}
