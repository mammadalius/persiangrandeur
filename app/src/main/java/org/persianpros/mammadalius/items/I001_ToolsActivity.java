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

public class I001_ToolsActivity extends BaseActivity
{
	int currentPosition = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		registerForContextMenu(getListView());
		getListView().setLongClickable(false);
		getListView().setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent;
				switch (position)
				{
				case 0:
					intent = new Intent(getApplicationContext(), I1_01CHMODActivity.class);
					intent.putExtra("resID", R.array.CHMOD);
					startActivity(intent);
					break;
				case 1:
					currentPosition = position;
					getListView().showContextMenu();
					break;
				case 2:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.UpdateModules-persianpalace.sh");
					break;
				case 3:
					intent = new Intent(getApplicationContext(),
							I1_04PartitionManagerActivity.class);
					intent.putExtra("resID", R.array.Partition_Manager);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(getApplicationContext(),
							I1_06IPKServerManagerActivity.class);
					intent.putExtra("resID", R.array.IPK_Server_Manager);
					startActivity(intent);
					break;
				case 5:
					intent = new Intent(getApplicationContext(),
							I1_07PiconPathManagerActivity.class);
					intent.putExtra("resID", R.array.Picon_Path_Manager);
					startActivity(intent);
					break;
				case 6:
					intent = new Intent(getApplicationContext(),
							I1_08PluginPathManagerActivity.class);
					intent.putExtra("resID", R.array.Plugin_Path_Manager);
					startActivity(intent);
					break;
				case 7:
					intent = new Intent(getApplicationContext(),
							I1_09TimeshiftPathManagerActivity.class);
					intent.putExtra("resID", R.array.Timeshift_Path_Manager);
					startActivity(intent);
					break;
				case 8:
					intent = new Intent(getApplicationContext(),
							I1_10MACAddressChangerActivity.class);
					intent.putExtra("resID", R.array.MAC_Address_Changer);
					startActivity(intent);
					break;
				case 9:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Time-persianpalace.sh");
					break;
				case 10:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.OPKG-persianpalace.sh");
					break;
				default:
					Toast.makeText(I001_ToolsActivity.this, position + " clicked",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);

		switch (currentPosition)
		{
		case 1:
			menu.setHeaderTitle("Swap Manager");
			break;
		default:
			break;
		}

		menu.add(0, v.getId(), 0, "Enable-Start");
		menu.add(0, v.getId(), 0, "Disable-Stop");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		if (item.getTitle() == "Enable-Start")
		{
			enable(currentPosition);
		}
		else if (item.getTitle() == "Disable-Stop")
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
		TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Swap-persianpalace.sh enable");
	}

	private void disable(int position)
	{
		TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.Swap-persianpalace.sh disable");
	}
}
