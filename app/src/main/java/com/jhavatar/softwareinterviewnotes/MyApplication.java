package com.jhavatar.softwareinterviewnotes;

import java.lang.reflect.Field;

import android.app.Application;
import android.content.Context;
import android.view.ViewConfiguration;

public class MyApplication extends Application {
	
	private static final Object appContextSyncLock = new Object();
	private static Context appContext;
	
	public static Context getAppContext()
	{
		synchronized(appContextSyncLock)
		{
			return appContext;
		}
	}
	
	private static void setAppContext(Context context)
	{
		synchronized(appContextSyncLock)
		{
			MyApplication.appContext = context;
		}
	}
	
	
	public void onCreate ()
	{
		super.onCreate();
		
		MyApplication.setAppContext(this.getApplicationContext());
		
		// Hack to make action bar overflow visible on devices with hard menu button
		try {
	        ViewConfiguration config = ViewConfiguration.get(this);
	        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
	        if(menuKeyField != null) {
	            menuKeyField.setAccessible(true);
	            menuKeyField.setBoolean(config, false);
	        
	        }
	    } 
		catch (Exception ex) {
	        // Ignore
	    }
	}

}
