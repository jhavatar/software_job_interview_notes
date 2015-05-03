package com.jhavatar.ads;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class AdmobAds implements AdCompany {
	
	private static final String UNIT_ID = "ca-app-pub-2437963888182986/8868826553";
	
	private void loadAdmobAd(AdView adView)
	{
		if (adView == null)
			Log.w("jhavatar", "Cannot load add since adView is null");
		
		 adView.loadAd(new AdRequest());
	}
	
	public void loadAd(View view)
	{	
		if (view == null)
			return;
		
		if (view instanceof AdView)
			loadAdmobAd((AdView) view);		
	}
	
	public View createAd(Activity activity)
	{
		return new AdView(activity, AdSize.SMART_BANNER, UNIT_ID);
	}
}
