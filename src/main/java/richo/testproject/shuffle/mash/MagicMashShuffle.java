package richo.testproject.shuffle.mash;

class MagicMashShuffle
{
	public static void main(String[] args)
	{
		Deck deck = new Deck(100);

		deck.doCommanderMashShuffle(1);

		int howManyCardsInOriginalPosition = deck.getCardsInOriginalPosition();


		System.out.println("Cards in orignal position: " + howManyCardsInOriginalPosition);
	}


}
