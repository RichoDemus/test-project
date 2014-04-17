package richo.testproject.shuffle.mash;

class MagicMashShuffle
{
	public static void main(String[] args)
	{
		Deck deck = new Deck(100);

		System.out.println("Before shuffle: " + deck);

		deck.doCommanderMashShuffle(10);

		int howManyCardsInOriginalPosition = deck.getCardsInOriginalPosition();

		deck.printShuffleInformation();

		System.out.println("After shuffle: " + deck);
		System.out.println("Cards in orignal position: " + howManyCardsInOriginalPosition);
	}


}
