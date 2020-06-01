// Daniel Chen
// 9 February 2020
// More 2d arrays exercises

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

class Arrays2DPerf {
	static int[][] array; // declare global arrays to be used throughout methods
	static String[] names;

	public static void main(String[] args) {
		initArray(); // read from file
		displayArray(); // output to screen
		writeArray(); // save to file
	}
	
	static void initArray() { // initialises global arrays with contents from user-declared filename
		Scanner input = new Scanner(System.in); // get user input for filename for flexibility
		try { // i don't like throwing exceptions
			System.out.print("Please enter a filename: "); // prompt for user to enter filename
			File file = new File(input.nextLine()); // create objects needed to read from file
			Scanner finput = new Scanner(file);
			
			array = new int[finput.nextInt()][finput.nextInt()]; // make array based on size from file
			names = new String[array.length];
			
			finput.nextLine(); // clear nextInt buffer
			for (int i = 0; i < array.length; i++) {
				names[i] = finput.nextLine(); // add name to array
				for (int j = 0; j < array[0].length; j++) {
					array[i][j] = finput.nextInt(); // add marks to array
				}
				if (i != array.length - 1) { // clear buffer, if is needed to avoid crashing
					finput.nextLine();
				}
			} // end for
		} catch (java.io.FileNotFoundException e) {
			System.out.println("something broke");
		}
	} // end initArray
	
	static double studentAverage(int student) { // internal method used to calculate the average of a student's marks
		int acc = 0; // accumulator
		for (int num : array[student]) {
			acc += num;
		}
		return (double) acc / array[student].length; // return average
	} // end studentAverage
	
	static double testAverage(int test) { // internal method used to calculate the average of a given test
		int acc = 0; // accumulator
		for (int i = 0; i < array.length; i++) {
			acc += array[i][test];
		}
		return (double) acc / array.length; // return average
	} // end testAverage
	
	static String format(int num) { // internal method used for formatting before I discovered printf
		return String.format("%-8s", num + "%"); // pad to 8 spaces with percent
	} // end format for ints
	
	static String format(double num) { // format for doubles
		return String.format("%-8s", num + "%");
	} // end format for doubles
	
	static String format(String string) { // format for strings
		return String.format("%-8s", string);
	} // end format for strings
	
	static void displayArray() { // show everything in nice pretty table
		System.out.printf("%-30s", "Student/Test #"); // apparently we have very long names
		for (int i = 0; i < array[0].length; i++) {
			System.out.print(format("#" + (i + 1))); // represents test number, can probably handle up to 30 marks before user has to extend window
		}
		System.out.println(format("Average")); // set up average

		for (int i = 0; i < array.length; i++) {
			System.out.printf("%-30s", names[i]); // print name under first column
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(format(array[i][j])); // print marks under other columns
			}
			System.out.println(format(studentAverage(i))); // also print average
		}

		double acc = 0; // accumulator
		System.out.printf("%-30s", "Average"); // set up average
		for (int i = 0; i < array[0].length; i++) {
			System.out.print(format(testAverage(i))); // print marks for last row
			acc += testAverage(i);
		}
		System.out.println(acc / array[0].length + "%"); // average of averages
	} // end displayArray

	static void writeArray() { // writes needed array data to a file nicely
		try {
			PrintWriter foutput = new PrintWriter(new File("results.txt"));
			foutput.println("Average per test");
			for (int i = 0; i < array[0].length; i++) {
				foutput.println("Test #" + (i+1) + ": " + format(testAverage(i))); // test averages
			}
			
			foutput.println(); // newline for niceness
			foutput.println("Average per student");
			double acc = 0;
			for (int i = 0; i < array.length; i++) {
				foutput.println(format(names[i]) + ": " + format(studentAverage(i))); // student averages
				acc += studentAverage(i);
			}

			foutput.println();
			foutput.println("Average course grade: " + acc / array.length + "%"); // print average of averages
			foutput.close(); // close file handle
			System.out.println("Averages saved to receipt.txt");
		} catch (Exception e) {
			System.out.println("something broke");
		}
	} // end writeArray
} // end class
