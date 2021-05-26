
public class Program {
	private final Input input = new Input();
	private final AuctionRegister auctionRegister = new AuctionRegister();
	private final DogRegister dogRegister = new DogRegister(auctionRegister);
	private final UserRegister userRegister = new UserRegister(auctionRegister, dogRegister);

	public static void main(String[] args) {
		Program program = new Program();
		program.run();
	}

	public void run() {
		startUp();
		runCommandLoop();
		closeDown();
	}

	private void startUp() {
		System.out.println("Hello, welcome to the Dog Program!");
	}

	private void runCommandLoop() {
		String command;
		do {
			command = getCommandFromInput();
			handleCommand(command);
		} while (!command.equals("exit"));
	}

	private String getCommandFromInput() {
		return input.readLowerCaseString("Command?> ");
	}

	private void handleCommand(String command) {
		switch (command) {
		case "register new dog":
			registerNewDog();
			break;
		case "increase age":
			increaseAge();
			break;
		case "list dogs":
			listDogs();
			break;
		case "remove dog":
			removeDog();
			break;
		case "register new user":
			registerNewUser();
			break;
		case "list users":
			listUsers();
			break;
		case "give dog":
			giveDog();
			break;
		case "remove user":
			removeUser();
			break;
		case "start auction":
			startAuction();
			break;
		case "make bid":
			makeBid();
			break;
		case "list auctions":
			listAuctions();
			break;
		case "list bids":
			listBids();
			break;
		case "close auction":
			closeAuction();
			break;
		case "exit":

			break;
		default:
			System.out.println("Error: unknown command");
		}
	}

	private void closeDown() {
		System.out.println("Goodbye!");
		input.close();
	}

	private void registerNewDog() {
		String name = getRequiredNameFromInput("Name?> ");
		String breed = getRequiredNameFromInput("Breed?> ");
		int age = input.readInt("Age?> ");
		int weight = input.readInt("Weight?> ");

		dogRegister.add(name, breed, age, weight);

		System.out.println(name + " added to the register");
	}

	private void increaseAge() {
		Dog dog = getDogFromInput();

		if (dog != null) {
			dog.increaseAgeByOne();
			System.out.println(dog.getName() + " is now one year older");
		}
	}

	private void listDogs() {
		if (dogRegister.isEmpty()) {
			System.out.println("Error: No dogs in register");
		} else {
			double minTailLength = input.readDouble("Smallest tail length to display?> ");
			System.out.println(dogRegister.listDogsAsString(minTailLength));
		}
	}

	private void removeDog() {
		Dog dog = getDogFromInput();
		if (dog != null) {
			dogRegister.remove(dog);
			System.out.println(dog.getName() + " is removed from the register");
		}
	}

	private Dog getDogFromInput() {
		String name = getRequiredNameFromInput("Enter the name of the dog?> ");
		Dog dog = dogRegister.find(name);

		if (dog == null) {
			System.out.println("Error: No such dog in register");
		}

		return dog;
	}

	private void registerNewUser() {
		String name = getRequiredNameFromInput("Name?> ");

		userRegister.add(name);
		System.out.println(name + " added to the register");
	}

	private void giveDog() {
		Dog dog = getDogFromInput();
		if (dog != null) {

			if (dog.getOwner() != null) {
				System.out.println("Error: The Dog already has an owner");
			} else {

				User user = getUserFromInput();

				if (user != null) {
					dog.setOwner(user);
					System.out.println(user.getName() + " now owns " + dog.getName());
				}
			}
		}
	}

	private void listUsers() {
		if (userRegister.isEmpty()) {
			System.out.println("Error: No users in register");
		} else {
			System.out.println(userRegister);
		}
	}

	private void removeUser() {
		User user = getUserFromInput();
		if (user != null) {

			userRegister.remove(user);
			System.out.println(user.getName() + " is removed from the register");
		}

	}

	private User getUserFromInput() {
		String name = getRequiredNameFromInput("Enter the name of the user?> ");
		User user = userRegister.find(name);
		if (user == null) {
			System.out.println("Error: No such user in register");
		}
		return user;
	}

	private void startAuction() {
		Dog dog = getDogFromInput();
		if (dog != null) {

			if (auctionRegister.find(dog) != null) {
				System.out.println("Error: This dog is already up for auction");

			} else if (dog.getOwner() != null) {
				System.out.println("Error: The Dog already has an owner");

			} else {
				auctionRegister.add(dog);
				System.out.println(dog.getName() + " has been put up for auction in auction #"
						+ auctionRegister.find(dog).getAuctionNumber());
			}
		}
	}

	private void makeBid() {
		User user = getUserFromInput();
		if (user != null) {

			Auction auction = getAuctionFromInput();
			if (auction != null) {

				int amount = getBidFromInput(auction.getMinToBid());

				auction.addBid(user, amount);
				System.out.println("Bid made");
			}
		}
	}

	private int getBidFromInput(int minToBid) {
		int bidAmount = input.readInt("Amount to bid (min " + minToBid + ")?> ");
		while (bidAmount < minToBid) {
			System.out.println("Error: The bid is to low");
			bidAmount = input.readInt("Amount to bid (min " + minToBid + ")?> ");
		}
		return bidAmount;
	}

	private void listAuctions() {
		if (auctionRegister.isEmpty()) {
			System.out.println("Error: No auctions in progress");
		} else {
			System.out.println(auctionRegister);
		}
	}

	private void listBids() {
		Auction auction = getAuctionFromInput();
		if (auction != null) {

			if (auction.listBidsAsString() == null) {
				System.out.println("No bids registered yet for this auction");

			} else {
				System.out.println(auction.listBidsAsString());
			}
		}
	}

	private void closeAuction() {
		Auction auction = getAuctionFromInput();
		if (auction != null) {

			Bid winningBid = auction.getWinningBid();
			auctionRegister.close(auction);

			if (winningBid == null) {
				System.out.println("The auction is closed. No bids where made for " + auction.getDog().getName());
			} else {
				System.out.println("The auction is closed. The winning bid was: " + winningBid);
			}
		}
	}

	private Auction getAuctionFromInput() {
		Auction auction = auctionRegister.find(getDogFromInput());
		if (auction == null) {
			System.out.println("Error: This dog is not up for auction");
		}
		return auction;
	}

	private String getRequiredNameFromInput(String prompt) {
		String name = input.readCapitalizedString(prompt);
		while (name == null) {
			System.out.println("Error: Please fill in the requested information");
			name = input.readCapitalizedString(prompt);
		}
		return name;
	}

}
