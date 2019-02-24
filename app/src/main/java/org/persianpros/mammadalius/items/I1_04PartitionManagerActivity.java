package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I1_04PartitionManagerActivity extends BaseActivity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		getListView().setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				Intent intent;
				switch (position)
				{
				case 0:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MountUSB-persianpalace.sh");					
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MountHDD-persianpalace.sh");					
					break;
				case 2:
					intent = new Intent(getApplicationContext(), I1_04_3FormatUSBActivity.class);
					intent.putExtra("resID", R.array.Format_USB);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent(getApplicationContext(), I1_04_4FormatHDDActivity.class);
					intent.putExtra("resID", R.array.Format_HDD);
					startActivity(intent);
					break;
				case 4:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.UnMountUSB-persianpalace.sh");					
					break;
				case 5:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.UnMountHDD-persianpalace.sh");					
					break;
				default:
					Toast.makeText(I1_04PartitionManagerActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});
	}
}
