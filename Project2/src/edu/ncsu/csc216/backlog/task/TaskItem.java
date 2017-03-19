package edu.ncsu.csc216.backlog.task;

import java.util.ArrayList;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.task.xml.Task;

/**
 * This class is responsible for creating the tasks that will be held in
 * TaskItemList
 * 
 * @author Caitlyn
 *
 */
public class TaskItem {

	/**
	 * The number assigned to a task, dependent on the most recently created
	 * task
	 */
	private int taskId;
	/** The current state that the task is in */
	private TaskItemState state;
	/** The title of the task */
	private String title;
	/** The creator of the task */
	private String creator;
	/** The owner of the task */
	private String owner;
	/** Tells whether the task has been verified or not */
	private boolean isVerified;
	/** Holds an instance of BacklogState */
	private final TaskItemState backlogState = new BacklogState();
	/** Holds an instance of OwnedState */
	private final TaskItemState ownedState = new OwnedState();
	/** Holds an instance of ProcessingState */
	private final TaskItemState processingState = new ProcessingState();
	/** Holds an instance of VerifyingState */
	private final TaskItemState verifyingState = new VerifyingState();
	/** Holds an instance of DoneState */
	private final TaskItemState doneState = new DoneState();
	/** Holds an instance of RejectedState */
	private final TaskItemState rejectedState = new RejectedState();
	/** The string displayed when the task is in the Backlog state */
	private static final String BACKLOG_NAME = "Backlog";
	/** The string displayed when the task is in the Owned state */
	private static final String OWNED_NAME = "Owned";
	/** The string displayed when the task is in the Processing state */
	private static final String PROCESSING_NAME = "Processing";
	/** The string displayed when the task is in the Verifying state */
	private static final String VERIFYING_NAME = "Verifying";
	/** The string displayed when the task is in the Done state */
	private static final String DONE_NAME = "Done";
	/** The string displayed when the task is in the Rejected state */
	private static final String REJECTED_NAME = "Rejected";
	/** The string displayed if the task is a Feature */
	private static final String T_FEATURE = "F";
	/** The string displayed if the task is a Bug */
	private static final String T_BUG = "B";
	/** The string displayed if the task is a Technical Work */
	private static final String T_TECHNICAL_WORK = "TW";
	/** The string displayed if the task is a Knowledge Acquisition */
	private static final String T_KNOWLEDGE_ACQUSITION = "KA";
	/**
	 * Updates when a task is created to keep track of the previous task's id
	 */
	private static int counter = 1;
	/** The type of the task */
	private Type type;
	/** An ArrayList of all notes made on the task */
	private ArrayList<Note> notes;

	/**
	 * Constructs a TaskItem with a title, type, creator and initial note
	 * 
	 * @param title
	 *            of the task
	 * @param type
	 *            of task created
	 * @param creator
	 *            id of the person who created the task
	 * @param noteText
	 *            the note added when creating the task
	 */
	public TaskItem(String title, Type type, String creator, String noteText) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a TaskItem when reading from an XML file and takes a Task as a
	 * parameter
	 * 
	 * @param task
	 *            the TaskItem to create
	 */
	public TaskItem(Task task) {

	}

	/**
	 * Increments the counter by 1 so the task id of a new task is 1 greater
	 * than the previous task
	 */
	public static void incrementCounter() {

	}

	/**
	 * Gets the id of the TaskItem
	 * 
	 * @return the id of the task
	 */
	public int getTaskItemId() {
		return -1;
	}

	/**
	 * Gets the name of the state the task is in
	 * 
	 * @return String representing the current state of the task
	 */
	public String getStateName() {
		return "";
	}

	/**
	 * Sets the state of the task to the state passed as a parameter
	 * 
	 * @param state
	 *            the state to set the task to
	 */
	private void setState(String state) {

	}

	/**
	 * Sets the type of the task to the type passed as a parameter
	 * 
	 * @param type
	 *            the type to set the task as
	 */
	private void setType(String type) {

	}

	/**
	 * Gets the type of task
	 * 
	 * @return the type of task
	 */
	public Type getType() {
		return null;
	}

	/**
	 * Gets the String representing the type of the task
	 * 
	 * @return String for the type of task (1 or 2 letters only)
	 */
	public String getTypeString() {
		return "";
	}

