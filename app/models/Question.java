package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Question extends Model {
	
	public String title;
	public String question;
	public String answer;
	public boolean isBinary;
	
	public Question(String title, String question, String answer, boolean isBinary) {
		this.title = title;
		this.question = question;
		this.answer = answer;
	}
	
}
