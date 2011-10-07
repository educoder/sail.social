package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Task extends Model {
    
	public String title;
	public String description;
	
	
	public String list;
	public boolean done;
	
	public Date timestamp;
	
	public Task(String title, String description, String list) {
		this.title = title;
		this.description = description;
		this.list = list;
		timestamp = new Date();
	}
	
	public static List<Task> allTasksByList(String list) {
		return find("byList", list).fetch();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Title:" + title + " " + "Description: " + description + " List: " + list;
	}
}
