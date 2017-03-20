package edu.ncsu.csc216.backlog.scrum_backlog;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.Task;

/**
 * This class creates and holds a list of TaskItems and can sort the list,
 * delete TaskItems, add TaskItems, etc
 * 
 * @author Caitlyn
 *
 */
public class TaskItemList {

	/** The id number for the first TaskItem created */
	private static final int INITIAL_COUNTER_VALUE = 1;
	/** This ArrayList holds all the TaskItems */
	private ArrayList<TaskItem> tasks;

	/**
	 * Constructs a TaskItemList and initializes the ArrayList of TaskItems
	 */
	public TaskItemList() {
		TaskItem.setCounter(INITIAL_COUNTER_VALUE);
		tasks = new ArrayList<TaskItem>();
	}

	/**
	 * Clears the ArrayList of TaskItems
	 */
	private void emptyList() {
		tasks = new ArrayList<TaskItem>();
	}

	/**
	 * Adds a TaskItem to the list
	 * 
	 * @param title
	 *            of the TaskItem
	 * @param type
	 *            of the TaskItem
	 * @param creator
	 *            who created the TaskItem
	 * @param noteText
	 *            text in the initial note for the TaskItem
	 * @return
	 */
	public int addTaskItem(String title, Type type, String creator, String noteText) {
		tasks.add(new TaskItem(title, type, creator, noteText));
		return tasks.get(tasks.size()).getTaskItemId();
	}

	/**
	 * Adds TaskItems to the ArrayList by reading them in through an XML file
	 * 
	 * @param tasks
	 *            the List of Tasks to be read
	 */
	public void addXMLTasks(List<Task> tasks) {
		for (int i = 0; i < tasks.size(); i++) {
			Task task = tasks.get(i);
			addTaskItem(task);
		}
		// TODO
	}

	/**
	 * Returns a List of TaskItems
	 * 
	 * @return the list of current tasks
	 */
	public List<TaskItem> getTaskItems() {
		return tasks;
	}

	/**
	 * Filters the list of TaskItems by their owner and returns all tasks owned
	 * by the user specified owner
	 * 
	 * @param owner
	 *            the owner that the user wants to view all tasks for
	 * @return a List of TaskItems whose owner is the owner passed as a
	 *         parameter
	 */
	public List<TaskItem> getTaskItemsByOwner(String owner) {
		ArrayList<TaskItem> haveSameOwner = new ArrayList<TaskItem>();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getOwner().equals(owner)) {
				haveSameOwner.add(tasks.get(i));
			}
		}
		return haveSameOwner;
	}

	/**
	 * Filters the list of TaskItems by their creator and returns all tasks
	 * created by the user specified creator
	 * 
	 * @param creator
	 *            the creator that the user wants to view all tasks for
	 * @return a List of TaskItems whose creator is the creator passed as a
	 *         parameter
	 */
	public List<TaskItem> getTasksByCreator(String creator) {
		ArrayList<TaskItem> haveSameCreator = new ArrayList<TaskItem>();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getOwner().equals(creator)) {
				haveSameCreator.add(tasks.get(i));
			}
		}
		return haveSameCreator;
	}

	/**
	 * Gets the task with the id specified
	 * 
	 * @param id
	 *            of the task the user wants
	 * @return the task with the id specified
	 */
	public TaskItem getTaskItemById(int id) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskItemId() == id) {
				return tasks.get(i);
			}
		}
		return null;
	}

	/**
	 * Executes a command from the user
	 * 
	 * @param taskId
	 *            the id of the task the command will be executed on
	 * @param command
	 *            tells what to do with the task
	 */
	public void executeCommand(int taskId, Command command) {
		getTaskItemById(taskId).update(command);
	}

	/**
	 * Deletes a task from the task list
	 * 
	 * @param id
	 *            id of the task to delete
	 */
	public void deleteTaskItemById(int id) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskItemId() == id) {
				tasks.remove(i);
			}
		}
	}
}
