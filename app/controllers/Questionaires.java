package controllers;

import java.util.Iterator;
import java.util.List;

import models.Question;
import models.QuestionAnswer;
import models.QuestionaireAssignment;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

import play.data.validation.Validation;
import play.mvc.Controller;

public class Questionaires extends Controller {

	public static void save(Long questionaireAssigmentId, List<QuestionAnswer> answers) {

		QuestionaireAssignment questionaireAssignment = QuestionaireAssignment.findById(questionaireAssigmentId);
		List<QuestionAnswer> answersFromDB = questionaireAssignment.questionAnswers;
		boolean allFilledIn = true;
		for (final QuestionAnswer answer : answers) {
			QuestionAnswer fromDB = (QuestionAnswer) CollectionUtils.find(answersFromDB, new Predicate() {
				@Override
				public boolean evaluate(Object questionAnswer) {
					return ((QuestionAnswer)questionAnswer).id.equals(answer.id);
				}
			});
			fromDB.answer = answer.answer;
			fromDB.hasAnswered = true;
			
			if (StringUtils.isEmpty(fromDB.answer)) allFilledIn = false;
			fromDB.save();
		}
		if (allFilledIn) Application.index();
		else validate(questionaireAssigmentId);
	}
	public static void validate(Long questionaireAssigmentId) {
		QuestionaireAssignment questionaireAssignment = QuestionaireAssignment.findById(questionaireAssigmentId);
		List<QuestionAnswer> questionAnswers = questionaireAssignment.questionAnswers;
		for (int j = 0; j < questionAnswers.size(); j++) {
			QuestionAnswer  questionAnswer = questionAnswers.get(j);
			if (StringUtils.isEmpty(questionAnswer.answer))				
				validation.addError(String.format("answers[%d].answer",j), "required");

		}
		validation.keep();
		form(questionaireAssigmentId);
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
			
			render(questionaireAssignment, questionAnswers);
		} else {
			List<QuestionAnswer> questionAnswers = questionaireAssignment.questionAnswers;
			
			render(questionaireAssignment, questionAnswers);
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
