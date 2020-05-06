/**
 * Abstract class used to give information to the player.
 * 
 * @author Benedikt Wotka, David Nickel
 * @version 0.1
 *
 */
public abstract class Writer {
  protected Subject subject;
  protected Difficulty difficulty;
  protected String word;

  public void setSubject(Subject newSubject) {
    subject = newSubject;
  }

  public void setDifficulty(Difficulty newDifficulty) {
    difficulty = newDifficulty;
  }

  public void setWord(String newWord) {
    word = newWord;
  }

  public abstract boolean write(char[] guessedLetters, char newLetter, int leftFails);

}
