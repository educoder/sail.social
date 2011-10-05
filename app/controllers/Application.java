package controllers;

import java.util.List;

import models.Profile;
import models.Task;
import models.User;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Application extends Controller {

	
	@Before(only={"login","logout"})
	static void setConnectedUser() {
		if (Security.isConnected()) {
			User user = User.find("byUsername", Security.connected()).first();
			renderArgs.put("user", user);
			show();
		}
	}

	public static void index() {
		
		if (Security.isConnected()) {
			User user = User.find("byUsername", Security.connected()).first();
			renderArgs.put("user", user.fullname);
		
				
		}
		
		List<Profile> firstProfile = Profile.findAll();
		List<Task> allTasks = Task.findAll();
		render(firstProfile, allTasks);
	}
	
	public static void show() {
		System.out.println("hello");
		render("hi");
	}

}