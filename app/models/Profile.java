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
	public String description;
	public Date timestamp;
	public String status;
	public boolean isComplete;

	public Profile(User user, String description, String status) {
		this.user = user;
		this.description = description;
		this.status = status;
		timestamp = new Date();
	}
	
	@Override
	public String toString() {
		return "User:" + user + " Description:" + description + " Status: " + status;
	}
}
