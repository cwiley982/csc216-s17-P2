package edu.ncsu.csc216.backlog.scrum_backlog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

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

	@Test
	public void testSaveTasksToFile() {
		ScrumBacklogModel.getInstance().addTaskItemToList("Express Carts", Type.FEATURE, "jep",
				"Express carts always choose the shortest line. If there are multiple shortest lines, an express cart chooses the one with the smallest index.");
		ScrumBacklogModel.getInstance().saveTasksToFile("/test-files/act_task_baclog");
		assertTrue(checkFiles("/test-files/exp_task_backlog", "/test-files/act_task_backlog"));
	}

	private boolean checkFiles(String expFile, String actFile) {
		try {
			Scanner exp = new Scanner(new File(expFile));
			Scanner act = new Scanner(new File(actFile));
			while (exp.hasNextLine()) {
				if (!exp.nextLine().equals(act.nextLine())) {
					exp.close();
					act.close();
					return false;
				}
			}
			exp.close();
			act.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

}
