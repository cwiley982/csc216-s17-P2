package edu.ncsu.csc216.backlog.task;

import java.util.ArrayList;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
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
	public static final String BACKLOG_NAME = "Backlog";
	/** The string displayed when the task is in the Owned state */
	public static final String OWNED_NAME = "Owned";
	/** The string displayed when the task is in the Processing state */
	public static final String PROCESSING_NAME = "Processing";
	/** The string displayed when the task is in the Verifying state */
	public static final String VERIFYING_NAME = "Verifying";
	/** The string displayed when the task is in the Done state */
	public static final String DONE_NAME = "Done";
	/** The string displayed when the task is in the Rejected state */
	public static final String REJECTED_NAME = "Rejected";
	/** The string displayed if the task is a Feature */
	public static final String T_FEATURE = "F";
	/** The string displayed if the task is a Bug */
	public static final String T_BUG = "B";
	/** The string displayed if the task is a Technical Work */
	public static final String T_TECHNICAL_WORK = "TW";
	/** The string displayed if the task is a Knowledge Acquisition */
	public static final String T_KNOWLEDGE_ACQUSITION = "KA";
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
	 * @param note
	 *            the note added when creating the task
	 */
	public TaskItem(String title, Type type, String creator, String note) {
		if (type == null || title == null || creator == null || note == null || title.isEmpty() || creator.isEmpty()
				|| note.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		this.type = type;
		this.creator = creator;
		owner = null;
		notes = new ArrayList<Note>();
		notes.add(new Note(creator, note));
		taskId = counter;
		setState(BACKLOG_NAME);
		incrementCounter();
	}

	/**
	 * Constructs a TaskItem when reading from an XML file and takes a Task as a
	 * parameter
	 * 
	 * @param task
	 *            the TaskItem to create
	 */
	public TaskItem(Task task) {
		notes = new ArrayList<Note>();
		this.title = task.getTitle();
		this.creator = task.getCreator();
		this.owner = task.getOwner();
		this.isVerified = task.isVerified();
		setType(task.getType());
		this.taskId = task.getId();
		setState(task.getState());
		for (int i = 0; i < task.getNoteList().getNotes().size(); i++) {
			Note note = new Note(task.getNoteList().getNotes().get(i).getNoteAuthor(),
					task.getNoteList().getNotes().get(i).getNoteText());
			notes.add(note);

		}
	}

	/**
	 * Increments the counter by 1 so the task id of a new task is 1 greater
	 * than the previous task
	 */
	public static void incrementCounter() {
		counter += 1;
	}

	/**
	 * Gets the id of the TaskItem
	 * 
	 * @return the id of the task
	 */
	public int getTaskItemId() {
		return taskId;
	}

	/**
	 * Gets the name of the state the task is in
	 * 
	 * @return String representing the current state of the task
	 */
	public String getStateName() {
		return state.getStateName();
	}

	/**
	 * Sets the state of the task to the state passed as a parameter
	 * 
	 * @param state
	 *            the state to set the task to
	 */
	private void setState(String state) {
		if (state == null) {
			throw new IllegalArgumentException();
		}
		if (!state.equals(BACKLOG_NAME) && !state.equals(OWNED_NAME) && !state.equals(PROCESSING_NAME)
				&& !state.equals(VERIFYING_NAME) && !state.equals(DONE_NAME) && !state.equals(REJECTED_NAME)) {
			throw new IllegalArgumentException();
		}
		if (state.equals(BACKLOG_NAME)) {
			this.state = backlogState;
		} else if (state.equals(OWNED_NAME)) {
			this.state = ownedState;
		} else if (state.equals(PROCESSING_NAME)) {
			this.state = processingState;
		} else if (state.equals(VERIFYING_NAME)) {
			this.state = verifyingState;
		} else if (state.equals(DONE_NAME)) {
			this.state = doneState;
		} else if (state.equals(REJECTED_NAME)) {
			this.state = rejectedState;
		}
	}

	/**
	 * Sets the type of the task to the type passed as a parameter
	 * 
	 * @param type
	 *            the type to set the task as
	 */
	private void setType(String type) {
		if (type == null) {
			throw new IllegalArgumentException();
		}
		if (!type.equals(T_BUG) && !type.equals(T_FEATURE) && !type.equals(T_KNOWLEDGE_ACQUSITION)
				&& !type.equals(T_TECHNICAL_WORK)) {
			throw new IllegalArgumentException();
		}
		if (type.equals(T_BUG)) {
			this.type = Type.BUG;
		} else if (type.equals(T_FEATURE)) {
			this.type = Type.FEATURE;
		} else if (type.equals(T_KNOWLEDGE_ACQUSITION)) {
			this.type = Type.KNOWLEDGE_ACQUISITION;
		} else if (type.equals(T_TECHNICAL_WORK)) {
			this.type = Type.TECHNICAL_WORK;
		}
	}

	/**
	 * Gets the type of task
	 * 
	 * @return the type of task
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Gets the String representing the type of the task
	 * 
	 * @return String for the type of task (1 or 2 letters only)
	 */
	public String getTypeString() {
		if (type == Type.BUG) {
			return T_BUG;
		} else if (type == Type.FEATURE) {
			return T_FEATURE;
		} else if (type == Type.KNOWLEDGE_ACQUISITION) {
			return T_KNOWLEDGE_ACQUSITION;
		} else { // if (type == Type.TECHNICAL_WORK)
			return T_TECHNICAL_WORK;
		}
	}

	/**
	 * Gets the String representing the type of the task
	 * 
	 * @return String for the type of task (full type name)
	 */
	public String getTypeFullString() {
		if (type == Type.BUG) {
			return "Bug";
		} else if (type == Type.FEATURE) {
			return "Feature";
		} else if (type == Type.KNOWLEDGE_ACQUISITION) {
			return "Knowledge Acquisition";
		} else { // if (type == Type.TECHNICAL_WORK)
			return "Technical Work";
		}
	}

	/**
	 * Gets the current owner of the task
	 * 
	 * @return String showing owner's id
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Gets the title of the task
	 * 
	 * @return title of the task
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the creator of the task
	 * 
	 * @return creator's id
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Gets a list of all the notes for the task
	 * 
	 * @return an array list of notes for the task
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}

	/**
	 * Updates the task with the command specified. Could be claiming a task,
	 * adding a note, etc.
	 * 
	 * @param command
	 *            the command to use to update the task
	 */
	public void update(Command command) {
		state.updateState(command);
	}

	/**
	 * Returns a Task in XML format. Used to write tasks to an XML file
	 * 
	 * @return a Task that can be written to an XML file
	 */
	public Task getXMLTask() {
		Task task = new Task();
		task.setTitle(getTitle());
		task.setType(getTypeString());
		task.setCreator(getCreator());
		task.setId(getTaskItemId());
		task.setState(getStateName());
		NoteList noteList = new NoteList();
		for (int i = 0; i < getNotes().size(); i++) {
			NoteItem noteItem = new NoteItem();
			noteItem.setNoteAuthor(getNotes().get(i).getNoteAuthor());
			noteItem.setNoteText(getNotes().get(i).getNoteText());
			noteList.getNotes().add(noteItem);
		}
		task.setVerified(isVerified);
		task.setNoteList(noteList);
		return task;
	}

	/**
	 * Sets the counter variable to a new number. Useful if a file of tasks is
	 * loaded and the user wants to add more tasks and doesn't want the task ids
	 * to start counting at 1
	 * 
	 * @param newCounter
	 *            the number to set the counter to
	 */
	public static void setCounter(int newCounter) {
		if (newCounter <= 0) {
			throw new IllegalArgumentException();
		} else {
			counter = newCounter;
		}
	}

	/**
	 * Gets a 2d array of notes so they can be displayed easier when viewing a
	 * specific task
	 * 
	 * @return 2d String array containing notes and their authors
	 */
	public String[][] getNotesArray() {
		String[][] notesArray = new String[notes.size()][notes.size()];
		for (int i = 0; i < notes.size(); i++) {
			notesArray[i] = notes.get(i).getNoteArray();
		}
		return notesArray;
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
		 * 
		 * @param c
		 *            the command to use to update the state
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.CLAIM) {
				state = ownedState;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
				owner = c.getNoteAuthor();
			} else if (c.getCommand() == CommandValue.REJECT) {
				state = rejectedState;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else {
				throw new UnsupportedOperationException();
			}

		}

		/**
		 * Returns the state name
		 * 
		 * @return the name of the state
		 */
		@Override
		public String getStateName() {
			return BACKLOG_NAME;
		}

	}

	/**
	 * This class handles any changes in state when the task is in the Owned
	 * state
	 * 
	 * @author Caitlyn
	 *
	 */
	private class OwnedState implements TaskItemState {

		/**
		 * Constructs a OwnedState object
		 */
		private OwnedState() {

		}

		/**
		 * Updates the state of the task by using the command given
		 * 
		 * @param c
		 *            the command to use to update the state
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.BACKLOG) {
				state = backlogState;
				owner = null;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.PROCESS) {
				state = processingState;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.REJECT) {
				state = rejectedState;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
				owner = null;
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns the state name
		 * 
		 * @return the name of the state
		 */
		@Override
		public String getStateName() {
			return OWNED_NAME;
		}

	}

	/**
	 * This class handles any changes in state when the task is in the
	 * Processing state
	 * 
	 * @author Caitlyn
	 *
	 */
	private class ProcessingState implements TaskItemState {

		/**
		 * Constructs a ProcessingState object
		 */
		private ProcessingState() {

		}

		/**
		 * Updates the state of the task by using the command given
		 * 
		 * @param c
		 *            the command to use to update the state
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.PROCESS) {
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.COMPLETE) {
				if (type == Type.KNOWLEDGE_ACQUISITION) {
					state = doneState;
					notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
					owner = "owner";
				} else {
					throw new UnsupportedOperationException();
				}
			} else if (c.getCommand() == CommandValue.VERIFY) {
				if (type != Type.KNOWLEDGE_ACQUISITION) {
					state = verifyingState;
					notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
				} else {
					throw new UnsupportedOperationException();
				}
			} else if (c.getCommand() == CommandValue.BACKLOG) {
				state = backlogState;
				owner = null;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else {
				throw new UnsupportedOperationException();
			}

		}

		/**
		 * Returns the state name
		 * 
		 * @return the name of the state
		 */
		@Override
		public String getStateName() {
			return PROCESSING_NAME;
		}

	}

	/**
	 * This class handles any changes in state when the task is in the Verifying
	 * state
	 * 
	 * @author Caitlyn
	 *
	 */
	private class VerifyingState implements TaskItemState {

		/**
		 * Constructs a VerifyingState object
		 */
		private VerifyingState() {

		}

		/**
		 * Updates the state of the task by using the command given
		 * 
		 * @param c
		 *            the command to use to update the state
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.PROCESS) {
				state = processingState;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else if (c.getCommand() == CommandValue.COMPLETE) {
				state = doneState;
				isVerified = true;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
				owner = c.getNoteAuthor();
			} else {
				throw new UnsupportedOperationException();
			}

		}

		/**
		 * Returns the state name
		 * 
		 * @return the name of the state
		 */
		@Override
		public String getStateName() {
			return VERIFYING_NAME;
		}

	}

	/**
	 * This class handles any changes in state when the task is in the Done
	 * state
	 * 
	 * @author Caitlyn
	 *
	 */
	private class DoneState implements TaskItemState {

		/**
		 * Constructs a DoneState object
		 */
		private DoneState() {

		}

		/**
		 * Updates the state of the task by using the command given
		 * 
		 * @param c
		 *            the command to use to update the state
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.PROCESS) {
				state = processingState;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
				owner = c.getNoteAuthor();
			} else if (c.getCommand() == CommandValue.BACKLOG) {
				state = backlogState;
				owner = null;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
			} else {
				throw new UnsupportedOperationException();
			}

		}

		/**
		 * Returns the state name
		 * 
		 * @return the name of the state
		 */
		@Override
		public String getStateName() {
			return DONE_NAME;
		}

	}

	/**
	 * This class handles any changes in state when the task is in the Rejected
	 * state
	 * 
	 * @author Caitlyn
	 *
	 */
	private class RejectedState implements TaskItemState {

		/**
		 * Constructs RejectedState object
		 */
		private RejectedState() {

		}

		/**
		 * Updates the state of the task by using the command given
		 * 
		 * @param c
		 *            the command to use to update the state
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.BACKLOG) {
				state = backlogState;
				notes.add(new Note(c.getNoteAuthor(), c.getNoteText()));
				owner = null;
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns the state name
		 * 
		 * @return the name of the state
		 */
		@Override
		public String getStateName() {
			return REJECTED_NAME;
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
		/** Feature type */
		FEATURE,
		/** Bug type */
		BUG,
		/** Technical work type */
		TECHNICAL_WORK,
		/** Knowledge acquisition type */
		KNOWLEDGE_ACQUISITION
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