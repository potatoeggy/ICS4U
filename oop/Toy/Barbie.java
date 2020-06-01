// Daniel Chen
// 3 April 2020
// I'm a Barbie girl, in a Barbie world...

class Barbie extends Toy {
	String hairColour;
	int pinkness;
	public Barbie(String name, double cost, double price, String hairColour, int pinkness) {
		super(name, cost, price);
		this.pinkness = pinkness;
		this.hairColour = hairColour;
	}

	public void sing() { // print song to console
		String[] lyrics = {
			"I'm a Barbie girl, in a Barbie world,",
			"Life in plastic, it's fantastic!",
			"You can brush my hair, undress me anywhere,",
			"Imagination, life is your creation!"
		};

		for (String s : lyrics) {
			System.out.println(s);
		}
	}

	public String toString() { // override
		return String.format("Hi, I'm %s! I cost $%.2f to make, and you can buy me for $%.2f!", name, cost, price);
	}

	public void isPink() { // is it pink?
		System.out.println(pinkness > 0 ? "I'm pink!" : "...what is pink?");
	}

	public void setPinkness(int pinkness) {
		this.pinkness = pinkness;
	}

	public void setHairColour(String hairColour) {
		this.hairColour = hairColour;
	}

	public String getHairColour() {
		return this.hairColour;
	}
}