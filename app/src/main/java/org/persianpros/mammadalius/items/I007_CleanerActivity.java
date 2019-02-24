package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I007_CleanerActivity extends BaseActivity
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
				case 0://Clear Cache
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.ClearCache-persianpalace.sh");
					break;
				case 1://Enable Spinner
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SpinnerOn-persianpalace.sh");
					break;
				case 2://Disable Spinner
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SpinnerOff-persianpalace.sh");
					break;
				case 3:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.CFClean-persianpalace.sh");
					break;
				case 4://USB Cleaner - All Files-Folders
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.USBClean-persianpalace.sh");
					break;
				case 5://HDD Cleaner - All Files-Folders
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.HDDClean-persianpalace.sh");
					break;
				case 6://Temp Cleaner - All Files-Folders
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.TempClean-persianpalace.sh");
					break;
				case 7://Keys Cleaner - All Keys-SoftCams
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.KeysClean-persianpalace.sh");
					break;
				case 8:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DeleteEPG-persianpalace.sh");
					break;
				case 9:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DeletePicons-persianpalace.sh");
					break;
				case 10:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DeleteTimers-persianpalace.sh");
					break;
				case 11:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DeleteTimeStamp-persianpalace.sh");
					break;
				case 12:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DeleteCrashLogs-persianpalace.sh");
					break;
				default:
					Toast.makeText(I007_CleanerActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
