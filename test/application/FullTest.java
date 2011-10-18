package application;

import models.TaskAssignment;
import models.User;

import org.junit.Test;

import play.test.Fixtures;
import play.test.FunctionalTest;

public class FullTest extends FunctionalTest {
	
	private User user;

	@Test
	public void fullTest() {
	    Fixtures.loadModels("data.yml");
	 
	    user = User.findUserByUsername("telsbot");
	    
	    assertEquals(false, user.taskAssignments.isEmpty());
	    
	    TaskAssignment taskAssignment = user.taskAssignments.get(0);
	    
	    assertNotNull(taskAssignment);
	    
	    assertEquals(false, taskAssignment.tasks.isEmpty());
	}

}
