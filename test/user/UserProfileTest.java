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
	    new User(username,password, fullname).save();
	    
	    // Retrieve the user with e-mail address bob@gmail.com
	    User telsbot = User.find("byUsername",username).first();
	    
	    // Test 
	    assertNotNull(telsbot);
	    assertEquals(fullname, telsbot.fullname);
	}

    @Test
    public void tryConnectAsUser() {
        // Create a new user and save it
        new User(email, password, fullname).save();
        
        // Test 
        assertNotNull(User.connect(email, password));
        assertNull(User.connect(email, "badpassword"));
        assertNull(User.connect("tom@gmail.com", "secret"));
    }
    
    @Test
    public void createProfile() {
        // Create a new user and save it
    	String firstProfile = "My first profile";
    	String firstStatus = "chilling";
        User bob = new User(username, password, fullname).save();
        
        // Create a new post
        new Profile(bob, firstProfile,firstStatus).save();
        
        // Test that the post has been created
        assertEquals(1, Profile.count());
        
        // Retrieve all posts created by Bob
        List<Profile> bobPosts = Profile.find("byUser", bob).fetch();
        
        // Tests
        assertEquals(1, bobPosts.size());
        Profile profile = bobPosts.get(0);
        assertNotNull(profile);
        assertEquals(bob, profile.user);
        assertEquals(firstProfile, profile.description);
        assertEquals(firstStatus, profile.status);
        assertNotNull(profile.timestamp);
    }
}
