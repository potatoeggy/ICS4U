// Daniel Chen
// 9 February 2020
// More 2d arrays exercises

import java.util.Scanner;
import java.io.File;

class Array42 { // add comments
	static int[][] array;
	static String[] names;
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		initArray();
		displayArray();
	}
	
	static void initArray() {
		System.out.print("Filename: ");
		try {
			File file = new File(input.nextLine());
			Scanner finput = new Scanner(file);
			
			array = new int[finput.nextInt()][finput.nextInt()];
			names = new String[array.length];
			
			finput.nextLine();
			
			for (int i = 0; i < array.length; i++) {
				names[i] = finput.nextLine();
				for (int j = 0; j < array[0].length; j++) {
					array[i][j] = finput.nextInt();
				}
				finput.nextLine();
			}
			
		} catch (Exception e) {
			System.out.println("something broke");
		}
	}
	
	static double studentAverage(int student) {
		int acc = 0;
		for (int num : array[student]) {
			acc += num;
		}
		return (double) acc / array[student].length;
	}
	
	static double testAverage(int test) {
		int acc = 0;
		for (int i = 0; i < array.length; i++) {
			acc += array[i][test];
		}
		return (double) acc / array.length;
	}
	
	static String format(int num) {
		return String.format("%-10s", num + "%");
	}
	
	static String format(double num) {
		return String.format("%-10s", num + "%");
	}
	
	static String format(String string) {
		return String.format("%-10s", string);
	}
	
	static void displayArray() {
		System.out.print(format("Student:"));
		for (String name : names) {
			System.out.print(format(name));
		}
		System.out.println("Average");
		
		for (int i = 0; i < array[0].length; i++) {
			System.out.print(format("Test #" + (i + 1) + ":"));
			for (int j = 0; j < array.length; j++) {
				System.out.print(format(array[j][i]));
			}
			System.out.println(format(testAverage(i)));
		}
		
		System.out.print(format("Average:"));
		int acc = 0;
		for (int i = 0; i < names.length; i++) {
			System.out.print(format(studentAverage(i)));
			acc += studentAverage(i);
		}
		System.out.println(format((double) acc / names.length));
	}
}
