package edu.ncsu.csc216.backlog.task;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.backlog.task.TaskItem.Type;

public class TaskItemTest {
	@Before
	public void setUp() {
		TaskItem.setCounter(1);
	}

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

	@Test
	public void testInvalidTaskItemStringTypeStringString() {
		TaskItem task = null;
		try {
			task = new TaskItem("Task 1", null, "cjwiley2", "Initial note");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(task);
		}
	}
}
