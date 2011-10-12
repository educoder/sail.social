package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class QuestionaireAssignment extends Model {

	@OneToOne
	public Questionaire questionaire;
	
	@ManyToMany
	public List<QuestionAnswer> questionAnswers = new ArrayList<QuestionAnswer>();
	
	public boolean hasSubmitted = false;
	
	public QuestionaireAssignment(Questionaire questionaire) {
		this.questionaire = questionaire;
	}
}
