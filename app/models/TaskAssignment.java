package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class TaskAssignment extends Model {

	@ManyToMany
	public List<Task> tasks;
	
	public String title;
	
	public TaskAssignment(String title) {
		this.title = title;
	}
		
}
