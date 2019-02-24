package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I3_1OldCamsActivity extends BaseActivity 
{
	int currentPosition = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		registerForContextMenu(getListView());

		getListView().setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				currentPosition = position;
				getListView().setLongClickable(false);
				getListView().showContextMenu();
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) 
	{
		super.onCreateContextMenu(menu, v, menuInfo);

		switch (currentPosition)
		{
		case 0:
			Intent intent = new Intent(getApplicationContext(), I3_1_1RqCamsActivity.class);
			intent.putExtra("resID", R.array.Rq_Cams);
			startActivity(intent);
			return;
		case 1:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Old_Cams_Versions)[1]);
			break;
		case 2:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Old_Cams_Versions)[2]);
			break;
		case 3:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Old_Cams_Versions)[3]);
			break;			
		case 4://VizCam
			menu.setHeaderTitle(getResources().getStringArray(R.array.Old_Cams_Versions)[4]);
			break;			
		case 5:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Old_Cams_Versions)[5]);
			break;
		case 6:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Old_Cams_Versions)[6]);
			break;
		case 7:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Old_Cams_Versions)[7]);
			break;
		case 8:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Old_Cams_Versions)[8]);
			break;
		case 9:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Old_Cams_Versions)[9]);
			break;
		case 10:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Old_Cams_Versions)[10]);
			break;
		case 11:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Old_Cams_Versions)[10]);
			break;
		default:
			break;
		}

		menu.add(0, v.getId(), 0, getResources().getString(R.string.Enable_Start));  
		menu.add(0, v.getId(), 0, getResources().getString(R.string.Disable_Stop));
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) 
	{
		if (item.getTitle() == getResources().getString(R.string.Enable_Start))
		{
			enable(currentPosition);
		}  
		else if (item.getTitle() == getResources().getString(R.string.Disable_Stop)) 
		{
			disable(currentPosition);
		}  
		else 
		{
			return false;
		}  
		return true;
	}

	private void enable(int position)
	{
		switch (position)
		{
		case 1:
			//TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.RqCS111-persianpalace.sh");
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MBox060010-persianpalace.sh");
			break;
		case 2:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MPCS0520-persianpalace.sh");
			break;				
		case 3:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.NCam03-persianpalace.sh");
			break;				
		case 4:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Camd3908L-persianpalace.sh");
			break;			
		case 5:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.VizCam112-persianpalace.sh");
			break;
		case 6:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.FireCam01-persianpalace.sh");
			break;
		case 7:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.EvoCamd217c-persianpalace.sh");
			break;
		case 8:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.OpenCam211-persianpalace.sh");
			break;
		case 9:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.HyperCam310Lite-persianpalace.sh");
			break;			
		case 10:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.AvatarCam01-persianpalace.sh");
			break;
		case 11:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SPCSNassCamd2617-persianpalace.sh");
			break;
		default:
			Toast.makeText(getApplicationContext(), position + " enabled (test)", Toast.LENGTH_SHORT).show();
		}		
	}

	private void disable(int position)
	{
		switch (position)
		{
		case 1:
			//TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.RqCS111-persianpalace.sh");
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MBox060010-persianpalace.sh");
			break;
		case 2:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MPCS0520-persianpalace.sh");
			break;				
		case 3:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.NCam03-persianpalace.sh");
			break;				
		case 4:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Camd3908L-persianpalace.sh");
			break;			
		case 5:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.VizCam112-persianpalace.sh");
			break;
		case 6:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.FireCam01-persianpalace.sh");
			break;
		case 7:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.EvoCamd217c-persianpalace.sh");
			break;
		case 8:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.OpenCam211-persianpalace.sh");
			break;
		case 9:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.HyperCam310Lite-persianpalace.sh");
			break;			
		case 10:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.AvatarCam01-persianpalace.sh");
			break;
		case 11:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SPCSNassCamd2617-persianpalace.sh");
			break;
		default:
			Toast.makeText(getApplicationContext(), position + " disabled (test)", Toast.LENGTH_SHORT).show();
		}
	}
}
