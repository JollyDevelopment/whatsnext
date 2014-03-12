package com.jollydevelopment.whatsnext;

import java.util.ArrayList;

import com.mobsandgeeks.adapters.InstantAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class Monthly_Frag extends Fragment {
	//Constants
	//end of constants
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//inflate the view, so it can be referenced when connecting the xml views
		holderView = inflater.inflate(R.layout.monthly_frag, container, false);
		return holderView;		
	}//end of onCreateView	
	
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		//connect to the xml views
		connectToViews(holderView);
				
		//initialize the Database, with this Activity as the Context
		mdbi = new MonthlyDataBaseInterface(getActivity());
		
		//TEST; get the number of entries in the database table
		int numEntries = mdbi.getTaskCount();
		
		//get all the tasks in the database
		monthlyTasks = mdbi.getAllTasks();
		
		//create the InstantAdapter
		//parameters:
		//InstantAdapter<ObjectClass>(Context, ListLayout, ObjectClass, ArrayList)
		InstantAdapter<Task> instantAdapter = new InstantAdapter<Task>(getActivity().getBaseContext(), R.layout.list_item_format, Task.class, monthlyTasks);
		
		//set the InstantAdapter to the ListView
		monthly_frag_monthly_listview.setAdapter(instantAdapter);
				
		//TEST: announce the number of tasks
		Toast.makeText(getActivity(), "Total Tasks = " + numEntries, Toast.LENGTH_LONG).show();
		
	}//end of onViewCreated()
	
	
	
	
	
	
	/*
	 * Method: connectToViews(holderView) This will connect the local instance objects
	 * to the correct XML views
	 */
	private void connectToViews(View V) {
		//ListView
		monthly_frag_monthly_listview = (ListView)V.findViewById(R.id.monthly_frag_monthly_listview);
	}//end of connectToViews()
	
	
	
	
	//Instance Variables
	//Views
	View holderView;
	//DataBaseInterfaces
	MonthlyDataBaseInterface mdbi;
	//ArrayList
	ArrayList<Task> monthlyTasks;
	//ListView
	ListView monthly_frag_monthly_listview;
	//end of variables
}//end of class
