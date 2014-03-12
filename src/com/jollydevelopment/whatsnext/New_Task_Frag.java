package com.jollydevelopment.whatsnext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class New_Task_Frag extends Fragment implements OnClickListener {
	//Constants
	//end of constants
	
		
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//inflate the view, so it can be referenced when connecting the xml views
		holderView = inflater.inflate(R.layout.new_task, container, false);
		return holderView;
		
	}//end of onCreateView
	
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		//connect to the xml views
		connectToViews(holderView);
		
		//setup the onClickListeners
		setupListeners();
		
	}//end of onViewCreated
	
	
	
	@Override
	public void onClick(View viewPressed) {
		//switch/case to determine what to do when a view is pressed
		switch (viewPressed.getId()) {
		case R.id.new_task_DailyIcon_img:
			//if this ImageView is selected, select the corresponding
			// RadioButton and de-select the other two
			new_task_DailyChoice_radio_btn.setChecked(true);
			new_task_WeeklyChoice_radio_btn.setChecked(false);
			new_task_MonthlyChoice_radio_btn.setChecked(false);
			//set the taskDataBaseSelection string to "Daily"
			taskDataBaseSelection = dailyDatabase;
			break;
		case R.id.new_task_WeeklyIcon_img:
			//if this ImageView is selected, select the corresponding
			// RadioButton and de-select the other two
			new_task_DailyChoice_radio_btn.setChecked(false);
			new_task_WeeklyChoice_radio_btn.setChecked(true);
			new_task_MonthlyChoice_radio_btn.setChecked(false);
			//set the taskDataBaseSelection string to "Weekly"
			taskDataBaseSelection = weeklyDatabase;
			break;
		case R.id.new_task_MonthlyIcon_img:
			//if this ImageView is selected, select the corresponding
			// RadioButton and de-select the other two
			new_task_DailyChoice_radio_btn.setChecked(false);
			new_task_WeeklyChoice_radio_btn.setChecked(false);
			new_task_MonthlyChoice_radio_btn.setChecked(true);
			//set the taskDataBaseSelection string to "Monthly"
			taskDataBaseSelection = monthlyDatabase;
			break;
		case R.id.new_task_DailyChoice_radio_btn:
			//if this RadioButton is selected, de-select the other two
			new_task_WeeklyChoice_radio_btn.setChecked(false);
			new_task_MonthlyChoice_radio_btn.setChecked(false);
			//set the taskDataBaseSelection string to "Daily"
			taskDataBaseSelection = dailyDatabase;
			break;
		case R.id.new_task_WeeklyChoice_radio_btn:
			//if this RadioButton is selected, de-select the other two
			new_task_DailyChoice_radio_btn.setChecked(false);
			new_task_MonthlyChoice_radio_btn.setChecked(false);
			//set the taskDataBaseSelection string to "Weekly"
			taskDataBaseSelection = weeklyDatabase;
			break;
		case R.id.new_task_MonthlyChoice_radio_btn:
			//if this RadioButton is selected, de-select the other two
			new_task_DailyChoice_radio_btn.setChecked(false);
			new_task_WeeklyChoice_radio_btn.setChecked(false);
			//set the taskDataBaseSelection string to "Monthly"
			taskDataBaseSelection = monthlyDatabase;
			break;
		case R.id.new_task_Save_button:
			saveTaskToDatabase(taskDataBaseSelection);
			break;
		case R.id.new_task_Clear_button:
			//clear any text in the EditText
			new_task_TaskDescription_edittxt.setText(null);
			//set the Hint
			new_task_TaskDescription_edittxt.setHint(getResources().getString(R.string.new_task_TaskDescriptionHint_hint));
			break;
		}//end of switch
		
	}//end of onClick()
	
	
	
	/*
	 * Method: connectToViews(View parentView) This connects the local objects to their
	 * corresponding .xml views
	 */
	public void connectToViews(View V) {
		//Buttons
		saveButton = (Button)V.findViewById(R.id.new_task_Save_button);
		clearButton = (Button)V.findViewById(R.id.new_task_Clear_button);
		//RadioButtons
		new_task_DailyChoice_radio_btn = (RadioButton)V.findViewById(R.id.new_task_DailyChoice_radio_btn);
		new_task_WeeklyChoice_radio_btn = (RadioButton)V.findViewById(R.id.new_task_WeeklyChoice_radio_btn);
		new_task_MonthlyChoice_radio_btn = (RadioButton)V.findViewById(R.id.new_task_MonthlyChoice_radio_btn);
		//EditTexts
		new_task_TaskDescription_edittxt = (EditText)V.findViewById(R.id.new_task_TaskDescription_edittxt);
		//ImageViews
		new_task_DailyIcon_img = (ImageView)V.findViewById(R.id.new_task_DailyIcon_img);
		new_task_WeeklyIcon_img = (ImageView)V.findViewById(R.id.new_task_WeeklyIcon_img);
		new_task_MonthlyIcon_img = (ImageView)V.findViewById(R.id.new_task_MonthlyIcon_img);
	}//end of connectToViews()
	
	
	
	/*
	 * Method: setupListeners() This adds the OnClickListeners to the buttons
	 */
	public void setupListeners() {
		//Buttons
		saveButton.setOnClickListener(this);
		clearButton.setOnClickListener(this);
		//RadioButtons
		new_task_DailyChoice_radio_btn.setOnClickListener(this);
		new_task_WeeklyChoice_radio_btn.setOnClickListener(this);
		new_task_MonthlyChoice_radio_btn.setOnClickListener(this);
		//ImageViews
		new_task_DailyIcon_img.setOnClickListener(this);
		new_task_WeeklyIcon_img.setOnClickListener(this);
		new_task_MonthlyIcon_img.setOnClickListener(this);
	}//end of setupListeners()
	
	
	
	/*
	 * Method: saveTaskToDatabase() This will take the Description in the EditText
	 * field and send it to the database dictated by the taskDataBaseSelection string.
	 */
	private void saveTaskToDatabase(String taskDataBaseSelection) {
		//if/else tree to determine to which database the task should be added.
		if (taskDataBaseSelection == dailyDatabase) {
			//make a String to hold the EditText data
			String taskDescription;
			
			//get the data from the EditText and save it to the String
			taskDescription = new_task_TaskDescription_edittxt.getText().toString();
			
			//make a new DailyDataBaseInterface
			DailyDataBaseInterface ddbi = new DailyDataBaseInterface(getActivity());
			
			//make a new Task with the taskDescription
			Task newTask = new Task(taskDescription);
			
			//store that task in the Database
			ddbi.addTask(newTask);
			
			//announce that it worked
			Toast.makeText(getActivity(), "Daily Database", Toast.LENGTH_LONG).show();
			
		}//end of dailyif
		else if (taskDataBaseSelection == weeklyDatabase) {
			//make a String to hold the EditText data
			String taskDescription;
			
			//get the data from the EditText and save it to the String
			taskDescription = new_task_TaskDescription_edittxt.getText().toString();
			
			//make a new DailyDataBaseInterface
			WeeklyDataBaseInterface wdbi = new WeeklyDataBaseInterface(getActivity());
			
			//make a new Task with the taskDescription
			Task newTask = new Task(taskDescription);
			
			//store that task in the Database
			wdbi.addTask(newTask);
			
			//announce that it worked
			Toast.makeText(getActivity(), "Weekly Database", Toast.LENGTH_LONG).show();
			
		}//end of weekly elseif
		else if (taskDataBaseSelection == monthlyDatabase) {
			//make a String to hold the EditText data
			String taskDescription;
			
			//get the data from the EditText and save it to the String
			taskDescription = new_task_TaskDescription_edittxt.getText().toString();
			
			//make a new DailyDataBaseInterface
			MonthlyDataBaseInterface mdbi = new MonthlyDataBaseInterface(getActivity());
			
			//make a new Task with the taskDescription
			Task newTask = new Task(taskDescription);
			
			//store that task in the Database
			mdbi.addTask(newTask);
			
			//announce that it worked
			Toast.makeText(getActivity(), "Monthly Database", Toast.LENGTH_LONG).show();
		}//end of monthly elseif
	}//end of saveTaskToDatabase()
	
	
	
	//Instance Variables
	//Views
	View holderView;
	//Buttons
	Button saveButton;
	Button clearButton;
	//RadioButtons
	RadioButton new_task_DailyChoice_radio_btn;
	RadioButton new_task_WeeklyChoice_radio_btn;
	RadioButton new_task_MonthlyChoice_radio_btn;
	//EditTexts
	EditText new_task_TaskDescription_edittxt;
	//ImageViews
	ImageView new_task_DailyIcon_img;
	ImageView new_task_WeeklyIcon_img;
	ImageView new_task_MonthlyIcon_img;
	//Strings
	String taskDataBaseSelection = "Daily";
	String dailyDatabase = "Daily";
	String weeklyDatabase = "Weekly";
	String monthlyDatabase = "Monthly";
	//end of variables

}//end of class
