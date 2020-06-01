// Daniel Chen
// 6 February 2020
// Array exercises 2

import java.util.Scanner;

class Array2b {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int user = 0;
		// user menu
		System.out.println("1. Count occurrences of a number from 1-10");
		System.out.println("2. Count occurrences of a number from 15-25");
		System.out.println("3. Display sum of numbers within range");
		System.out.println("4. Display sum of numbers within larger range");
		System.out.println("5. Exit");
		System.out.println();
		
		while (user != 5) { // run menu until user quits
			System.out.print("Option #: ");
			user = input.nextInt();
			
			switch (user) {
				case 1:
					countOccurrences(input);
					break;
				case 2:
					countOccurrences2(input);
					break;
				case 3:
					totals(input);
					break;
				case 4:
					totals2(input);
					break;
			}
		}
	}
	
	static void countOccurrences(Scanner input) { // counts number of times user enters number between 1-10
		int[] array = new int[10];
		
		for (int i = 0; i < array.length; i++) {
			System.out.print("Enter a number: ");
			try {
				array[input.nextInt()-1]++; // add 1 to array index (userinput)
			} catch (Exception e) { // if out of range ignore
				// do nothing because this is fine
			}
		}
		
		for (int i = 0; i < array.length; i++) {
			System.out.println("The number " + (i + 1) + " appears " + array[i] + " times.");
		}
	}
	
	static void countOccurrences2(Scanner input) { // counts number of times user enters number between 15-25
		int[] array = new int[11];
		
		for (int i = 0; i < array.length; i++) {
			System.out.print("Enter a number: ");
			try {
				array[input.nextInt()-15]++; // add 1 to array index (userinput)
			} catch (Exception e) { // out of range then ignore
				// this is fine
			}
		}
		
		for (int i = 0; i < array.length; i++) { // print to screen
			System.out.println("The number " + (i + 15) + " appears " + array[i] + " times.");
		}
	}
	
	static void totals(Scanner input) { // get total of each range of numbers
		int[] array = new int[10];
		int temp;
		
		while (true) {
			temp = input.nextInt();
			try {
				array[temp / 10] += temp; // accumulate to array index assigned to range
			} catch (Exception e) {
				break; // if out of range break
			}
		}
		
		for (int i = 0; i < array.length; i++) { // print to screen
			System.out.println("Sum of numbers entered in " + (i * 10) + "s = " + array[i]);
		}
	}
	
	static void totals2(Scanner input) { // get total of each increasing range of numbers
		int[] array = new int[10];
		int temp;
		
		while (true) {
			temp = input.nextInt();
			try {
				for (int i = array.length - 1; i >= temp / 10; i--) {
					array[i] += temp; // accumulate to each array index less than input
				}
			} catch (Exception e) { // if out of range break;
				break; // if out of range break
			}
		} // end while
		
		for (int i = 0; i < array.length; i++) { // print to screen
			System.out.println("Sum of numbers entered in " + (i * 10) + "s = " + array[i]);
		}
	} // end totals2
} // end class
