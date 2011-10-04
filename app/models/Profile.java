package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Profile extends Model {
    	    
	    @OneToOne
	    public User user;
	    public String description;
	    public Date timestamp;
		public String status;
	    
	    public Profile(User user, String description, String status) {
	        this.user = user;
	        this.description = description;
	        this.status = status;
	        timestamp = new Date();
	    }
}
