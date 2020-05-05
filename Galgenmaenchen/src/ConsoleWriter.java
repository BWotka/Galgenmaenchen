
public class ConsoleWriter extends Writer {

	@Override
	public boolean write(char[] guessedLetters, char newLetter, int leftFails) {
		System.out.println("verbleibende Fehlversuche: " + leftFails);
		System.out.println("der geratene Buchstabe ist: " + newLetter);
		System.out.println("die bereits geratenen Buchstaben sind: ");
		for (int i = 0; i < guessedLetters.length; i++) {
			System.out.print( guessedLetters[i]);
		}
		System.out.println();
		System.out.println("der geratene Buchstabe ist: " + newLetter);
		
		boolean guess = word.matches("*" + newLetter + "*");
		for (int i = 0; i < word.length(); i++) {
			boolean guessed = false;
			if (word.charAt(i) == '-') {
				System.out.print(word.charAt(i));
			}
			else {
				for (int j = 0; j < guessedLetters.length; j++) {
					if (word.charAt(i) == guessedLetters[j]) {
						System.out.print(word.charAt(i));
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
