package edu.ncsu.csc216.backlog.scrum_backlog;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;

public class TaskItemList {

	private static final int INITIAL_COUNTER_VALUE = 1;
	private ArrayList<TaskItem> tasks;

	public TaskItemList() {
		// TODO Auto-generated constructor stub
	}

	private void emptyList() {

	}

	public int addTaskItem(String title, Type type, String creator, String noteText) {

	}

	public void addXMLTasks(List<Task> tasks) {

	}

	public List<TaskItem> getTaskItems() {
		return null;
	}

	public List<TaskItem> getTaskItemsByOwner(String owner) {
		return null;
	}

	public List<TaskItem> getTasksByCreator(String creator) {
		return null;
	}

	public TaskItem getTaskItemById(int id) {
		return null;
	}

	public void executeCommand(int taskId, Command command) {

	}

	public void deleteTaskItemById(int id) {

	}
}
