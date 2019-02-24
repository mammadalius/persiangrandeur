package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class I3_2_4n_DOSCamActivity extends BaseActivity 
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
			menu.setHeaderTitle(getResources().getStringArray(R.array.Multicams_DOSCam_Version)[0]);
			menu.add(0, 0, 0, getResources().getString(R.string.Enable_Start));  
			menu.add(0, 1, 0, getResources().getString(R.string.Disable_Stop));
			break;
		case 1:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Multicams_DOSCam_Version)[1]);
			menu.add(1, 0, 0,getResources().getString(R.string.Enable_Start));
			menu.add(1, 1, 0,getResources().getString(R.string.Disable_Stop));
			break;
		case 2:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Multicams_DOSCam_Version)[2]);
			menu.add(2, 0, 0,getResources().getString(R.string.Enable_Start));
			menu.add(2, 1, 0,getResources().getString(R.string.Disable_Stop));
			break;
		case 3:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Multicams_DOSCam_Version)[3]);
			menu.add(3, 0, 0,getResources().getString(R.string.Enable_Start));
			menu.add(3, 1, 0,getResources().getString(R.string.Disable_Stop));
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) 
	{
		switch (item.getGroupId()) 
		{
		case 0:
			if (item.getItemId() == 0)
				TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DOSCam019-SBox00544-persianpalace.sh");
			else if (item.getItemId() == 1)
				TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DOSCam019-SBox00544-persianpalace.sh");
			break;
		case 1:
			if (item.getItemId() == 0)
				TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DOSCam019-CCcam230-persianpalace.sh");
			else if (item.getItemId() == 1)
				TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DOSCam019-CCcam230-persianpalace.sh");
			break;
		case 2:
			if (item.getItemId() == 0)
				TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DOSCam019-WiCard115-persianpalace.sh");
			else if (item.getItemId() == 1)
				TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DOSCam019-WiCard115-persianpalace.sh");
			break;
		case 3:
			if (item.getItemId() == 0)
				TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DOSCam019-MgCamd138c-persianpalace.sh");
			else if (item.getItemId() == 1)
				TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DOSCam019-MgCamd138c-persianpalace.sh");
			break;
		default:
			break;
		}
		return true;
	}

	//	private void showToast(int groupID, int itemID)
	//	{
	//		Toast.makeText(this, "group <" + groupID + "> item <" + itemID + ">" , Toast.LENGTH_SHORT).show();
	//	}
}
