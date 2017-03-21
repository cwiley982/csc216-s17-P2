package edu.ncsu.csc216.backlog.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Tests the functionality of the TaskItem class
 * 
 * @author Caitlyn
 *
 */
public class TaskItemTest {

	/**
	 * Sets the counter back to 1 before running each test
	 */
	@Before
	public void setUp() {
		TaskItem.setCounter(1);
	}

	/**
	 * Tests constructing a TaskItem with valid parameters
	 */
	@Test
	public void testValidTaskItemStringTypeStringString() {
		TaskItem task = null;
		try {
			task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note");
			assertNotNull(task);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests constructing a TaskItem with invalid parameters
	 */
	@Test
	public void testInvalidTaskItemStringTypeStringString() {
		TaskItem task = null;
		try {
			task = new TaskItem("", Type.BUG, "cjwiley2", "Initial note");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(task);
		}

		TaskItem task2 = null;
		try {
			task2 = new TaskItem(null, Type.BUG, "cjwiley2", "Initial note");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(task2);
		}

		TaskItem task3 = null;
		try {
			task3 = new TaskItem("Task 1", null, "cjwiley2", "Initial note");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(task3);
		}

		TaskItem task4 = null;
		try {
			task4 = new TaskItem("Task 1", Type.FEATURE, "", "Initial note");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(task4);
		}

		TaskItem task5 = null;
		try {
			task5 = new TaskItem("Task 1", Type.FEATURE, null, "Initial note");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(task5);
		}

		TaskItem task6 = null;
		try {
			task6 = new TaskItem("Task 1", Type.KNOWLEDGE_ACQUISITION, "cjwiley2", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(task6);
		}

		TaskItem task7 = null;
		try {
			task7 = new TaskItem("Task 1", Type.TECHNICAL_WORK, "cjwiley2", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(task7);
		}
	}

	/**
	 * Tests getting the task's id number
	 */
	@Test
	public void testGetId() {
		TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		assertEquals(1, task.getTaskItemId());
		TaskItem task2 = new TaskItem("Task 2", Type.FEATURE, "cjwiley2", "Initial note.");
		assertEquals(2, task2.getTaskItemId());
	}

	/**
	 * Tests getting the name of the state the task is in
	 */
	@Test
	public void testGetStateName() {
		TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		assertEquals("Backlog", task.getStateName());

		TaskItem task2 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		task2.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
		assertEquals("Owned", task2.getStateName());

		TaskItem task3 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		task3.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
		task3.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
		assertEquals("Processing", task3.getStateName());

		TaskItem task4 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		task4.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
		task4.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
		task4.update(new Command(CommandValue.VERIFY, "cjwiley2", "Fourth note."));
		assertEquals("Verifying", task4.getStateName());

		TaskItem task5 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		task5.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
		task5.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
		task5.update(new Command(CommandValue.VERIFY, "cjwiley2", "Fourth note."));
		task5.update(new Command(CommandValue.COMPLETE, "cjwiley2", "Fifth note."));
		assertEquals("Done", task5.getStateName());

		TaskItem task6 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		task6.update(new Command(CommandValue.REJECT, "cjwiley2", "Second note."));
		assertEquals("Rejected", task6.getStateName());
	}

	/**
	 * Tests setting the state of a task
	 */
	@Test
	public void testSetState() {
		// TODO
	}

	/**
	 * Tests getting the task's type
	 */
	@Test
	public void testGetType() {
		TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		assertEquals(Type.BUG, task.getType());
	}

	/**
	 * Tests getting the string representing the task type that only has one or
	 * two letters
	 */
	@Test
	public void testGetTypeString() {
		TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		assertEquals("B", task.getTypeString());

		TaskItem task2 = new TaskItem("Task 2", Type.FEATURE, "cjwiley2", "Initial note.");
		assertEquals("F", task2.getTypeString());

		TaskItem task3 = new TaskItem("Task 3", Type.KNOWLEDGE_ACQUISITION, "cjwiley2", "Initial note.");
		assertEquals("KA", task3.getTypeString());

		TaskItem task4 = new TaskItem("Task 4", Type.TECHNICAL_WORK, "cjwiley2", "Initial note.");
		assertEquals("TW", task4.getTypeString());
	}

	/**
	 * Tests getting the full string representing the task type
	 */
	@Test
	public void testGetTypeFullString() {
		TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		assertEquals("Bug", task.getTypeFullString());

		TaskItem task2 = new TaskItem("Task 2", Type.FEATURE, "cjwiley2", "Initial note.");
		assertEquals("Feature", task2.getTypeFullString());

		TaskItem task3 = new TaskItem("Task 3", Type.KNOWLEDGE_ACQUISITION, "cjwiley2", "Initial note.");
		assertEquals("Knowledge Acquisition", task3.getTypeFullString());

		TaskItem task4 = new TaskItem("Task 4", Type.TECHNICAL_WORK, "cjwiley2", "Initial note.");
		assertEquals("Technical Work", task4.getTypeFullString());
	}

	/**
	 * Tests getting the task's owner
	 */
	@Test
	public void testGetOwner() {
		TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		assertNull(task.getOwner());
		task.update(new Command(CommandValue.CLAIM, "caitlyn", "Second note."));
		assertEquals("caitlyn", task.getOwner());
	}

	/**
	 * Tests getting the task's title
	 */
	@Test
	public void testGetTitle() {
		TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		assertEquals("Task 1", task.getTitle());
	}

	/**
	 * Tests getting the task's creator
	 */
	@Test
	public void testGetCreator() {
		TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		assertEquals("cjwiley2", task.getCreator());
	}

	/**
	 * Tests getting the arraylist containing a task's notes
	 */
	@Test
	public void testGetNotes() {
		TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		task.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
		task.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
		task.update(new Command(CommandValue.VERIFY, "cjwiley2", "Fourth note."));
		task.update(new Command(CommandValue.COMPLETE, "cjwiley2", "Fifth note."));
		ArrayList<Note> notes = new ArrayList<Note>();
		notes.add(new Note("cjwiley2", "Initial note."));
		notes.add(new Note("cjwiley2", "Second note."));
		notes.add(new Note("cjwiley2", "Third note."));
		notes.add(new Note("cjwiley2", "Fourth note."));
		notes.add(new Note("cjwiley2", "Fifth note."));
		assertEquals(notes.get(3).getNoteText(), task.getNotes().get(3).getNoteText());
	}

	/**
	 * Tests getting a Task object that can be written to an XML file that
	 * contains the same info as a TaskItem
	 */
	@Test
	public void testGetXMLTask() {
		TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		Task xmlTask = new Task();
		xmlTask.setCreator("cjwiley2");
		xmlTask.setTitle("Task 1");
		xmlTask.setType("B");
		xmlTask.setId(1);
		xmlTask.setOwner(null);
		xmlTask.setState("Backlog");
		xmlTask.setVerified(false);
		NoteList list = new NoteList();
		NoteItem note = new NoteItem();
		note.setNoteAuthor("cjwiley2");
		note.setNoteText("Initial note.");
		list.getNotes().add(note);
		xmlTask.setNoteList(list);
		assertEquals(xmlTask.getTitle(), task.getXMLTask().getTitle());
		assertEquals(xmlTask.getType(), task.getXMLTask().getType());
		assertEquals(xmlTask.getCreator(), task.getXMLTask().getCreator());
		assertEquals(xmlTask.getId(), task.getXMLTask().getId());
		assertEquals(xmlTask.getOwner(), task.getXMLTask().getOwner());
		assertEquals(xmlTask.getState(), task.getXMLTask().getState());
		assertEquals(xmlTask.getNoteList().getNotes().get(0).getNoteText(),
				task.getXMLTask().getNoteList().getNotes().get(0).getNoteText());
	}

	/**
	 * Tests setting the counter to valid and invalid values
	 */
	@Test
	public void testSetCounter() {
		try {
			TaskItem.setCounter(3);
			TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			assertEquals(3, task.getTaskItemId());
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			TaskItem.setCounter(-2);
			fail();
		} catch (IllegalArgumentException e) {
			TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			assertEquals(4, task.getTaskItemId()); // should continue counting
													// from previous task
		}
	}

	/**
	 * Tests getting an array of notes for a task
	 */
	@Test
	public void testGetNotesArray() {
		TaskItem task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
		task.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
		task.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
		task.update(new Command(CommandValue.VERIFY, "cjwiley2", "Fourth note."));
		task.update(new Command(CommandValue.COMPLETE, "cjwiley2", "Fifth note."));
		String[][] notes = new String[5][2];
		notes[0][0] = "cjwiley2";
		notes[0][1] = "Initial note.";
		notes[1][0] = "cjwiley2";
		notes[1][1] = "Second note.";
		notes[2][0] = "cjwiley2";
		notes[2][1] = "Third note.";
		notes[3][0] = "cjwiley2";
		notes[3][1] = "Fourth note.";
		notes[4][0] = "cjwiley2";
		notes[4][1] = "Fifth note.";
		assertEquals(notes[2][1], task.getNotesArray()[2][1]);
	}
}
