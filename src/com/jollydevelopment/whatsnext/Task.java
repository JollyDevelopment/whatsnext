package com.jollydevelopment.whatsnext;

import com.mobsandgeeks.adapters.InstantText;

public class Task {
	//Constants
	//end of constants
	
	//Empty Constructor
	public Task() {}//end of constructor
	
	//Constructor, description parameter only
	public Task(String taskDescription) {		
		this.taskDescription = taskDescription;
	}//end of constructor
	
		
	//Constructor, both parameters
	public Task(int _id, String taskDescription) {
		this._id = _id;
		this.taskDescription = taskDescription;
	}//end of constructor
	
	
	//Setters
	public void setId(int _id) {
		this._id = _id;
	}//end of setId()
		
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}//end of setTaskDescription()
	
	
	
	
	//Getters
	@InstantText(viewId = R.id.list_item_format_TaskId_txtview)
	public int getId() {
		return _id;
	}//end of getId()
	
	@InstantText(viewId = R.id.list_item_format_TaskDescription_txtview)
	public String getTaskDescription() {
		return taskDescription;
	}//end of getTaskDescription()
		
	
	
	
	//Instance Variables
	//Strings
	String taskDescription;
	//Integers
	int _id;
	//end of variables
}//end of class
