package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.BissKeyAdderActivity;
import org.persianpros.mammadalius.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I004_KeysSoftcamDownloaderActivity extends BaseActivity 
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
					//Get Biss key
					intent = new Intent(getApplicationContext(), BissKeyAdderActivity.class);
					startActivityForResult(intent, 1);
					//Telnet on getting result
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.IRIBTV3-persianpalace.sh");
					break;

				case 2:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.AdelSatKeys-persianpalace.sh");
					break;
				case 3:
					intent = new Intent(getApplicationContext(), I4_03KeysDownloaderActivity.class);
					intent.putExtra("resID", R.array.Keys_Downloader);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(getApplicationContext(), I4_04SoftCamDownloaderActivity.class);
					intent.putExtra("resID", R.array.SoftCam_Downloader);
					startActivity(intent);
					break;
				case 5:
					intent = new Intent(getApplicationContext(), I4_05CCcamFilesDownloaderActivity.class);
					intent.putExtra("resID", R.array.CCcam_Files_Downloader);
					startActivity(intent);
					break;
				case 6:
					intent = new Intent(getApplicationContext(), I4_06CCcamChannelInfoConverterActivity.class);
					intent.putExtra("resID", R.array.CCcam_channelinfo_to_oscam_srvid_Converter);
					startActivity(intent);
					break;
				case 7:
					intent = new Intent(getApplicationContext(), I4_07KeysSoftCamConverterActivity.class);
					intent.putExtra("resID", R.array.Keys_Softcam_Converter_Requires_Keys_Softcam);
					startActivity(intent);
					break;
				default:
					Toast.makeText(I004_KeysSoftcamDownloaderActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK)
		{
			TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.BISSKeyAdder-persianpalace.sh en " +
					data.getStringExtra("code"));
		}
	}
}
