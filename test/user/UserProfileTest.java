package user;
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class UserProfileTest extends UnitTest {

	static String username = "telsbot";
	static String password = "secret";
	static String fullname = "Mr. Tels J. Bot";
	static String email = "telsbot@robot.com";
	static String description = "I am me";
	static String status = "Working on the railroad.";
  	static String firstName = "tels j.";
	static String lastName = "bot";
	static String gender = "male";
	static String phonenumber = "98989988";
	
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
    public void createBasicProfileWithUser() {
        // Create a new user and save it
    	
    	Profile p = getProfile();
        // Create a new post
      
        p.save();
        
        // Test that the post has been created
        assertEquals(1, Profile.count());
        
        // Retrieve all posts created by Bob
        Profile userProfile = Profile.findProfileByUser(p.user);
        
        // Tests
        assertNotNull(userProfile);
        assertEquals(p.user, userProfile.user);
    
    }
    
    //@Test
    public void profilePropertiesWithUser() {
		Profile profile = getProfile();
		profile.gender = gender;
		profile.phonenumber = phonenumber;
		profile.description = description;
		profile.status = status;
		profile.isComplete = true;
		profile.save();
		
		Profile found = Profile.findById(profile.id);
		
	    assertEquals(firstName, found.firstname);
        assertEquals(lastName, found.lastname);
        assertEquals(description, found.description);
        assertEquals(status, found.status);
        
        assertEquals(gender, found.gender);
        assertEquals(phonenumber, found.phonenumber);
        
        assertNotNull(found.timestamp);
        assertTrue(found.isComplete);
    }
    
    public static User getUser() {
        User user = new User(username, password).save();
        return user;
    }
    
    public static Profile getProfile() {
        Profile p = new Profile(getUser(), firstName,lastName).save() ;
        return p;
    }
}
