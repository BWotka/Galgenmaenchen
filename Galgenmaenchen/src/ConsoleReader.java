import java.io.BufferedReader;
import java.io.IOException;

/**
 * 
 * @author Benedikt Wotka, David Nickel
 * @version 0.1
 *
 *          This class is used to read the players guessed letters
 */
public class ConsoleReader {
  private BufferedReader reader;

  public ConsoleReader(BufferedReader breader) {
    reader = breader;
  }

  /**
   * @return returns input Char
   */
  public char readNextChar() {
    try {
      return reader.readLine().charAt(0);
    } catch (IOException e) {
      System.out.println("Error when reading the letter.");
      return 'a';
    }
  }
}
