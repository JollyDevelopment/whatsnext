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
			
	//Constructor, two parameters
	public Task(int _id, String taskDescription) {
		this._id = _id;
		this.taskDescription = taskDescription;
	}//end of constructor
	
	//Constructor, two parameters
	public Task(String taskDescription, String taskListName) {
		this.taskDescription = taskDescription;
		this.taskListName = taskListName;
	}//end of constructor 
	
	//Constructor, both parameters
	public Task(int _id, String taskDescription, String taskListName) {
		this._id = _id;
		this.taskDescription = taskDescription;
		this.taskListName = taskListName;
	}//end of constructor
	
	//Setters
	public void setId(int _id) {
		this._id = _id;
	}//end of setId()
		
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}//end of setTaskDescription()
	
	public void setIsComplete(Boolean taskCompletionStatus) {
		this.isComplete = taskCompletionStatus;
	}//end of setIsComplete()
	
	public void setTaskListName(String taskListName) {
		this.taskListName = taskListName;
	}//end of setTaskListName
	
	
	
	
	//Getters
	@InstantText(viewId = R.id.list_item_format_TaskId_txtview)
	public int getId() {
		return _id;
	}//end of getId()
	
	@InstantText(viewId = R.id.list_item_format_TaskDescription_txtview)
	public String getTaskDescription() {
		return taskDescription;
	}//end of getTaskDescription()
	
	public Boolean getCompletionStatus() {
		return isComplete;
	}//end of getCompletionStatus()
	
	@InstantText(viewId = R.id.list_item_format_TaskListName_txtview)
	public String getTaskListName() {
		return taskListName;
	}//end of getTaskListName
		
	
	
	
	//Instance Variables
	//Strings
	String taskDescription;
	String taskListName;
	//Integers
	int _id;
	//Booleans
	Boolean isComplete = false;
	//end of variables
}//end of class
