package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Question extends Model {
	public String title;
	public String question;
	public String answer;
	
	public Question(String title, String question, String answer) {
		this.title = title;
		this.question = question;
		this.answer = answer;
	}
	
}
