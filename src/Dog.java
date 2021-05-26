

public class Dog {

	private static final double DACHSHUND_TAIL_LENGTH = 3.7;
	private static final String[] DACHSHUND_TRANSLATIONS = { "Tax", "Dachshund", "Mäyräkoira", "Teckel", "Dackel",
			"Taksas" };
	private String name;
	private String breed;
	private int age;
	private int weight;
	private User owner;

	public Dog(String name, String breed, int age, int weight) {
		this.name = name;
		this.breed = breed;
		this.age = age;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public String getBreed() {
		return breed;
	}

	public int getAge() {
		return age;
	}

	public void increaseAgeByOne() {
		age++;
	}

	public int getWeight() {
		return weight;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		if (this.owner != owner) {
			User oldOwner = this.owner;

			if (oldOwner != null) {
				this.owner = null;
				oldOwner.removeDog(this);
			}

			if (owner != null) {
				this.owner = owner;
				owner.addDog(this);
			}
		}
	}

	public double getTailLength() {
		if (isDachshund()) {
			return DACHSHUND_TAIL_LENGTH;
		}
		return calculateNonDachshundTailLength();
	}

	private boolean isDachshund() {
		for (String translation : DACHSHUND_TRANSLATIONS) {
			if (breed.equalsIgnoreCase(translation)) {
				return true;
			}
		}
		return false;
	}

	private double calculateNonDachshundTailLength() {
		final double NON_DACHSHUND_TAIL_LENGTH_DENOMINATOR = 10.0;
		return age * weight / NON_DACHSHUND_TAIL_LENGTH_DENOMINATOR;
	}

	public int myCompareTo(Dog dogToCompareTo) {
		if (getTailLength() < dogToCompareTo.getTailLength()) {
			return -1;

		} else if (getTailLength() == dogToCompareTo.getTailLength()) {
			return getName().compareTo(dogToCompareTo.getName());
		}

		return 1;
	}

	@Override
	public String toString() {
		return String.format("* %s (%s, %d years, %d kilo, %.2f cm tail%s)", name, breed, age, weight, getTailLength(),
				owner == null ? "" : ", Owned by " + owner.getName());
	}

}
