// Daniel Chen
// 9 February 2020
// ArrayList exercises

import java.util.Scanner;
import java.util.ArrayList;

class ArrayList1 { // TODO: add support for -1s using filled variable, only if needed by teacher
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Integer> array = new ArrayList<Integer>();
		// set up initial array contents
		enterFromKeyboard(array, input);
		
		// tell user what they can do
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
				case 2:
					enterFromKeyboard(array, input);
					break;
				case 3:
					countWhole(array);
					break;
				case 4:
					display(array);
					break;
				case 5:
					displayReverse(array);
					break;
				case 6:
					sum(array);
					break;
				case 7:
					average(array);
					break;
				case 8:
					findMax(array);
					break;
				case 9:
					findMin(array);
					break;
				case 10:
					search(array, input);
					break;
				case 11:
					sort(array);
					break;
				case 12:
					improvedBubbleSort(array);
					break;
			} // end switch
			System.out.println(); // look pretty
		} // end while
	} // end main
	
	static void enterFromKeyboard(ArrayList<Integer> array, Scanner input) { // Fill up up to ten indexes of array
		System.out.println("Enter any non-integer to continue");
		try {
			for (int i = 0; i < 10; i++) {
				System.out.print("Number: ");
				array.add(input.nextInt());
			} // end for
		} catch (Exception e) { // least amount of code necessary to do the most
			input.nextLine(); // clear buffer thingy or something
		} // end catch
	} // end EnterFromKeyboard
	
	static void countWhole(ArrayList<Integer> array) { // count all positive integers in array
		int counter = 0;
		for (int i : array) {
			if (i >= 0) {
				counter++;
			} // end if
		} // end for
		System.out.println("There are " + counter + " whole numbers in the array.");
	} // end CountWhole
	
	static void display(ArrayList<Integer> array) { // display entered numbers in array
		System.out.print("The numbers in the array are");
		for (int i : array) {
			if (i != -1) {
				System.out.print(" " + i);
			} // end if
		} // end for
	} // end Display
	
	static void displayReverse(ArrayList<Integer> array) { // display entered numbers in reverse order
		System.out.print("The numbers in reverse order in the array are");
		for (int i = 9; i >= 0; i--) { // iterate through array in reverse order
			if (array.get(i) != -1) {
				System.out.print(" " + array.get(i));
			} // end if
		} // end for
	} // end DisplayReverse
	
	static void sum(ArrayList<Integer> array) { // find sum of entered numbers of array
		int accumulator = 0;
		for (int i : array) {
			if (i != -1) {
				accumulator += i;
			} // end if
		} // end for
		System.out.println("The sum of the numbers in the array is " + accumulator);
	} // end Sum
	
	static void average(ArrayList<Integer> array) { // find average of numbers in array in array
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
	
	static void findMax(ArrayList<Integer> array) { // find largest number in array
		int max = array.get(0);
		for (int i : array) {
			if (i > max) {
				max = i;
			}
		}
		System.out.println("The largest number in the array is " + max);
	} // end FindMax
	
	static void findMin(ArrayList<Integer> array) { // find lowest number in array
		int min = array.get(0);
		for (int i : array) {
			if (i < min) {
				min = i;
			} // end if
		} // end for
		System.out.println("The smallest number in the array is " + min); // tell user
	} // end FindMin
	
	static void search(ArrayList<Integer> array, Scanner input) { // searches array for user-input number
		System.out.print("Number to search: ");
		int num = input.nextInt(); // prompt for input
		System.out.print(num + " can be found at array index(es) ");
		
		for (int i = 0; i < 10; i++) {
			if (array.get(i) == num) {
				System.out.print(i + "; "); // use semi-colons in an attempt to be lazy and at least somewhat grammatically correct
			} // end if
		} // end for
	} // end Search
	
	static void sort(ArrayList<Integer> array) { // instructions say to sort only entered numbers but since -1s are ignored it should be ok
		int temp;
		for (int i = 0; i < 9; i++) { // bubble sort algorithm
			for (int j = 1; j < 10 - i; j++) {
				if (array.get(j - 1) > array.get(j)) {
					temp = array.get(j - 1);
					array.set(j - 1, array.get(j));
					array.set(j, temp);
				}
			}
		}
		System.out.println("Numbers sorted."); // tell user that things went well
	} // end Sort
	
	static void improvedBubbleSort(ArrayList<Integer> array) {
		int temp;
		boolean sorted = false; // init variable
		for (int i = 0; ! sorted; i++) { // exit early if sorted
			sorted = true;
			for (int j = 1; j < 10 - i; j++) {
				if (array.get(j - 1) > array.get(j)) {
					temp = array.get(j - 1);
					array.set(j - 1, array.get(j));
					array.set(j, temp);
					sorted = false;
				}
			}
		}
		System.out.println("Numbers sorted.");
	} // end ImprovedBubbleSort
} // end class
