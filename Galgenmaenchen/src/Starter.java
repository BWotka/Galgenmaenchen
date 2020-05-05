/**
 * 
 */

/**
 * @author David Nickel, Benedikt Wotka
 *
 */
public class Starter {

  /**
   * @param args
   */
  public static void main(String[] args) {
    WordQuiz game = new WordQuiz();
    ConsoleReader creader = new ConsoleReader(null);
    ConsoleWriter cwriter = new ConsoleWriter();
    ExcelWriter exwriter = new ExcelWriter();

    game.addWriter(cwriter);
    game.addWriter(exwriter);
    game.setConsoleReader(creader);

    WordList listAnimal = new WordList(Subject.Animals);
    WordList listCar = new WordList(Subject.Cars);
    WordList listDrink = new WordList(Subject.Drinks);
    WordList listCountries = new WordList(Subject.Countries);
    WordList listCities = new WordList(Subject.Cities);

    game.addWordList(listAnimal);
    game.addWordList(listCar);
    game.addWordList(listDrink);
    game.addWordList(listCountries);
    game.addWordList(listCities);


    game.playGame(6, Subject.Animals, Difficulty.Normal);

  }

}
