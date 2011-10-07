package user;

import models.Question;

import org.junit.Test;

import play.test.UnitTest;

public class QuestionTest extends UnitTest {
	
	static String questionTitle = "first task";
	static String questionText = "what is your favorite color";
	static String questionAnswer = "whatever";
	
	@Test
	public void createQuestion() {
	    // Create a new user and save it
		
		Question q = new Question(questionTitle,questionText,questionAnswer).save();
	    
		Question question = Question.find("byTitle",q.title).first();
	    
	    
	    // Test 
	    assertNotNull(question);
	    assertEquals(questionTitle, question.title);
	    assertEquals(questionText, question.question);
	    assertEquals(questionAnswer, question.answer);
	}

}
