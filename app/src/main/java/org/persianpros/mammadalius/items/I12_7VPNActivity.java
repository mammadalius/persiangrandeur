package org.persianpros.mammadalius.items;

import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.VPNActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I12_7VPNActivity extends BaseActivity 
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
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.TunnelStop-persianpalace.sh");
					break;
				case 1:
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.TunnelRestart-persianpalace.sh");
					break;
				case 2:
					Intent intent = new Intent(getApplicationContext(), VPNActivity.class);					
					startActivityForResult(intent, 1);
					break;
				default:
					Toast.makeText(I12_7VPNActivity.this, position + " clicked", Toast.LENGTH_SHORT).show();
				}				
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{ 
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK && data != null)
		{
			TelnetCommand(
					"/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.TunnelStart-persianpalace.sh en " + 
							data.getStringExtra("user") + ":" + 
							data.getStringExtra("pass") + ":" + 
							data.getStringExtra("server"));
		}
	}
}
