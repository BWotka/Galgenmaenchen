
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
