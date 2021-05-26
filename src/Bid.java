

public class Bid {
	private final User bidder;
	private final int amount;

	public Bid(User bidder, int amount) {
		this.bidder = bidder;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public User getBidder() {
		return bidder;
	}

	@Override
	public String toString() {
		return String.format("%s %dkr", bidder.getName(), amount);
	}
}
