package controllers;

import java.util.List;

import models.Profile;
import models.Questionaire;
import models.User;
import play.data.validation.Error;
import play.db.jpa.Blob;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Profiles extends Controller {

	public static void form() {
		Profile profile = getCurrentProfile();
		
		render(profile);
	}

	public static void save(String firstname, String lastname, String email,
			String gender, String phonenumber) {
		Profile profile = getCurrentProfile();
		profile.firstname = firstname;
		profile.lastname = lastname;
		profile.email = email;
		profile.gender = gender;
		profile.phonenumber = phonenumber;

		validation.required("firstname", firstname);
		validation.required("lastname", lastname);
		validation.required("email", email);
		validation.required("gender", gender);
		validation.required("phonenumber", phonenumber);

		if (validation.hasErrors()) {
			for (Error error : validation.errors()) {
				render("@form", profile);

			}
		} else {
			profile.save();
			
			//if they have filled the questionaire
			String username = Security.connected();	    
			User user = User.findUserByUsername(username);
			    
			Application.index();
		}

	}

	public static void uploadPicture(Blob picture) {
		Profile profile = getCurrentProfile();
		profile.picture = picture;
		profile.save();
		//form();
	}

	public static void getPicture(long id) {
		Profile profile = Profile.findById(id);
		response.setContentTypeIfNotSet(profile.picture.type());
		renderBinary(profile.picture.get());
	}

	public static Profile getCurrentProfile() {
		String profileId = session.get("profile.id");
		Profile profile = Profile.findById(new Long(profileId));
		return profile;
	}

}
