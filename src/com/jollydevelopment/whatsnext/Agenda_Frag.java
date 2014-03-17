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
	
		
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		//connect to the xml views
		connectToViews(holderView);
		
		//initialize the Database, with this Activity as the Context
		ddbi = new DailyDataBaseInterface(getActivity());
		wdbi = new WeeklyDataBaseInterface(getActivity());
		mdbi = new MonthlyDataBaseInterface(getActivity());
		
		//TEST; get the number of entries in the database table
		int numEntriesDaily = ddbi.getTaskCount();
		int numEntriesWeekly = wdbi.getTaskCount();
		int numEntriesMonthly = mdbi.getTaskCount();
		
		//get all the tasks in the databases
		dailyTasks = ddbi.getAllTasks();
		weeklyTasks = wdbi.getAllTasks();
		monthlyTasks = mdbi.getAllTasks();
		
		//put all the Tasks from the Daily/Weekly/Monthly lists into the Agenda list
		buildAgendaArray(dailyTasks, weeklyTasks, monthlyTasks);
		
		
		//create the InstantAdapter
		//parameters:
		//InstantAdapter<ObjectClass>(Context, ListLayout, ObjectClass, ArrayList)
		//InstantAdapter<Task> instantAdapterDaily = new InstantAdapter<Task>(getActivity().getBaseContext(), R.layout.list_item_format, Task.class, dailyTasks);
		//InstantAdapter<Task> instantAdapterWeekly = new InstantAdapter<Task>(getActivity().getBaseContext(), R.layout.list_item_format, Task.class, weeklyTasks);
		//InstantAdapter<Task> instantAdapterMonthly = new InstantAdapter<Task>(getActivity().getBaseContext(), R.layout.list_item_format, Task.class, monthlyTasks);
		InstantAdapter<Task> instantAdapterMonthly = new InstantAdapter<Task>(getActivity().getBaseContext(), R.layout.list_item_format, Task.class, agendaTasks);
		
		//set the InstantAdapter to the ListView
		//agenda_frag_daily_listview.setAdapter(instantAdapterDaily);
		//agenda_frag_weekly_listview.setAdapter(instantAdapterWeekly);
		//agenda_frag_monthly_listview.setAdapter(instantAdapterMonthly);
		agenda_frag_agenda_listview.setAdapter(instantAdapterMonthly);
		
		
		//TEST: announce the number of tasks
		Toast.makeText(getActivity(), "Daily Tasks = " + numEntriesDaily + ", Weekly Tasks = " + numEntriesWeekly + ", Monthly Tasks = " + numEntriesMonthly, Toast.LENGTH_LONG).show();
		
	}//end of onViewCreated



	/*
	 * Method: connectToViews(holderView) This will connect the local instance objects
	 * to the correct XML views
	 */
	private void connectToViews(View V) {
		//ListViews
		agenda_frag_daily_listview = (ListView)V.findViewById(R.id.agenda_frag_daily_listview);
		agenda_frag_weekly_listview = (ListView)V.findViewById(R.id.agenda_frag_weekly_listview);
		agenda_frag_monthly_listview = (ListView)V.findViewById(R.id.agenda_frag_monthly_listview);
		agenda_frag_agenda_listview = (ListView)V.findViewById(R.id.agenda_frag_agenda_listview);
	}//end of connectToViews()

	
	/*
	 * Method: buildAgendaArray() This method takes the three ArrayLists from Daily/Weekly/Monthly and pulls the
	 * Task objects from each list, then puts them in a new list. It then returns the new list
	 */
	private void buildAgendaArray(ArrayList<Task> dailyList, ArrayList<Task> weeklyList, ArrayList<Task> monthlyList) {
		//a new Task 
		Task taskHolder;
		
		//a "for loop to pull the Tasks from dailyList		
		for (int x = 0; x < dailyList.size(); x++ ) {
			taskHolder = dailyList.get(x);
			agendaTasks.add(taskHolder);
		}//end of daily for loop
		
		//a "for loop to pull the Tasks from weeklyList		
		for (int x = 0; x < weeklyList.size(); x++ ) {
			taskHolder = weeklyList.get(x);
			agendaTasks.add(taskHolder);
		}//end of daily for loop		
		
		//a "for loop to pull the Tasks from monthlyList		
		for (int x = 0; x < monthlyList.size(); x++ ) {
			taskHolder = monthlyList.get(x);
			agendaTasks.add(taskHolder);
		}//end of daily for loop			
		
		
	}//end of buildAgendaArray()



	//Instance Variables
	//Views
	View holderView;
	//ListViews
	ListView agenda_frag_daily_listview;
	ListView agenda_frag_weekly_listview;
	ListView agenda_frag_monthly_listview;
	ListView agenda_frag_agenda_listview;
	//DataBaseInterfaces
	DailyDataBaseInterface ddbi;
	WeeklyDataBaseInterface wdbi;
	MonthlyDataBaseInterface mdbi;
	//ArrayList
	ArrayList<Task> dailyTasks;
	ArrayList<Task> weeklyTasks;
	ArrayList<Task> monthlyTasks;
	ArrayList<Task> agendaTasks = new ArrayList<Task>();
	//end of variables
}//end of class
