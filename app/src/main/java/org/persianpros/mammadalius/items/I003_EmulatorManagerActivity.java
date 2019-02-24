package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;
import org.persianpros.mammadalius.SectionAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class I003_EmulatorManagerActivity extends BaseActivity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grandeur2);
		//		registerForContextMenu(getListView());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		ListView list = (ListView) findViewById(R.id.lstItems);
		//		list.setAdapter(ArrayAdapter.createFromResource(this,
		//				getIntent().getIntExtra("resID", R.array.PersianPalace),
		//				android.R.layout.simple_list_item_1));

		list.setAdapter(new SectionAdapter(this,
				R.layout.list_item_with_separator,
				getIntent().getIntExtra("resID", R.array.PersianPalace)));
		list.setDivider(getResources().getDrawable(R.drawable.list_divider));

		list.setTextFilterEnabled(true);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		getListView().setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				Intent intent;
				switch (position)
				{
				case 0:
					intent = new Intent(getApplicationContext(), I3_1OldCamsActivity.class);
					intent.putExtra("resID", R.array.Old_Cams);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), I3_2MultiCamsActivity.class);
					intent.putExtra("resID", R.array.Multi_Cams);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(getApplicationContext(), I3_3GBoxCamsActivity.class);
					intent.putExtra("resID", R.array.GBox_Cams);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent(getApplicationContext(), I3_4SingleCamsActivity.class);
					intent.putExtra("resID", R.array.Single_Cams);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(getApplicationContext(), I3_5OriginalCamsActivity.class);
					intent.putExtra("resID", R.array.Original_Cams);
					startActivity(intent);
					break;
				case 5://Dongle
					intent = new Intent(getApplicationContext(), I3_6TwinCamsActivity.class);
					intent.putExtra("resID", R.array.Twin_Cams_Dongle);
					startActivity(intent);
					break;
				default:
				}
			}
		});
	}

	//	@Override
	//	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) 
	//	{
	//		super.onCreateContextMenu(menu, v, menuInfo);
	//		menu.setHeaderTitle(getResources().getStringArray(R.array.Twin_Protocol_Dongle_Version)[0]);		
	//		menu.add(0, 0, 0, getResources().getString(R.string.Enable_Start));  
	//		menu.add(0, 1, 0, getResources().getString(R.string.Disable_Stop));
	//	}
	//
	//	@Override
	//	public boolean onContextItemSelected(MenuItem item) 
	//	{
	//		if (item.getTitle() == getResources().getString(R.string.Enable_Start))
	//			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.TwinProtocol1-persianpalace.sh");  
	//		else if (item.getTitle() == getResources().getString(R.string.Disable_Stop))
	//			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.TwinProtocol1-persianpalace.sh");
	//		else
	//			return false;
	//		return true;
	//	}
}
