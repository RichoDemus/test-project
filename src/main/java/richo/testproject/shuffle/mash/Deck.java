package richo.testproject.shuffle.mash;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tjerngrr on 2014-02-12.
 */
class Deck
{
	private static final int LEFT_HAND_SIZE = 20;
	//private final Card[] cards;
	private final List<Card> cards;

	Deck(int size)
	{
		cards = new ArrayList<>();
		for(int i = 0; i < cards.size(); i++)
		{
			cards.add(new Card(i));
		}
	}

	int getCardsInOriginalPosition()
	{
		int count = 0;
		for(int i = 0; i < cards.size(); i++)
		{
			if(cards.get(i).getIndex() == i)
			{
				count++;
			}
		}
		return count;
	}

	public void doCommanderRiffle(int amount)
	{
		for(int i = 0; i < amount; i++)
		{
			doOneCommanderRiffleShuffle();
		}
	}

	private void doOneCommanderRiffleShuffle()
	{
		List<Card> leftHand = cards.subList(0, LEFT_HAND_SIZE);
		cards.removeAll(leftHand);

	}
}
