/**
 * Enum of all possible difficulties.
 * 
 * @author Benedikt Wotka, David Nickel
 * @version 0.1
 * 
 * 
 *
 */
public enum Difficulty {
  Easy(15), Normal(10), Hard(5), Extrem(1);

  private final int diff;

  private Difficulty(int pdiff) {
    diff = pdiff;
  }

  public int getDifficulty() {
    return diff;
  }
}
