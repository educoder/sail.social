package models;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class QuestionaireAnswer extends Model {

	@OneToOne
	public Questionaire questionaire;
	
	@ElementCollection
	public Map<Question,String> questionAnswers = new HashMap<Question,String>();
	
	public boolean hasAnswered = false;

	public QuestionaireAnswer(Questionaire questionaire) {
		this.questionaire = questionaire;
		for (Question question : questionaire.questions) {
			questionAnswers.put(question, "");
		}
	}
	
	public static QuestionaireAnswer findByQuestionaire(Questionaire questionaire) {
		return find("byQuestionaire", questionaire).first();
	}
}
