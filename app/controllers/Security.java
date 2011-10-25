package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.Profile;
import models.User;
import play.Logger;
import play.Play;
import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.libs.WS.WSRequest;

/**
 * Checks user login pass
 * 
 * @author anthonjp
 *
 */
public class Security extends controllers.Secure.Security {

	
	static boolean authenticate(String username, String password) {
		// authenticate with rollcall
		String rollcallUrl = Play.configuration.getProperty("sail.rollcall.url");
		
		WSRequest req = WS.url(rollcallUrl+"/login.json");
		req.setParameter("session[login]", username);
		req.setParameter("session[password]", password);
		WS.HttpResponse res = req.post();
		
		JsonElement json = res.getJson();
		
		if (res.getStatus() == 201) { // session created successfully
			JsonObject session = json.getAsJsonObject().getAsJsonObject("session");
			JsonObject account = session.getAsJsonObject("account");
			
			String token = session.get("token").getAsString();
			String encryptedPassword = account.get("encrypted_password").getAsString();
			
			// now check if we already have this user in the local database
			if (User.findUserByUsername(username) == null) { // user doesn't yet exist
				(new User(username, null)).save();
				Logger.info("User '"+username+"' created and authenticated.");
				return true;
			} else { // user exists
				Logger.info("User '"+username+"' successfuly authenticated.");
				return true;
			}
			
		} else { // some error during session creation
			Logger.warn("Authentication for '"+username+"' failed because: "+json.toString());
			flash.put("error", "Rollcall authentication failed: "+json.toString());
			return false;
		}
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
