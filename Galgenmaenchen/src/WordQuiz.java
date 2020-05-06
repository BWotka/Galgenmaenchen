
import java.util.ArrayList;
import java.util.List;

public class WordQuiz {

  protected ConsoleReader myCReader;
  protected List<Writer> myWriters;
  protected List<WordList> wordLists;

  /*
   * Verarbeitung der Informationen fuer das Galgenmaenchen
   */
  public WordQuiz() {
    wordLists = new ArrayList<>();
    myWriters = new ArrayList<>();

  }

  /*
   * Adds WordList to WordLists to choose from
   * 
   * @return returns boolean weither method was successful
   */
  public boolean addWordList(WordList pwordList) {
    boolean listexist = false;
    for (WordList exlist : wordLists) {
      if (exlist.getSubject() == pwordList.getSubject()) {
        listexist = true;
      }
    }

    if (!listexist) {
      wordLists.add(pwordList);
      return true;
    } else {
      return false;
    }


  }

  public boolean deleteWordList(WordList pwordList) {
    return wordLists.remove(pwordList);

  }

  public void setConsoleReader(ConsoleReader creader) {
    myCReader = creader;
  }

  public void addWriter(Writer pwriter) {
    myWriters.add(pwriter);
  }

  public void playGame() {
    playGame(8, Subject.Animals, Difficulty.Normal);
  }

  public void playGame(int wordLength) {
    playGame(wordLength, Subject.Animals, Difficulty.Normal);
  }

  public void playGame(Subject wordSubject) {
    playGame(8, wordSubject, Difficulty.Normal);
  }

  public void playGame(Difficulty gameDifficulty) {
    playGame(8, Subject.Animals, gameDifficulty);
  }

  public void playGame(int wordLength, Difficulty gameDifficulty) {
    playGame(wordLength, Subject.Animals, gameDifficulty);
  }

  public void playGame(Subject wordSubject, Difficulty gameDifficulty) {
    playGame(8, wordSubject, gameDifficulty);
  }

  public void playGame(int wordLength, Subject wordSubject) {
    playGame(wordLength, wordSubject, Difficulty.Normal);
  }

  /*
   * @method playGame and variants start the game
   * 
   * @param wordLength Length of word default:8
   * 
   * @param wordSubject Subject/Topic of Word
   * 
   * @param gameDifficulty Amount of errors before gameend
   * 
   */
  public void playGame(int wordLength, Subject wordSubject, Difficulty gameDifficulty) {
    String gameWord =
        wordLists.get(wordLists.indexOf(new WordList(wordSubject)) + 1).getWordOfLength(wordLength);

    System.out.println(gameWord);
    System.out.println("" + wordLength);
    System.out.println("" + gameDifficulty.getDifficulty());

    // both writers are set up
    for (Writer aktWriter : myWriters) {
      aktWriter.setSubject(wordSubject);
      aktWriter.setWord(gameWord);
      aktWriter.setDifficulty(gameDifficulty);
    }

    char[] correctLetters = new char[wordLength];
    int errorsLeft = gameDifficulty.getDifficulty();
    char letter;

    // the game runs in this loop
    while (errorsLeft > 0) {

      letter = Character.toUpperCase(myCReader.readNextChar());


      // letter is wrong
      if (gameWord.indexOf(letter) == -1) {
        errorsLeft += -1;
      }

      // letter is right
      else {
        // the letter gets put into the list of correct chars
        for (int c = 0; c < correctLetters.length; c++) {
          if (correctLetters[c] == '\u0000') {
            correctLetters[c] = letter;
            c = correctLetters.length;
          }
        }

        for (Writer aktWriter : myWriters) {
          aktWriter.write(correctLetters, letter, errorsLeft);

        }

      }


    }

  }

}


