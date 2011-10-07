package controllers;

import models.Profile;
import models.User;

/**
 * Checks user login pass
 * 
 * @author anthonjp
 *
 */
public class Security extends controllers.Secure.Security {

	static boolean authenticate(String username, String password) {
		return User.connect(username, password) != null;
	}
	
	static void onAuthenticated() {
	    String username = Security.connected();	    
	    User user = User.findUserByUsername(username);
	    Profile profile = Profile.findProfileByUser(user);
	    
	    if( profile == null ) {
	    	profile = new Profile(user, null, null).save();
	    }
	    
	    profile.isComplete = false;
	    
	    session.put("profile.id", profile.id);
	    
	    if( profile.isComplete ) {
	    	Application.index();
	    } else {
	    	Profiles.form();
	    }
	   
	}
}
