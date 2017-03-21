package edu.ncsu.csc216.backlog.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command.CommandValue;

/**
 * Tests the functionality of the Command class
 * 
 * @author Caitlyn
 *
 */
public class CommandTest {

	/**
	 * Tests constructing a command with valid parameters
	 */
	@Test
	public void testValidCommand() {
		Command c = null;
		try {
			c = new Command(CommandValue.BACKLOG, "cjwiley2", "note");
			assertNotNull(c);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests constructing commands with invalid parameters
	 */
	@Test
	public void testInvalidCommand() {
		Command c = null;
		try {
			c = new Command(null, "cjwiley2", "note");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		Command c2 = null;
		try {
			c2 = new Command(CommandValue.CLAIM, null, "note");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c2);
		}

		Command c3 = null;
		try {
			c3 = new Command(CommandValue.CLAIM, "", "note");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c3);
		}

		Command c4 = null;
		try {
			c4 = new Command(CommandValue.CLAIM, "cjwiley2", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c4);
		}

		Command c5 = null;
		try {
			c5 = new Command(CommandValue.CLAIM, "cjwiley2", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c5);
		}
	}

	/**
	 * Tests getting the command value for the command
	 */
	@Test
	public void testGetCommand() {
		Command c = new Command(CommandValue.BACKLOG, "cjwiley2", "note");
		assertEquals(CommandValue.BACKLOG, c.getCommand());
	}

	/**
	 * Tests getting the note text from the command
	 */
	@Test
	public void testGetNoteText() {
		Command c = new Command(CommandValue.BACKLOG, "cjwiley2", "note");
		assertEquals("note", c.getNoteText());
	}

	/**
	 * Tests getting the author of the note for the command
	 */
	@Test
	public void testGetNoteAuthor() {
		Command c = new Command(CommandValue.BACKLOG, "cjwiley2", "note");
		assertEquals("cjwiley2", c.getNoteAuthor());
	}

}
