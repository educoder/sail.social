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
		
	    Task t = new Task("first task","do something 1","stuff").save();
	    
	    Task aTask = Task.find("byTitle",t.title).first();
	    
	    
	    // Test 
	    assertNotNull(aTask);
	    assertEquals("first task", aTask.title);
	    assertEquals("do something 1", aTask.description);
	}

}
