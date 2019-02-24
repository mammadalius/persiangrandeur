package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I4_03KeysDownloaderActivity extends BaseActivity 
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
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Uydu-persianpalace.sh");
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.LuxSatKeys-persianpalace.sh");
					break;
				case 2:
					intent = new Intent(getApplicationContext(), I4_03_2MiniCatActivity.class);
					intent.putExtra("resID", R.array.MiniCat_Keys);
					startActivity(intent);
					break;
				case 3:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.TechSat-persianpalace.sh");
					break;
				case 4:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.PandaSatKeys-persianpalace.sh");
					break;
				case 5:
					intent = new Intent(getApplicationContext(), I4_07SatAngelesKeysActivity.class);
					intent.putExtra("resID", R.array.SatAngeles_Keys);
					startActivity(intent);
					break;
				case 6:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DemoniSatKeys-persianpalace.sh");
					break;
				case 7:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SoftCamTV-persianpalace.sh");
					break;
				case 8:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MasterElantKeys-persianpalace.sh");
					break;
				case 9:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.NewNigma2Keys-persianpalace.sh");
					break;
				case 10:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DarkNetworkKeys-persianpalace.sh");
					break;
				default:
					Toast.makeText(I4_03KeysDownloaderActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});
	}
}
