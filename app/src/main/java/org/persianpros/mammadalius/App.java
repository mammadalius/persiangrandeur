package org.persianpros.mammadalius;

import android.app.Application;

public class App extends Application
{	
	boolean isPassed = false;
	@Override
	public void onCreate() 
	{
		super.onCreate();
		isPassed = true;
	}
}
