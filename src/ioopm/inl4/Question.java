package ioopm.inl4;

import java.util.Random;

public class Question {
    private String[] answers;
    private int correctIdx;
    private String text;

    /**
     * Creates a question with 1 answer
     * @param text The question
     * @param answers Possible answer
     * @param correctIdx The index of the correct answer
     */

    public Question(String text, String[] answers, int correctIdx){
        this.text = text;
        this.answers = answers;
        this.correctIdx = correctIdx;
    }

    /**
     * Gets the question text
     * @return Question text
     */

    public String getText(){
        return text;
    }

    /**
     * Gets the correct answer to the question
     * @return The correct question answer
     */

    public String getCorrectAnswer(){
        return answers[correctIdx];
    }

    /**
     * Gets the correct answer index to the question
     * @return the correct question answer index
     */

    public int getCorrectAnswerIdx(){
        return correctIdx;
    }

    /**
     * Forms the question to the player with 3 possible answers (2 if hasBook is true)
     * @param hasBook whether the player has the course book
     * @return the question as a string
     */

    public String ask(boolean hasBook){
        String[] copy = new String[answers.length];
        System.arraycopy(answers, 0, copy, 0, answers.length);
        if (hasBook) {
            Random rand = new Random();
            int idxToRemove = correctIdx;
            while (idxToRemove == correctIdx) {
                idxToRemove = rand.nextInt(answers.length-1);
            }
            copy[idxToRemove] = copy[idxToRemove] + " - the book says No!";
        }
        return text + " (1-3)\n#1 " + copy[0] + "\n#2 " + copy[1] + "\n#3 " + copy[2] + "\n";
    }
}
