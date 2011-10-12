package models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class QuestionAnswer extends Model {

	@ManyToOne
	public Question question;
	
	public String answer = null;
	
	public boolean hasAnswered = false;

	public QuestionAnswer(Question question) {
		this.question = question;
	}
	
	public static QuestionAnswer findByQuestion(Question question) {
		return find("byQuestion", question).first();
	}
	
}
