package controllers;

import models.Profile;
import play.mvc.Controller;
import play.mvc.With;
import play.data.validation.*;
import play.data.validation.Error;

@With(Secure.class)
public class Profiles extends Controller {
	
	public static void form() {
		Profile profile = getCurrentProfile();
	    render(profile);
	}
	 
	public static void save(String firstname, String lastname, String email) {
		 Profile profile = getCurrentProfile();
		 profile.firstname = firstname;
		 profile.lastname = lastname;
		 profile.email = email;
		
		validation.required("firstname",firstname);
	    validation.required("lastname", lastname);
	    validation.required("email", email);
	     
		if (validation.hasErrors()) {
			for (Error error : validation.errors()) {
		        	render("@form", profile);

			}
	     } else {
	    	 profile.save();
	    	 Application.index();
		 }
	     
	}

	public static Profile getCurrentProfile() {
		String profileId = session.get("profile.id");
		Profile profile = Profile.findById(new Long(profileId));
		return profile;
	}

}
