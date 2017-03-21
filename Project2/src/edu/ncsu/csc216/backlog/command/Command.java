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
	 * @param c
	 *            corresponds to the action taken by the user
	 * @param noteAuthor
	 *            the author of the note
	 * @param noteText
	 *            the note the owner left when they changed the task
	 */
	public Command(CommandValue c, String noteAuthor, String noteText) {
		if (c == null || noteAuthor == null || noteText == null || noteAuthor.equals("") || noteText.equals("")) {
			throw new IllegalArgumentException();
		}
		this.c = c;
		this.note = noteText;
		this.noteAuthor = noteAuthor;
	}

	/**
	 * Gets the command value for the command the user made on the task
	 * 
	 * @return the command value corresponding to the command
	 */
	public CommandValue getCommand() {
		return c;
	}

	/**
	 * Gets the text in the note made
	 * 
	 * @return the note text
	 */
	public String getNoteText() {
		return note;
	}

	/**
	 * Gets the author of the note
	 * 
	 * @return the author of the note
	 */
	public String getNoteAuthor() {
		return noteAuthor;
	}

	/**
	 * This enumeration contains instances of itself corresponding to actions
	 * the user can take on a task
	 * 
	 * @author Caitlyn
	 *
	 */
	public static enum CommandValue {
		/** command to backlog task */
		BACKLOG,
		/** command to claim task */
		CLAIM,
		/** command to process task */
		PROCESS,
		/** command to verify task */
		VERIFY,
		/** command to complete task */
		COMPLETE,
		/** command to rejet task */
		REJECT
	}

}
