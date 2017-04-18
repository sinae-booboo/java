package ioopm.inl4.tests;

import ioopm.inl4.Question;
import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuestionTest {

    Question question = new Question("Question text", new String[]{"Answer #1", "Answer #2", "Answer #3"}, 2);

    @Test
    public void testGetCorrectAnswerIdx() throws Exception {
        assertEquals(question.getCorrectAnswerIdx(), 2);
    }

    @Test
    public void testAsk() throws Exception {
        String text = question.ask(false);

        assert(text.equals("Question text" + " (1-3)\n#1 " + "Answer #1" + "\n#2 " + "Answer #2" + "\n#3 " + "Answer #3" + "\n"));
    }
}
