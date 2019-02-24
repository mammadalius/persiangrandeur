package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I012_PersianEmpireEnigma2ImageActivity extends BaseActivity 
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
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.FixSpinner-persianpalace.sh");
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.FixBootLogo720-persianpalace.sh");
					break;
				case 2:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.FixBootLogo-persianpalace.sh");
					break;
				case 3:
					intent = new Intent(getApplicationContext(), I12_4DefaultChannelsAudioConfigurationActivity.class);
					intent.putExtra("resID", R.array.Default_Channels_Audio_Configuration);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(getApplicationContext(), I12_05PIDActivity.class);
					intent.putExtra("resID", R.array.Audio_Video_PID_Changer);
					startActivity(intent);
					break;
				case 5:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Charset-persianpalace.sh");
					break;
				case 6:
					intent = new Intent(getApplicationContext(), I12_7VPNActivity.class);
					intent.putExtra("resID", R.array.PPTP_VPN_for_Persian_Empire_Images);
					startActivity(intent);
					break;
				case 7:
					Toast.makeText(I012_PersianEmpireEnigma2ImageActivity.this, "Under development...", Toast.LENGTH_SHORT).show();
					break;
				case 8:
					intent = new Intent(getApplicationContext(), I12_8TakeHighQualityScreenshotActivity.class);
					intent.putExtra("resID", R.array.Take_High_Quality_Screenshot);
					startActivity(intent);
					break;
				default:
					Toast.makeText(I012_PersianEmpireEnigma2ImageActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});
	}
}
