

public class User {
	private String name;
	private Dog[] dogs = new Dog[0];

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addDog(Dog dog) {
		if (!isOwnerOf(dog)) {

			increaseDogArrayByOne();
			dogs[dogs.length - 1] = dog;

			dog.setOwner(this);
		}
	}

	private boolean isOwnerOf(Dog dog) {
		for (Dog ownedDog : dogs) {
			if (ownedDog == dog) {
				return true;
			}
		}
		return false;
	}

	private void increaseDogArrayByOne() {
		changeDogArraySize(dogs.length + 1);
	}

	public void removeDog(Dog dog) {
		for (int i = 0; i < dogs.length; i++) {
			if (dogs[i] == dog) {

				switchDogWithLastInArray(i);
				decreaseDogArrayByOne();

				dog.setOwner(null);
			}
		}
	}

	private void switchDogWithLastInArray(int index) {
		Dog temp = dogs[index];
		dogs[index] = dogs[dogs.length - 1];
		dogs[dogs.length - 1] = temp;
	}

	private void decreaseDogArrayByOne() {
		changeDogArraySize(dogs.length - 1);
	}

	private void changeDogArraySize(int newArraySize) {
		int noOfPlacesToCopy = Math.min(newArraySize, dogs.length);

		Dog[] temp = new Dog[newArraySize];
		for (int i = 0; i < noOfPlacesToCopy; i++) {
			temp[i] = dogs[i];
		}

		dogs = temp;
	}

	@Override
	public String toString() {
		String stringToReturn = name + " [" + listDogNames() + "]";
		return stringToReturn;
	}

	private String listDogNames() {
		String dogNames = "";
		boolean isFirstDog = true;

		for (Dog dog : dogs) {

			if (isFirstDog) {
				dogNames += dog.getName();
				isFirstDog = false;

			} else {
				dogNames += ", " + dog.getName();
			}
		}
		return dogNames;
	}

}
