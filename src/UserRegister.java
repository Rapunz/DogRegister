import java.util.ArrayList;



public class UserRegister {
	private final ArrayList<User> users = new ArrayList<>();
	private final AuctionRegister auctionRegister;
	private final DogRegister dogRegister;

	public UserRegister(AuctionRegister auctionRegister, DogRegister dogRegister) {
		this.auctionRegister = auctionRegister;
		this.dogRegister = dogRegister;
	}

	public void add(String name) {
		users.add(new User(name));
	}

	public void remove(User user) {
		dogRegister.removeAllOwnedBy(user);
		auctionRegister.removeAllBidsBy(user);

		users.remove(user);
	}

	public User find(String name) {
		for (User user : users) {
			if (user.getName().equals(name)) {
				return user;
			}
		}

		return null;
	}

	public boolean isEmpty() {
		return users.isEmpty();
	}

	public String toString() {
		String userList = "";
		for (User user : users) {
			userList += user + "\n";
		}

		return userList;
	}

}
