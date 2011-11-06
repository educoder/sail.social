package model;

import java.util.ArrayList;
import java.util.List;

import models.Question;
import models.Questionaire;

import org.junit.Test;

import play.test.UnitTest;

public class QuestionTest extends UnitTest {
	
	static String questionTitle1 = "q1";
	static String questionText1 = "what is your favorite color";
	static String questionAnswer1 = "blue";
	
	static String questionTitle2 = "q2";
	static String questionText2 = "what is your favorite band";
	static String questionAnswer2 = "radiohead";
	
	
	//@Test
	public void createQuestion() {
	    // Create a new user and save it
		
		List<Question> questions = getQuestions();
	    
		Question question = Question.find("byTitle",questions.get(0).title).first();
	    
	    
	    // Test 
	    assertNotNull(question);
	    assertEquals(questionTitle1, question.title);
	    assertEquals(questionText1, question.question);
	    assertEquals(questionAnswer1, question.answer);
	}
	
	
	@Test
	public void createQuestionaire(){
		
		
		Questionaire qa = new Questionaire("user questions");
		List<Question> questions = getQuestions();
		for (Question question : questions) {
			qa.questions.add(question);
		}
		qa.save();
		
		
		Questionaire qb = Questionaire.findById(qa.id);
		
		assertNotNull(qb.questions);
		assertEquals(2, qb.questions.size());
		assertEquals(qa.questions.get(0), qb.questions.get(0));
		assertEquals(qa.questions.get(1), qb.questions.get(1));
		
	}
	
	@Test
	public void createQuestionaireWithAnswers(){
		
		
		Questionaire qa = new Questionaire("user questions");
		List<Question> questions = getQuestions();
		for (Question question : questions) {
			qa.questions.add(question);
		}
		qa.save();
		
		
		Questionaire qb = Questionaire.findById(qa.id);
		
//		QuestionaireAnswer answers = new QuestionaireAnswer(qb);
		
//		int i = 0;
//		for (Question question : qb.questions) {
//			answers.questionAnswers.put(question, "answer " + i++);
//		}
//		
//		answers.save();
//		
//		QuestionaireAnswer qAnswer = QuestionaireAnswer.findById(answers.id);
//		
//		assertTrue(qAnswer.questionAnswers.size() > 0);
//		
//		QuestionaireAnswer findByQuestionaire = QuestionaireAnswer.findByQuestionaire(qb);
//		
//		assertEquals(qAnswer, findByQuestionaire);
		
	}
	
	
	public static List<Question> getQuestions() {
		List<Question> questions = new ArrayList<Question>();
		Question q1 = new Question(questionTitle1,questionText1,questionAnswer1,false).save();
		Question q2 = new Question(questionTitle2,questionText2,questionAnswer2, true).save();
		
		
		questions.add(q1);
		questions.add(q2);
		
		return questions;
	}

}
