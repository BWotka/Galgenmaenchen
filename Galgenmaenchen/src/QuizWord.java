/**
 * 
 * @author Benedikt Wotka, David Nickel
 * @version 0.1
 * 
 *          Class that holds a quizword and its length
 *
 */
public class QuizWord {
  private String word;
  private int length;

  public QuizWord(String pword) {
    word = pword;
    length = pword.length();
  }

  public int getlength() {
    return length;
  }

  public String getword() {
    return word;
  }

}
