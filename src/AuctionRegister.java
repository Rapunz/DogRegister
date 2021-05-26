import java.util.ArrayList;



public class AuctionRegister {
	private final ArrayList<Auction> auctions = new ArrayList<>();

	public void add(Dog dog) {
		auctions.add(new Auction(dog));
	}

	public void remove(Auction auction) {
		auctions.remove(auction);
	}

	public void remove(Dog dog) {
		remove(find(dog));
	}

	public void close(Auction auction) {
		auction.appointWinner();
		remove(auction);
	}

	public void removeAllBidsBy(User bidder) {
		for (Auction auction : auctions) {
			auction.removeBid(bidder);
		}
	}

	public Auction find(Dog dog) {
		for (Auction auction : auctions) {
			if (auction.getDog() == dog) {
				return auction;
			}
		}
		return null;
	}

	public boolean isEmpty() {
		return auctions.isEmpty();
	}

	@Override
	public String toString() {
		String result = "";
		for (Auction auction : auctions) {
			result += auction + "\n";
		}

		return result;
	}
}
