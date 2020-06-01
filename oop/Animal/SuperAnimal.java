// Daniel Chen
// 4 April 2020
// better animals

package oop.Animal;

public class SuperAnimal extends Animal {
	boolean wings, canTeleport;

	public SuperAnimal(String name, String species, boolean isMale, int age, double weight, double height, boolean wings, boolean canTeleport) {
		super(name, species, isMale, age, weight, height); // less typing
		this.wings = wings;
		this.canTeleport = canTeleport;
	}

	public SuperAnimal(String name, String species, boolean wings, boolean canTeleport) { // no weight/height, gender, and age
		super(name);
		this.species = species;
		this.wings = wings;
		this.canTeleport = canTeleport;
	}

	public SuperAnimal(String name, String species, double weight, double height, boolean wings, boolean canTeleport) { // no age or gender
		super(name, weight, height);
		this.species = species;
		this.wings = wings;
		this.canTeleport = canTeleport;
	}

	public SuperAnimal(Animal a) { // convert to superanimal
		this(a.name, a.species, a.isMale, a.age, a.weight, a.height, true, true);
	}

	// set and get methods
	public void setWings(boolean wings) {
		this.wings = wings;
	}

	public boolean getWings() {
		return wings;
	}

	public void setTeleport(boolean canTeleport) {
		this.canTeleport = canTeleport;
	}

	public boolean getTeleport() {
		return canTeleport;
	}

	public String fly() { // if has wings go fly
		return name + (wings ? " is flying!" : " cannot fly :(.");
	}

	public String teleport(String toPlace) { // if can teleport go teleport
		return name + String.format(" %s!", canTeleport ? "teleported to " + toPlace : "cannot teleport");
	}

	public String toSpace() { // if can teleport and has wings go to space
		return name + (wings && canTeleport ? " is in space!" : " is stuck on Earth :(");
	}
}