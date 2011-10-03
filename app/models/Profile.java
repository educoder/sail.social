package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Profile extends Model {
    	    
	  	public Date postedAt;
	  
	    @OneToOne
	    public User user;
	    
	    public String description;
	    
	    public Profile(User user, String description) {
	        this.user = user;
	        this.description = description;
	        postedAt = new Date();
	    }
}
