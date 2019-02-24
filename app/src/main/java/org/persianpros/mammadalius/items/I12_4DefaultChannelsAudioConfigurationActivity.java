package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I12_4DefaultChannelsAudioConfigurationActivity extends BaseActivity 
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
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.EnglishAudio-persianpalace.sh");
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.PersianAudio-persianpalace.sh");
					break;
				default:
					Toast.makeText(I12_4DefaultChannelsAudioConfigurationActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
				}				
			}
		});
	}
}
