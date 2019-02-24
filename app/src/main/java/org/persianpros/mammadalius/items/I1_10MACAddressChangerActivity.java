package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.GetPathActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I1_10MACAddressChangerActivity extends BaseActivity
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
					// Get MAC
					intent = new Intent(getApplicationContext(), GetPathActivity.class);
					intent.putExtra("desc", "Enter Desired MAC Address:");
					intent.putExtra("title", "Custom MAC Address");
					startActivityForResult(intent, 1);
					// Telnet on getting result
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MACRemover-persianpalace.sh");
					break;
				default:
					Toast.makeText(I1_10MACAddressChangerActivity.this, position + " clicked",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK)
		{
			TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.MACChanger-persianpalace.sh en "
					+ data.getStringExtra("path"));
		}
	}
}
