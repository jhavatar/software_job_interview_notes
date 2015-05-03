package com.jhavatar.softwareinterviewnotes;

import com.bricolsoftconsulting.webview.WebViewClientEx;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.jhavatar.ads.AdUtils;
import com.jhavatar.softwareinterviewnotes.R; 



/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class DummySectionFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	public static final String GOTO_PAGE_JS_PREFIX = "http://gotoPage(";
	
	int position;
	int orientation;
	int actualOrientation;

	public DummySectionFragment() {
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		position = getArguments().getInt(ARG_SECTION_NUMBER);
		orientation = this.getActivity().getResources().getConfiguration().orientation;
		actualOrientation = orientation;
		//Log.d("jhavatar", "onCreateView, position = " + position + ", orientation = " + orientation);
		
		View rootView;
		if (position == 0)
		{
			rootView = inflater.inflate(R.layout.fragment_index_list,container, false);
			ListView l = (ListView) rootView.findViewById(R.id.index_list);

			// do not include first title in values
		    String[] values = new String[HtmlResources.TITLE_IDS.length-1];
		    for (int idx = 1; idx < HtmlResources.TITLE_IDS.length; idx += 1)
		    {
		    	//int titleId = MainMenu.TITLE_IDS[idx];
		    	values[idx - 1] = (String) HtmlResources.getIndentedPageTitle(idx, this.getActivity());//this.getActivity().getString(titleId);
		    }

		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, values);
		    l.setAdapter(adapter);
		    
		    l.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					((MainMenu) getActivity()).gotoPage(position + 1); // index is the first position
					
				}
		    	
		    });
		    

		}
		else
		{				
			rootView = inflater.inflate(R.layout.fragment_main_menu_dummy, container, false);
						
			final WebView webView = (WebView) rootView.findViewById(R.id.section_content);
			webView.getSettings().setJavaScriptEnabled(true);
			webView.setWebViewClient(new WebViewClientEx(this.getActivity()) {
				@Override
				public boolean shouldOverrideUrlLoadingEx(WebView view, String url)
				{
					//Log.d("jhavatar", "shouldOverrideUrlLoading, url = " + url);
					if (url.startsWith(GOTO_PAGE_JS_PREFIX))
					{
						((MainMenu) getActivity()).gotoPage(Integer.parseInt(url.substring(GOTO_PAGE_JS_PREFIX.length(), url.indexOf(")"))));
						return true;
					}
					else
						return false;					
				}
			});
			//webview.addJavascriptInterface(new JSInterface(), "jsinterface");
			//Log.d("jhavatar", "setJavaScriptEnabled(true) for " +  HtmlResources.HTML_FILE_NAMES[position]);
			webView.loadUrl("file:///android_asset/" + HtmlResources.HTML_FILE_NAMES[position] + "?page=" + position + HtmlResources.genHtmlParams());

			//Log.d("jhavatar", "Fragment onCreateView, setWebView");
			SearchManager.getInstance().setWebView(webView, position);
			
			AdUtils.getInstance().loadAd(rootView, this.getActivity());
		}
		
				
		return rootView;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onConfigurationChanged(android.content.res.Configuration)
	 *
	 * Called when app rotates
	 *
	 */
	@Override
	public void onConfigurationChanged (Configuration newConfig)
	{
		actualOrientation = newConfig.orientation;
		View rootView = this.getView();
		
		//Log.d("jhavatar", "onConfigurationChanged position = " + position + ", displayed = " + SearchManager.getInstance().getPosition() + ", orientation = " + orientation + ", actual orient = " + newConfig.orientation );
		if ((newConfig.orientation != orientation) && (this.position == SearchManager.getInstance().getPosition()))
		{
			orientation = newConfig.orientation;
			if (rootView != null)
			{
				//Log.d("jhavatar", "onConfigurationChanged, refresh add");
				AdUtils.getInstance().reLayoutAd(rootView, this.getActivity());
			}
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#setUserVisibleHint(boolean)
	 *
	 * Called when fragment is made visible to user
	 * Note, onResume is not reliable since views are cached
	 */
	@Override
	public void setUserVisibleHint (boolean isVisibleToUser)
	{
		super.setUserVisibleHint(isVisibleToUser);
		//Log.d("jhavatar", "setUserVisibleHint to " + isVisibleToUser);
		if (this.getActivity() == null)
			return;
		View rootView = this.getView();
	
		//Log.d("jhavatar", "setUserVisibleHint position = " + position + ", displayed = " + SearchManager.getInstance().getPosition() + ", orientation = " + orientation + ", actual orient = " + actualOrientation + ", orient2 = " + this.getActivity().getWindowManager().getDefaultDisplay().getOrientation() + ", user vis hint = " + this.getUserVisibleHint());
		if ((actualOrientation != orientation) && isVisibleToUser)
		{
			orientation = actualOrientation;
			//Log.d("jhavatar", "require orient fix");
			if (rootView != null)
				AdUtils.getInstance().reLayoutAd(rootView, this.getActivity());
		}
	}

}