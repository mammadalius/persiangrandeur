package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.ClineActivity;
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

public class I3_4SingleCamsActivity extends BaseActivity 
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
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[0]);
			break;
		case 1:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[1]);
			break;
		case 2:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[2]);
			break;
		case 3:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[3]);
			break;
		case 4:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[4]);
			break;
		case 5:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[5]);
			break;			
		case 6:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[6]);
			break;			
		case 7:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[7]);
			break;
		case 8:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[8]);
			break;
		case 9:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[9]);
			break;
		case 10:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[10]);
			break;
		case 11:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[11]);
			break;
		case 12:
			menu.setHeaderTitle(getResources().getStringArray(R.array.Single_Cams_Versions)[12]);
			break;
		default:
			break;
		}

		if (currentPosition != 3)//not CCcam
		{
			menu.add(0, 0, 0, getResources().getString(R.string.Enable_Start));  
			menu.add(0, 1, 0, getResources().getString(R.string.Disable_Stop));
		}
		else//is CCcam
		{
			menu.add(1, 0, 0, getResources().getString(R.string.Enable_Start));
			menu.add(1, 1, 0, getResources().getString(R.string.Disable_Stop));
			menu.add(1, 2, 0, "CCcam.cfg Creator");			
			menu.add(1, 3, 0, "Fix CCcam Permissions (Config Files)");			
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) 
	{
		if (item.getGroupId() == 0)//not CCcam
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
		}
		else //is CCcam
		{
			switch (item.getItemId()) {
			case 0:				
				TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.CCcam230-persianpalace.sh");
				break;
			case 1:
				TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.CCcam230-persianpalace.sh");
				break;
			case 2:///usr/script/CreateCCcamCFG.sh
				Intent intent = new Intent(getApplicationContext(), ClineActivity.class);
				intent.putExtra("title", "CCcam.cfg Creator");
				startActivityForResult(intent, 5);
				break;
			case 3:
				TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.FixCCcamPermissions-persianpalace.sh");
				break;			
			default:
				break;
			}
		}
		return true;
	}

	private void enable(int position)
	{
		switch (position)
		{
		case 0:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SBox00544-persianpalace.sh");
			break;
		case 1:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SCam360-persianpalace.sh");
			break;
		case 2:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.NewCS171-persianpalace.sh");
			break;
		case 3://Cccam
			break;
		case 4:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.OSCam120-persianpalace.sh");
			break;
		case 5:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.WiCard115-persianpalace.sh");
			break;
		case 6:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MultiCSR77-persianpalace.sh");
			break;
		case 7:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.UniCam320027-persianpalace.sh");
			break;
		case 8:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.BtkCam086-persianpalace.sh");
			break;
		case 9:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DOSCam019-persianpalace.sh");
			break;
		case 10:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MgCamd138c-persianpalace.sh");
			break;
		case 11:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.OSCamYModV18T50-persianpalace.sh");
			break;
		case 12:
			TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.OSCamHDSC60-persianpalace.sh");
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
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SBox00544-persianpalace.sh");
			break;
		case 1:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.SCam360-persianpalace.sh");
			break;
		case 2:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.NewCS171-persianpalace.sh");
			break;
		case 3://Cccam
			break;
		case 4:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.OSCam120-persianpalace.sh");
			break;
		case 5:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.WiCard115-persianpalace.sh");
			break;
		case 6:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MultiCSR77-persianpalace.sh");
			break;
		case 7:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.UniCam320027-persianpalace.sh");
			break;
		case 8:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.BtkCam086-persianpalace.sh");
			break;
		case 9:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DOSCam019-persianpalace.sh");
			break;
		case 10:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MgCamd138c-persianpalace.sh");
			break;
		case 11:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.OSCamYModV18T50-persianpalace.sh");
			break;
		case 12:
			TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.OSCamHDSC60-persianpalace.sh");
			break;
		default:
			Toast.makeText(getApplicationContext(), position + " enabled (test)", Toast.LENGTH_SHORT).show();
		}		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{	
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 5 && resultCode == RESULT_OK)
		{
			String[] lines = data.getStringArrayExtra("lines");
			String[] commands = new String[] {"/usr/script/CreateCCcamCFG.sh",
					lines[0], lines[1], lines[2], lines[3], lines[4] };
			TelnetCommand(commands);
		}
	}
}