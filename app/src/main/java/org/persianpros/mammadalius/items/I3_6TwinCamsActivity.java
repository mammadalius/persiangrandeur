package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.R;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I3_6TwinCamsActivity extends BaseActivity 
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
			menu.setHeaderTitle(getResources().getStringArray(R.array.Twin_Cams_Dongle_Version)[0]);
			break;
		case 1:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Twin_Cams_Dongle_Version)[1]);
			break;
		case 2:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Twin_Cams_Dongle_Version)[2]);
			break;
		case 3:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Twin_Cams_Dongle_Version)[3]);
			break;
		default:
			break;
		}

		menu.add(0, 0, 0, getResources().getString(R.string.Enable_Start));  
		menu.add(0, 1, 0, getResources().getString(R.string.Disable_Stop));
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
		case 0:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Twiner130a-persianpalace.sh");
			break;
		case 1:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Twin2CS010-persianpalace.sh");
			break;
		case 2:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Twin2CC103a-persianpalace.sh");
			break;
		case 3:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Twin2CC103a-persianpalace.sh");
			break;
		default:
			Toast.makeText(getApplicationContext(), position + " enabled (test)", Toast.LENGTH_SHORT).show();
		}
	}

	private void disable(int position)
	{
		switch (position)
		{
		case 0:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Twiner130a-persianpalace.sh");
			break;
		case 1:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Twin2CS010-persianpalace.sh");
			break;
		case 2:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Twin2CC103a-persianpalace.sh");
			break;
		case 3:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Twin2CC103a-persianpalace.sh");
			break;
		default:
			Toast.makeText(getApplicationContext(), position + " disabled (test)", Toast.LENGTH_SHORT).show();
		}
	}
}
