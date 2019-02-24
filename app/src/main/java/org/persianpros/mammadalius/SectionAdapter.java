package org.persianpros.mammadalius;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SectionAdapter extends ArrayAdapter<String>
{
	String[] mObjects;
	Context mContext;

	public SectionAdapter(Context context, int textViewResourceId, String[] objects) {
		super(context, textViewResourceId, objects);
		mObjects = objects;
		mContext = context;
	}

	public SectionAdapter(Context context, int textViewResourceId, int resID) {
		super(context, textViewResourceId, context.getResources().getStringArray(resID));
		mObjects = context.getResources().getStringArray(resID);
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater inflater = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		View row = inflater.inflate(R.layout.list_item_with_separator, null);
		String[] currentItem = mObjects[position].split("\\.");
		
		for (int i = 2; i < currentItem.length; i++) {
			currentItem[1] += "." + currentItem[i];
		}

		if (mObjects[position].startsWith("-"))//item is a Separator
		{
			row.findViewById(R.id.section).setVisibility(View.VISIBLE);
			row.findViewById(R.id.separator).setVisibility(View.GONE);
			currentItem[0] = currentItem[0].replaceFirst("-", "");
		}

		((TextView)row.findViewById(R.id.tvText)).setText(currentItem[1]);

		if (Integer.parseInt(currentItem[0]) < 10)
			((TextView)row.findViewById(R.id.tvNumber)).setText(currentItem[0]);
		else
			switch (Integer.parseInt(currentItem[0]))
			{
			case 10:
				((TextView)row.findViewById(R.id.tvNumber)).setVisibility(View.INVISIBLE);
				((ImageView)row.findViewById(R.id.imgColor)).setVisibility(View.VISIBLE);
				((ImageView)row.findViewById(R.id.imgColor)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.red_r));
				break;
			case 11:
				((TextView)row.findViewById(R.id.tvNumber)).setVisibility(View.INVISIBLE);
				((ImageView)row.findViewById(R.id.imgColor)).setVisibility(View.VISIBLE);
				((ImageView)row.findViewById(R.id.imgColor)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.green_r));
				break;
			case 12:
				((TextView)row.findViewById(R.id.tvNumber)).setVisibility(View.INVISIBLE);
				((ImageView)row.findViewById(R.id.imgColor)).setVisibility(View.VISIBLE);
				((ImageView)row.findViewById(R.id.imgColor)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.yellow_r));
				break;
			case 13:
				((TextView)row.findViewById(R.id.tvNumber)).setVisibility(View.INVISIBLE);
				((ImageView)row.findViewById(R.id.imgColor)).setVisibility(View.VISIBLE);
				((ImageView)row.findViewById(R.id.imgColor)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.blue_r));
				break;
			default:
				((TextView)row.findViewById(R.id.tvNumber)).setText(currentItem[0]);
				break;
			}
		return row;
	}
}