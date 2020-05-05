import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleReader {
  private BufferedReader reader;

  public ConsoleReader(BufferedReader breader) {
    reader = breader;
  }

  public char readNextChar() {
    try {
      return reader.readLine().charAt(0);
    } catch (IOException e) {
      System.out.println("Error when reading the letter.");
      return 'a';
    }
  }
}
