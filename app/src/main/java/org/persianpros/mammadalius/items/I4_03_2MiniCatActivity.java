package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I4_03_2MiniCatActivity extends BaseActivity 
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
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MiniCatGBoxUpdate-persianpalace.sh");
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MiniCatSCamUpdate-persianpalace.sh");
					break;
				case 2:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MiniCatBundleUpdate-persianpalace.sh");
					break;
				case 3:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MiniCatCamd3Update-persianpalace.sh");
					break;
				case 4:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MiniCatCCcamUpdate-persianpalace.sh");
					break;
				case 5:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MiniCatMgCamdUpdate-persianpalace.sh");
					break;
				case 6:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MiniCatEvoCamdUpdate-persianpalace.sh");
					break;
				case 7:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MiniCatNewCamdUpdate-persianpalace.sh");
					break;
				case 8:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MiniCatLastUpdate-persianpalace.sh");
					break;
				default:
					Toast.makeText(I4_03_2MiniCatActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
