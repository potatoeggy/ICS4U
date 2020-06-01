// Daniel Chen
// 9 April 2020
// Abstract class for all other coins

public abstract class Coin implements Comparable<Coin> {
	private int value;
	
	public Coin(int val) {
		this.value = val;
	}

	public double getValue() {
		return this.value;
	}

	public String toString() { // used for debugging and for printing everything
		return String.valueOf(this.value);
	}

	public int compareTo(Coin other) { // used in sorting
		return Integer.compare(this.value, other.value);
	}
}
