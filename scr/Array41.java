// Daniel Chen
// 9 February 2020
// Two-dimensional 12-unit arrays

import java.util.Scanner; // add comments, make UI

class Array41 {
	static int[][] array = new int[3][4];
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		fillArray();
		displayArraySum();
		transposeArray();
	}
	
	static void fillArray() {
		for (int[] list : array) {
			for (int i = 0; i < list.length; i++) {
				System.out.print("Number: " );
				list[i] = input.nextInt();
			}
		}
	}
	
	static void displayArray() {	
		for (int[] list : array) {
			for (int i = 0; i < list.length; i++) {
				System.out.print(list[i] + " ");
			}
			System.out.println();
		}
	}
	
	static void transposeArray() {
		for (int i = 0; i < array[0].length; i++) {
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[j][i] + " ");
			}
			System.out.println();
		}
	}
	
	static void displayArraySum() {
		int xsum = 0;
		int[] ysum = new int[array[0].length];
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j] + " ");
				xsum += array[i][j];
				ysum[j] += array[i][j];
			}
			System.out.print(xsum);
			xsum = 0;
			System.out.println();
		}
		
		for (int i = 0; i < ysum.length; i++) {
			System.out.print(ysum[i] + " ");
		}
	}	
}
