package org.persianpros.mammadalius.items;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.persianpros.mammadalius.BaseActivity;
import org.persianpros.mammadalius.DropboxConfirmDialog;
import org.persianpros.mammadalius.GetInputActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class I2_8_1SetupDropBoxAccountActivity extends BaseActivity
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
					intent = new Intent(getApplicationContext(), GetInputActivity.class);
					intent.putExtra("desc", "Enter your Dropbox App Key:");
					intent.putExtra("title", "Input");
					startActivityForResult(intent, 0);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), GetInputActivity.class);
					intent.putExtra("desc", "Enter your Dropbox App Secret:");
					intent.putExtra("title", "Input");
					startActivityForResult(intent, 1);
					break;
				case 2:

					TelnetCommand(new String[] { "/usr/script/DropBox.sh", "noexit" });

					// wait for file in Temp
					try
					{
						Thread.sleep(2000);
					}
					catch (InterruptedException e1)
					{
						e1.printStackTrace();
					}

					// Show a dialog and wait for the user to press ALLOW
					Intent intent2 = new Intent(getApplicationContext(), DropboxConfirmDialog.class);
					startActivityForResult(intent2, 2);

					break;
				case 3:// Uninstall
					TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.DBRM-persianpalace.sh");
					break;
				default:
					Toast.makeText(I2_8_1SetupDropBoxAccountActivity.this, position + " clicked",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK)
		{
			switch (requestCode)
			{
			case 0:// App key
				TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.APPKEY-persianpalace.sh en "
						+ data.getStringExtra("input"));
				break;
			case 1:// App Secret
				TelnetCommand("/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/.Scripts/.APPSECRET-persianpalace.sh en "
						+ data.getStringExtra("input"));
				data.getStringExtra("input");
				break;
			case 2:
				// Enter in Telnet
				sendCommands(new String[] { "\r" });
				break;
			default:
				break;
			}
		}
	}

}
