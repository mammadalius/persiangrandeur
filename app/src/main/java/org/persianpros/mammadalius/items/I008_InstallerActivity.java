package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.GetPathActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I008_InstallerActivity extends BaseActivity 
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
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.CFInstall-persianpalace.sh");
					break;
				case 1:
					//Get path
					Intent intent = new Intent(getApplicationContext(), GetPathActivity.class);
					intent.putExtra("desc", "Enter URL (Direct Link With IPK Extension):");
					intent.putExtra("title", "URL Installer - IPK");
					startActivityForResult(intent, 3);
					//Telnet on getting result					
					break;
				case 2:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.USBInstall-persianpalace.sh");
					break;
				case 3:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.HDDInstall-persianpalace.sh");
					break;
				case 4:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.TempInstall-persianpalace.sh");
					break;
				case 5:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.BootLogoChanger-persianpalace.sh");
					break;
				default:
					Toast.makeText(I008_InstallerActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
				}				
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 3 && resultCode == RESULT_OK)
		{
			TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.URLInstall-persianpalace.sh en " +
					data.getStringExtra("path"));
		}
	}
}