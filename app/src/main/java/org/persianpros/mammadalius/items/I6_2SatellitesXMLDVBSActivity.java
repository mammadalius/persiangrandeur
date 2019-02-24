package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I6_2SatellitesXMLDVBSActivity extends BaseActivity 
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
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SatellitesXMLWinTV-persianpalace.sh");
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SatellitesXMLSatXML-persianpalace.sh");
					break;
				case 2:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SatellitesXMLLyngSat-persianpalace.sh");
					break;
				case 3:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SatellitesXMLSatBeams-persianpalace.sh");
					break;
				case 4:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SatellitesXMLSatHunter-persianpalace.sh");
					break;
				case 5:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SatellitesXMLSatLoader-persianpalace.sh");
					break;
				case 6:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SatellitesXMLIslaRemota-persianpalace.sh");
					break;
				case 7:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SatellitesXMLSatFinder-persianpalace.sh");
					break;
				case 8:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SatellitesXMLDreamboxTools-persianpalace.sh");
					break;
				case 9:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SatellitesXMLTurkDreamworld-persianpalace.sh");
					break;
				case 10:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SatellitesXML-persianpalace.sh");
					break;
				default:
					Toast.makeText(I6_2SatellitesXMLDVBSActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
				}				
			}
		});
	}
}
