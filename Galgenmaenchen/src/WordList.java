import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class saves a list of words from a specific subject.
 * 
 * @author Benedikt Wotka, David Nickel
 * @version 0.1
 * 
 *
 */
public class WordList {
  private Subject subject;
  protected List<QuizWord> words;
  Scanner sc;

  /**
   * constructor of WordList.
   * 
   * @param psubject Topic of the Word List that gets created
   */
  public WordList(Subject psubject) {
    subject = psubject;
    words = new ArrayList<>();
    if (!readListFromFile(psubject.toString() + ".txt")) {
      System.out.println("Error with reading in" + psubject.toString() + ".txt");
    }

  }

  /**
   * Method to read Words of a topic from a text file.
   * 
   * @param fileName Name of the file, has to be topic of the list e.g. : "Animals.txt"
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
   * Gives random word from the list.
   * 
   * @return returns a random String
   */
  public String getRandomWord() {

    return words.get(ThreadLocalRandom.current().nextInt(0, words.size())).getword();
  }

  /**
   * Gets a word with an specific length.
   * 
   * @param plength length of the word that is wanted
   * @return returns a word with the specific length
   */
  public String getWordOfLength(int plength) {
    List<QuizWord> wordsWithLength = new ArrayList<>();

    // all words with the correct length are placed in a separate list
    for (QuizWord word : words) {
      if (word.getlength() == plength) {
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
