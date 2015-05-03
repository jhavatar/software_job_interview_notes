package com.jhavatar.softwareinterviewnotes;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import com.jhavatar.softwareinterviewnotes.R; 

public class MainMenu extends FragmentActivity {
	
	static final String TAG = "MainMenu";
	

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	
	SearchManager searchManager = SearchManager.createInstance(this);
	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	public static void launchSourcesDialog(Activity activity)
    {
		LayoutInflater inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
	    View layout = inflater.inflate(R.layout.html_alert_layout, null);
	    
    	TextView text = (TextView) layout.findViewById(R.id.text);
	    text.setMovementMethod(LinkMovementMethod.getInstance());

	    text.setText(Html.fromHtml("&nbsp; &bull; <a href=http://en.wikipedia.org> Wikipedia</a> " +
			"<br>&nbsp; &bull; <a href=http://stackoverflow.com> Stack Overflow</a> " +
			"<br>&nbsp; &bull; <a href=http://www.codeproject.com> CodeProject</a> " +
			"<br>&nbsp; &bull; <a href=http://www.geeksforgeeks.org> GeeksforGeeks</a> "
    		));
	    
	    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    	builder.setCancelable(true)
    	       .setTitle("Sources")
    	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
    	        	public void onClick(DialogInterface dialog, int id) {
    	        	}
    	        });

    	builder.setView(layout);
    	AlertDialog alert = builder.create();
    	alert.show();
    }
	
	
	public static void launchBuyProDialog(final Activity activity)
    {
//		LayoutInflater inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
//	    View layout = inflater.inflate(R.layout.pro_alert_layout, null);
//	    
//    	TextView text = (TextView) layout.findViewById(R.id.text);
//	    text.setMovementMethod(LinkMovementMethod.getInstance());
//
//	    text.setText(Html.fromHtml("Buy the Pro version and support the developer" +
//	    		"<br>" +
//	    		"<br> Pro version features:" +
//	    		"<br> &nbsp; &bull; No ads " +
//	    		"<br> &nbsp; &bull; No app permissions " +
//	    		"<br> &nbsp; &bull; Same content! "
//    		));
//	    
//	    
//	    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//    	builder.setCancelable(true)
//    	       .setTitle("Pro version")
//    	       .setPositiveButton("Support", new DialogInterface.OnClickListener() {
//    	        	public void onClick(DialogInterface dialog, int id) {
//    	        		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.amazon.com/Software-Job-Interview-Notes-Pro/dp/B00GQITFLA/ref=sr_1_2?s=mobile-apps&ie=UTF8&qid=1384892337&sr=1-2"));
//    	        		activity.startActivity(browserIntent);
//    	        	}
//    	        })
//    	        .setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
//    	        	public void onClick(DialogInterface dialog, int id) {
//    	        	}
//    	        });
//
//    	builder.setView(layout);
//    	AlertDialog alert = builder.create();
//    	
//    	alert.setIcon(R.drawable.icon_launch_pro);
//    	
//    	alert.show();
		
		Intent intent = new Intent(activity, DialogActivity.class);
		activity.startActivity(intent);
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() 
		{
			
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				//Log.d("jhavatar", "onPageScrolled, position = " + position);
				
			}

			@Override
			public void onPageSelected(int position) {
				searchManager.setPosition(position);
			}
			
		});
		
		ActionBar bar = getActionBar();
		//bar.setTitle("hello world");
		//bar.setDisplayShowCustomEnabled(true);
		bar.setDisplayShowTitleEnabled(false);
		bar.setDisplayHomeAsUpEnabled(true);		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Log.d("jhavatar", "onCreateOptionsMenu");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		
		// Note, xml onClick gives errors on Android 4.0.3, so do manually
		MenuItem item = menu.findItem(R.id.action_sources);
		item.setOnMenuItemClickListener
	    (
	        new MenuItem.OnMenuItemClickListener () 
	        { 
	            public boolean onMenuItemClick(MenuItem item) 
	            { 
	            	onSourcesItemClick(item); 
					return true;
	            }
	        } 
	    ); 
		
		

		final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
		final SearchView searchView = (SearchView) searchMenuItem.getActionView();
		searchView.setQueryHint("Find text");
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				//Log.d("jhavatar", "searched " + query);
				if ((query != null) && (query.length() > 0))
				{
					//Log.d("jhavatar", "onQueryTextSubmit search");
					searchMenuItem.collapseActionView();
					searchManager.search(query);
				}
				return false;
			}
		});
		
		boolean showSearch = this.mViewPager.getCurrentItem() != 0;
		searchMenuItem.setVisible(showSearch);

		final MenuItem searchNextMenuItem = menu.findItem(R.id.action_search_next);
		searchNextMenuItem.setVisible(searchManager.isSearching() && showSearch);
		final MenuItem searchPrevMenuItem = menu.findItem(R.id.action_search_prev);
		searchPrevMenuItem.setVisible(searchManager.isSearching() && showSearch);
		final MenuItem searchCancelMenuItem = menu.findItem(R.id.action_search_cancel);
		searchCancelMenuItem.setVisible(searchManager.isSearching() && showSearch);
		
		
		final MenuItem buyProMenuItem = menu.findItem(R.id.action_pro);
		boolean buyPro = this.getResources().getBoolean(R.bool.buyPro);
		buyProMenuItem.setVisible(buyPro);
		if (buyPro)
		{
			buyProMenuItem.setOnMenuItemClickListener
		    (
		        new MenuItem.OnMenuItemClickListener () 
		        { 
		            public boolean onMenuItemClick(MenuItem item) 
		            { 
		            	onBuyProItemClick(item); 
						return true;
		            }
		        } 
		    ); 
		}
		return true;
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//Log.d("jhavatar", "Item selected: " + item.getItemId());
	    // Handle item selection
		int itemId = item.getItemId();
	    //switch (item.getItemId()) {
