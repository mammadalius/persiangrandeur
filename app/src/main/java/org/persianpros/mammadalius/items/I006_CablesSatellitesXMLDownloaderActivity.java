package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class I006_CablesSatellitesXMLDownloaderActivity extends BaseActivity 
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
					intent = new Intent(getApplicationContext(), I6_1CablesXMLDVBCActivity.class);
					intent.putExtra("resID", R.array.Cables_XML_DVB_C);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), I6_2SatellitesXMLDVBSActivity.class);
					intent.putExtra("resID", R.array.Satellites_XML_DVB_S);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(getApplicationContext(), I6_3TerrestrialXMLDVBTActivity.class);
					intent.putExtra("resID", R.array.Terrestrial_XML_DVB_T);
					startActivity(intent);
					break;
				default:
					Toast.makeText(I006_CablesSatellitesXMLDownloaderActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});
	}
}
