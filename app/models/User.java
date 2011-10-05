package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class User extends Model {

	@Required
	public String username;
	@Required
	public String password;
	public String fullname;
	public boolean isAdmin;
	public Date timestamp;

	public User(String username, String password, String fullname) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		timestamp = new Date();
	}

	public static User connect(String username, String password) {
		return find("byUsernameAndPassword", username, password).first();
	}
	
	@Override
	public String toString() {
		return "Username: " + username + " Name: " + fullname + "isAdmin: " + isAdmin;
	}

}
