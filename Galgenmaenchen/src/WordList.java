import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class WordList {
  private Subject subject;
  protected List<QuizWord> words;
  Scanner sc;

  public WordList(Subject pSubject) {
    subject = pSubject;
    words = new ArrayList<>();
    if (readListFromFile(pSubject.toString() + ".txt")) {
      System.out.println("List with the topic '" + subject.toString() + "' was succesfully read.");
    }

  }

  protected boolean readListFromFile(String fileName) {
    try {
      sc = new Scanner(new File(fileName));
    } catch (Exception e) {
      return false;
    }
    while (sc.hasNextLine()) {
      words.add(new QuizWord(sc.nextLine()));
    }
    return true;
  }

  public String getRandomWord() {
    return words.get(ThreadLocalRandom.current().nextInt(0, words.size() + 1)).getword();
  }

  public String getWordOfLength(int pLength) {
    List<QuizWord> wordsWithLength = new ArrayList<>();

    // all words with the correct length are placed in a separate list
    for (QuizWord word : words) {
      if (word.getlength() == pLength) {
        wordsWithLength.add(word);
      }
    }
    return wordsWithLength.get(ThreadLocalRandom.current().nextInt(0, wordsWithLength.size() + 1))
        .getword();

  }

  public Subject getSubject() {
    return this.subject;
  }
}
