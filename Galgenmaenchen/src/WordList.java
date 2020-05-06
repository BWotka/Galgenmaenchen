import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author Benedikt Wotka, David Nickel
 * @version 0.1
 * 
 *          This class saves a list of words from a specific subject
 *
 */
public class WordList {
  private Subject subject;
  protected List<QuizWord> words;
  Scanner sc;

  public WordList(Subject pSubject) {
    subject = pSubject;
    words = new ArrayList<>();
    if (!readListFromFile(pSubject.toString() + ".txt")) {
      System.out.println("Error with reading in" + pSubject.toString() + ".txt");
    }

  }

  /**
   * 
   * @param fileName Name of the file e.g. : "Animals.txt"
   * @return weither the file could be read
   */
  protected boolean readListFromFile(String fileName) {

    try {
      sc = new Scanner(new File(fileName));
    } catch (FileNotFoundException e) {

      e.printStackTrace();
      return false;
    }

    while (sc.hasNextLine()) {
      words.add(new QuizWord(sc.nextLine().toUpperCase()));
    }
    return true;
  }

  /**
   * 
   * @return returns a random word from this list
   */
  public String getRandomWord() {

    return words.get(ThreadLocalRandom.current().nextInt(0, words.size())).getword();
  }

  /**
   * 
   * @param pLength length of the word that is wanted
   * @return returns a word with the specific length
   */
  public String getWordOfLength(int pLength) {
    List<QuizWord> wordsWithLength = new ArrayList<>();

    // all words with the correct length are placed in a separate list
    for (QuizWord word : words) {
      if (word.getlength() == pLength) {
        wordsWithLength.add(word);
      }
    }
    return wordsWithLength.get(ThreadLocalRandom.current().nextInt(0, wordsWithLength.size()))
        .getword();

  }

  public Subject getSubject() {
    return this.subject;
  }
}
