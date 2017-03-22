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

	/**
	 * Tests possible transitions between states that haven't been covered in
	 * previous tests to cover all branches
	 */
	@Test
	public void testTransitionsBetweenStates() {
		// backlog to verify
		TaskItem task = null;
		try {
			task = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task.update(new Command(CommandValue.VERIFY, "cjwiley2", "Second note."));
			fail();
		} catch (UnsupportedOperationException e) {
			assertNotNull(task);
			assertEquals("Backlog", task.getStateName());
		}
		// owned to backlog
		TaskItem task2 = null;
		try {
			task2 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task2.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task2.update(new Command(CommandValue.BACKLOG, "cjwiley2", "Third note."));
			assertEquals("Backlog", task2.getStateName());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		// owned to rejected
		TaskItem task3 = null;
		try {
			task3 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task3.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task3.update(new Command(CommandValue.REJECT, "cjwiley2", "Third note."));
			assertEquals("Rejected", task3.getStateName());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		// owned to verify
		TaskItem task4 = null;
		try {
			task4 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task4.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task4.update(new Command(CommandValue.VERIFY, "cjwiley2", "Third note."));
			fail();
		} catch (UnsupportedOperationException e) {
			assertNotNull(task4);
			assertEquals("Owned", task4.getStateName());
		}
		// processing to processing
		TaskItem task5 = null;
		try {
			task5 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task5.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task5.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
			task5.update(new Command(CommandValue.PROCESS, "cjwiley2", "Fourth note."));
			assertEquals("Fourth note.", task5.getNotes().get(3).getNoteText());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		// processing to complete (KA)
		TaskItem task6 = null;
		try {
			task6 = new TaskItem("Task 1", Type.KNOWLEDGE_ACQUISITION, "cjwiley2", "Initial note.");
			task6.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task6.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
			task6.update(new Command(CommandValue.COMPLETE, "cjwiley2", "Fourth note."));
			assertEquals("Done", task6.getStateName());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		// processing to complete (other type)
		TaskItem task7 = null;
		try {
			task7 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task7.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task7.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
			task7.update(new Command(CommandValue.COMPLETE, "cjwiley2", "Third note."));
			fail();
		} catch (UnsupportedOperationException e) {
			assertNotNull(task7);
			assertEquals("Processing", task7.getStateName());
		}
		// processing to verify (KA)
		TaskItem task8 = null;
		try {
			task8 = new TaskItem("Task 1", Type.KNOWLEDGE_ACQUISITION, "cjwiley2", "Initial note.");
			task8.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task8.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
			task8.update(new Command(CommandValue.VERIFY, "cjwiley2", "Fourth note."));
			fail();
		} catch (UnsupportedOperationException e) {
			assertNotNull(task8);
			assertEquals("Processing", task8.getStateName());
		}
		// processing to backlog
		TaskItem task9 = null;
		try {
			task9 = new TaskItem("Task 1", Type.KNOWLEDGE_ACQUISITION, "cjwiley2", "Initial note.");
			task9.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task9.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
			task9.update(new Command(CommandValue.BACKLOG, "cjwiley2", "Fourth note."));
			assertEquals("Backlog", task9.getStateName());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		// processing to rejected
		TaskItem task10 = null;
		try {
			task10 = new TaskItem("Task 1", Type.KNOWLEDGE_ACQUISITION, "cjwiley2", "Initial note.");
			task10.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task10.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
			task10.update(new Command(CommandValue.REJECT, "cjwiley2", "Fourth note."));
			fail();
		} catch (UnsupportedOperationException e) {
			assertNotNull(task10);
			assertEquals("Processing", task10.getStateName());
		}
		// verify to processing
		TaskItem task11 = null;
		try {
			task11 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task11.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task11.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
			task11.update(new Command(CommandValue.VERIFY, "cjwiley2", "Fourth note."));
			task11.update(new Command(CommandValue.PROCESS, "cjwiley2", "Fifth note."));
			assertEquals("Fifth note.", task11.getNotes().get(4).getNoteText());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		// verify to backlog
		TaskItem task12 = null;
		try {
			task12 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task12.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task12.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
			task12.update(new Command(CommandValue.VERIFY, "cjwiley2", "Fourth note."));
			task12.update(new Command(CommandValue.BACKLOG, "cjwiley2", "Fifth note."));
			fail();
		} catch (UnsupportedOperationException e) {
			assertNotNull(task12);
			assertEquals("Verifying", task12.getStateName());
		}
		// done to process
		TaskItem task13 = null;
		try {
			task13 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task13.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task13.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
			task13.update(new Command(CommandValue.VERIFY, "cjwiley2", "Fourth note."));
			task13.update(new Command(CommandValue.COMPLETE, "cjwiley2", "Fifth note."));
			task13.update(new Command(CommandValue.PROCESS, "cjwiley2", "Sixth note."));
			assertEquals("Processing", task13.getStateName());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		// done to backlog
		TaskItem task14 = null;
		try {
			task14 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task14.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task14.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
			task14.update(new Command(CommandValue.VERIFY, "cjwiley2", "Fourth note."));
			task14.update(new Command(CommandValue.COMPLETE, "cjwiley2", "Fifth note."));
			task14.update(new Command(CommandValue.BACKLOG, "cjwiley2", "Fifth note."));
			assertEquals("Backlog", task14.getStateName());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		// done to reject
		TaskItem task15 = null;
		try {
			task15 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task15.update(new Command(CommandValue.CLAIM, "cjwiley2", "Second note."));
			task15.update(new Command(CommandValue.PROCESS, "cjwiley2", "Third note."));
			task15.update(new Command(CommandValue.VERIFY, "cjwiley2", "Fourth note."));
			task15.update(new Command(CommandValue.COMPLETE, "cjwiley2", "Fifth note."));
			task15.update(new Command(CommandValue.REJECT, "cjwiley2", "Fifth note."));
			fail();
		} catch (UnsupportedOperationException e) {
			assertNotNull(task15);
			assertEquals("Done", task15.getStateName());
		}
		// reject to backlog
		TaskItem task16 = null;
		try {
			task16 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task16.update(new Command(CommandValue.REJECT, "cjwiley2", "Second note."));
			task16.update(new Command(CommandValue.BACKLOG, "cjwiley2", "Third note."));
			assertEquals("Backlog", task16.getStateName());
		} catch (UnsupportedOperationException e) {
			fail();
		}
		// reject to verify
		TaskItem task17 = null;
		try {
			task17 = new TaskItem("Task 1", Type.BUG, "cjwiley2", "Initial note.");
			task17.update(new Command(CommandValue.REJECT, "cjwiley2", "Second note."));
			task17.update(new Command(CommandValue.VERIFY, "cjwiley2", "Third note."));
			fail();
		} catch (UnsupportedOperationException e) {
			assertNotNull(task17);
			assertEquals("Rejected", task17.getStateName());
		}
	}
}
