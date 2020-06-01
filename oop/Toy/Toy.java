// Daniel Chen
// 3 April 2020
// OOP

public class Toy implements Comparable<Toy> {
	String name;
	double cost, price; // declare thingies

	public Toy(String name, double cost, double price) { // constructor
		this.name = name;
		this.cost = cost;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public double getCost() {
		return this.cost;
	}

	public double getPrice() {
		return this.price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getProfit() {
		return price-cost;
	}

	public String toString() {
		return String.format("%s costs $%.2f to make and sells for $%.2f", name, cost, price);
	}

	public int compareTo(Toy t) {
		return Double.compare(this.getProfit(), t.getProfit());
	}
}