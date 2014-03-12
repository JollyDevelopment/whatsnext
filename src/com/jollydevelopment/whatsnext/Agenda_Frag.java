package com.jollydevelopment.whatsnext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Agenda_Frag extends Fragment {
	//Constants
	//end of constants
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//inflate the view, so it can be referenced when connecting the xml views
		holderView = inflater.inflate(R.layout.agenda_frag, container, false);
		return holderView;		
	}//end of onCreateView	
	
	
	
	
	
	
	
	
	
	//Instance Variables
	//Views
	View holderView;
	//end of variables
}//end of class
