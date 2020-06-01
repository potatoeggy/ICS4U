// Daniel Chen
// 11 April 2020
// Piggybank tester

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MySavings {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // input handling
	static StringTokenizer st;
	static Piggybank piggybank = new Piggybank(); // global piggybank for all the methods to see

	static String next() throws IOException { // input handing
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine().trim());
		}
		return st.nextToken();
	}

	static int nextInt() throws IOException { // more input handling
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException { // even more input handling
		return Double.parseDouble(next());
	}
	public static void main(String[] args) throws IOException {
		String[] greeting = { // less "System.out.println"
			"Welcome to your piggybank!",
			"What would you like to do?",
			"1. Show total in piggybank",
			"2. Add a nickel(s)",
			"3. Add a dime(s)",
			"4. Add a quarter(s)",
			"5. Add a loonie(s)",
			"6. Add a toonie(s)",
			"7. Remove all coins from piggybank",
			"8. Remove an amount from piggybank",
			"9. Remove specific coin from piggybank",
			"10. Show coins in piggybank",
			"0. Quit",
			""
		};

		for (String s : greeting) {
			System.out.println(s); // print out above glob of text
		}

		System.out.print("Please enter an option number: "); // ask for input
		int input = nextInt();
		while (input != 0) { // keep on accepting input until they quit
			if (input == 1) { // print out total nicely
				System.out.println("There is $" + String.format("%.2f", piggybank.getTotal() / 100.0) + " in the bank.");
			} else if (input >= 2 && input <= 6) { // add coins to bank
				addCoins(input);
			} else if (input == 7) { // reset piggybank
				piggybank.clear();
				System.out.println("Piggybank successfully emptied.");
			} else if (input == 8) { 
				removeValue();
			} else if (input == 9) {
				removeCoins();
			} else if (input == 10) {
				showCoins();
			}
			System.out.print("Please enter an option number: ");
			input = nextInt();
		}
	} // end main

	static void showCoins() { // print out coins in piggybank nicely with their values
		System.out.println("Coins in piggybank:");
		System.out.println(String.format("Nickels: %d - $%.2f", piggybank.getNickels(), piggybank.getNickels() * 0.05));
		System.out.println(String.format("Dimes: %d - $%.2f", piggybank.getDimes(), piggybank.getDimes() * 0.1));
		System.out.println(String.format("Quarters: %d - $%.2f", piggybank.getQuarters(), piggybank.getQuarters() * 0.25));
		System.out.println(String.format("Loonies: %d - $%.2f", piggybank.getLoonies(), piggybank.getLoonies() * 1.0));
		System.out.println(String.format("Toonies: %d - $%.2f", piggybank.getToonies(), piggybank.getToonies() * 2.0));
	} // end showCoins

	static void removeCoins() throws IOException { // remove specific type of coin from bank
		String[] greeting = {
			"What type of coin would you like to remove?",
			"1. Nickel",
			"2. Dime",
			"3. Quarter",
			"4. Loonie",
			"5. Toonie",
			"",
		};

		for (String s : greeting) {
			System.out.println(s);
		}
		System.out.print("Please enter a coin number: ");
		int coin = nextInt() - 1; // have numbers correspond to array
		if (piggybank.removeCoin(coin)) { // try to remove one of those
			System.out.println("Successfully removed coin from piggybank.");
		} else {
			System.out.println("Could not remove coin from piggybank.");
		}
	} // end removeCoins

	static void removeValue() throws IOException { // remove specific value from piggybank
		System.out.print("Money to remove in dollars (X.XX): "); // tell user to please format the thing nicely
		int toRemove = (int) (nextDouble() * 100); // ints are nicer to work with
		if (piggybank.removeValue(toRemove)) { // tell user what happened
			System.out.println("Removed $" + (toRemove / 100.0) + " from piggybank.");
		} else {
			System.out.println("Could not remove $" + (toRemove / 100.0) + " from piggybank.");
		}
	} // end removeValue

	static void addCoins(int coin) throws IOException { // add any number of coins
		String[] name = {"Nickels", "Dimes", "Quarters", "Loonies", "Toonies"};
		int[] toAdd = new int[5];
		coin -= 2; // correspond input to array indices
		System.out.print(name[coin] + " to add: "); // ask how many to add
		toAdd[coin] = nextInt();
		
		piggybank.addCoins(toAdd[0], toAdd[1], toAdd[2], toAdd[3], toAdd[4]); // actually add them (only one argument will be not zero)
		System.out.println("Added " + toAdd[coin] + " " + name[coin].substring(0, 1).toLowerCase() + name[coin].substring(1) + " to piggybank."); // tell user it worked
	} // end addCoins
} // end class
