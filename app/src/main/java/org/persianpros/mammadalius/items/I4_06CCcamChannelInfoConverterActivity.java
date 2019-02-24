package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I4_06CCcamChannelInfoConverterActivity extends BaseActivity 
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
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.CCcam2OSCamE-persianpalace.sh");
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.CCcam2OSCamB-persianpalace.sh");
					break;
				case 2:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.CCcam2OSCamC-persianpalace.sh");
					break;
				case 3:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.CCcam2OSCamA-persianpalace.sh");
					break;
				case 4:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.CCcam2OSCamD-persianpalace.sh");
					break;
				default:
					Toast.makeText(I4_06CCcamChannelInfoConverterActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
