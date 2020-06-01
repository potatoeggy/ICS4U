// Daniel Chen
// 3 April 2020
// Testing more toy OOP

class TestBarbie {
	public static void main(String[] args) {
		Barbie[] barbies = {
			new Barbie("Barbie", 0, 0, "Blonde", 9001),
			new Barbie("Chelsea", 500, 2, "Blonde", 1),
			new Barbie("Skipper", 1, 10, "Black", -5)
		};

		for (Barbie barbie : barbies) {
			barbie.sing();
			System.out.println(barbie.toString());
			barbie.isPink();
			System.out.println(barbie.getProfit());
		}
	}
}