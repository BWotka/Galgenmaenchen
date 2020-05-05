import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordQuiz {

  protected ConsoleReader myCReader;
  protected Writer myExWriter;
  protected Writer myWriter;
  protected List<WordList> wordLists;

  public WordQuiz() {
    wordLists = new ArrayList<>();

  }

  public boolean addWordList(WordList pWordList) {
    wordLists.add(pWordList);
    return true;
  }

  public boolean deleteWordList(WordList pWordList) {
    wordLists.remove(pWordList);
    return true;
  }

  public void setConsoleReader(ConsoleReader pCReader) {
    myCReader = pCReader;
  }

  public void addWriter(Writer pWriter) {
    myWriter = pWriter;
  }

  public void playGame() {

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

  public void playGame(int wordLength, Subject wordSubject, Difficulty gameDifficulty) {
    String gameWord = new WordList(wordSubject).getWordOfLength(wordLength);
    myWriter.setSubject(wordSubject);
    myWriter.setWord(gameWord);
    myWriter.setDifficulty(gameDifficulty);

    // the word presented in the console, will be * until the correct letter is found (A-Z, '-')
    char[] knownWord = new char[wordLength];
    for (char b : knownWord) {
      b = '*';
    }

    // all letters that were tried go in here
    char[] triedChars = new char[27];

    // current letter
    char letter;

    int errorsLeft = gameDifficulty.getDifficulty();



    // loop in which the turns are made
    while (errorsLeft > 0) {
      letter = myCReader.readNextChar();

      // letter is wrong
      if (gameWord.indexOf(letter) == -1) {

        // was this letter entered before?
        boolean letterTriedBefore = false;
        for (char c : triedChars) {
          if (c == letter) {
            letterTriedBefore = true;
          }
        }
        // yes, it was entered before
        if (letterTriedBefore) {
          System.out.println("You have entered this letter before");
        }
        // no, its a new letter
        else {
          System.out.println("This letter is not part of the solution.");
          errorsLeft += -1;
          // the letter is added to the list of tried letters
          for (int c = 0; c < triedChars.length; c++) {
            if (triedChars[c] == null) {
              triedChars[c] = letter;
              c = triedChars.length;
            }
          }

        }

      }
      // letter is right
      else {
        System.out.println("This letter is part of the solution.");



        // the letter is added to the list of tried letters
        for (int c = 0; c < triedChars.length; c++) {
          if (triedChars[c] == null) {
            triedChars[c] = letter;
            c = triedChars.length;
          }
        }
      }
      myWriter.write(triedChars, letter, errorsLeft);

      // if the solution is found out:
      if (Arrays.equals(knownWord, gameWord.toCharArray())) {
        // The player correctly guessed the word
        System.out.println("You won!");
        System.out.println("You correctly guessed the word: '" + gameWord + "' !");
        System.out
            .println("You had " + errorsLeft + " errors left before you would have lost the game!");
        return;
      }
    }
    // The player has no tries left, he loses the game
    System.out.println("Game Over!");
    System.out.println("The Solution was: " + gameWord);
    System.out
        .println("You made " + gameDifficulty.getDifficulty() + "mistakes and lost the game!");


  }

}


