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
	
	public boolean isAdmin;
	public Date timestamp;
	
	@ManyToMany(cascade = CascadeType.ALL)
	public List<QuestionaireAnswer> questionaireAnswer = new ArrayList<QuestionaireAnswer>();

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		timestamp = new Date();
	}

	public static User connect(String username, String password) {
		return find("byUsernameAndPassword", username, password).first();
	}
	
	public static User findUserByUsername(String username) {
		return find("byUsername", username).first();
	}
	
	@Override
	public String toString() {
		return "Username: " + username + " Password: " + password;
	}

}
