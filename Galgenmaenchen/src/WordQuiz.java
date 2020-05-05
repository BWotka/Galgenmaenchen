import java.io.Writer;
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
   * @return returns boolean weither method was succesful
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
   * @param gameDifficulty Amount of errors before Gameend
   * 
   */
  public void playGame(int wordLength, Subject wordSubject, Difficulty gameDifficulty) {
    String gameWord = new WordList(wordSubject).getWordOfLength(wordLength);

    // both writers are set up
    for (Writer aktWriter : MyWriters) {
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

        for (Writer aktWriter : MyWriters) {
          aktWriter.write(correctLetters, letter, errorsLeft);

        }

      }


    }
    /*
     * // the word presented in the console, will be _ until the correct letter is found (A-Z, '-')
     * char[] knownWord = new char[wordLength]; for (char b : knownWord) { b = '*'; }
     * 
     * // all letters that were tried go in here char[] triedChars = new char[27];
     * 
     * // current letter char letter;
     * 
     * int errorsLeft = gameDifficulty.getDifficulty();
     * 
     * 
     * 
     * // loop in which the turns are made while (errorsLeft > 0) { letter =
     * myCReader.readNextChar();
     * 
     * // letter is wrong if (gameWord.indexOf(letter) == -1) {
     * 
     * // was this letter entered before? boolean letterTriedBefore = false; for (char c :
     * triedChars) { if (c == letter) { letterTriedBefore = true; } } // yes, it was entered before
     * if (letterTriedBefore) { System.out.println("You have entered this letter before"); } // no,
     * its a new letter else { System.out.println("This letter is not part of the solution.");
     * errorsLeft += -1; // the letter is added to the list of tried letters for (int c = 0; c <
     * triedChars.length; c++) { if (triedChars[c] == null) { triedChars[c] = letter; c =
     * triedChars.length; } }
     * 
     * }
     * 
     * } // letter is right else { System.out.println("This letter is part of the solution.");
     * 
     * 
     * 
     * // the letter is added to the list of tried letters for (int c = 0; c < triedChars.length;
     * c++) { if (triedChars[c] == null) { triedChars[c] = letter; c = triedChars.length; } } } for
     * (Writer aktWriter : MyWriters) { aktWriter.write(triedChars, letter, errorsLeft);
     * 
     * }
     * 
     * 
     * // if the solution is found out: if (Arrays.equals(knownWord, gameWord.toCharArray())) { //
     * The player correctly guessed the word System.out.println("You won!");
     * System.out.println("You correctly guessed the word: '" + gameWord + "' !"); System.out
     * .println("You had " + errorsLeft + " errors left before you would have lost the game!");
     * return; } } // The player has no tries left, he loses the game
     * System.out.println("Game Over!"); System.out.println("The Solution was: " + gameWord);
     * System.out .println("You made " + gameDifficulty.getDifficulty() +
     * "mistakes and lost the game!");
     * 
     */
  }

}


