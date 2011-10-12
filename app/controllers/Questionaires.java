package controllers;

import java.util.List;

import models.Question;
import models.QuestionAnswer;
import models.QuestionaireAssignment;
import play.db.jpa.JPABase;
import play.mvc.Controller;

public class Questionaires extends Controller {

	public static void save(String[] answers) {
		
		
		System.out.println("saving");
	}
	public static void form(Long questionaireAssignmentId) {
		
		QuestionaireAssignment questionaireAssignment = QuestionaireAssignment.findById(questionaireAssignmentId);
		
		if( questionaireAssignment.questionAnswers.isEmpty() ) {
			List<Question> questions = questionaireAssignment.questionaire.questions;
			for (Question question : questions) {
				QuestionAnswer answer = new QuestionAnswer(question);
				answer.hasAnswered = false;
				answer.save();
				questionaireAssignment.questionAnswers.add(answer);
			}
			
			questionaireAssignment.save();
			List<QuestionAnswer> questionAnswers = questionaireAssignment.questionAnswers;
			
			render(questionAnswers);
		} else {
			List<QuestionAnswer> questionAnswers = questionaireAssignment.questionAnswers;
			
			render(questionAnswers);
		}
		
		
		
//		QuestionaireAnswer questionaireAnswer = QuestionaireAnswer.findById(questionAnswerId);
//		
//		Questionaire questionaire = questionaireAnswer.questionaire;
//		//Map<Question, String> questionAnswers = questionaireAnswer.questionAnswers;
//		
//		render(questionaire, questionAnswers);
	}
	public static void form(QuestionaireAssignment questionaireAssignment) {
		
		if( questionaireAssignment.questionAnswers.isEmpty() ) {
			List<Question> questions = questionaireAssignment.questionaire.questions;
			for (Question question : questions) {
				QuestionAnswer answer = new QuestionAnswer(question);
				answer.hasAnswered = false;
				answer.save();
				questionaireAssignment.questionAnswers.add(answer);
			}
			
			questionaireAssignment.save();
			List<QuestionAnswer> questionAnswers = questionaireAssignment.questionAnswers;
			
			render(questionAnswers);
		}
		
		// TODO Auto-generated method stub
		
	}
	
}
