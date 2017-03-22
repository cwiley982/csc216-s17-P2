package edu.ncsu.csc216.backlog.scrum_backlog;

import java.util.ArrayList;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.TaskIOException;
import edu.ncsu.csc216.task.xml.TaskReader;
import edu.ncsu.csc216.task.xml.TaskWriter;

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
	private static ScrumBacklogModel singleton = new ScrumBacklogModel();

	/**
	 * Constructs an instance of the ScrumBacklogModel
	 */
	private ScrumBacklogModel() {
		taskItemList = new TaskItemList();
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
		try {
			TaskWriter writer = new TaskWriter(filename);
			ArrayList<TaskItem> taskItems = (ArrayList<TaskItem>) taskItemList.getTaskItems();
			for (int i = 0; i < taskItems.size(); i++) {
				writer.addItem(taskItems.get(i).getXMLTask());
			}
			writer.marshal();
		} catch (TaskIOException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Loads tasks from a file
	 * 
	 * @param filename
	 *            the name of the file to load tasks from
	 */
	public void loadTasksFromFile(String filename) {
		try {
			TaskReader reader = new TaskReader(filename);
			taskItemList.addXMLTasks(reader.getTasks());
			TaskItem.setCounter(reader.getTasks().size() + 1);
		} catch (TaskIOException e) {
			throw new IllegalArgumentException();
		}
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
		Object[][] taskArray = new Object[taskItemList.getTaskItems().size()][3];
		for (int i = 0; i < taskItemList.getTaskItems().size(); i++) {
			taskArray[i][0] = taskItemList.getTaskItems().get(i).getTaskItemId();
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
		Object[][] taskArray = new Object[listOfTasksOwnedByOwner.size()][3];
		for (int i = 0; i < listOfTasksOwnedByOwner.size(); i++) {
			taskArray[i][0] = listOfTasksOwnedByOwner.get(i).getTaskItemId();
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
				.getTasksByCreator(creator);
		Object[][] taskArray = new Object[listOfTasksCreatedByCreator.size()][3];
		for (int i = 0; i < listOfTasksCreatedByCreator.size(); i++) {
			taskArray[i][0] = listOfTasksCreatedByCreator.get(i).getTaskItemId();
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
	 * @return the task with the matching id number
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
		taskItemList.executeCommand(id, command);
	}

	/**
	 * Deletes a task
	 * 
	 * @param id
	 *            the id of the task to delete
	 */
	public void deleteTaskItemById(int id) {
		taskItemList.deleteTaskItemById(id);
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
		taskItemList.addTaskItem(title, type, creator, noteText);
	}
}