/**
 * 
 * @author Benedikt Wotka, David Nickel
 * @version 0.1 @
 *
 */
public class ConsoleWriter extends Writer {

  /**
   * 
   * @return correct: if the last letter is correct, guess is true.
   * @param guessedLetters all letters that the user guessed correctly.
   * @param newLetter the last letter that the user put in.
   * @param leftFails enamored attempts.
   */
  @Override
  public boolean write(char[] guessedLetters, char newLetter, int leftFails) {
    System.out.println("********************");
    System.out.println("Your left attmepts are: " + leftFails);
    System.out.println("Your letter is: " + newLetter);
    System.out.print("The letters you´ve guessed are: ");
    for (int i = 0; i < guessedLetters.length; i++) {
      System.out.print(guessedLetters[i]);
    }
    System.out.println();

    for (int i = 0; i < word.length(); i++) { // output the word in the current state of the game

      boolean guessed = false;
      if (word.charAt(i) == '-') { // '-' is the symbol for a whitespace and the user has not to
                                   // guess it
        System.out.print(word.charAt(i));
      } else {
        for (int j = 0; j < guessedLetters.length; j++) {
          if (word.charAt(i) == guessedLetters[j]) {
            System.out.print(word.charAt(i) + " ");
            guessed = true;
          }
        }
        if (!guessed) {
          System.out.print("_ ");
        }
      }
    }
    System.out.println();

    boolean correct; // tests if the new letter is in the word
    if (word.indexOf(newLetter) != -1) {
      correct = true;

      System.out.println("The letter is correct!");
    } else {
      correct = false;
      System.out.println("The letter is wrong!");
    }
    System.out.println("********************");
    return correct;
  }

}
