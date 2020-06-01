// Daniel Chen
// 6 February 2020
// Level three array exercises

import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

class Array3a {
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		// make pretty
		System.out.println("1. Check 7 unique numbers");
		System.out.println("2. Generate 10 unique numbers within range");
		System.out.println("3. Count number from file");
		System.out.println("4. Randomise divers");
		System.out.println("5. Randomise divers multiple times and better");
		System.out.println("6. Exit");
		System.out.println();
		
		int user;
		do {
			System.out.print("Option #: ");
			user = input.nextInt(); // get user option
			switch (user) { // do something based on user option
				case 1:
					noDuplicates();
					break;
				case 2:
					randomNoDuplicates();
					break;
				case 3:
					enterAndCount();
					break;
				case 4:
					randomOrder();
					break;
				case 5:
					threeRandomOrder();
			}
		} while (user != 6); // loop until user quit
	}
	
	static void noDuplicates() { // checks for duplicates in user input
		System.out.println("Please enter seven unique numbers below:");
		int[] array = new int[7];
		
		for (int i = 0; i < array.length; i++) {
			System.out.print("Number: ");
			array[i] = input.nextInt();
			
			for (int j = 0; j < i; j++) {
				if (array[i] == array[j]) {
					System.out.print("Re-enter ");
					i--;
					break;
				}
			}
		}
		
		System.out.print("Entered numbers: ");
		for (int i = 0; i < array.length; i++) {
			System.out.print(" " + i);
		}
		System.out.println();
	}
	
	static void randomNoDuplicates() {
		System.out.print("Min: ");
		int min = input.nextInt();
		System.out.print("Max: ");
		int max = input.nextInt();
		
		if (Math.abs(max - min) < 9) {
			System.out.println("Invalid range.");
			randomNoDuplicates();
		} else {
			int[] array = new int[10];
			for (int i = 0; i < 10; i++) {
				array[i] = (int) (Math.random() * max + min);
				for (int j = 0; j < i; j++) {
					if (array[i] == array[j]) {
						i--;
						break;
					}
				}
			}
			System.out.print("Numbers:");
			for (int num : array) {
				System.out.print(" " + num);
			}
			System.out.println();
		}
	}
	
	static void enterAndCount() {
		int[] array = new int[4];
		for (int i = 0; i < array.length; i++) {
			System.out.print("Unique number (1-100): ");
			array[i] = input.nextInt();
			for (int j = 0; j < i; j++) {
				if (array[i] == array[j]) {
					i--;
					break;
				}
			}
		}
		input.nextLine();
		
		int[] fileArray = new int[100];
		System.out.print("Filename: ");
		try {
			File file = new File(input.nextLine());
			Scanner finput = new Scanner(file);
			
			while (finput.hasNextInt()) {
				try {
					fileArray[finput.nextInt() - 1]++;
				} catch (Exception e) {
					// this is fine it just means that we hit a word or something
				}
			}
			finput.close();
		} catch (Exception e) {
			System.out.println("File broken.");
			return;
		}
		
		int max = 1;
		int maxIndex = 0;
		for (int num : array) {
			if (fileArray[num] != 0) {
				System.out.print((num) + ": ");
				for (int j = 0; j < fileArray[num]; j++) {
					System.out.print("*");
				}
				
				if (fileArray[num] > max) {
					maxIndex = num;
					max = fileArray[num];
				}
				
				System.out.println();
			}
		}
		System.out.println("Mode: " + maxIndex);
	}
	
	static void randomOrder() {
		String[] names = {"Joey", "Bobby", "Georgy", "Louis", "Buoy"};
		
		int temp;
		for (int i = 0; i < 5; i++) {
			temp = (int) (Math.random() * 5);
			if (! names[temp].equals("")) {
				System.out.println(names[temp]);
				names[temp] = "";
			} else {
				i--;
			}
		}
	}
	
	static void threeRandomOrder() {
		final String[] divers = {"Joey", "Bobby", "Georgy", "Louis", "Buoy"};
		
		int[] starter = {-1, -1, -1};
		int[] ender = {-1, -1, -1};
		
		int[] array;
		boolean failed;
		for (int i = 0; i < 3; i++) {
			failed = false;
			array = new int[5];
			// random order
			for (int j = 0; j < array.length; j++) {
				array[j] = (int) (Math.random() * array.length); // index
				for (int k = 0; k < j; k++) { // if already exists try again
					if (array[j] == array[k]) {
						j--;
						break;
					}
				}
			}
			
			starter[i] = array[0];
			ender[i] = array[4];
			
			
			// verify that first and last are unique
			for (int j = 0; j < i; j++) {
				if (starter[i] == starter[j] || ender[i] == ender[j]) {
					i--;
					failed = true;
					break;
				}
			}
			 // print if everything fine
			if (! failed) {
				for (int num : array) {
					System.out.println(divers[num]);
				}
				System.out.println();
			}
		}
	}
}
