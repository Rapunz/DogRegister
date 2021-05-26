import java.util.ArrayList;



public class DogRegister {
	private final ArrayList<Dog> dogs = new ArrayList<>();
	private final AuctionRegister auctionRegister;

	public DogRegister(AuctionRegister auctionRegister) {
		this.auctionRegister = auctionRegister;
	}

	public void add(String name, String breed, int age, int weight) {
		dogs.add(new Dog(name, breed, age, weight));
	}

	public void remove(Dog dog) {
		dog.setOwner(null);
		auctionRegister.remove(dog);

		dogs.remove(dog);
	}

	public void removeAllOwnedBy(User owner) {
		ArrayList<Dog> dogsToRemove = new ArrayList<>();
		for (Dog dog : dogs) {
			if (dog.getOwner() == owner) {
				dogsToRemove.add(dog);
			}
		}

		for (Dog dog : dogsToRemove) {
			remove(dog);
		}
	}

	public Dog find(String name) {
		for (Dog dog : dogs) {
			if (dog.getName().equals(name)) {
				return dog;
			}
		}
		return null;
	}

	public boolean isEmpty() {
		return dogs.isEmpty();
	}

	public String listDogsAsString(double minTailLength) {
		sort();

		String dogList = "";
		for (Dog dog : dogs) {
			if (dog.getTailLength() >= minTailLength) {
				dogList += dog + "\n";
			}
		}

		return dogList;
	}

	private void sort() {
		for (int i = 0; i < dogs.size() - 1; i++) {
			Dog currentMin = dogs.get(i);
			int currentMinIndex = i;

			for (int j = i + 1; j < dogs.size(); j++) {
				if (dogs.get(j).myCompareTo(currentMin) < 0) {
					currentMin = dogs.get(j);
					currentMinIndex = j;
				}
			}

			if (currentMinIndex != i) {
				dogs.set(currentMinIndex, dogs.get(i));
				dogs.set(i, currentMin);
			}
		}
	}

}
