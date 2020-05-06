
public class ConsoleWriter extends Writer {

  @Override
  public boolean write(char[] guessedLetters, char newLetter, int leftFails) {
    System.out.println("verbleibende Fehlversuche: " + leftFails);
    System.out.println("der geratene Buchstabe ist: " + newLetter);
    for (int i = 0; i < guessedLetters.length; i++) {
      System.out.print(guessedLetters[i]);
    }
    System.out.println();
    System.out.println("der geratene Buchstabe ist: " + newLetter);

    boolean guess = false;
    if (word.indexOf(newLetter) != -1) {
      guess = true;
    }
    for (int i = 0; i < word.length(); i++) {
      boolean guessed = false;
      if (word.charAt(i) == '-') {
        System.out.print(word.charAt(i));
      } else {
        for (int j = 0; j < guessedLetters.length; j++) {
          if (word.charAt(i) == guessedLetters[j]) {
            System.out.print(word.charAt(i));
            guessed = true;
          }
        }
        if (!guessed) {
          System.out.print("_");
        }
      }
    }
    System.out.println();

    return guess;
  }

}
