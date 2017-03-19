package edu.ncsu.csc216.backlog.task;

/**
 * This class creates a Note object that can be added to a task and holds an
 * author and note text
 * 
 * @author Caitlyn
 *
 */
public class Note {

	/** The id of the person who wrote the note */
	private String noteAuthor;
	/** The text in the note */
	private String noteText;

	/**
	 * Constructs a note object with an author and note text
	 * 
	 * @param noteAuthor
	 *            the author of the note
	 * @param noteText
	 *            the text in the body of the note
	 */
	public Note(String noteAuthor, String noteText) {
		if (noteAuthor == null || noteText == null || noteAuthor.equals("") || noteText.equals("")) {
			throw new IllegalArgumentException();
		}
		this.noteAuthor = noteAuthor;
		this.noteText = noteText;
	}

	/**
	 * Gets the author of the note
	 * 
	 * @return author of the note
	 */
	public String getNoteAuthor() {
		return noteAuthor;
	}

	/**
	 * Gets the note text
	 * 
	 * @return text in the body of the note
	 */
	public String getNoteText() {
		return noteText;
	}


	/**
	 * Gets an array containing the note author and note text
	 * 
	 * @return a 1d array containing the note info
	 */
	public String[] getNoteArray() {
		String[] noteArray = { noteAuthor, noteText };
		return noteArray;
	}
}
