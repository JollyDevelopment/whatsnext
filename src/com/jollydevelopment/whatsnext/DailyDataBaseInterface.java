package com.jollydevelopment.whatsnext;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DailyDataBaseInterface extends SQLiteOpenHelper {
	//Constants
	//database version
	private static final int DATABASE_VERSION = 1;
	//database name
	private static final String DATABASE_NAME = "com.jollydevelopment.whatsnext.daily.db";
	//table name
	public static final String TABLE_NAME_TASKS = "daily_tasks";
	//column names
	public static final String COLUMN_ID = "_id";
	//public static final String COLUMN_TASK_NAME = "task_name";
	public static final String COLUMN_TASK_DESCRIPTION = "task_description";
	public static final String COLUMN_LIST_NAME = "list_name";
	
	
	//Create a string that will be the command line command
	//The string will look like this to the OS:
	//"CREATE TABLE tasks (_id INTEGER PRIMARY KEY AUTOINCREMENT, task_description TEXT NOT NULL, list_name TEXT NOT NULL)"
	private static final String CREATE_DATABASE = "CREATE TABLE " + TABLE_NAME_TASKS + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TASK_DESCRIPTION + " TEXT NOT NULL, " + COLUMN_LIST_NAME + " TEXT NOT NULL);";
	
	
	//Constructor. When this is called it will either access or create the database with the name
	//in the DATABASE_NAME constant
	public DailyDataBaseInterface(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}//end of Constructor
	
	
	/*
	 * Method: onCreate() In this instance I am overriding the default value and making a specific
	 * SQLite command call. This is basically a string built to be the command using the variables and
	 * SQLite command line commands
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {					
		//send the command to the device's SQLiteDatabase
		db.execSQL(CREATE_DATABASE);
	}//end of onCreate()
	
	
	
	/*
	 * (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 * Method onUpgrade() This drops any previous versions of the database and the calls onCreate to make it again
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Drop old table if it exists
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TASKS);		
		//create new version
		onCreate(db);
	}//end of onUpgrade
	
	
	
	/*
	 * C.R.U.D. Operations (Create, Read, Update, Delete)--------------------
	 */
	
	
	public void addTask(Task task) {
		//connect to the database to write to it
		SQLiteDatabase db = this.getWritableDatabase();
		
		//get the value(s) from the Contact and put them into a ContentValues holder
		ContentValues values = new ContentValues();		
		values.put(COLUMN_TASK_DESCRIPTION, task.getTaskDescription());
		values.put(COLUMN_LIST_NAME, task.getTaskListName());
		
		//insert the values above into the "tasks" table in the database
		db.insert(TABLE_NAME_TASKS, null, values);
		//close the connection
		db.close();
		
	}//end of addTask(Task task)
	
	
	
	/*
	 * Method: getTask() This will pull a single task from the database
	 */
		
    // Getting single contact
	public Task getTask(long _id) {
		//create new Task
		Task task;
		
		//connect to the database to read from it
	    SQLiteDatabase db = this.getReadableDatabase();
	    
	    //create a Cursor to query the Database. The cursor will then get the single row corresponding
	    //to the "_id" number
	    Cursor cursor = db.query(TABLE_NAME_TASKS, new String[] { COLUMN_ID,
	            COLUMN_TASK_DESCRIPTION }, COLUMN_ID + "=?",
	            new String[] { String.valueOf(_id) }, null, null, null, null);
	    
	    //if the cursor is not empty, go to the first value (the integer in COLUMN_ID, or Column Zero(0))
	    if (cursor != null)
	        cursor.moveToFirst();
	    
	    //make a new Task object and fill it with the data from the database row
	    //constructor = Task(int, string, string) 
	    // 0 = _id
	    // 1 = taskDescription
	    // 2 = taskListName
	    task = new Task(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
	    
	    // return contact
	    return task;
	}//end of getTask()
	
	
	/*
	 * Method: getAllTasks() This will query the database, get the infromation for each row,
	 * turn that row into a Task object, then add that Task object to an ArrayList<>. That ArrayList<> is what
	 * will be returned 
	 */
	
	public ArrayList<Task> getAllTasks() {
		//create the ArrayList<>
		ArrayList<Task> allTasks = new ArrayList<Task>();
		
		//make a string to be the SQLit command
		String selectAllRows = "SELECT * FROM " + TABLE_NAME_TASKS;
		
		//connect to the database to read from it
		SQLiteDatabase db = this.getWritableDatabase();
		
		//create a cursor to send the SQLite command and to hold the result
		Cursor cursor = db.rawQuery(selectAllRows, null);
		
		//loop through the rows and make a new Task from the information contained in
		//each row
	    // 0 = _id
	    // 1 = taskDescription	
		// 2 = taskListName
		
		//if cursor.moveToFirst() returns "true" then do the following
		if (cursor.moveToFirst()) {
			do {
				//Make a new empty Task, then put the data from the current row into the Task 
				Task task = new Task();
				task.setId(Integer.parseInt(cursor.getString(0)));
				task.setTaskDescription(cursor.getString(1));
				task.setTaskListName(cursor.getString(2));
				//add that task to the ArrayList<Task>
				allTasks.add(task);				
			}//end of do
			//continue the loop until cursor.moveToNext() returns "false", meaning there are
			//no more rows 
			while (cursor.moveToNext());
		}//end of if
		
		
		//return the ArrayList<Task>
		return allTasks;
	}//end of getAllTasks()
	
	
	
	/*
	 * Method: getTaskCount() This will return the total of how many Tasks (rows) exist in the Database
	 */
	public int getTaskCount() {
		//make a String to hold the command to send to the database
		String selectAll = "SELECT * FROM " + TABLE_NAME_TASKS;
		
		//connect to the database to be able to read from it
		SQLiteDatabase db = this.getReadableDatabase();
		
		//make a Cursor to send the command and hold the result
		Cursor cursor = db.rawQuery(selectAll, null);
		
		//get the count and put it into an int variable
		int itemCount = cursor.getCount();
		
		//close the connection using the cursor
		cursor.close();
		
		//return the count
		return itemCount;
	}//end of getTaskCount()
	
	
	/*
	 * Method: updateTask() This will overwrite data in a particular row in the database,
	 * determined by the "_id" field in the Task that is passed in as a parameter
	 */
	public int updateTask(Task task) {
		//connect to the database to write to it
		SQLiteDatabase db = this.getWritableDatabase();
		
		//make a new ContentValues object to hold the Strings to put into the database
		//then put in the KEY/Value pairs, pulling the values from the passed in Task
		ContentValues values = new ContentValues();
		values.put(COLUMN_TASK_DESCRIPTION, task.getTaskDescription());
		
		//variable to hold the number of rows affected int returned when updating the database row
		int numberOfRowsAffected = db.update(TABLE_NAME_TASKS, values, COLUMN_ID + " = ?", new String[] { String.valueOf(task.getId()) });
		
		//return the int
		return numberOfRowsAffected;
	}//end of updateTask()
	
	
	/*
	 * Method: deleteTask() This will delete a single task from the database table
	 */
	public void deleteTask(Task task) {
		//connect to the database to write to it
		SQLiteDatabase db = this.getWritableDatabase();
		
		//send the delete command, using the "_id" field from the passed in Task object
		db.delete(TABLE_NAME_TASKS, COLUMN_ID + " = ?", new String[] { String.valueOf(task.getId())});
		
		//close the database connection
		db.close();
		
	}//end of deleteTask()
	
	
	
	
	
	
}//end of class
