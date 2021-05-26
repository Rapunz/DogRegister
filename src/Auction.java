import java.util.ArrayList;


public class Auction {
	private static final int MAX_BIDS_IN_LIST = 3;
	private static int count;
	private final Dog dog;
	private final int auctionNumber;
	private final ArrayList<Bid> bids = new ArrayList<>();

	public Auction(Dog dog) {
		this.dog = dog;
		auctionNumber = ++count;
	}

	public Dog getDog() {
		return dog;
	}

	public int getAuctionNumber() {
		return auctionNumber;
	}

	public void addBid(User bidder, int amount) {
		removeBid(bidder);
		bids.add(0, new Bid(bidder, amount));
	}

	public void removeBid(User bidder) {
		bids.remove(findBid(bidder));
	}

	public int getMinToBid() {
		if (bids.isEmpty()) {
			return 1;
		}
		return bids.get(0).getAmount() + 1;
	}

	public Bid getWinningBid() {
		if (bids.isEmpty()) {
			return null;
		}
		return bids.get(0);
	}

	public void appointWinner() {
		Bid winningBid = getWinningBid();
		if (winningBid != null) {
			dog.setOwner(winningBid.getBidder());
		}
	}

	private Bid findBid(User bidder) {
		for (Bid bid : bids) {
			if (bid.getBidder() == bidder) {
				return bid;
			}
		}
		return null;
	}

	public String listBidsAsString() {
		if (bids.isEmpty()) {
			return null;
		} else {

			String result = "";
			for (Bid bid : bids) {
				result += bid + "\n";
			}

			return result;
		}
	}

	@Override
	public String toString() {
		return String.format("Auction #%d: %s. Top bids: %s", auctionNumber, dog.getName(),
				bids.subList(0, Math.min(bids.size(), MAX_BIDS_IN_LIST)));
	}

}
