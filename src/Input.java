import java.util.Scanner;


public class Input {

	private final Scanner input = new Scanner(System.in);

	public int readInt(String prompt) {
		System.out.print(prompt);

		int number = input.nextInt();
		input.nextLine(); // Ta bort radbryt efter inputen

		return number;
	}

	public double readDouble(String prompt) {
		System.out.print(prompt);

		double number = input.nextDouble();
		input.nextLine(); // Ta bort radbryt efter inputen

		return number;
	}

	public String readString(String prompt) {
		System.out.print(prompt);

		return input.nextLine().trim();
	}

	public String readLowerCaseString(String prompt) {
		return readString(prompt).toLowerCase();
	}

	public String readCapitalizedString(String prompt) {
		String unformatedString = readString(prompt);

		if (unformatedString == null || unformatedString.equals("")) {
			return null;
		}

		return capitalizeString(unformatedString);
	}

	private String capitalizeString(String str) {
		str = str.toLowerCase();

		String[] words = str.split("\\s");
		String capitalizedString = "";
		for (String word : words) {
			String firstLetter = word.substring(0, 1).toUpperCase();
			String restOfWord = word.substring(1);
			capitalizedString += firstLetter + restOfWord + " ";
		}

		return capitalizedString.trim();
	}

	public void close() {
		input.close();
	}

}
