package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class User extends Model {
    
	  	public String username;
	    public String password;
	    public String fullname;
	    public boolean isAdmin;
	    
	    public User(String username, String password, String fullname) {
	        this.username = username;
	        this.password = password;
	        this.fullname = fullname;
	    }
	    
	    public static User connect(String username, String password) {
		    return find("byUsernameAndPassword", username, password).first();
		}

}
