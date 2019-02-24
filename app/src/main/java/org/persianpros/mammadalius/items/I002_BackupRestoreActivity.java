package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I002_BackupRestoreActivity extends BaseActivity 
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
					intent = new Intent(getApplicationContext(), I2_1PluginActivity.class);
					intent.putExtra("resID", R.array.Plugins);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), I2_2SkinPiconActivity.class);
					intent.putExtra("resID", R.array.Skins_Picons);
					startActivity(intent);
					break;
				case 2:					
					TelnetCommand("/usr/script/DefaultSkin.sh");
					break;
				case 3:
					intent = new Intent(getApplicationContext(), I2_3CCcamSettingsActivity.class);
					intent.putExtra("resID", R.array.CCcam_Settings);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(getApplicationContext(), I2_4Enigma2SettingsActivity.class);
					intent.putExtra("resID", R.array.Enigma2_Settings);
					startActivity(intent);
					break;
				case 5:
					TelnetCommand("/usr/script/RestoreSkinXML.sh");
					break;
				case 6:
					TelnetCommand("/usr/script/RestoreLameDB.sh");
					break;
				case 7:
					intent = new Intent(getApplicationContext(), I2_8DropboxActivity.class);
					intent.putExtra("resID", R.array.DropBox_Online_Backup_Restore);
					startActivity(intent);
					break;
				default:
					Toast.makeText(I002_BackupRestoreActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
					break;
				}				
			}
		});
	}
}
