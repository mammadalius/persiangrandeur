package org.persianpros.mammadalius;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends BaseActivity 
{	
	
	CheckResult telnetCheck = CheckResult.Unknown;
	CheckResult ftpCheck2 = CheckResult.Unknown;
	String resultException = "";
	boolean isButtonClicked = false;
	boolean isTelnetCheckFinished = false;

	String telnetMessage = "";
	String ftpMessage = "";
	String ftpMessage2 = "";
	
	@Override
	protected void onResume() {
		super.onResume();
		init();

	}

	private void init() {
		setContentView(R.layout.main);
		getActionBar().setDisplayHomeAsUpEnabled(false);
		getActionBar().setHomeButtonEnabled(false);

		((Button)findViewById(R.id.btnGrandeur)).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) 
			{
				if (((App)getApplicationContext()).isPassed)
				{
					//show activity
					Intent intent = new Intent(getApplicationContext(), GrandeurActivity.class);
					startActivity(intent);
				}
				else
				{
					if (!isButtonClicked)
					{
						isButtonClicked = true;
						telnetCheck = CheckResult.Unknown;
						ftpCheck2 = CheckResult.Unknown;

						setProgressBarIndeterminateVisibility(true);

						PECheck check = new PECheck();
						check.execute(new Void[]{});
					}
				}
			}
		});

		((Button)findViewById(R.id.btnDroidPE)).setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				try
				{
					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.setClassName("net.reichholf.dreamdroid", "net.reichholf.dreamdroid.activities.TabbedNavigationActivityPE");
					startActivity(intent);
				}
				catch (Exception e)
				{
					Toast.makeText(getApplicationContext(), "Please install the PEDroid package", Toast.LENGTH_SHORT).show();
					//e.printStackTrace();
				}	
			}
		});
		try {
			((TextView)findViewById(R.id.tvVersion)).setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
		} catch (NameNotFoundException e){}
	}
	
	public class PECheck extends AsyncTask <Void, Void, Void> 
	{
		@Override
		protected Void doInBackground(Void... params) 
		{
			try 
			{
				//check FTP
				Log.d("CHECK", "FTP check started.");
				//FTPCheck(); Checking python 2.6 is not required anymore. So we go for Python 2.7
				//FTPCheck2(); new lock structure. not required

				//check Telnet
				Log.d("CHECK", "Telnet check started.");
				//TelnetCommand("hostid"); Checking hostid is not required anymore!
				isTelnetCheckFinished = false;
//				TelnetCommand(new String[] //new lock structure. not required
//						{
//						"busybox > /tmp/.pe",
//						"sed -i -e '1,/functions/d' /tmp/.pe",
//						"md5sum /tmp/.pe",
//						"rm -rf /tmp/.pe"
//						});
				TelnetCommand("cat /proc/stb/info/persianempire");
			} 
			catch (Exception e) 
			{
				//return new String[]{"Exception", e.getMessage()};
				//return new CheckResult[]{telnetCheck, ftpCheck};
				return null;
			}
			//			finally
			//			{
			//				return new CheckResult[]{telnetCheck, ftpCheck};
			//			}
			return null;
		}
	}

	@Override
	public void ShowTelnetResult(String result) 
	{
//		if (result.contains("rm -rf"))//deleting temp file
//			return;

		if (isTelnetCheckFinished)
			return;

		if (!result.contains("Not connected"))
		{
			if (result.contains("Exception"))
			{
				telnetMessage = "Connection problem. " + result;
				SetTelnetResult(CheckResult.Failed);				
			}
			//else if (result.contains("65daecaf3c18d46c98fc986707beb9a7"))
			else if (result.contains("http://e2pe.com"))
				SetTelnetResult(CheckResult.Pass);
			else
			{
				telnetMessage = "Only Compatible with Persian Empire Enigma2 Images.\nPlease refer to http://e2pe.com for more info.";
				SetTelnetResult(CheckResult.NotPE);
			}
		}
		else
		{
			telnetMessage = "Connection problem. ";
			SetTelnetResult(CheckResult.Failed);
		}
	}

	private void FTPCheck2()
	{
		SharedPreferences prf = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		AsyncFTPClient2 ftp2 = new AsyncFTPClient2();
		//String[] params = new String[]{"192.168.137.2", "root", "", "/usr/lib/enigma2/python/Plugins/Extensions/PersianPalace/"};
		//ftp.execute(new String[]{server, username, password, folder});
		try 
		{
			String address = "";
			if (!prf.getBoolean("switch", true))
				address = prf.getString("hostname", "");
			else
				address = prf.getString("ip", "");

			ftp2.execute(
					address,
					prf.getString("user", ""),
					prf.getString("pass", ""), 
					"/usr/lib/python2.7/");
		}
		catch (Exception e) 
		{
			ftpMessage2 = "This App is only working with Persian Empire.\nPlease refer to http://PersianPros.org for more info.";
			SetFTPResult2(CheckResult.NotPE);
		}
	}

	private class AsyncFTPClient2 extends AsyncTask <String, String, String[]> 
	{

		@Override
		protected String[] doInBackground(String... params)
		{
			try {
				return getList2(params);
			} catch (SocketException e) {
				return new String[]{"SocketException", e.getMessage()};
			} catch (UnknownHostException e) {
				return new String[]{"UnknownHostException", e.getMessage()};
			} catch (IOException e) {
				return new String[]{"IOException", e.getMessage()};
			} catch (Exception e) {
				return new String[]{"Exception", e.getMessage()};
			}
		}

		private String[] getList2(String[] params) throws SocketException, UnknownHostException, IOException
		{
			FTPClient ftpClient = new FTPClient();
			ftpClient.setConnectTimeout(2000);
			ftpClient.connect(InetAddress.getByName(params[0]));
			ftpClient.enterLocalPassiveMode();

			ftpClient.login(params[1], params[2]);
			ftpClient.changeWorkingDirectory(params[3]);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			String[] files = null;
			files = ftpClient.listNames();

			for (String string : files) 
			{
				Log.d("FTP LIST 2", string);
			}

			ftpClient.logout();
			ftpClient.disconnect();
			return files;
		}

		@Override
		protected void onPostExecute(String[] result) 
		{
			super.onPostExecute(result);
			if (result != null && result.length == 2 && result[0].contains("Exception"))
			{
				ftpMessage2 = "Connection Error. " + result[1];
				SetFTPResult2(CheckResult.Failed);				
			}
			else
			{
				for (String file : result) 
				{
					if (file.contentEquals("pe.pyo"))
					{
						SetFTPResult2(CheckResult.Pass);
						return;
					}
				}
				ftpMessage2 = "This App is only working with Persian Empire.\nPlease refer to http://e2pe.com for more info.";
				SetFTPResult2(CheckResult.NotPE);				
			}
		}
	}

	private void SetTelnetResult(CheckResult cr)
	{
		telnetCheck = cr;
		Log.d("Check", "Telnet result: " + telnetCheck.toString());

		isTelnetCheckFinished = true;
		//TelnetCommand("rm -rf /tmp/.pe");//delete temp file

		//MakeDecision2();
		MakeDecision3();
	}

	//	private void SetFTPResult(CheckResult cr) 
	//	{
	//		ftpCheck = cr;
	//		Log.d("Check", "FTP result: " + ftpCheck.toString());
	//		MakeDecision();
	//	}

	private void SetFTPResult2(CheckResult cr)
	{
		ftpCheck2 = cr;
		Log.d("Check", "FTP result 2: " + ftpCheck2.toString());
		MakeDecision2();
	}

	private void MakeDecision2()
	{
		if (telnetCheck == CheckResult.Pass && ftpCheck2 == CheckResult.Pass)//Passed
		{
			setProgressBarIndeterminateVisibility(false);
			((App)getApplicationContext()).isPassed = true;
			Intent intent = new Intent(getApplicationContext(), GrandeurActivity.class);
			startActivity(intent);
		}
		else//not completed / connection problem / not persian empire 
		{
			if (telnetCheck == CheckResult.NotPE)//Absolutely NOT PE
			{
				Toast.makeText(getApplicationContext(), telnetMessage, Toast.LENGTH_LONG).show();
				setProgressBarIndeterminateVisibility(false);
				recreate();
			}
			else if (telnetCheck == CheckResult.Failed)
			{				
				Toast.makeText(getApplicationContext(), telnetMessage, Toast.LENGTH_LONG).show();
				setProgressBarIndeterminateVisibility(false);
				recreate();
			}
			else if (telnetCheck == CheckResult.Unknown)
			{
				//Do nothing (wait for the other check)
			}
			else if (telnetCheck == CheckResult.Pass)
			{
				if (ftpCheck2 == CheckResult.NotPE)
				{
					Toast.makeText(getApplicationContext(), ftpMessage2, Toast.LENGTH_LONG).show();
					setProgressBarIndeterminateVisibility(false);
					recreate();
				}
				else if (ftpCheck2 == CheckResult.Failed)
				{
					Toast.makeText(getApplicationContext(), ftpMessage2, Toast.LENGTH_LONG).show();
					setProgressBarIndeterminateVisibility(false);
					recreate();
				}
				else if (ftpCheck2 == CheckResult.Unknown)//Pass (will not reach this line) (just for clarify)
				{
					//Do nothing (wait for the other check)
				}
				else if (ftpCheck2 == CheckResult.Pass)//Pass
				{
					setProgressBarIndeterminateVisibility(false);
					((App)getApplicationContext()).isPassed = true;
					Intent intent = new Intent(getApplicationContext(), GrandeurActivity.class);
					startActivity(intent);
				}					
			}
		}
	}
	
	private void MakeDecision3()
	{
		if (telnetCheck == CheckResult.Pass)//Passed
		{
			setProgressBarIndeterminateVisibility(false);
			((App)getApplicationContext()).isPassed = true;
			Intent intent = new Intent(getApplicationContext(), GrandeurActivity.class);
			startActivity(intent);
		}
		else//not completed / connection problem / not persian empire 
		{
			if (telnetCheck == CheckResult.NotPE)//Absolutely NOT PE
			{
				Toast.makeText(getApplicationContext(), telnetMessage, Toast.LENGTH_LONG).show();
				setProgressBarIndeterminateVisibility(false);
				recreate();
			}
			else if (telnetCheck == CheckResult.Failed)
			{				
				Toast.makeText(getApplicationContext(), telnetMessage, Toast.LENGTH_LONG).show();
				setProgressBarIndeterminateVisibility(false);
				recreate();
			}
			else if (telnetCheck == CheckResult.Unknown)
			{
				//Do nothing (wait for the other check)
			}
		}
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
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) 
	{	
		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
			init();
		else
			init();
	}

	private enum CheckResult
	{
		Unknown, Pass, Failed, NotPE
	}	
}