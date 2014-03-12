package com.jollydevelopment.whatsnext;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Menu_Frag extends Fragment implements OnClickListener {
	//Constants
	//end of constants
	
	
	/*
	 * Interface: fragSwitchChoice() This will provide a way for the StartHere_Responsive
	 * activity to receive directions from this fragment as to which List Fragment to display
	 * in the Content_Frame
	 */
	public interface fragSwitchChoice {
	    public void onDataPass(String data);
	}//end of interface
	
	//This is called when the Fragment is attached to the Activity
	//It connects the Interface to the Activity
	@Override
	public void onAttach(Activity theActivityThisFragIsAttachedTo) {
	    super.onAttach(theActivityThisFragIsAttachedTo);
	    dataPasser = (fragSwitchChoice) theActivityThisFragIsAttachedTo;
	}//end of onAttach()
	
	
	//This does the actual sending of data from this Fragment to the StartHere_Responsive
	//activity. It sends a String that says which Fragment to display
	public void fragToUse(String data) {
	    dataPasser.onDataPass(data);
	}//end of fragToUse
	
	
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//inflate the view, so it can be referenced when connecting the xml views
		holderView = inflater.inflate(R.layout.menu_frag, container, false);
		return holderView;
		
	}//end of onCreateView
	
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		//connect to the xml views
		connectToViews(holderView);
		
		//setup the onClickListeners
		setupListeners();
		
	}//end of onViewCreated
	
	
	

	
	
	
	
	
	@Override
	public void onClick(View theViewClicked) {
		//switch/case to determine what to do when a particular view is pressed
		switch (theViewClicked.getId()) {
		case R.id.menu_frag_agenda_clickableLinear:
			//make a String to hold the fragment name
			fragmentName = "@string/constant_Agenda_Frag_Name";
			//pass that name to the StartHere_Responsive activity
			fragToUse(fragmentName);			
			//Toast.makeText(getActivity(), "Agenda Pressed", Toast.LENGTH_LONG).show();
			break;
		case R.id.menu_frag_daily_clickableLinear:
			//make a String to hold the fragment name
			fragmentName = "@string/constant_Daily_Frag_Name";
			//pass that name to the StartHere_Responsive activity
			fragToUse(fragmentName);
			//Toast.makeText(getActivity(), "Daily Pressed", Toast.LENGTH_LONG).show();
			break;
		case R.id.menu_frag_weekly_clickableLinear:
			//make a String to hold the fragment name
			fragmentName = "@string/constant_Weekly_Frag_Name";
			//pass that name to the StartHere_Responsive activity
			fragToUse(fragmentName);			
			//Toast.makeText(getActivity(), "Weekly Pressed", Toast.LENGTH_LONG).show();
			break;
		case R.id.menu_frag_monthly_clickableLinear:
			//make a String to hold the fragment name
			fragmentName = "@string/constant_Monthly_Frag_Name";
			//pass that name to the StartHere_Responsive activity
			fragToUse(fragmentName);
			//Toast.makeText(getActivity(), "Monthly Pressed", Toast.LENGTH_LONG).show();
			break;
		case R.id.menu_frag_settings_clickableLinear:
			Toast.makeText(getActivity(), "Settings Pressed", Toast.LENGTH_LONG).show();
			break;
		}//end of switch
	}//end of onClick()
	
	
	
	/*
	 * Method: connectToViews() This will connect the local object instances to the
	 * correct .xml views
	 */
	private void connectToViews(View V) {
		menu_frag_agenda_clickableLinear = (LinearLayout)V.findViewById(R.id.menu_frag_agenda_clickableLinear);
		menu_frag_daily_clickableLinear = (LinearLayout)V.findViewById(R.id.menu_frag_daily_clickableLinear);
		menu_frag_weekly_clickableLinear = (LinearLayout)V.findViewById(R.id.menu_frag_weekly_clickableLinear);
		menu_frag_monthly_clickableLinear = (LinearLayout)V.findViewById(R.id.menu_frag_monthly_clickableLinear);
		menu_frag_settings_clickablelinear = (LinearLayout)V.findViewById(R.id.menu_frag_settings_clickableLinear);
	}//end of connectToViews()
	
	
	
	/*
	 * Method: setupListeners() This will setup onClickListeners to the LinearLayout objects
	 */
	private void setupListeners() {
		menu_frag_agenda_clickableLinear.setOnClickListener(this);
		menu_frag_daily_clickableLinear.setOnClickListener(this);
		menu_frag_weekly_clickableLinear.setOnClickListener(this);
		menu_frag_monthly_clickableLinear.setOnClickListener(this);
		menu_frag_settings_clickablelinear.setOnClickListener(this);
	}//end of setupListeners()
	
	
	
	
	
	//Instance Variables
	//LinearLayout Views
	LinearLayout menu_frag_agenda_clickableLinear;
	LinearLayout menu_frag_daily_clickableLinear;
	LinearLayout menu_frag_weekly_clickableLinear;
	LinearLayout menu_frag_monthly_clickableLinear;
	LinearLayout menu_frag_settings_clickablelinear;
	//Holder View
	View holderView;
	//Interface
	fragSwitchChoice dataPasser;
	//Strings
	String fragmentName;
	//end of variables


}//end of class
