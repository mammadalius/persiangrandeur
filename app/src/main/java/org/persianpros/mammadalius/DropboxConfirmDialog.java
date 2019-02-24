package org.persianpros.mammadalius;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DropboxConfirmDialog extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dropbox_confirm);

		((Button) findViewById(R.id.btnOpenWebPage)).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// Run the url
				SharedPreferences prf = PreferenceManager
						.getDefaultSharedPreferences(getApplicationContext());
				AsyncFTPDownload ftp = new AsyncFTPDownload();
				try
				{
					String address = "";
					if (!prf.getBoolean("switch", true))
						address = prf.getString("hostname", "");
					else
						address = prf.getString("ip", "");

					ftp.execute(address, prf.getString("user", ""), prf.getString("pass", ""),
							"/var/volatile/tmp");
				}
				catch (Exception e)
				{
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT)
							.show();

				}

				// TelnetCommand("cat /var/volatile/tmp/xx.htm");
			}
		});

		((Button) findViewById(R.id.btnDone)).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				setResult(RESULT_OK);
				File f = new File(Environment.getExternalStorageDirectory(), "DropBox.html");
				boolean res = f.delete();
				Log.d("DropBox", "File removed? " + res);
				finish();
			}
		});
	}

	// @Override
	// public void ShowTelnetResult(String result)
	// {
	// Intent intent = new Intent(getApplicationContext(), ResultDialog.class);
	// if (!result.contains("Not connected"))
	// {
	// int start = result.indexOf("<html");
	// int end = result.lastIndexOf("</html>") + 7;
	// try
	// {
	// result = result.substring(start, end);
	// result = result.trim();
	// }
	// catch (Exception e)
	// {
	// result = "Error on parsing received data.";
	// Log.d("BaseActivity", "Exception on result subString. Message: " +
	// e.getMessage());
	// }
	//
	// makeFile("DropBox.html", result);
	// }
	// else
	// // Not connected
	// {
	// intent.putExtra("text", result);
	// startActivity(intent);
	// recreate();
	// }
	// }
	//
	// public void makeFile(String fileName, String body)
	// {
	// try
	// {
	// File root = new File(Environment.getExternalStorageDirectory(),
	// "PersianGrandeur");
	// if (!root.exists())
	// {
	// root.mkdirs();
	// }
	// File gpxfile = new File(root, fileName);
	// FileWriter writer = new FileWriter(gpxfile);
	// writer.append(body);
	// writer.flush();
	// writer.close();
	// Toast.makeText(this, "Cached", Toast.LENGTH_SHORT).show();
	// }
	// catch (IOException e)
	// {
	// e.printStackTrace();
	// }
	// }

	private class AsyncFTPDownload extends AsyncTask<String, String, String>
	{

		@Override
		protected String doInBackground(String... params)
		{
			try
			{
				return ftpDownload(params);
			}
			catch (SocketException e)
			{
				return "SocketException. " + e.getMessage();
			}
			catch (UnknownHostException e)
			{
				return "UnknownHostException. " + e.getMessage();
			}
			catch (IOException e)
			{
				return "IOException. " + e.getMessage();
			}
			catch (Exception e)
			{
				return "Exception. " + e.getMessage();
			}
		}

		private String ftpDownload(String[] params) throws SocketException, UnknownHostException,
				IOException
		{
			FTPClient ftpClient = new FTPClient();
			ftpClient.setConnectTimeout(2000);
			ftpClient.connect(InetAddress.getByName(params[0]));
			ftpClient.enterLocalPassiveMode();

			ftpClient.login(params[1], params[2]);
			ftpClient.changeWorkingDirectory(params[3]);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

//			File root = new File(Environment.getExternalStorageDirectory(), "PersianGrandeur");
//			if (!root.exists())
//			{
//				root.mkdirs();
//			}

			OutputStream output;
			output = new FileOutputStream(new File(Environment.getExternalStorageDirectory(),
					"DropBox.html"));
			ftpClient.retrieveFile("DropBox.html", output);
			output.close();

			ftpClient.logout();
			ftpClient.disconnect();
			return Environment.getExternalStorageDirectory() + "/DropBox.html";
		}

		@Override
		protected void onPostExecute(String result)
		{
			super.onPostExecute(result);
			if (result != null && result.contains("Exception"))
			{
				// ftpMessage2 = "Connection Error. " + result[1];
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			}
			else
			{
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.fromFile(new File(result)));
				try
				{
					intent.setClassName("com.android.browser",
							"com.android.browser.BrowserActivity");
					startActivity(intent);
				}
				catch (Exception e)
				{
					intent.setClassName("com.android.chrome", "com.android.chrome.Main");
					startActivity(intent);
				}
			}
		}
	}

}
