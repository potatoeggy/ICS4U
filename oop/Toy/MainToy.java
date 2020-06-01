// Daniel Chen
// 3 April 2020
// Class that calls other classes

import java.util.Arrays;

class MainToy {
	public static void main(String[] args) {
		Toy car = new Toy("Hot Wheels", 5.0, 100.0); // make new toy
		Toy chair = new Toy("IKEA", 1.0, 0.5);
		Toy tomato = new Toy("Pizza", 2.0, 2.0);

		Toy[] toys = new Toy[3]; // store new toys

		toys[0] = car;
		toys[1] = chair;
		toys[2] = tomato;

		Arrays.sort(toys); // bubble sort takes too long to implement and sorting unit is over anyway

		for (Toy t : toys) {
			System.out.println(String.format("$%.2f", t.getProfit())); // print to screen
		}
	}
}

// Classes are not necessarily objects, but all objects are part of classes
// objects have constructors