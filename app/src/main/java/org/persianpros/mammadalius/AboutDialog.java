package org.persianpros.mammadalius;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AboutDialog extends Activity 
{
	String currentApplicationVersion = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_dialog);
		findViewById(R.id.btnCloseAbout).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) 
			{
				finish();
			}
		});
		try {
			currentApplicationVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e1) {
		}

		((TextView)findViewById(R.id.tvBuildDate)).setText("Build Date: " + getResources().getString(R.string.Build_Date));
		((TextView)findViewById(R.id.tvPersianGrandeur)).setText("Persian Grandeur " + currentApplicationVersion);

		((Button)findViewById(R.id.btnCheckForUpdate)).setOnClickListener(new OnClickListener() {

			public void onClick(View v) 
			{
				final AsyncGetVersion versionGetter = new AsyncGetVersion();
				versionGetter.execute("http://persiangrandeur.e2pe.com/version.txt");
				findViewById(R.id.pgGetVersion).setVisibility(View.VISIBLE);
				Log.d("VERSION", "Execution started");
			}
		});
		
		((Button)findViewById(R.id.btnReleaseNotes)).setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				final AsyncGetReleaseNotes releaseNotesGetter = new AsyncGetReleaseNotes();
				releaseNotesGetter.execute("http://persiangrandeur.e2pe.com/changelogs.txt");
				findViewById(R.id.pgGetReleaseNotes).setVisibility(View.VISIBLE);
				Log.d("ReleaseNotes", "Execution started");
			}
		});
	}
	String version = "";
	private void SetVersionString(String version)
	{
		this.version = version;
		Log.d("VERSION", version);		
		//findViewById(R.id.pgGetVersion).setVisibility(View.INVISIBLE);

		if (this.version.equals(currentApplicationVersion))
			Toast.makeText(getApplicationContext(), "You already have the latest version", Toast.LENGTH_LONG).show();
		else if (this.version.equals("Canceled"))
			Toast.makeText(getApplicationContext(), "No connection could be made!", Toast.LENGTH_LONG).show();
		else if (this.version.contains("Exception"))
			Toast.makeText(getApplicationContext(), "Problem on receiving result from server!", Toast.LENGTH_LONG).show();
		else
			for (int i=0; i < 2; i++)
				Toast.makeText(getApplicationContext(), "Newer version [" + this.version + "] found, check <http://e2pe.com> for new updates!", Toast.LENGTH_LONG).show();      
	}
	
	private void ShowReleaseNotes(String releaseNotes)
	{
		Log.d("ReleaseNotes", releaseNotes);
		
		Intent intent = new Intent(getApplicationContext(), ResultDialog.class); 
		intent.putExtra("text", releaseNotes);
		startActivity(intent);
	}

	private class AsyncGetVersion extends AsyncTask <String, String, String> 
	{
		@Override
		protected String doInBackground(String... params) {
			String ver = "";
			try {
				ver = GetVersionFromWeb(params[0]);
			} catch (Exception e) {
				SetVersionString(e.getMessage());
			}
			//			SetVersionString(ver);
			return ver;
		}

		@Override
		protected void onPostExecute(String result) 
		{
			super.onPostExecute(result);
			findViewById(R.id.pgGetVersion).setVisibility(View.INVISIBLE);
			SetVersionString(result);
			Log.d("VERSION", "Execution finished");
			if (result != null && result.length() > 0)
				Log.d("VERSION", result);
		}

		@Override
		protected void onCancelled() {
			//super.onCancelled();
			Log.d("VERSION", "Execution canceled");
			SetVersionString("Canceled");
		}

		@SuppressWarnings("finally")
		private String GetVersionFromWeb(String address) throws Exception
		{
			String str = "";
			String ver = "";
			URL url;
			BufferedReader in = null;
			try 
			{
				// Create a URL for the desired page
				url = new URL(address);

				// Read all the text returned by the server
				in = new BufferedReader(new InputStreamReader(url.openStream()));

				while ((str = in.readLine()) != null) {
					// str is one line of text; readLine() strips the newline character(s)
					ver = ver.concat(str);
				}

				return ver;
			}
			catch (MalformedURLException e)
			{
				ver = "Exception: " + e.getMessage();
			}
			catch (IOException e)
			{
				ver = "Exception: " + e.getMessage();
			}
			finally
			{
				if (in != null)
					in.close();
				return ver;
			}
		}
	}
	
	
	private class AsyncGetReleaseNotes extends AsyncTask <String, String, String> 
	{
		@Override
		protected String doInBackground(String... params) {
			String releaseNotes = "";
			try {
				releaseNotes = GetReleaseNotesFromWeb(params[0]);
			} catch (Exception e) {
				ShowReleaseNotes(e.getMessage());
			}
			return releaseNotes;
		}

		@Override
		protected void onPostExecute(String result) 
		{
			super.onPostExecute(result);
			findViewById(R.id.pgGetReleaseNotes).setVisibility(View.INVISIBLE);
			ShowReleaseNotes(result);
			Log.d("ReleaseNotes", "Execution finished");
			if (result != null && result.length() > 0)
				Log.d("ReleaseNotes", result);
		}

		@Override
		protected void onCancelled() {
			//super.onCancelled();
			Log.d("ReleaseNotes", "Execution canceled");
			ShowReleaseNotes("Canceled");
		}

		@SuppressWarnings("finally")
		private String GetReleaseNotesFromWeb(String address) throws Exception
		{
			String str = "";
			String releaseNotes = "";
			URL url;
			BufferedReader in = null;
			try 
			{
				// Create a URL for the desired page
				url = new URL(address);

				// Read all the text returned by the server
				in = new BufferedReader(new InputStreamReader(url.openStream()));

				while ((str = in.readLine()) != null) {
					// str is one line of text; readLine() strips the newline character(s)
					releaseNotes = releaseNotes.concat("\n" + str);
				}

				return releaseNotes;
			}
			catch (MalformedURLException e)
			{
				releaseNotes = "Exception: " + e.getMessage();
			}
			catch (IOException e)
			{
				releaseNotes = "Exception: " + e.getMessage();
			}
			finally
			{
				if (in != null)
					in.close();
				return releaseNotes;
			}
		}
	}
}
