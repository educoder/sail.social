package controllers;

import java.util.List;

import models.Profile;
import models.Task;
import models.User;
import play.db.jpa.JPABase;
import play.i18n.Lang;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Application extends Controller {

	/**
	 * Index
	 */
	public static void index() {
		
		Profile profile = Profiles.getCurrentProfile();
		String username = Security.connected();	    
	    User user = User.findUserByUsername(username);
		
	    render(profile, user);
	}
	
	public static void changeLanguage(String lang) {
		Lang.change(lang);
		System.out.println("the language is now " + lang);
		try {
			Secure.login();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}