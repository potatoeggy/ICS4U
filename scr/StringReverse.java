// Daniel Chen
// 27 February 2020
// Reverse string without external methods

import java.util.Scanner;

class StringReverse {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter a number to be reversed: ");
		String string = input.nextLine();

		System.out.println("The number reversed is: " + reverse(string));
	}

	static String reverse(String string) {
		String reversedString = "";
		for (int i = string.length() -1; i >= 0; i--) {
			reversedString += string.substring(i, i+1); // i would prefer chararray but this seems better?
		}
		return reversedString;
	}
}
