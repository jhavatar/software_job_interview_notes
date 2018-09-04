package com.jhavatar.softwareinterviewnotes;

import android.app.Activity;
import android.util.Log;
import android.webkit.WebView;

public class SearchManager
{
	private static SearchManager instance;
	
	private int position = 0;
	private int prevPosition = 0;
	private final Activity activity;
	private WebView[] webViews = new WebView[HtmlResources.TITLE_IDS.length];
	private boolean[] searchings = new boolean[HtmlResources.TITLE_IDS.length];
	
	
	public static SearchManager createInstance(Activity activity)
	{
		instance = new SearchManager(activity);
		return instance;
		
	}
	
	public static SearchManager getInstance()
	{
		return instance;
	}

	public static void setInstance(SearchManager instance)
	{
		SearchManager.instance = instance;
	}
	
	private SearchManager(Activity activity)
	{
		super();
		this.activity = activity;
	}
	
	public boolean isSearching()
	{
		return searchings[this.position];
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		//Log.d("jhavatar", "set position " + position);
		boolean wasSearching = this.isSearching();
		this.prevPosition = this.position;
		this.position = position;
		
		if ((position == 0) && (prevPosition > 0))
		{
			updateOptions();
		}
		else if ((position > 0) && (prevPosition == 0))
		{
			updateOptions();
		}
		else if (wasSearching != this.isSearching())
		{
			updateOptions();
		}
		
		//Log.d("jhavatar", "SearchManager, position = " + position + ", prevPosition = " + prevPosition);
	}

	public int getPrevPosition() {
		return prevPosition;
	}

	public void setPrevPosition(int prevPosition) {
		this.prevPosition = prevPosition;
	}

	public WebView getWebView(int position) {
		return this.webViews[position];
	}

	public void setWebView(WebView webView, int position) {
		//Log.d("jhavatar", "setWebview webview = " + webView + ", position = " + position);
		//if (this.webViews[this.position] != null)
		//	Log.d("jhavatar", "RECREATED webview");
		this.webViews[position] = webView;
		this.searchings[position] = false;
	}

	void updateOptions()
	{
		activity.invalidateOptionsMenu();
	}
	
	void search(String query)
	{
		//Log.d("jhavatar", "query = " + query + ", webview = " + webView + ", position = " + position + ", prevPosition = " + this.prevPosition);
		if ((query != null) && (query.length() > 0))
		{
			if (this.webViews[this.position] != null)
	    	{
	    		//Log.d("jhavatar", "FIND query = " + query);
	    		this.webViews[position].findAll(query);
	    		if (!this.isSearching())
	    		{
	    			this.searchings[this.position] = true;
	    			updateOptions();
	    		}
	    	}
		}
	}
	
	
	void cancelSearch()
	{
		if (this.isSearching())
		{
			this.searchings[this.position] = false;
			if (this.webViews[this.position] != null)
				this.webViews[this.position].clearMatches();
			updateOptions();
		}
	}
	
	
	void searchNext()
	{
		if (this.webViews[this.position] != null)
    	{
			this.webViews[position].findNext(true);
    	}
	}
	
	void searchPrev()
	{
		if (this.webViews[this.position] != null)
    	{
			this.webViews[position].findNext(false);
    	}
	}
	
	
	
	
	
	
}
