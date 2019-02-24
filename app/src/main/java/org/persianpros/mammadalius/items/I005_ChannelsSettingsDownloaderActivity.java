package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I005_ChannelsSettingsDownloaderActivity extends BaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getListView().setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				switch (position)
				{
				case 0:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.AdelSatSettings-persianpalace.sh");
					break;
				case 1:
					Intent intent2 = new Intent(getApplicationContext(),
							I5_2OldSettingsActivity.class);
					intent2.putExtra("resID", R.array.Old_Settings);
					startActivity(intent2);
					break;
				case 2:
					Intent intent5 = new Intent(getApplicationContext(),
							I5_3NewSettingsActivity.class);
					intent5.putExtra("resID", R.array.New_Settings);
					startActivity(intent5);
					break;
				case 3:
					Intent intent3 = new Intent(getApplicationContext(),
							I5_4RemoveChannelsSettingsActivity.class);
					intent3.putExtra("resID", R.array.Remove_Channels_Settings);
					startActivity(intent3);
					break;
				case 4:// Add Online Persian Radio - TV Channels
					Intent intent4 = new Intent(getApplicationContext(),
							I5_5AddOnlinePersianChannelsActivity.class);
					intent4.putExtra("resID", R.array.Add_Online_Persian_Radio_TV_Channels);
					startActivity(intent4);
					break;
				case 5:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.UserSettings-persianpalace.sh");
					break;
				default:
					Toast.makeText(I005_ChannelsSettingsDownloaderActivity.this,
							position + " clicked", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
