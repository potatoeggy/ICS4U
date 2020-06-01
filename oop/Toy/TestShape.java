// Daniel Chen
// 4 April 2020
// Testing triangles and others

import java.util.Scanner;

public class TestShape {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Shape[] shapes = {new Triangle(), new Square(), new Circle()};
		System.out.println("Please enter an option number to draw a shape.");
		System.out.println("1. Triangle");
		System.out.println("2. Square");
		System.out.println("3. Circle");
		System.out.println("4. Quit\n");

		int num = sc.nextInt();

		while (num != 4) {
			shapes[num-1].draw();
			num = sc.nextInt();
		}
		sc.close();
	}
}