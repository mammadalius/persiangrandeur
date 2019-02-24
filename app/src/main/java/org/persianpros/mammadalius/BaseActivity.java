package org.persianpros.mammadalius;

import jackpal.androidterm.emulatorview.EmulatorView;
import jackpal.androidterm.emulatorview.TermSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

public abstract class BaseActivity extends Activity
{
	EditText mEntry;
	EmulatorView mEmulatorView;
	TermSession mSession;
	String[] commands;
	String username;
	String password;
	String telnetRespond = "";
	OnRespondListener resListener;
	boolean isConnected = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		setContentView(R.layout.grandeur3);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		ListView list = (ListView) findViewById(R.id.lstItems);
		//		list.setAdapter(ArrayAdapter.createFromResource(this,
		//				getIntent().getIntExtra("resID", R.array.PersianPalace),
		//				android.R.layout.simple_list_item_1));
		list.setAdapter(new SectionAdapter(this,
				R.layout.list_item_with_separator,
				getIntent().getIntExtra("resID", R.array.PersianPalace)));
		list.setDivider(getResources().getDrawable(R.drawable.list_divider));

		list.setTextFilterEnabled(true);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public ListView getListView()
	{
		return (ListView) findViewById(R.id.lstItems);
	}

	public String TelnetCommand(String command)
	{
		String result = "unknown";
		try
		{
			//SendWithTelnet("192.168.137.2", "root", "", new String[] {command});
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());			

			String address = "";
			if (!prefs.getBoolean("switch", true))
				address = prefs.getString("hostname", "");
			else
				address = prefs.getString("ip", "");

			SendWithTelnet(address,
					prefs.getString("user", ""),
					prefs.getString("pass", ""),
					new String[] {command});
			result = "Done";
		}
		catch (Exception e) 
		{
			result = "Exception: " + e.getMessage();
			return result;
		}
		return result;
	}

	public String TelnetCommand(String[] commands)
	{
		setProgressBarIndeterminateVisibility(true);
		String result = "";
		try 
		{
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

			String address = "";
			if (!prefs.getBoolean("switch", true))
				address = prefs.getString("hostname", "");
			else
				address = prefs.getString("ip", "");

			SendWithTelnet(address,
					prefs.getString("user", ""),
					prefs.getString("pass", ""),
					commands);

			result = "Done";
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	public String TelnetEnable(String command)
	{
		return TelnetCommand(new String[] {
				command + " enable",
				command + " start"
		});
	}

	public String TelnetDisable(String command)
	{
		return TelnetCommand(new String[] {
				command + " disable",
				command + " stop"
		});
	}














	private void SendWithTelnet(String host, String user, String pass, String[] commands) throws Exception 
	{
		this.commands = commands;
		this.username = user;
		this.password = pass;
		/**
		 * EmulatorView setup.
		 */
		EmulatorView view = (EmulatorView)findViewById(R.id.emulatorView);
		mEmulatorView = view;

		/* Let the EmulatorView know the screen's density. */
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		view.setDensity(metrics);

		@SuppressWarnings("unused")
		TermSession session = null;
		connectToTelnet(host);

		/* Attach the TermSession to the EmulatorView. */
		//view.attachSession(session);

		/* That's all you have to do!  The EmulatorView will call the attached
           TermSession's initializeEmulator() automatically, once it can
           calculate the appropriate screen size for the terminal emulator. */

		//return mSession.getRespond();
	}

	/**
	 * Connect to the Telnet server.
	 */
	public void connectToTelnet(String server) 
	{
		String[] telnetServer = server.split(":", 2);
		final String hostname = telnetServer[0];
		int port = 23;
		if (telnetServer.length == 2) {
			port = Integer.parseInt(telnetServer[1]);
		}
		final int portNum = port;

		/* On Android API >= 11 (Honeycomb and later), network operations
           must be done off the main thread, so kick off a new thread to
           perform the connect. */
		new Thread() {
			public void run() {
				// Connect to the server
				try {
					//Socket socket = new Socket(hostname, portNum);

					Socket socket = new Socket();
					SocketAddress adr = new InetSocketAddress(hostname, portNum);
					socket.connect(adr, 2000);
					if (socket.isConnected())
					{				
						mSocket = socket;
					}
					else
					{
						Log.e("Telnet", "Not connected in 2 seconds.");
						mHandler.sendEmptyMessage(MSG_NOTCONNECTED);
						return;
					}
				} catch (UnknownHostException e) {
					Log.e("Telnet", e.toString());
					mHandler.sendEmptyMessage(MSG_NOTCONNECTED);
					return;
				} catch (IOException e) {
					Log.e("Telnet", e.toString());
					mHandler.sendEmptyMessage(MSG_NOTCONNECTED);
					return;
				}

				// Notify the main thread of the connection
				mHandler.sendEmptyMessage(MSG_CONNECTED);
			}
		}.start();
	}

	/**
	 * Handler which will receive the message from the Telnet connect thread
	 * that the connection has been established.
	 */
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == MSG_CONNECTED) {
				//Log.d("Telnet", "Connceted on handler.");
				createTelnetSession();
			} else if (msg.what == MSG_NOTCONNECTED)
			{
				//Log.d("Telnet", "Not connceted on handler.");
				ShowTelnetResult("Not connected");
			}
		}
	};

	Socket mSocket;
	private static final int MSG_CONNECTED = 1;
	private static final int MSG_NOTCONNECTED = 2;

	/** Create the TermSession which will handle the Telnet protocol and
    terminal emulation. */
	private void createTelnetSession() {
		Socket socket = mSocket;

		// Get the socket's input and output streams
		InputStream termIn;
		OutputStream termOut;
		try {
			termIn = socket.getInputStream();
			termOut = socket.getOutputStream();
		} catch (IOException e) {
			// Handle exception here
			return;
		}

		/* Create the TermSession and attach it to the view.  See the
        TelnetSession class for details. */
		TermSession session = new TelnetSession(termIn, termOut);
		mEmulatorView.attachSession(session);
		mSession = session;
		mSession.getRespond();//Clear the buffer


		mSession.SetRespondListener(new OnRespondListener() 
		{			
			public void onEvent(String message) 
			{
				ShowTelnetResult(message);
			}

			@Override
			public void onConnectedAndReceivedData() 
			{
				isConnected = true;
				sendCommands();
			}
		});


	}

	int numCommands;
	int counter;

	private void sendCommands()
	{
		numCommands = commands.length;
		counter = 0;
		final java.util.Timer timer1 = new Timer();

		java.util.TimerTask ttask = new TimerTask() {

			@Override
			public void run() 
			{
				Log.d("Telnet", "Total Commands: [" + numCommands + "] Counter: [" + counter + "]");
				if (numCommands > counter)
				{
					mSession.write(commands[counter] + "\r");
					counter = counter + 1;
					if (numCommands > counter && commands[counter].contains("noexit"))//Leave the telnet connection OPEN
						timer1.cancel();
				}
				else
				{
					mSession.write("exit\r");
					timer1.cancel();
				}
			}
		};

		if (isConnected)
		{
			mSession.write(username + "\r");
			if (password.length() > 0)
				mSession.write(password + "\r");
			timer1.scheduleAtFixedRate(ttask, 500, 500);
		}

		//**********************OLD TELNET*************************
		//		if (isConnected)
		//		{
		//			mSession.write(username + "\r");
		//			if (password.length() > 0)
		//				mSession.write(password + "\r");
		//
		//			for (String string : commands) 
		//			{
		//				mSession.write(string + "\r");
		//			}
		//
		//			mSession.write("exit\r");
		//		}
		//		else
		//		{	
		//
		//		}
	}
	
	public void sendCommands(final String[] cmds)
	{
		numCommands = cmds.length;
		counter = 0;
		final java.util.Timer timer1 = new Timer();

		java.util.TimerTask ttask = new TimerTask() {

			@Override
			public void run() 
			{
				Log.d("Telnet", "Total Commands: [" + numCommands + "] Counter: [" + counter + "]");
				if (numCommands > counter)
				{
					mSession.write(cmds[counter] + "\r");
					counter = counter + 1;
//					if (cmds[counter].contains("noexit"))//Leave the telnet connection OPEN
//						timer1.cancel();
				}
				else
				{
					mSession.write("exit\r");
					timer1.cancel();
				}
			}
		};

		if (isConnected)
		{
			mSession.write(username + "\r");
			if (password.length() > 0)
				mSession.write(password + "\r");
			timer1.scheduleAtFixedRate(ttask, 500, 500);
		}
	}

	public void ShowTelnetResult(String result)
	{
		Intent intent = new Intent(getApplicationContext(), ResultDialog.class);
		if (!result.contains("Not connected"))
		{
			setProgressBarIndeterminateVisibility(true);

			int start = result.indexOf("~# ") + 3;
			int end = result.lastIndexOf("Persian Grandeur Ready") - 1;
			try 
			{
				SharedPreferences prfs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				result = result.substring(start, end);
				String c = prfs.getString("user", "") + ".*:~#.*";
				result = result.replaceAll(c, "");	
				result = result.replaceAll(".*Scripts.*", "");
				result = result.replaceAll(".*.sh.*", "");
				result = result.replaceAll("Persian Grandeur Ready", "");

				result = result.trim();
			} 
			catch (Exception e) 
			{
				result = "Error on parsing received data.";
				Log.d("BaseActivity", "Exception on result subString. Message: " + e.getMessage());
			}

			intent.putExtra("text", result);
			startActivity(intent);
			recreate();
		}
		else //Not connected
		{
			intent.putExtra("text", result);
			startActivity(intent);
			recreate();
		}
	}	
}