package oop.Animal;
import java.io.IOException;

// Daniel Chen
// 3 April 2020
// Birbs

/*
 * Properties:
 * Name
 * Species name
 * Age
 * Sex
 * Weight
 * Height
 * 
 * Actions:
 * Grow
 * Clone
 * Die
 * Eat
 * Fight
 * Procreate
*/

public class Animal {
	// properties
	String name, species;
	int age;
	boolean isMale;
	double weight, height;

	public Animal(String name, String species, boolean isMale, int age, double weight, double height) { // everything constructor
		this(name, species, isMale, weight, height);
		this.age = age;
	}

	public Animal(String name, String species, boolean isMale, double weight, double height) { // no age constructor
		this(name, weight, height);
		this.isMale = isMale;
		this.species = species;
	}

	public Animal(String name, double weight, double height) { // no species no age no sex
		this(name);
		this.weight = weight;
		this.height = height;
	}

	public Animal(String name) { // nothing but name
		this.name = name;
		this.weight = 1;
		this.height = 1;
		this.isMale = true;
		this.age = 1;
		this.species = "Unknown";
	}

	public Animal(String name, boolean isMale, int age, double weight, double height) { // no species
		this(name, weight, height);
		this.age = age;
		this.isMale = isMale;
	} 

	Animal(Animal old) { // copy constructor, cloning is not allowed unless in this class
		this(old.name, old.species, old.isMale, old.weight, old.height);
	}

	// set and get methods
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getSpecies() {
		return species;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public boolean getMale() {
		return isMale;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}

	public double getHeight() {
		return height;
	}

	public String toString() { // print out nicely
		return String.format("%s is a %s %s who is %d year(s) old, weighing %.2f kg and with a height of %.2f cm.",
				name, isMale ? "male" : "female", species, age, weight, height);
	}

	public void grow(int years) {
		if (species.equals("Dead")) return; // dead things don't grow
		age += years;
		if (age > 150) {
			weight = 0;
			height = 0;
			species = "Dead";
		} else if (age > 20) { // (humans at least) stop growing after maturing
			weight *= Math.pow(0.95, years);
			height *= Math.pow(0.95,  years);
		} else if (age > 13) { // teenagers grow a lot i think
			weight *= Math.pow(1.35, years);
			height *= Math.pow(1.35, years);
		} else { // kids grow a lot too
			weight *= Math.pow(1.1, years);
			height *= Math.pow(1.1, years);
		}
	}

	public void kill() { // :(
		this.species = "Dead";
	}

	public void eat(int nutrientLevel) { // increase weight and height without aging
		if (species.equals("Dead")) return;
		this.weight += 1.05 * nutrientLevel;
		this.height += 1.05 * nutrientLevel;
	}

	public Animal clone() { // special cool technology
		if (Math.random() < 0.1) {
			return new Animal(this);
		} else {
			return new Animal(name, "Dead", false, -1, -1, -1);
		}
	}

	public boolean fight(Animal second) { // fight
		if (this.species.equals("Dead")) return false; // dead things can't fight
		if (second.species.equals("Dead")) return true;
		double strength1 = this.weight * this.age / Math.sqrt(this.height); // random formula that seems to make enough sense
		double strength2 = second.weight * second.age / Math.sqrt(second.height);

		return strength1 > strength2;
	}

	public void exercise(int intensity) { // grow height without aging
		int ageHolder = age; // temporary variable
		this.grow(intensity);
		age = ageHolder;
		weight -= weight * 0.25; // lose weight
	}

	public Animal mate(Animal second) throws IOException { // make new baby
		if (this.species.equals("Dead") || second.species.equals("Dead")) { // dead things can't reproduce
			throw new IOException("Postmortem reproduction is not yet supported in Animal.");
		} else if (!this.getSpecies().equals(second.getSpecies())) { // different things can't reproduce
			throw new IOException(String.format("%ss cannot procreate with %ss. Inter-species reproduction is not yet supported in Animal.", this.species, second.species));
		} else if (this.getMale() == second.getMale()) { // same-sex cannot biologically reproduce
			throw new IOException(String.format("%s and %s are both %s. Same-sex reproduction is not yet supported in Animal.", this.name, second.name, (this.isMale ? "male" : "female")));
		}
		
		Animal copy1 = new Animal(this); // make copies
		Animal copy2 = new Animal(second);

		copy1.grow(-copy1.age + 1); // make them go back in time
		copy2.grow(-copy2.age + 1);

		String name1 = copy1.name.substring(0, copy1.name.length() / 2); // merge their names
		String name2 = copy2.name.substring(copy2.name.length() / 2);
		boolean newMale = Math.random() > 0.5; // random chance for male or female
		// generate new baby
		Animal baby = new Animal(name1 + name2, this.species, newMale, 1, (copy1.weight + copy2.weight) / 2 * (Math.random()+1), (copy1.height + copy2.height) / 2 * (Math.random()+1));
		return baby;
	}
}