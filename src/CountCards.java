/**
 * Card counting
 * 
 * @author Allen Han
 * @version December 1st, 2015
 */
public class CountCards {
	// The amount of each card in the deck
	private int[] amount;
	// The current total that the AI has
	private int currentTotal;

	public CountCards() {
		reset();
	}

	/**
	 * Resets all of the probabilities, this should be called each time the deck
	 * is shuffled
	 */
	public void reset() {
		amount = new int[11];
		for (int i = 0; i < amount.length; i++) {
			// 24 of each card in 6 decks
			amount[i] = 24;
		}
		// 24 of each card except 10 since all face cards count as 10
		amount[9] = 24 * 4; // 24 times Jacks, Queens, Kings, and 10s
	}

	/**
	 * Subtracts the card played from the amount of cards in the deck
	 * 
	 * @param card
	 *            the card that has been played
	 */
	public void countCard(int card) {
		// If the card is an Ace it's subtracted from 1 and 11 because it is
		// counted twice
		if (card == 11 || card == 1) {
			amount[0]--;
			amount[10]--;
			return;
		} else {
			amount[card - 1]--;
		}
	}

	/**
	 * Calculates the probability of busting
	 * 
	 * @return the probability of busting
	 */
	public double probabilityOfBusting(int currentTotal) {
		// The amount need to not bust
		int amountNeeded = 21 - currentTotal;
		// The total amount of cards left in the deck
		int totalLeft = 0;
		// The cards left in the deck that will make us bust
		int bust = 0;
		for (int i = 0; i < amount.length; i++) {
			// Total amount of cards
			totalLeft += amount[i];
			if (i < amountNeeded) {
				// Cards that will not make us bust
				bust += amount[i];
			}
		}
		// Cards that will make us bust
		bust = totalLeft - bust;
		// Subtract ace from the total and the probability of busting because it
		// is counted twice
		if (bust != 0)
			bust -= amount[10];
		totalLeft -= amount[10];
		// Returns the probability of busting
		return ((bust * 1.0) / (totalLeft * 1.0)) * 100.0;
	}

	public static void main(String[] args) {
		CountCards c = new CountCards();
		System.out.println(c.probabilityOfBusting(10));
	}
}
