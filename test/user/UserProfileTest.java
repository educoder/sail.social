package user;
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class UserProfileTest extends UnitTest {

	String username = "telsbot";
	String password = "secret";
	String fullname = "Mr. Tels J. Bot";
	String email = "telsbot@robot.com";
	
	@Before
	public void setup() {
		 Fixtures.deleteAll();
	}
	 
	@Test
	public void createAndRetrieveUser() {
	    // Create a new user and save it
	    new User(username,password).save();
	    
	    // Retrieve the user with e-mail address bob@gmail.com
	    User telsbot = User.findUserByUsername(username);
	    
	    // Test 
	    assertNotNull(telsbot);
	}

    @Test
    public void tryConnectAsUser() {
        // Create a new user and save it
        new User(email, password).save();
        
        // Test 
        assertNotNull(User.connect(email, password));
        assertNull(User.connect(email, "badpassword"));
        assertNull(User.connect("tom@gmail.com", "secret"));
    }
    
    @Test
    public void createProfile() {
        // Create a new user and save it
    	String firstProfile = "tels j.";
    	String firstStatus = "bot";
        User user = new User(username, password).save();
        
        // Create a new post
        Profile p = new Profile(user, firstProfile,firstStatus);
        p.isComplete = true;
        p.save();
        
        // Test that the post has been created
        assertEquals(1, Profile.count());
        
        // Retrieve all posts created by Bob
        Profile userProfile = Profile.findProfileByUser(user);
        
        // Tests
        assertNotNull(userProfile);
        assertEquals(user, userProfile.user);
        assertEquals(firstProfile, userProfile.firstname);
        assertEquals(firstStatus, userProfile.lastname);
        assertNotNull(userProfile.timestamp);
        assertTrue(p.isComplete);
    }
}