//	        case R.id.button_index:
//	        	Log.d("jhavatar", "go to index");
//	        	if (this.mViewPager.getCurrentItem() != 0)
//	        		this.mViewPager.setCurrentItem(0);
//	            return true;
	        //case android.R.id.home:
			if (itemId == android.R.id.home)
			{
	        	if (this.mViewPager.getCurrentItem() != 0)
	        		this.mViewPager.setCurrentItem(0);
	            return true;
			}
	        //case R.id.action_search:
			else if (itemId == R.id.action_search)
			{
	        	//Log.d("jhavatar", "SEARCH!");
	        	return true;
			}
	        //case R.id.action_search_next:
			else if (itemId == R.id.action_search_next)
			{
	        	//Log.d("jhavatar", "SEARCH NEXT!");
	        	searchManager.searchNext();
	        	return true;
			}
	        //case R.id.action_search_prev:
			else if (itemId == R.id.action_search_prev)
			{
	        	//Log.d("jhavatar", "SEARCH PREV!");
	        	searchManager.searchPrev();
	        	return true;
			}
			//case R.id.action_search_cancel:
			else if (itemId ==  R.id.action_search_cancel)
			{
	        	//Log.d("jhavatar", "SEARCH CANCEL!");
	        	searchManager.cancelSearch();
	        	return true;
			}
	        //default:
			else
	            return super.onOptionsItemSelected(item);
	    //}
	}
	
	public void onSourcesItemClick(MenuItem item) {
		//Log.d("jhavatar", "onSourcesItemClick");
		launchSourcesDialog(this);
	}
	
	public void onBuyProItemClick(MenuItem item) {
		//Log.d("jhavatar", "onBuyProItemClick");
		launchBuyProDialog(this);
	}
	
	
	@Override
	protected void onPause() {
		super.onPause();
		
		Log.d(TAG, "onPause");
	}
	
	@Override
	protected void onStop() {
		super.onPause();
		
		Log.d(TAG, "onStop");
	}
	
	@Override
	protected void onDestroy ()
	{
		SearchManager.setInstance(null);
    	this.searchManager = null;
		super.onDestroy();
	}
	
	
	@Override
	public void onBackPressed ()
	{
		if (this.mViewPager.getCurrentItem() != 0)
    		this.mViewPager.setCurrentItem(0);
		else
		{
			new AlertDialog.Builder(this)
		    .setTitle("Exit app")
		    .setMessage("Are you sure you want to exit this app?")
		    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            MainMenu.this.finish();
		        }
		     })
		    .setNegativeButton("No", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // do nothing
		        }
		     })
		     .show();
		}
	}
	
	public void gotoPage(int position) {
		if (this.mViewPager.getCurrentItem() != position)
    		this.mViewPager.setCurrentItem(position);
	}
	
	public void gotoIndex(View view) {
		gotoPage(0);
	 }

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			//Log.d("jhavatar", " new fragment at position " + position + " = " + fragment );
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return HtmlResources.TITLE_IDS.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return HtmlResources.getPageTitle(position, MainMenu.this);
		}
	}

}
