package edu.ncsu.csc216.backlog.scrum_backlog;

import java.util.ArrayList;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;

/**
 * This class handles all actions the user can take and edits the tasks
 * accordingly
 * 
 * @author Caitlyn
 *
 */
public class ScrumBacklogModel {

	/** A list of all tasks in the scrum system */
	private TaskItemList taskItemList;
	/** Holds a single instance of ScrumBacklogModel that never changes */
	private static ScrumBacklogModel singleton;

	/**
	 * Constructs an instance of the ScrumBacklogModel
	 */
	private ScrumBacklogModel() {
		singleton = new ScrumBacklogModel();
	}

	/**
	 * Returns the instance of ScrumBacklogModel that was made so only one
	 * instance is ever used
	 * 
	 * @return the instance of ScrumBacklogModel
	 */
	public static ScrumBacklogModel getInstance() {
		return singleton;
	}

	/**
	 * Saves the current tasks to a file
	 * 
	 * @param filename
	 *            the name of the file to save the tasks to
	 */
	public void saveTasksToFile(String filename) {
		if (taskItemList == null) {
			throw new IllegalArgumentException();
		}
		// TODO use xml library
	}

	/**
	 * Loads tasks from a file
	 * 
	 * @param filename
	 *            the name of the file to load tasks from
	 */
	public void loadTasksFromFile(String filename) {
		// TODO use xml library
	}

	/**
	 * Creates a new list of task items by clearing all current tasks
	 */
	public void createNewTaskItemList() {
		taskItemList = new TaskItemList();
	}

	/**
	 * Gets the task items in an array containing all task info
	 * 
	 * @return a 2d array with all tasks' info
	 */
	public Object[][] getTaskItemListAsArray() {
		String[][] taskArray = new String[taskItemList.getTaskItems().size()][3];
		for (int i = 0; i < taskItemList.getTaskItems().size(); i++) {
			taskArray[i][0] = Integer.toString(taskItemList.getTaskItems().get(i).getTaskItemId());
			taskArray[i][1] = taskItemList.getTaskItems().get(i).getStateName();
			taskArray[i][2] = taskItemList.getTaskItems().get(i).getTitle();
		}
		return taskArray;
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
		ArrayList<TaskItem> listOfTasksOwnedByOwner = (ArrayList<TaskItem>) taskItemList.getTaskItemsByOwner(owner);
		String[][] taskArray = new String[listOfTasksOwnedByOwner.size()][3];
		for (int i = 0; i < listOfTasksOwnedByOwner.size(); i++) {
			taskArray[i][0] = Integer.toString(listOfTasksOwnedByOwner.get(i).getTaskItemId());
			taskArray[i][1] = listOfTasksOwnedByOwner.get(i).getStateName();
			taskArray[i][2] = listOfTasksOwnedByOwner.get(i).getTitle();
		}
		return taskArray;
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
		ArrayList<TaskItem> listOfTasksCreatedByCreator = (ArrayList<TaskItem>) taskItemList
				.getTaskItemsByOwner(creator);
		String[][] taskArray = new String[listOfTasksCreatedByCreator.size()][3];
		for (int i = 0; i < listOfTasksCreatedByCreator.size(); i++) {
			taskArray[i][0] = Integer.toString(listOfTasksCreatedByCreator.get(i).getTaskItemId());
			taskArray[i][1] = listOfTasksCreatedByCreator.get(i).getStateName();
			taskArray[i][2] = listOfTasksCreatedByCreator.get(i).getTitle();
		}
		return taskArray;
	}

	/**
	 * Gets the task with the specified id
	 * 
	 * @param id
	 *            the id of the task wanted
	 * @return
	 */
	public TaskItem getTaskItemById(int id) {
		for (int i = 0; i < taskItemList.getTaskItems().size(); i++) {
			if (taskItemList.getTaskItems().get(i).getTaskItemId() == id) {
				return taskItemList.getTaskItems().get(i);
			}
		}
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
		for (int i = 0; i < taskItemList.getTaskItems().size(); i++) {
			if (taskItemList.getTaskItems().get(i).getTaskItemId() == id) {
				taskItemList.getTaskItems().get(i).update(command);
			}
		}
	}

	/**
	 * Deletes a task
	 * 
	 * @param id
	 *            the id of the task to delete
	 */
	public void deleteTaskItemById(int id) {
		for (int i = 0; i < taskItemList.getTaskItems().size(); i++) {
			if (taskItemList.getTaskItems().get(i).getTaskItemId() == id) {
				taskItemList.getTaskItems().remove(i);
			}
		}
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
		taskItemList.getTaskItems().add(new TaskItem(title, type, creator, noteText));
	}
}