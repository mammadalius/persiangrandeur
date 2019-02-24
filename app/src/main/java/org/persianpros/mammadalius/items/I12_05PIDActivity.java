package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.BissKeyAdderActivity;
import org.persianpros.mammadalius.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I12_05PIDActivity extends BaseActivity 
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
					intent = new Intent(getApplicationContext(), BissKeyAdderActivity.class);
					intent.putExtra("type", 2);
					intent.putExtra("desc", getResources().getString(R.string.AudioPIDDescHex));
					intent.putExtra("title", getResources().getString(R.string.AudioPIDTitleHex));
					startActivityForResult(intent, 2);
					//Telnet on getting result
					break;
				case 1:
					intent = new Intent(getApplicationContext(), BissKeyAdderActivity.class);
					intent.putExtra("type", 3);
					intent.putExtra("desc", getResources().getString(R.string.AudioPIDDescDecimal));
					intent.putExtra("title", getResources().getString(R.string.AudioPIDTitleDecimal));
					startActivityForResult(intent, 3);
					//Telnet on getting result
					break;
				case 2:
					intent = new Intent(getApplicationContext(), BissKeyAdderActivity.class);
					intent.putExtra("type", 4);
					intent.putExtra("desc", getResources().getString(R.string.VideoPIDDescHex));
					intent.putExtra("title", getResources().getString(R.string.VideoPIDTitleHex));
					startActivityForResult(intent, 4);
					//Telnet on getting result
					break;
				case 3:
					intent = new Intent(getApplicationContext(), BissKeyAdderActivity.class);
					intent.putExtra("type", 5);
					intent.putExtra("desc", getResources().getString(R.string.VideoPIDDescDecimal));
					intent.putExtra("title", getResources().getString(R.string.VideoPIDTitleDecimal));
					startActivityForResult(intent, 5);
					//Telnet on getting result
					break;
				default:
					Toast.makeText(I12_05PIDActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
				}				
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK)
		{
			switch (requestCode) 
			{
			case 2:
				TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.AudioPID-persianpalace.sh en " +
						data.getStringExtra("code"));
				break;
			case 3:
				TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.AudioPIDDec-persianpalace.sh en " +
						data.getStringExtra("code"));
				break;
			case 4:
				TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.VideoPID-persianpalace.sh en " +
						data.getStringExtra("code"));
				break;
			case 5:
				TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.VideoPIDDec-persianpalace.sh en " +
						data.getStringExtra("code"));
				break;
			default:
				break;
			}
		}
	}
}
