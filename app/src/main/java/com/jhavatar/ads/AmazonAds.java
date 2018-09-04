package com.jhavatar.ads;

import android.app.Activity;
import android.util.Log;
import android.view.View;

//import com.amazon.device.ads.AdError;
//import com.amazon.device.ads.AdLayout;
//import com.amazon.device.ads.AdListener;
//import com.amazon.device.ads.AdProperties;
//import com.amazon.device.ads.AdRegistration;
//import com.amazon.device.ads.AdTargetingOptions;
//import com.amazon.device.ads.AdSize;

public class AmazonAds implements AdCompany {
	@Override
	public void loadAd(View view) {

	}

	@Override
	public View createAd(Activity activity) {
		return null;
	}

//	private static final String APP_KEY = "540b756fc24b4a41a741520c71b94893";
//	private static final int MAX_FAIL_COUNT = 3;
//
//	private int failCount = 0;
//
//	public AmazonAds()
//	{
//		 // For debugging purposes enable logging, but disable for production builds
//        AdRegistration.enableLogging(false);
//        // For debugging purposes flag all ad requests as tests, but set to false for production builds
//        AdRegistration.enableTesting(false);
//
//        try {
//            AdRegistration.setAppKey(APP_KEY);
//        } catch (Exception e) {
//            Log.e("jhavatar", "AmazonAdUtils, Exception thrown: " + e.toString());
//            return;
//        }
//	}
//
//	private void loadAmazonAd(AdLayout adView)
//	{
//		if (adView == null)
//			Log.w("jhavatar", "Cannot load add since adView is null");
//
//		 // Load the ad with the appropriate ad targeting options.
//        AdTargetingOptions adOptions = new AdTargetingOptions();
//        adView.loadAd(adOptions);
//	}
//
//	public void loadAd(View view)
//	{
//		if (view == null)
//			return;
//
//		if (view instanceof AdLayout)
//			loadAmazonAd((AdLayout) view);
//	}
//
//	public View createAd(Activity activity)
//	{
//		AdLayout adLayout = new AdLayout(activity, AdSize.SIZE_AUTO);
//		adLayout.setListener(new AdListener(){
//
//			@Override
//			public void onAdCollapsed(AdLayout view) {
//			}
//
//			@Override
//			public void onAdExpanded(AdLayout view) {
//
//			}
//
//			@Override
//			public void onAdFailedToLoad(AdLayout view, AdError error) {
//				// Note that currenlty amazon ads are only suported in the US
//				failCount += 1;
//				//Log.d("jhavatar", "amazon ads onAdFailedToLoad: " + error.getMessage() + ", failCount = " + failCount);
//				if (failCount >= MAX_FAIL_COUNT)
//				{
//					Log.d("jhavatar", "amazon ad loading keeps on failing. Now switching to next ad company");
//					AdUtils.getInstance().setNextAddCompany();
//				}
//			}
//
//			@Override
//			public void onAdLoaded(AdLayout view, AdProperties adProperties) {
//				failCount = 0;
//			}
//
//		});
//
//		return adLayout;
//	}
}
