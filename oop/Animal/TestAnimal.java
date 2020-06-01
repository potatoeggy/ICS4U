package oop.Animal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Daniel Chen
// 4 April 2020
// Testing birbs

class TestAnimal {
	public static void main(String[] args) throws IOException {
		List<Animal> birbs = new ArrayList<Animal>();// animal list
		for (Animal a : new Animal[] {
			new Animal("Bobby", true, 2, 4, 0.5),
			new Animal("Joey", true, 1, 5, 100),
			new Animal("George", true, 10, 0.5, 0.7),
			new Animal("Georgia", false, 6, 0.7, 1),
			new Animal("Joeie", false, 3, 1, 5),
			new Animal("Bobbie", false, 5, 2, 4)
		}) {
			birbs.add(a); // add all above birbs to list
		}

		Animal newBirb = new Animal("Caroline", "Birb", false, 3, 2, 3); // make the dummy animal
		Animal whale = new Animal("Dumbledore", "Whale", true, 100, 2837, 23872); // make the bigger dummy animal
		try {
			newBirb.mate(whale); // demonstrating IOExceptions
		} catch (IOException ie) {
			System.out.println(ie.getLocalizedMessage());
		}

		for (int i = 0; i < birbs.size(); i++) { // loop through all birbs
			Animal a = birbs.get(i);
			a.setSpecies("Birb");
			try {
				Animal baby = a.mate(newBirb); // make new baby
				System.out.println("New baby - " + baby.toString()); // tell the world there was a new baby
				birbs.add(baby);
			} catch (IOException ie) {
				System.out.println(ie.getLocalizedMessage()); // print what went wrong
			}
			if (!a.fight(newBirb)) {
				a.kill();
				System.out.println(a.getName() + " was killed by " + newBirb.getName()); // oh no our birb died
			} else {
				System.out.println(newBirb.getName() + " was defeated by " + a.getName()); // caroline never dies
			}
			System.out.println(a.toString()); // print status of bird
		}
	}
}