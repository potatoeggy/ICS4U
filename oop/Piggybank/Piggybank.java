// Daniel Chen
// 9 April 2020
// One piggybank to rule all the coins

import java.util.ArrayList;
import java.util.Collections;

public class Piggybank {
	private ArrayList<Coin> piggybank; // holds coins
	protected static final Coin[] treasury = {new Nickel(), new Dime(), new Quarter(), new Loonie(), new Toonie()}; // holds coins to add
	private int[] bank; // holds frequency of coins
	public Piggybank() { // init everything to 0
		this.piggybank = new ArrayList<Coin>();
		this.bank = new int[5];
	}

	public Piggybank(int nickels, int dimes, int quarters, int loonies, int toonies) { // init everything to suggested values
		this();
		int[] arrayBank = new int[] {nickels, dimes, quarters, loonies, toonies}; // iterate through input
		for (int i = 0; i < arrayBank.length; i++) {
			bank[i] = arrayBank[i]; // set frequency
			for (int j = 0; j < arrayBank[i]; j++) { // add coins one by one
				piggybank.add(treasury[i]);
			}
		}
	} // end constructor

	public int getTotal() { // adds up values of all coins in piggybank
		int total = 0;
		for (Coin c : piggybank) {
			total += c.getValue();
		}
		return total;
	} // end getTotal

	public void addCoins(int nickels, int dimes, int quarters, int loonies, int toonies) { // adds input number of coins into piggybank
		int[] arrayBank = new int[] {nickels, dimes, quarters, loonies, toonies}; // iterate through arguments
		for (int i = 0; i < arrayBank.length; i++) {
			for (int j = 0; j < arrayBank[i]; j++) {
				piggybank.add(treasury[i]); // adds coins one by one to piggybank object
			}
			bank[i] += arrayBank[i]; // adds coins all at once to frequency array
		}
	} // end addCoins

	public int getNickels() { // returns number of nickels
		return bank[0];
	}

	public int getDimes() { // returns number of dimes
		return bank[1];
	}

	public int getQuarters() { // returns number of quarters
		return bank[2];
	}

	public int getLoonies() { // returns number of loonies
		return bank[3];
	}

	public int getToonies() { // return number of toonies
		return bank[4];
	}

	public void clear() { // re-initialise data structures
		piggybank = new ArrayList<Coin>();
		bank = new int[5];
	}

	public boolean removeCoin(int type) { // remove one specific coin
		if (bank[type] > 0) { // ensure we don't go negative
			bank[type]--;
			piggybank.remove(treasury[type]);
			return true; // successfully removed coin
		}
		return false; // cannot remove coin
	} // end removeCoin

	public boolean removeValue(int value) { // remove specific value of coins
		Collections.sort(piggybank); // sorting makes greedy algorithm easier

		ArrayList<Coin> toRemove = new ArrayList<Coin>(); // temporary storage
		for (int i = piggybank.size()-1; i >= 0; i--) { // iterate from highest to lowest value
			if (piggybank.get(i).getValue() <= value) { // if we can subtract subtract
				value -= piggybank.get(i).getValue();
				toRemove.add(piggybank.get(i));
			}
		}

		if (value == 0) { // we have enough coins to reach target value
			for (Coin c : toRemove) {
				if (c.getValue() == 5) { // wish there was a better way of doing this
					bank[0]--;
				} else if (c.getValue() == 10) {
					bank[1]--;
				} else if (c.getValue() == 25) {
					bank[2]--;
				} else if (c.getValue() == 100) {
					bank[3]--;
				} else {
					bank[4]--;
				}
				piggybank.remove(c); // remove them all
			}
			return true; // successfully removed value
		}
		return false; // failed to remove value
	} // end removeValue
} // end class
