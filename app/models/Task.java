package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Task extends Model {
    
	@ManyToOne
    public User user;
	public String title;
	public String description;
	public String list;
	
	public Date timestamp;
	
	public Task(User user, String title, String description, String list) {
		this.user = user;
		this.title = title;
		this.description = description;
		this.list = list;
		timestamp = new Date();
	}
	
	public static List<Task> allTasksByUser(User user) {
		return find("byUser", user).fetch();
	}
	
	public static List<Task> allTasksByList(String list) {
		return find("byList", list).fetch();
	}
}
