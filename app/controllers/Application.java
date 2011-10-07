package controllers;

import java.util.List;

import models.Profile;
import models.Task;
import models.User;
import play.db.jpa.JPABase;
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
		
		
	    render(profile);
	}
	

}