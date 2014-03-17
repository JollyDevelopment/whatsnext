package com.jollydevelopment.whatsnext;

/**
 * The code that provides the slidingmenu responsiveness is based off the work in the ExampleListActivity app that comes 
 * with the SlidingMenu Library. That code originally belongs to Jeremy Feinstein, and is used here under the Apache GPL. 
 * See below:
 * Copyright 2012 Jeremy Feinstein
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.jollydevelopment.whatsnext.Menu_Frag.fragSwitchChoice;
import com.jollydevelopment.whatsnext.R;

public class StartHere_Responsive extends SlidingFragmentActivity implements fragSwitchChoice {
	//Constants
	//end of constants
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		/*
		 * set the content view. There are three versions of the starthereresponsive.xml file. One in the /layout folder,
		 * one in the /layout-xlarge folder, and one in the /layout-land-large folder. The android operating system will
		 * use the correct one depending on what size screen the app is being displayed on. This plays the major role 
		 * in determining how the slidingmenu library treats the displayed views.
		 */
		setContentView(R.layout.starthere_responsive);
				
		/*
		 * This next bit is lifted straight from the example code. It checks to see if the "starthereresponsive_menu_frame"
		 * framelayout exists on the screen. This is only going to exist if the OS is showing one of the large screen layouts
		 * 
		 */
		/*
		 * check if the content frame contains the menu frame. If it does not, then that means that the layout being shown
		 * is the one in /layout which only has the "starthereresponsive_content_frame" framelayout. SlidingMenuLibrary then
		 * sets up the BehindContentView to be the menu_frame.xml layout. This layout contains a Framelayout called 
		 * "starthere_responsive_menu_frame"
		 */
		if (findViewById(R.id.starthere_responsive_menuFrame) == null) {
			setBehindContentView(R.layout.menu_frame);
			getSlidingMenu().setSlidingEnabled(true);
			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);			
			// show home as up so we can toggle
			getActionBar().setDisplayHomeAsUpEnabled(true);
		} else {
			// add a dummy view
			View v = new View(this);
			setBehindContentView(v);
			getSlidingMenu().setSlidingEnabled(false);
			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
			//set the isTablet variable to true
			isTablet = true;
		}//end of else
		
		/*
		 * Again the next three code sections are taken from the SlidingMenu example code. They set the menu and the content
		 * fragments to the correct framelayouts. I have changed some of the variable names, so I can read it easier and
		 * changed the displayed fragments to mine, rather than the ones from the example.
		 */
		// set the Above View Fragment
		//if the view is being re-created, get the last used fragment and put it back
		if (savedInstanceState != null)
			contentFrag = getSupportFragmentManager().getFragment(savedInstanceState, "ContentFragmentContents");
		//if this is the first time, set the fragment to the AgendaFrag
		if (contentFrag == null)
			contentFrag = new Agenda_Frag();	
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.starthere_responsive_contentFrame, contentFrag)
		.commit();
		
		// set the Behind View Fragment to the MenuFrag
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.starthere_responsive_menuFrame, new Menu_Frag())
		.commit();
		
		// customize the SlidingMenu appearance and dimensions
		SlidingMenu sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.starthere_responsive_slidingmenu_offset);
		sm.setShadowWidthRes(R.dimen.starthere_responsive_shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindScrollScale(0.25f);
		sm.setFadeDegree(0.25f);
		
		//Create the databases to put objects into.
		createDatabases();
		
	}//end of onCreate
	
	
	/*
	 * Override the menu items in the ActionBarSherlock
	 */
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
	   android.view.MenuInflater inflater = getMenuInflater();
	   inflater.inflate(R.menu.menu_items, menu);
	   return true;
	}//end of onCreateOptionsMenu
	
	
	
	/*
	 * This next sets the SherlockActionBar icon to be able to toggle the slidingmenu open/closed
	 */
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			break;
		case R.id.menu_items_add_item:
			//change the fragment in the Content_Frame to be the New_Task_Frag
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.starthere_responsive_contentFrame, new New_Task_Frag())
			.commit();	
			//Toast.makeText(this, "New Task Pressed", Toast.LENGTH_LONG).show();
			break;
		}//end of switch
		return super.onOptionsItemSelected(item);
	}//end of onOoptionsItemSelected()
	
	
	
	/*
	 * This saves whatever the current fragment is that is being displayed in the 
	 * starthereresponsive_content_frame and adds it the savedstate bundle
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		//save the current Conent_Frame fragment to the bundle
		try {
			getSupportFragmentManager().putFragment(outState, "ContentFragmentContents", contentFrag);
		}
		catch (Exception e) {};
		
		
	}//end of onSaveInstanceState()
	
	
	
	/*
	 * Method: onDataPAss() This will take data (Strings) from the Menu_Frag through
	 * the fragSwitchChoice interface. It will then choose what Fragment to display
	 * and pass that choice to the FragmentManager
	 */
	@Override
	public void onDataPass(String fragString) {
		//if/else tree to choose which Fragment to display
		if (fragString == "@string/constant_Agenda_Frag_Name") {
			// set the Agenda_Frag to the Content_Frame
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.starthere_responsive_contentFrame, new Agenda_Frag())
			.commit();		
			
			//check if the screen does not contain starthere_responsive_menuFrame. If it doesn't 
			//then that means its a non-tablet screen, and the toggle() should be applied
			if (findViewById(R.id.starthere_responsive_menuFrame) == null) {			
				//close the drawer
				toggle();
				//TEST: toast to see if the code gets here
				Toast.makeText(getBaseContext(), "Agenda Frag Toggle Pressed", Toast.LENGTH_LONG).show();
			}//end of if
			
			//if the device is not a tablet, toggle the sliding menu
			if (isTablet != true) {
				//close the drawer
				toggle();
			}//end of if
			
		}//end of if
		else if (fragString == "@string/constant_Daily_Frag_Name") {
			// set the Daily_Frag to the Content_Frame
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.starthere_responsive_contentFrame, new Daily_Frag())
			.commit();
			
			//if the device is not a tablet, toggle the sliding menu
			if (isTablet != true) {
				//close the drawer
				toggle();
			}//end of if
			
		}//end of else if
		else if (fragString == "@string/constant_Weekly_Frag_Name") {
			// set the Weekly_Frag to the Content_Frame
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.starthere_responsive_contentFrame, new Weekly_Frag())
			.commit();
			
			//if the device is not a tablet, toggle the sliding menu
			if (isTablet != true) {
				//close the drawer
				toggle();
			}//end of if
			
		}//end of else if
		else if (fragString == "@string/constant_Monthly_Frag_Name") {
			// set the Monthly_Frag to the Content_Frame
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.starthere_responsive_contentFrame, new Monthly_Frag())
			.commit();
			
			//if the device is not a tablet, toggle the sliding menu
			if (isTablet != true) {
				//close the drawer
				toggle();
			}//end of if
			
		}//end of else if
	}//end of onDataPass()
	
	
	
	/*
	 * Method: createDatabases() This just calls the DataBaseInterfaces to initialize the tables for this application
	 */
	private void createDatabases() {
		//initialize the Database, with this Activity as the Context
		ddbi = new DailyDataBaseInterface(this);
		wdbi = new WeeklyDataBaseInterface(this);
		mdbi = new MonthlyDataBaseInterface(this);
		
	}//end of createDatabases()
	
	
	//Instance Variables
	//DataBaseInterfaces
	DailyDataBaseInterface ddbi;
	WeeklyDataBaseInterface wdbi;
	MonthlyDataBaseInterface mdbi;
	//Fragments
	Fragment menuFrag, contentFrag;
	//FragmentTransactions
	FragmentTransaction FragTrans;
	//Booleans
	Boolean isTablet = false;
	//end of variables
}//end of class
