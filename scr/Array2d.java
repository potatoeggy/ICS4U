// Daniel Chen
// 3 February 2020
// Array diagnostic part 2

import java.util.Scanner;

class Array2d { // TODO: add support for -1s using filled variable, only if needed by teacher
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		final int MAX = 10;
		int[] array = new int[MAX];
		
		// set up initial array contents
		initialiseArray(array);
		EnterFromKeyboard(input, array);
		
		// tell user what they can do
		System.out.println("1. Wipe array");
		System.out.println("2. Reset array");
		System.out.println("3. Count whole numbers in array");
		System.out.println("4. Display numbers in array");
		System.out.println("5. Display numbers in array in reverse order");
		System.out.println("6. Display sum of numbers in array");
		System.out.println("7. Display average of numbers in array");
		System.out.println("8. Find largest value in array");
		System.out.println("9. Find smallest value in array");
		System.out.println("10. Search for value in array");
		System.out.println("11. Sort array");
		System.out.println("12. Sort array faster");
		System.out.println("13. Quit");
		System.out.println();
		
		int user = 0;
		while (user != 13) {
			System.out.print("Option #: "); // prompt for input
			user = input.nextInt();
			switch (user) { // check what user entered
				case 1:
					initialiseArray(array);
					break;
				case 2:
					EnterFromKeyboard(input, array);
					break;
				case 3:
					CountWhole(array);
					break;
				case 4:
					Display(array);
					break;
				case 5:
					DisplayReverse(array);
					break;
				case 6:
					Sum(array);
					break;
				case 7:
					Average(array);
					break;
				case 8:
					findMax(array);
					break;
				case 9:
					findMin(array);
					break;
				case 10:
					search(input, array);
					break;
				case 11:
					Sort(array);
					break;
				case 12:
					ImprovedBubbleSort(array);
					break;
			} // end switch
			System.out.println(); // look pretty
		} // end while
	} // end main
	
	static void initialiseArray(int[] array) { // initialise array contents as -1
		for (int i = 0; i < array.length; i++) { // loop through array
			array[i] = -1;
		}
	} // end initialiseArray
	
	static void EnterFromKeyboard(Scanner input, int[] array) { // Fill up up to ten indexes of array
		System.out.println("Enter any non-integer to continue");
		try {
			for (int i = 0; i < array.length; i++) {
				System.out.print("Number: ");
				array[i] = input.nextInt();
			} // end for
		} catch (Exception e) { // least amount of code necessary to do the most
			input.nextLine(); // clear buffer thingy or something
		} // end catch
	} // end EnterFromKeyboard
	
	static void CountWhole(int[] array) { // count all positive integers in array
		int counter = 0;
		for (int i : array) {
			if (i >= 0) {
				counter++;
			} // end if
		} // end for
		System.out.println("There are " + counter + " whole numbers in the array.");
	} // end CountWhole
	
	static void Display(int[] array) { // display entered numbers in array
		System.out.print("The numbers in the array are");
		for (int i : array) {
			if (i != -1) {
				System.out.print(" " + i);
			} // end if
		} // end for
	} // end Display
	
	static void DisplayReverse(int[] array) { // display entered numbers in reverse order
		System.out.print("The numbers in reverse order in the array are");
		for (int i = array.length - 1; i >= 0; i--) { // iterate through array in reverse order
			if (array[i] != -1) {
				System.out.print(" " + array[i]);
			} // end if
		} // end for
	} // end DisplayReverse
	
	static void Sum(int[] array) { // find sum of entered numbers of array
		int accumulator = 0;
		for (int i : array) {
			if (i != -1) {
				accumulator += i;
			} // end if
		} // end for
		System.out.println("The sum of the numbers in the array is " + accumulator);
	} // end Sum
	
	static void Average(int[] array) { // find average of numbers in array in array
		int accumulator = 0;
		int counter = 0;
		for (int i : array) {
			if (i != -1) {
				accumulator += i;
				counter++;
			} // end if
		} // end for
		System.out.println("The average of the numbers in the array is " + accumulator / (double) counter); // cast so java doesn't use integer division
	} // end Average
	
	static void findMax(int[] array) { // find largest number in array
		int max = array[0];
		int counter = 1;
		for (int i : array) {
			if (i > max) {
				max = i;
				counter = 1;
			} else if (i == max) {
				counter++;
			}
		}
		
		System.out.println("The largest number in the array is " + max + ", appearing " + counter + " times.");
	} // end FindMax
	
	static void findMin(int[] array) { // find lowest number in array
		int min = array[0];
		int counter = 1;
		for (int i : array) {
			if (i < min) {
				min = i;
				counter = 1;
			} else if (i == min) {
				counter++;
			}
		} // end for
		System.out.println("The smallest number in the array is " + min + ", appearing " + counter + " times."); // tell user
	} // end FindMin
	
	static void search(Scanner input, int[] array) { // searches array for user-input number
		System.out.print("Number to search: ");
		int num = input.nextInt(); // prompt for input
		boolean found = false;
		System.out.print(num + " can be found at array index(es): "); 
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] == num) {
				System.out.print(i + "; "); // use semi-colons in an attempt to be lazy and at least somewhat grammatically correct
				found = true;
			} // end if
		} // end for
		
		if (! found) {
			System.out.println("Nowhere.");
		} // end if
	} // end Search
	
	static void Sort(int[] array) { // instructions say to sort only entered numbers but since -1s are ignored it should be ok
		int temp;
		for (int i = 0; i < array.length - 1; i++) { // bubble sort algorithm
			for (int j = 1; j < array.length - i; j++) {
				if (array[j - 1] > array[j]) {
					temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
				}
			}
		}
		System.out.println("Numbers sorted."); // tell user that things went well
	} // end Sort
	
	static void ImprovedBubbleSort(int[] array) {
		int temp;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 1; j < array.length - i; j++) {
				 if (array[j - 1] > array[j]) {
					 temp = array[j - 1];
					 array[j - 1] = array[j];
					 array[j] = temp;
				 }
			 }
		 }
	} // end ImprovedBubbleSort
} // end class
