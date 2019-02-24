package org.persianpros.mammadalius;

import org.persianpros.mammadalius.items.I001_ToolsActivity;
import org.persianpros.mammadalius.items.I002_BackupRestoreActivity;
import org.persianpros.mammadalius.items.I003_EmulatorManagerActivity;
import org.persianpros.mammadalius.items.I004_KeysSoftcamDownloaderActivity;
import org.persianpros.mammadalius.items.I005_ChannelsSettingsDownloaderActivity;
import org.persianpros.mammadalius.items.I006_CablesSatellitesXMLDownloaderActivity;
import org.persianpros.mammadalius.items.I007_CleanerActivity;
import org.persianpros.mammadalius.items.I008_InstallerActivity;
import org.persianpros.mammadalius.items.I009_ExtractorConverterActivity;
import org.persianpros.mammadalius.items.I010_SoftwareHardwareInformationActivity;
import org.persianpros.mammadalius.items.I011_FontChangerActivity;
import org.persianpros.mammadalius.items.I012_PersianEmpireEnigma2ImageActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GrandeurActivity extends Activity 
{
	protected void onCreate(android.os.Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grandeur);
		ListView list = (ListView) findViewById(R.id.lstItems);
//		list.setAdapter(ArrayAdapter.createFromResource(this,
//				R.array.PersianPalace,
//				android.R.layout.simple_list_item_1));
		list.setAdapter(new SectionAdapter(this,
				R.layout.list_item_with_separator,
				R.array.PersianPalace));
		list.setDivider(getResources().getDrawable(R.drawable.list_divider));

		list.setTextFilterEnabled(true);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		list.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				Intent intent;
				switch (position)
				{
				case 0:
					intent = new Intent(getApplicationContext(), I001_ToolsActivity.class);
					intent.putExtra("resID", R.array.Tools);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), I002_BackupRestoreActivity.class);
					intent.putExtra("resID", R.array.Backup_Restore);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(getApplicationContext(), I003_EmulatorManagerActivity.class);
					intent.putExtra("resID", R.array.Emulator_Manager);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent(getApplicationContext(), I004_KeysSoftcamDownloaderActivity.class);
					intent.putExtra("resID", R.array.Keys_SoftCam_Downloader);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent(getApplicationContext(), I005_ChannelsSettingsDownloaderActivity.class);
					intent.putExtra("resID", R.array.Channels_Settings_Downloader);
					startActivity(intent);
					break;
				case 5:
					intent = new Intent(getApplicationContext(), I006_CablesSatellitesXMLDownloaderActivity.class);
					intent.putExtra("resID", R.array.Cables_Satellites_Terrestrial_XML_Downloader);
					startActivity(intent);
					break;
				case 6:
					intent = new Intent(getApplicationContext(), I007_CleanerActivity.class);
					intent.putExtra("resID", R.array.Cleaner);
					startActivity(intent);
					break;
				case 7:
					intent = new Intent(getApplicationContext(), I008_InstallerActivity.class);
					intent.putExtra("resID", R.array.Installer);
					startActivity(intent);
					break;
				case 8:
					intent = new Intent(getApplicationContext(), I009_ExtractorConverterActivity.class);
					intent.putExtra("resID", R.array.Extractor_Coverter);
					startActivity(intent);
					break;
				case 9:
					intent = new Intent(getApplicationContext(), I010_SoftwareHardwareInformationActivity.class);
					intent.putExtra("resID", R.array.Software_Hardware_Information);
					startActivity(intent);
					break;
				case 10:
					intent = new Intent(getApplicationContext(), I011_FontChangerActivity.class);
					intent.putExtra("resID", R.array.FontChanger_DejaVuSans_2_33);
					startActivity(intent);
					break;
				case 11:
					intent = new Intent(getApplicationContext(), I012_PersianEmpireEnigma2ImageActivity.class);
					intent.putExtra("resID", R.array.Persian_Empire_Enigma2_Images_Tweaks_Fixes);
					startActivity(intent);
					break;
				default:
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
//		if (item.getItemId() == R.id.btnActbarTelnet)
//		{
//			Toast.makeText(GrandeurActivity.this, "Telnet menu clicked", Toast.LENGTH_SHORT).show();
//			return true;
//		}
		if (item.getItemId() == R.id.menu_About)
		{
			Intent intent = new Intent(getApplicationContext(), AboutDialog.class);
			startActivity(intent);
			return true;
		}
		else if (item.getItemId() == R.id.menu_settings)
		{
			Intent intent = new Intent(getApplicationContext(), SharedPrefs.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}