package edu.ncsu.csc216.backlog.command;

/**
 * This class creates a command which corresponds to a user action
 * 
 * @author Caitlyn
 *
 */
public class Command {

	/** The new note written when an action is taken on a task */
	private String note;
	/** The author of the note */
	private String noteAuthor;
	/** The command value for the command */
	private CommandValue c;

	/**
	 * Constructs a command that takes a command value, note and note author
	 * 
	 * @param commandValue
	 *            corresponds to the action taken by the user
	 * @param note
	 *            the note the owner left when they changed the task
	 * @param noteAuthor
	 *            the author of the note
	 */
	public Command(CommandValue commandValue, String note, String noteAuthor) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the command value for the command the user made on the task
	 * 
	 * @return the command value corresponding to the command
	 */
	public CommandValue getCommand() {
		return null;
	}

	/**
	 * Gets the text in the note made
	 * 
	 * @return the note text
	 */
	public String getNoteText() {
		return "";
	}

	/**
	 * Gets the author of the note
	 * 
	 * @return the author of the note
	 */
	public String getNoteAutor() {
		return "";
	}

	/**
	 * This enumeration contains instances of itself corresponding to actions
	 * the user can take on a task
	 * 
	 * @author Caitlyn
	 *
	 */
	public static enum CommandValue {
		BACKLOG, CLAIM, PROCESS, VERIFY, COMPLETE, REJECT
	}

}
