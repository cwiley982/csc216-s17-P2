package edu.ncsu.csc216.backlog.scrum_backlog;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;

public class ScrumBacklogModel {

	private Array<TaskItem> taskItemList;

	private ScrumBacklogModel() {
		// TODO Auto-generated constructor stub
	}

	public static ScrumBacklogModel getInstance() {

	}

	public void saveTasksToFile(String filename) {

	}

	public void loadTasksFromFile(String filename) {

	}

	public void createNewTaskItemList() {

	}

	public Object[][] getTaskItemListAsArray() {
		return null;
	}

	public Object[][] getTaskItemListByOwnerAsArray(String owner) {
		return null;
	}

	public Object[][] getTaskItemListByCreatorAsArray(String creator) {
		return null;
	}

	public TaskItem getTaskItemById(int id) {
		return null;
	}

	public void executeCommand(int id, Command command) {

	}

	public void deleteTaskItemById(int id) {

	}

	public void addTaskItemToList(String title, Type type, String creator, String noteText) {

	}
}