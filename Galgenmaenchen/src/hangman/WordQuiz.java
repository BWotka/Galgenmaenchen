package hangman;

import java.util.ArrayList;
import java.util.List;

public class WordQuiz {

  protected ConsoleReader myCReader;
  protected List<Writer> myWriters;
  protected List<WordList> wordLists;

  /**
   * Verarbeitung der Informationen fuer das Galgenmaenchen.
   */
  public WordQuiz() {
    wordLists = new ArrayList<>();
    myWriters = new ArrayList<>();

  }

  /**
   * Adds WordList to WordLists to choose from.
   * 
   * @param pwordList WordList that gets added to List
   * @return returns boolean weither method was successful
   * 
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

  /**
   * Removes one WordList from the wordLists List.
   * 
   * @param pwordList List that should be removed
   * @return weither the removal was succesful
   */
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

  /**
   * playing method.
   * 
   * 
   * 
   * @param wordLength Length of word default: 8
   * 
   * @param wordSubject Subject/Topic of Word default: animal
   * 
   * @param gameDifficulty Amount of errors before gameend default: normal(10)
   * 
   */
  public void playGame(int wordLength, Subject wordSubject, Difficulty gameDifficulty) {
    String gameWord =
        wordLists.get(wordLists.indexOf(new WordList(wordSubject)) + 1).getWordOfLength(wordLength);



    // both writers are set up
    for (Writer aktWriter : myWriters) {
      aktWriter.setSubject(wordSubject);
      aktWriter.setWord(gameWord);
      aktWriter.setDifficulty(gameDifficulty);
    }

    char[] correctLetters = new char[wordLength];
    int errorsLeft = gameDifficulty.getDifficulty();
    char letter;

    if (gameWord.indexOf('-') != -1) {
      correctLetters[0] = '-';
    }
    // the game runs in this loop
    while (errorsLeft > 0) {

      letter = Character.toUpperCase(myCReader.readNextChar());


      // letter is wrong
      if (gameWord.indexOf(letter) == -1) {
        errorsLeft += -1;
        for (Writer aktWriter : myWriters) {
          aktWriter.write(correctLetters, letter, errorsLeft);
        }

        // letter is right
      } else {
        // test if letter was tried before
        boolean triedBefore = false;
        for (char t : correctLetters) {
          if (t == letter) {
            triedBefore = true;
          }
        }



        if (!triedBefore) {
          // the letter gets put into the list of correct chars
          for (int c = 0; c < correctLetters.length; c++) {
            if (correctLetters[c] == '\u0000') {
              correctLetters[c] = letter;
              c = correctLetters.length;
            }
          }

        }

        for (Writer aktWriter : myWriters) {
          aktWriter.write(correctLetters, letter, errorsLeft);

        }

      }
      boolean missing = false;
      for (int l = 0; l < gameWord.length(); l++) {
        boolean missinghere = true;
        for (int k = 0; k < correctLetters.length; k++) {
          if (gameWord.charAt(l) == correctLetters[k]) {
            missinghere = false;
          }
        }
        if (missinghere) {
          missing = true;
        }

      }

      if (!missing) {
        System.out.println("You won!");
        System.out.println("You correctly guessed the word: " + gameWord);
        System.out.println("You still had " + errorsLeft + " tries until gameover");
        return;
      }
      if (errorsLeft == 0) {
        System.out.println("Gameover! You lost");
        System.out.println("The word was: " + gameWord);
        return;
      }
    }

  }

}


