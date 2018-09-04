package com.jhavatar.ads;

import java.util.LinkedList;
import java.util.Queue;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;


import com.jhavatar.softwareinterviewnotes.MyApplication;
import com.jhavatar.softwareinterviewnotes.R;

public class AdUtils {
	public static final int ADMOB = 0;
	public static final int AMAZON = 1;
	
	private static AdUtils instance;
	
	private boolean showAds;
	private AdCompany adCompany;
	private Queue<Integer> remainingAdCompanies;
	
	public static AdUtils getInstance()
	{
		if (instance == null)
			instance = new AdUtils();
		return instance;
	}
	
	public static void setInstance(AdUtils instance)
	{
		AdUtils.instance = instance;
	}
	
	private AdUtils()
	{
		showAds = MyApplication.getAppContext().getResources().getBoolean(R.bool.showAds);
		if (!showAds)
			return;
		
		int[] adCompanyOrder = MyApplication.getAppContext().getResources().getIntArray(R.array.ad_company_order);
		remainingAdCompanies = new LinkedList<Integer>();
		for (int company : adCompanyOrder)
		{
			remainingAdCompanies.add(company);
		}
		this.setNextAddCompany();
	}
	
	
	public void setNextAddCompany()
	{
		if (!showAds)
		{
			Log.w("jhavatar", "Requesting next add company but showAds is set to false");
			return;
		}
		
		boolean foundCompany = false;
		while(!foundCompany && !remainingAdCompanies.isEmpty())
		{
			int company = remainingAdCompanies.remove();
			
			if (company == ADMOB)
			{
				try
				{
					//Log.d("jhavatar", "com.google.ads.AdView");
					Class.forName("com.google.ads.AdView");
					//Log.d("jhavatar", "admob ads lib found");
					adCompany = new AdmobAds();
					foundCompany = true;
				}
				catch (Exception e)
				{
					Log.w("jhavatar", "Admob ads requested but library not found");
				}
			}
			else if (company == AMAZON)
			{
				try
				{
					//Log.d("jhavatar", "check for com.amazon.device.ads.AdLayout");
					Class.forName("com.amazon.device.ads.AdLayout");
					//Log.d("jhavatar", "amazon ads lib found");
					adCompany = new AmazonAds();
					foundCompany = true;
				}
				catch (Exception f)
				{
					Log.w("jhavatar", "Amazon ads requested but library not found");
				}
			}
			else
			{
				Log.w("jhavatar", "ad company " + company + " is not valid");
			}
		}
		
		
		if (!foundCompany)
		{
			Log.w("jhavatar", "showAds is set to true but no ad company lib found. Setting showAds to false");
			this.showAds = false;
		}
	}
	
	
	
	private View getAdContainer(View view)
	{
		return (View) view.findViewById(R.id.adContainer);
	}
	
	private String getAdTag(Activity activity)
	{
		return activity.getResources().getString(R.string.adViewTag);
	}

	public void loadAd(View view, Activity activity)
	{
		if (!showAds)
			return;
		
		View adView = view.findViewWithTag(getAdTag(activity));
		if (adView != null)
		{
			//Log.d("jhavatar", "load ad, adView exists");
			adCompany.loadAd(adView);
		}
		else
		{
			//Log.d("jhavatar", "reload ad, adView not exists");
			reloadAd(getAdContainer(view), activity);
		}
	}
	
	
	private void reloadAd(View view, Activity activity)
	{
		if (view == null)
			return; // layout does not have container for ad
		
		//Log.d("jhavatar", "reload ad");
		
		View newAdView = adCompany.createAd(activity);
		newAdView.setTag(getAdTag(activity));
		
		newAdView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		((LinearLayout)view).addView(newAdView);
		
		adCompany.loadAd(newAdView);
	}
	
	public void reLayoutAd(View view, Activity activity)
	{
		if (!showAds)
			return;
		
		View adView = view.findViewWithTag(getAdTag(activity));
		//Log.d("jhavatar", "relayout ad");
		if (adView != null) // form does not contain adView
		{
			//Log.d("jhavatar", "reLayoutAd, adView exists");
			LinearLayout parent = (LinearLayout) adView.getParent();
			parent.removeView(adView);
		}
		//else
		//	Log.d("jhavatar", "reLayoutAd, adView NOT exists");
		
		reloadAd(getAdContainer(view), activity);
	}
	
}
