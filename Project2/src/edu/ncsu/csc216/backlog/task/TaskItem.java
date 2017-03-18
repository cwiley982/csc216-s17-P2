package edu.ncsu.csc216.backlog.task;

import java.util.ArrayList;

import edu.ncsu.csc216.backlog.command.Command;

public class TaskItem {

	private int taskId;
	private TaskItemState state;
	private String title;
	private String creator;
	private String owner;
	private boolean isVerified;
	private final TaskItemState backlogState = new BacklogState();
	private final TaskItemState ownedState = new OwnedState();
	private final TaskItemState processingState = new ProcessingState();
	private final TaskItemState verifyingState = new VerifyingState();
	private final TaskItemState doneState = new DoneState();
	private final TaskItemState rejectedState = new RejectedState();
	private static final String BACKLOG_NAME = "Backlog";
	private static final String OWNED_NAME = "Owned";
	private static final String PROCESSING_NAME = "Processing";
	private static final String DONE_NAME = "Done";
	private static final String REJECTED_NAME = "Rejected";
	private static final String T_FEATURE = "F";
	private static final String T_BUG = "B";
	private static final String T_TECHNICAL_WORK = "TW";
	private static final String T_KNOWLEDGE_ACQUSITION = "KA";
	private static int counter = 1;
	private Type type;
	private ArrayList<Note> notes;

	public TaskItem(String title, Type type, String creator, String noteText) {
		// TODO Auto-generated constructor stub, don't actually know order of
		// parameters
	}

	public TaskItem(Task task) {

	}

	public static void incrementCounter() {

	}

	public int getTaskItemId() {
		return -1;
	}

	public String getStateName() {
		return "";
	}

	private void setState(String state) {

	}

	private void setType(String type) {

	}

	public Type getType() {
		return null;
	}

	public String getTypeString() {
		return "";
	}

	public String getTypeFullString() {
		return "";
	}

	public String getOwner() {
		return "";
	}

	public String getTitle() {
		return "";
	}

	public String getCreator() {
		return "";
	}

	public ArrayList<Note> getNotes() {
		return null;
	}

	public void update(Command command) {

	}

	public Task getXMLTask() {

	}

	public static void setCounter(int counter) {

	}

	public String[][] getNotesArray() {
		return null;
	}

	private class BacklogState implements TaskItemState {

		private BacklogState() {

		}

		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private class OwnedState implements TaskItemState {

		private OwnedState() {

		}

		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private class ProcessingState implements TaskItemState {

		private ProcessingState() {

		}

		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private class VerifyingState implements TaskItemState {

		private VerifyingState() {

		}

		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private class DoneState implements TaskItemState {

		private DoneState() {

		}

		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private class RejectedState implements TaskItemState {

		private RejectedState() {

		}

		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub

		}

		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private enum Type {
		private static final Type FEATURE;
		private static final Type BUG;
		private static final Type TECHNICAL_WORK;
		private static final Type KNOWLEDGE_ACQUISITION;

		public Type() {

		}
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