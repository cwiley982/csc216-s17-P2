package edu.ncsu.csc216.backlog.scrum_backlog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;

/**
 * Tests the functionality of ScrumBacklogModel class
 * 
 * @author Caitlyn
 *
 */
public class ScrumBacklogModelTest {

	/**
	 * Empties the task item list so I can work with a new list for each test
	 * since ScrumBacklogModel uses the singleton method and I would end up
	 * being able to see tasks from previous tests
	 */
	@Before
	public void setUp() {
		ScrumBacklogModel.getInstance().createNewTaskItemList();
	}

	/**
	 * Tests getting the single instance of ScrumBacklogModel
	 */
	@Test
	public void testGetInstance() {
		ScrumBacklogModel.getInstance().createNewTaskItemList();
		assertEquals(0, ScrumBacklogModel.getInstance().getTaskItemListAsArray().length);
	}

	/**
	 * Tests adding TaskItems to the list
	 */
	@Test
	public void testAddTaskItemToList() {
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 1", Type.BUG, "cjwiley2", "note");
		assertEquals(1, ScrumBacklogModel.getInstance().getTaskItemListAsArray().length);
	}

	/**
	 * Tests saving a task list to an xml file
	 */
	@Test
	public void testSaveTasksToFile() {
		ScrumBacklogModel.getInstance().addTaskItemToList("Express Carts", Type.FEATURE, "jep",
				"Express carts always choose the shortest line. If there are multiple shortest lines, an express cart chooses the one with the smallest index.");
		ScrumBacklogModel.getInstance().saveTasksToFile("test-files/act_task_backlog.xml");
		assertTrue(checkFiles("test-files/exp_task_backlog.xml", "test-files/act_task_backlog.xml"));

	}

	/**
	 * Tests loading valid and invalid files
	 */
	@Test
	public void testLoadTasksFromFile() {
		// invalid file
		try {
			ScrumBacklogModel.getInstance().loadTasksFromFile("test-files/tasks19.xml");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(ScrumBacklogModel.getInstance().getTaskItemById(1));
		}
		// valid file
		try {
			ScrumBacklogModel.getInstance().loadTasksFromFile("test-files/tasks_valid.xml");
			assertEquals("Regular Carts", ScrumBacklogModel.getInstance().getTaskItemById(2).getTitle());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Tests getting the list of TaskItems as an array
	 */
	@Test
	public void testGetTaskItemListAsArray() {
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 1", Type.KNOWLEDGE_ACQUISITION, "me", "note");
		assertEquals("Task 1", ScrumBacklogModel.getInstance().getTaskItemListAsArray()[0][2]);
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 2", Type.TECHNICAL_WORK, "me", "note");
		assertEquals(2, ScrumBacklogModel.getInstance().getTaskItemListAsArray()[1][0]);
	}

	/**
	 * Tests getting the array of tasks owned by a certain person
	 */
	@Test
	public void testGetTaskItemListByOwnerAsArray() {
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 1", Type.KNOWLEDGE_ACQUISITION, "me", "note");
		ScrumBacklogModel.getInstance().getTaskItemById(1).update(new Command(CommandValue.CLAIM, "me", "note2"));
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 2", Type.TECHNICAL_WORK, "you", "note");
		ScrumBacklogModel.getInstance().getTaskItemById(2).update(new Command(CommandValue.CLAIM, "me", "note2"));
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 3", Type.BUG, "me", "note");
		ScrumBacklogModel.getInstance().getTaskItemById(3).update(new Command(CommandValue.CLAIM, "you", "note2"));
		assertEquals("Task 2", ScrumBacklogModel.getInstance().getTaskItemListByOwnerAsArray("me")[1][2]);
	}

	/**
	 * Tests getting the array of tasks created by a certain person
	 */
	@Test
	public void testGetTaskItemListByCreatorAsArray() {
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 1", Type.KNOWLEDGE_ACQUISITION, "me", "note");
		ScrumBacklogModel.getInstance().getTaskItemById(1).update(new Command(CommandValue.CLAIM, "me", "note2"));
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 2", Type.TECHNICAL_WORK, "you", "note");
		ScrumBacklogModel.getInstance().getTaskItemById(2).update(new Command(CommandValue.CLAIM, "me", "note2"));
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 3", Type.BUG, "me", "note");
		ScrumBacklogModel.getInstance().getTaskItemById(3).update(new Command(CommandValue.CLAIM, "you", "note2"));
		assertEquals("Task 2", ScrumBacklogModel.getInstance().getTaskItemListByCreatorAsArray("you")[0][2]);
	}

	/**
	 * Tests executing a command on a task through ScrumBacklogModel
	 */
	@Test
	public void testExecuteCommand() {
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 1", Type.KNOWLEDGE_ACQUISITION, "me", "note");
		ScrumBacklogModel.getInstance().executeCommand(1, new Command(CommandValue.CLAIM, "me", "note2"));
		assertEquals("Owned", ScrumBacklogModel.getInstance().getTaskItemListAsArray()[0][1]);

		setUp();
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 1", Type.KNOWLEDGE_ACQUISITION, "me", "note");
		ScrumBacklogModel.getInstance().executeCommand(3, new Command(CommandValue.CLAIM, "me", "note2"));
		assertEquals("Backlog", ScrumBacklogModel.getInstance().getTaskItemListAsArray()[0][1]);
	}

	/**
	 * Tests deleting a task by its id number
	 */
	@Test
	public void testDeleteTaskItemById() {
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 1", Type.KNOWLEDGE_ACQUISITION, "me", "note");
		ScrumBacklogModel.getInstance().deleteTaskItemById(1);
		assertEquals(0, ScrumBacklogModel.getInstance().getTaskItemListAsArray().length);

		setUp();
		ScrumBacklogModel.getInstance().addTaskItemToList("Task 1", Type.KNOWLEDGE_ACQUISITION, "me", "note");
		ScrumBacklogModel.getInstance().deleteTaskItemById(3);
		assertEquals("Task 1", ScrumBacklogModel.getInstance().getTaskItemListAsArray()[0][2]);
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile
	 *            expected output
	 * @param actFile
	 *            actual output
	 */
	private boolean checkFiles(String expectedFile, String actualFile) {
		try {
			Scanner expectedIn = new Scanner(new File(expectedFile));
			Scanner actualIn = new Scanner(new File(actualFile));

			while (expectedIn.hasNextLine()) {
				assertEquals("Expected File: " + expectedFile, expectedIn.nextLine(), actualIn.nextLine());
			}
			if (actualIn.hasNextLine()) {
				expectedIn.close();
				actualIn.close();
				return false;
			}
			expectedIn.close();
			actualIn.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
