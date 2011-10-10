package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import play.db.jpa.Model;

@Entity
public class Questionaire extends Model {
	
	@ManyToMany(cascade = CascadeType.ALL)	
	public List<Question> questions = new ArrayList<Question>();
	
	public String title;
	
	
	public Questionaire(String title) {
		this.title = title;
	}
	
}
