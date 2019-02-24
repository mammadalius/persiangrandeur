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
import android.widget.AdapterView.OnItemClickListener;

public class I3_2MultiCamsActivity extends BaseActivity 
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
		Intent intent;
		switch (currentPosition)
		{
		case 0:
			//menu.setHeaderTitle("ScEmu 1.8 + CCcam 2.3.0");
			menu.setHeaderTitle(getResources().getStringArray(R.array.Multi_Cams_Vresions)[0]);
			menu.add(2, 0, 0, getResources().getString(R.string.Enable_Start));  
			menu.add(2, 1, 0, getResources().getString(R.string.Disable_Stop));
			break;
		case 1:
			//menu.setHeaderTitle("ScEmu 1.8 + CCcam 2.3.0");
			menu.setHeaderTitle(getResources().getStringArray(R.array.Multi_Cams_Vresions)[1]);
			menu.add(0, 0, 0, getResources().getString(R.string.Enable_Start));  
			menu.add(0, 1, 0, getResources().getString(R.string.Disable_Stop));
			break;
		case 2:
			//menu.setHeaderTitle("MgCamd 1.38 + CCcam 2.3.0");
			menu.setHeaderTitle(getResources().getStringArray(R.array.Multi_Cams_Vresions)[2]);
			menu.add(1, 0, 0,getResources().getString(R.string.Enable_Start));
			menu.add(1, 1, 0,getResources().getString(R.string.Disable_Stop));
			menu.add(1, 2, 0,"cccamd.list Creator");
			menu.add(1, 3, 0,"MgCamd-CCcam Ready Mode");

			break;
		case 3:
			//			menu.setHeaderTitle("SBox");
			//			menu.add(2, 0, 0,"SBox + CCcam");
			//			menu.add(2, 1, 0,"SBox + MgCamd");
			intent = new Intent(getApplicationContext(), I3_2_3SBoxActivity.class);
			intent.putExtra("resID", R.array.SBox_Requires_CCcam_and_MgCamd);
			startActivity(intent);
			break;
		case 4:
			//			menu.setHeaderTitle("OSCam");
			//			menu.add(3, 0, 0,"OSCam + SBox");
			//			menu.add(3, 1, 0,"OSCam + CCcam");
			//			menu.add(3, 2, 0,"OSCam + MgCamd");
			intent = new Intent(getApplicationContext(), I3_2_4OSCamActivity.class);
			intent.putExtra("resID", R.array.OSCam_Requires_CCcam_and_MgCamd_and_SBox);
			startActivity(intent);
			break;
		case 5://DOSCam
			intent = new Intent(getApplicationContext(), I3_2_4n_DOSCamActivity.class);
			intent.putExtra("resID", R.array.DOSCam_Requires_CCcam_and_MgCamd_and_SBox);
			startActivity(intent);
			break;
		case 6:
			//			menu.setHeaderTitle("OSCam YMod");
			//			menu.add(4, 0, 0,"OSCam YMod + SBox");
			//			menu.add(4, 1, 0,"OSCam YMod + CCcam");
			//			menu.add(4, 2, 0,"OSCam YMod + MgCamd");
			intent = new Intent(getApplicationContext(), I3_2_5OSCamYModActivity.class);
			intent.putExtra("resID", R.array.OSCam_YMod_Requires_CCcam_and_MgCamd_and_SBox);
			startActivity(intent);
			break;
		case 7:
			//			menu.setHeaderTitle("OSCam YMod");
			//			menu.add(4, 0, 0,"OSCam YMod + SBox");
			//			menu.add(4, 1, 0,"OSCam YMod + CCcam");
			//			menu.add(4, 2, 0,"OSCam YMod + MgCamd");
			intent = new Intent(getApplicationContext(), I3_2_6OSCamHDSCActivity.class);
			intent.putExtra("resID", R.array.OSCam_HDSC_Requires_CCcam_MgCamd_SBox);
			startActivity(intent);
			break;
		case 8:
			//			menu.setHeaderTitle("NewCS");
			//			menu.add(5, 0, 0,"NewCS + MBox");
			//			menu.add(5, 1, 0,"NewCS + Camd3");
			//			menu.add(5, 2, 0,"NewCS + CCcam");
			//			menu.add(5, 3, 0,"NewCS + MgCamd");
			//			menu.add(5, 4, 0,"NewCS + EvoCamd");
			intent = new Intent(getApplicationContext(), I3_2_6NewCSActivity.class);
			intent.putExtra("resID", R.array.NewCS_Requires_Camd3_and_CCcam_and_EvoCamd_and_Mbox_and_MgCamd);
			startActivity(intent);
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
				TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.ScEmu18-CCcam230-persianpalace.sh");
			else if (item.getItemId() == 1)
				TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.ScEmu18-CCcam230-persianpalace.sh");
			break;
		case 1:
			if (item.getItemId() == 0)
				TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MgCamd138c-CCcam230-persianpalace.sh");
			else if (item.getItemId() == 1)
				TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MgCamd138c-CCcam230-persianpalace.sh");
			else if (item.getItemId() == 2) ///usr/script/CreateCCcamLIST.sh
			{
				Intent intent = new Intent(getApplicationContext(), ClineActivity.class);
				intent.putExtra("title", "cccamd.list Creator");
				startActivityForResult(intent, 6);
			}
			else if (item.getItemId() == 3)
				TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.ReadyMode-persianpalace.sh");
			break;
		case 2:
			if (item.getItemId() == 0)
				TelnetEnable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.RqCS111-CCcam230-persianpalace.sh");
			else if (item.getItemId() == 1)
				TelnetDisable("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.RqCS111-CCcam230-persianpalace.sh");
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{	
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 6 && resultCode == RESULT_OK)
		{
			String[] lines = data.getStringArrayExtra("lines");
			String[] commands = new String[] {"/usr/script/CreateCCcamLIST.sh",
					lines[0], lines[1], lines[2], lines[3], lines[4] };
			TelnetCommand(commands);
		}
	}
}