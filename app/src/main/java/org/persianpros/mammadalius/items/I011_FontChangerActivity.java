package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.GetPathActivity;
import org.persianpros.mammadalius.R;
import org.persianpros.mammadalius.ResultDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I011_FontChangerActivity extends BaseActivity
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
					// Get path
					Intent intent2 = new Intent(getApplicationContext(), GetPathActivity.class);
					intent2.putExtra("desc", "Enter Your Desired Font Name Without .ttf");
					intent2.putExtra("title", "Desired Font");
					startActivityForResult(intent2, 4);
					// Telnet on getting result
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.ChangeFontFreeSerif-persianpalace.sh");
					break;
				case 2:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.ChangeFont-persianpalace.sh");
					break;
				case 3:
					// Read Before Do Anything
					Intent intent = new Intent(getApplicationContext(), ResultDialog.class);
					intent.putExtra("text",
							getResources().getString(R.string.Read_Before_Do_Anything));
					startActivity(intent);
					break;
				case 4:
					// Plugin: Font Magnifier
					break;
				default:
					Toast.makeText(I011_FontChangerActivity.this, position + " clicked",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 4 && resultCode == RESULT_OK)
		{
			TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DesiredFont-persianpalace.sh en "
					+ data.getStringExtra("path"));
		}
	}
}