	/**
	 * Gets the String representing the type of the task
	 * 
	 * @return String for the type of task (full type name)
	 */
	public String getTypeFullString() {
		return "";
	}

	/**
	 * Gets the current owner of the task
	 * 
	 * @return String showing owner's id
	 */
	public String getOwner() {
		return "";
	}

	/**
	 * Gets the title of the task
	 * 
	 * @return title of the task
	 */
	public String getTitle() {
		return "";
	}

	/**
	 * Gets the creator of the task
	 * 
	 * @return creator's id
	 */
	public String getCreator() {
		return "";
	}

	/**
	 * Gets a list of all the notes for the task
	 * 
	 * @return an array list of notes for the task
	 */
	public ArrayList<Note> getNotes() {
		return null;
	}

	/**
	 * Updates the task with the command specified. Could be claiming a task,
	 * adding a note, etc.
	 * 
	 * @param command
	 *            the command to use to update the task
	 */
	public void update(Command command) {

	}

	/**
	 * Returns a Task in XML format. Used to write tasks to an XML file
	 * 
	 * @return a Task that can be written to an XML file
	 */
	public Task getXMLTask() {
		return null;
	}

	/**
	 * Sets the counter variable to a new number. Useful if a file of tasks is
	 * loaded and the user wants to add more tasks and doesn't want the task ids
	 * to start counting at 1
	 * 
	 * @param counter
	 *            the number to set the counter to
	 */
	public static void setCounter(int counter) {

	}

	/**
	 * Gets a 2d array of notes so they can be displayed easier when viewing a
	 * specific task
	 * 
	 * @return 2d String array containing notes and their authors
	 */
	public String[][] getNotesArray() {
		return null;
	}

	/**
	 * This class handles any changes in state when the task is in the Backlog
	 * state
	 * 
	 * @author Caitlyn
	 *
	 */
	private class BacklogState implements TaskItemState {

		/**
		 * Constructs a BacklogState object
		 */
		private BacklogState() {

		}

		/**
		 * Updates the state of the task by using the command given
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		/**
		 * Updates the state name
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private class OwnedState implements TaskItemState {

		/**
		 * Constructs a OwnedState object
		 */
		private OwnedState() {

		}

		/**
		 * Updates the state of the task by using the command given
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		/**
		 * Updates the state name
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private class ProcessingState implements TaskItemState {

		/**
		 * Constructs a ProcessingState object
		 */
		private ProcessingState() {

		}

		/**
		 * Updates the state of the task by using the command given
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		/**
		 * Updates the state name
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private class VerifyingState implements TaskItemState {

		/**
		 * Constructs a VerifyingState object
		 */
		private VerifyingState() {

		}

		/**
		 * Updates the state of the task by using the command given
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		/**
		 * Updates the state name
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private class DoneState implements TaskItemState {

		/**
		 * Constructs a DoneState object
		 */
		private DoneState() {

		}

		/**
		 * Updates the state of the task by using the command given
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		/**
		 * Updates the state name
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private class RejectedState implements TaskItemState {

		/**
		 * Constructs RejectedState object
		 */
		private RejectedState() {

		}

		/**
		 * Updates the state of the task by using the command given
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		/**
		 * Updates the state name
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/**
	 * This enumeration holds instances of itself to be used to identify a
	 * task's type since the type will never change
	 * 
	 * @author Caitlyn
	 *
	 */
	public static enum Type {
		FEATURE, BUG, TECHNICAL_WORK, KNOWLEDGE_ACQUISITION
	}

	/**
	 * Interface for states in the Task State Pattern. All concrete task states
	 * must implement the TaskState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private interface TaskItemState {

		/**
		 * Update the {@link TaskItem} based on the given {@link Command}. An
		 * {@link UnsupportedOperationException} is throw if the
		 * {@link CommandValue} is not a valid action for the given state.
		 * 
		 * @param c
		 *            {@link Command} describing the action that will update the
		 *            {@link TaskItem}'s state.
		 * @throws UnsupportedOperationException
		 *             if the {@link CommandValue} is not a valid action for the
		 *             given state.
		 */
		void updateState(Command c);

		/**
		 * Returns the name of the current state as a String.
		 * 
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}
}