package user;
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class TaskTest extends UnitTest {

	String username = "telsbot";
	String password = "secret";
	String fullname = "Mr. Tels J. Bot";
	String email = "telsbot@robot.com";
	
	
	
	@Before
	public void setup() {
		 Fixtures.deleteAll();
	}
	 
	@Test
	public void createTask() {
	    // Create a new user and save it
		
		User user = new User(email, password, fullname).save();
		
	    Task t = new Task(user, "first task","do something 1","stuff");
	    t.done = true;
	    t.save();
	    
	    // Retrieve the user with e-mail address bob@gmail.com
	    Task aTask = Task.find("byTitle","first task").first();
	    
	    
	    // Test 
	    assertNotNull(aTask);
	    assertEquals("first task", aTask.title);
	    assertEquals("do something 1", aTask.description);
	    assertTrue(aTask.done);
        
        new Task(user, "second task","do something 2","stuff").save();
        
	    // Retrieve all posts created by Bob
        List<Task> tasks = Task.allTasksByUser(user);
        
        // Tests
        assertEquals(2, tasks.size());
        Task task = tasks.get(1);
        assertNotNull(task);
        assertEquals(user, task.user);
        assertEquals("second task", task.title);
        assertEquals("do something 2", task.description);
        assertEquals("stuff", task.list);
        assertNotNull(task.timestamp);
        
        //get tasks by list
        List<Task> taskList = Task.allTasksByList("stuff");
       
        assertEquals(2, taskList.size());
        
	}

}
