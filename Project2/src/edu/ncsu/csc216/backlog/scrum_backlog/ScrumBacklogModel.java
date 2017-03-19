package edu.ncsu.csc216.backlog.scrum_backlog;

import java.util.ArrayList;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;

/**
 * This class handles all actions the user can take and edits the tasks
 * accordingly
 * 
 * @author Caitlyn
 *
 */
public class ScrumBacklogModel {

	/** A list of all tasks in the scrum system */
	private ArrayList<TaskItem> taskItemList;

	/**
	 * Constructs an instance of the ScrumBacklogModel
	 */
	private ScrumBacklogModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the instance of ScrumBacklogModel that was made so only one
	 * instance is ever used
	 * 
	 * @return the instance of ScrumBacklogModel
	 */
	public static ScrumBacklogModel getInstance() {
		return null;
	}

	/**
	 * Saves the current tasks to a file
	 * 
	 * @param filename
	 *            the name of the file to save the tasks to
	 */
	public void saveTasksToFile(String filename) {

	}

	/**
	 * Loads tasks from a file
	 * 
	 * @param filename
	 *            the name of the file to load tasks from
	 */
	public void loadTasksFromFile(String filename) {

	}

	/**
	 * Creates a new list of task items by clearing all current tasks
	 */
	public void createNewTaskItemList() {

	}

	/**
	 * Gets the task items in an array containing all task info
	 * 
	 * @return a 2d array with all tasks' info
	 */
	public Object[][] getTaskItemListAsArray() {
		return null;
	}

	/**
	 * Gets all tasks for a specific owner
	 * 
	 * @param owner
	 *            the owner the user wants to see tasks for
	 * @return a 2d array with the tasks' info whose owner is the owner
	 *         specified
	 */
	public Object[][] getTaskItemListByOwnerAsArray(String owner) {
		return null;
	}

	/**
	 * Gets all tasks for a specific creator
	 * 
	 * @param creator
	 *            the creator the user wants to see tasks for
	 * @return a 2d array with the tasks' info whose creator is the creator
	 *         specified
	 */
	public Object[][] getTaskItemListByCreatorAsArray(String creator) {
		return null;
	}

	/**
	 * Gets the task with the specified id
	 * 
	 * @param id
	 *            the id of the task wanted
	 * @return
	 */
	public TaskItem getTaskItemById(int id) {
		return null;
	}

	/**
	 * Executes a command on a specific task
	 * 
	 * @param id
	 *            the id of the task to execute the command on
	 * @param command
	 *            the command to execute
	 */
	public void executeCommand(int id, Command command) {

	}

	/**
	 * Deletes a task
	 * 
	 * @param id
	 *            the id of the task to delete
	 */
	public void deleteTaskItemById(int id) {

	}

	/**
	 * Adds a task to the list
	 * 
	 * @param title
	 *            task's title
	 * @param type
	 *            the type of task being added
	 * @param creator
	 *            who created the task
	 * @param noteText
	 *            the initial note left on the task
	 */
	public void addTaskItemToList(String title, Type type, String creator, String noteText) {

	}
}