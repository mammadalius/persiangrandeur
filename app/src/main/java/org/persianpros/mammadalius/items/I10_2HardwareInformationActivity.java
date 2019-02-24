package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I10_2HardwareInformationActivity extends BaseActivity 
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
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.CPU-persianpalace.sh");
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MAC-persianpalace.sh");
					break;					
				case 2:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Tuner-persianpalace.sh");
					break;				
				case 3:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MemoryInfo-persianpalace.sh");
					break;
				case 4:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Chipset-persianpalace.sh");
					break;
				case 5:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.FreeSpace-persianpalace.sh");
					break;
				case 6:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.FreeMemory-persianpalace.sh");
					break;
				case 7:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.KernelModules-persianpalace.sh");
					break;
				case 8:
					intent = new Intent(getApplicationContext(), I10_2_8HDDPerformanceActivity.class);
					intent.putExtra("resID", R.array.HDD_Performance);
					startActivity(intent);
					break;
				case 9:
					intent = new Intent(getApplicationContext(), I10_2_9InternetPerformanceActivity.class);
					intent.putExtra("resID", R.array.Internet_Performance);
					startActivity(intent);
					break;
				case 10:
					intent = new Intent(getApplicationContext(), I10_2_10SpeedTestActivity.class);
					intent.putExtra("resID", R.array.Speed_Test_for_Mounted_Storages);
					startActivity(intent);
					break;
				default:
					Toast.makeText(I10_2HardwareInformationActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});
	}
}
