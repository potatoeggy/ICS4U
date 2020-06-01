// Daniel Chen
// 4 March 2020
// Recursion exercises

import java.util.Scanner;

class Recursion {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter a positive integer: ");
		int x = input.nextInt();
		System.out.println(x + "! = " + factorial(x));

		System.out.println("Please enter two positive integers: ");
		x = input.nextInt();
		int y = input.nextInt();
		System.out.println("The greatest common factor of " + x + " and " + y  + " is " + gcf(x, y));

		System.out.println("Please enter one real number and one integer: ");
		double real = input.nextDouble();
		int notReal = input.nextInt();
		System.out.println(real + "^" + notReal + " = " + pow(real, notReal));
		input.close();
	}

	static int factorial(int x) {
		if (x > 0) {
			return x * factorial(x-1);
		}
		return 1;
	}

	static int gcf(int m, int n) {
		if (m > n) {
			return gcf(n, m-n);
		} else if (m < n) {
			return gcf(n, m);
		}
		return m;
	}

	static double pow(double real, int notReal) {
		if (notReal < 0) {
			return pow(1/real*notReal, -notReal-1);
		} else if (notReal == 0) {
			return 1;
		} else if (notReal != 1) {
			return pow(real*notReal, notReal-1);
		} // doesn't work

		return real;
	}

	// 4: Pathfinding + navigation
	static void dfs(boolean[][] visited, char[][] array, int x, int y) {
		int modX = 0;
		int modY = 0;
		if (array[x][y] == 'N') {
			modX = -1;
		} else if (array[x][y] == 'S') {
			modX = 1;
		} else if (array[x][y] == 'W') {
			modY = -1;
		} else {
			modY = 1;
		}

		if (modX != 0) {
			for (int i = x; i >= 0 && i < array.length; i += modX) {
				if (!visited[i][y]) {
					visited[i][y] = true;
					if (array[i][y] != '.') {
						dfs(visited, array, i, y);
					}
				}
			}
		} else {
			for (int i = y; i >= 0 && i < array[0].length; i += modY) {
				if (!visited[x][i]) {
					visited[x][i] = true;
					if (array[x][i] != '.') {
						dfs(visited,array, x, i);
					}
				}
			}
		}
		System.out.println("(" + x + "," + y + ")");
	}
}