import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

	@Before
	public void setup() {
		 Fixtures.deleteAll();
	}
	 
	@Test
	public void createAndRetrieveUser() {
	    // Create a new user and save it
	    new User("bob", "secret", "Bob").save();
	    
	    // Retrieve the user with e-mail address bob@gmail.com
	    User bob = User.find("byUsername", "bob").first();
	    
	    // Test 
	    assertNotNull(bob);
	    assertEquals("Bob", bob.fullname);
	}

    @Test
    public void tryConnectAsUser() {
        // Create a new user and save it
        new User("bob@gmail.com", "secret", "Bob").save();
        
        // Test 
        assertNotNull(User.connect("bob@gmail.com", "secret"));
        assertNull(User.connect("bob@gmail.com", "badpassword"));
        assertNull(User.connect("tom@gmail.com", "secret"));
    }
    
    @Test
    public void createPost() {
        // Create a new user and save it
        User bob = new User("bob@gmail.com", "secret", "Bob").save();
        
        // Create a new post
        new Profile(bob, "My first post").save();
        
        // Test that the post has been created
        assertEquals(1, Profile.count());
        
        // Retrieve all posts created by Bob
        List<Profile> bobPosts = Profile.find("byUser", bob).fetch();
        
        // Tests
        assertEquals(1, bobPosts.size());
        Profile firstPost = bobPosts.get(0);
        assertNotNull(firstPost);
        assertEquals(bob, firstPost.user);
        assertEquals("My first post", firstPost.description);
        assertNotNull(firstPost.postedAt);
    }
}
