// Daniel Chen
// 4 April 2020
// Testing better animals

import java.io.IOException;

public class TestSuperAnimal {
	public static void main(String[] args) throws IOException {
		SuperAnimal altaria = new SuperAnimal("Altaria", "Pokemon", 20.6, 110, true, false); // new superanimals
		SuperAnimal kadabra = new SuperAnimal("Kadabra", "Pokemon", 56.5, 130, false, true);
		altaria.setMale(false); // allow them to breed

		for (SuperAnimal sa : new SuperAnimal[] {altaria, kadabra}) { // use all three methods for each superanimal
			System.out.println(sa.fly());
			System.out.println(sa.teleport("Pomdeters"));
			System.out.println(sa.toSpace());
		}
		SuperAnimal baby = new SuperAnimal(altaria.mate(kadabra)); // use one method from animal

		System.out.println(baby.toSpace()); // have fun
	}
}