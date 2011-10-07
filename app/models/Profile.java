package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Profile extends Model {

	@Required
	@OneToOne
	public User user;
	
	public String firstname;
	public String lastname;
	public String email;
	public String description;
	public String status;
	public String gender;
	public String phonenumber;
	
	public Date timestamp;
	
	public boolean isComplete;

	public Profile(User user, String firstname, String lastname) {
		this.user = user;
		this.firstname = firstname;
		this.lastname = lastname;
		timestamp = new Date();
	}
	
	public static Profile findProfileByUser(User user) {
		return find("byUser", user).first();
	}
	
	@Override
	public String toString() {
		return "User id:" + user.id + " firstname: " + firstname + " lastname: " + lastname;
	}
}
