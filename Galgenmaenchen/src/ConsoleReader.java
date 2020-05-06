import java.io.BufferedReader;

/**
 * This class is used to read the players guessed letters.
 * 
 * @author Benedikt Wotka, David Nickel
 * @version 0.1
 *
 * 
 */
public class ConsoleReader {
  private BufferedReader reader;

  public ConsoleReader(BufferedReader breader) {
    reader = breader;
  }

  /**
   * Gets a single char from the console.
   * 
   * @return returns input Char
   */
  public char readNextChar() {
    System.out.println("Write a single character that you want to guess:");
    try {
      return reader.readLine().charAt(0);
    } catch (Exception e) {
      System.out.println("Error when reading the letter.");
      System.out.println("Default value A was returned");
      return 'A';
    }
  }
}
