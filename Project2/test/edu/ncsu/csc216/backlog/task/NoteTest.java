package edu.ncsu.csc216.backlog.task;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the functionality of the Note class
 * 
 * @author Caitlyn
 *
 */
public class NoteTest {

	/**
	 * Tests creating a note with valid parameters
	 */
	@Test
	public void testValidNote() {
		Note note = null;
		try {
			note = new Note("cjwiley2", "note");
			assertNotNull(note);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests creating notes with invalid parameters
	 */
	@Test
	public void testInvalidNote() {
		Note note = null;
		try {
			note = new Note("", "note");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(note);
		}

		Note note2 = null;
		try {
			note2 = new Note("cjwiley2", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(note2);
		}

		Note note3 = null;
		try {
			note3 = new Note(null, "note");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(note3);
		}

		Note note4 = null;
		try {
			note4 = new Note("cjwiley2", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(note4);
		}
	}

}
