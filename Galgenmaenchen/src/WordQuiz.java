
public class WordQuiz {

  protected ConsoleReader myCReader;
  protected Writer myWriter;

  public boolean addWordList(WordList pWordList) {
    return true;
  }

  public boolean deleteWordList(WordList pWordList) {
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
  String gameWord =new WordList(wordSubject).getWordOfLength(wordLength);
  char[] knownWord = new char[wordLength];
  char[] triedChars = new char[26];
  char letter;
  int errorsLeft = gameDifficulty.getDifficulty();
  myWriter.setSubject(wordSubject);
  myWriter.setWord(gameWord);
  myWriter.setDifficulty(gameDifficulty);
  
  
 
    //loop in which the turns are made
    while (errorsLeft > 0) {
      letter = myCReader.readNextChar();
      if (gameWord.indexOf(letter) == -1) {
        errorsLeft += -1;
        triedChars.get;
      }
      else {
        knownWord
      }
      myWriter.write(triedChars, letter, errorsLeft);
      
      if (Arrays.equals( knownWord,gameWord.toCharArray())) {
        // The player correctly guessed the word
        System.out.println("You won!");
        System.out.println("You correctly guessed the word: '" + gameWord + "' !");
        System.out.println("You had " + errorsLeft + " errors left before you would have lost the game!");
        return;
      }
    }
  // The player has no tries left, he loses the game
  System.out.println("Game Over!");
  System.out.println("The Solution was: "+gameWord);
  System.out.println("You made "+gameDifficulty.getDifficulty()+"mistakes and lost the game!");
  
  
  }

}


