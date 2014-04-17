package richo.testproject.shuffle.mash;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * Created by tjerngrr on 2014-02-12.
 */
class Deck
{
	private final List<Card> cards;
	private final Random rand = new Random();

	Deck(int size)
	{
		cards = new ArrayList<>();
		for (int i = 0; i < size; i++)
		{
			cards.add(new Card(i));
		}
	}

	int getCardsInOriginalPosition()
	{
		int count = 0;
		for (int i = 0; i < cards.size(); i++)
		{
			if (cards.get(i).getIndex() == i)
			{
				count++;
			}
		}
		return count;
	}

	public void doCommanderMashShuffle(int amount)
	{
		for (int i = 0; i < amount; i++)
		{
			doOneCommanderMashShuffle();
		}
	}

	private void doOneCommanderMashShuffle()
	{
		List<Card> leftHand = takeWithLeftHand(cards);

		int indexFromRightToInsertInto = 0;

		while (CollectionUtils.isNotEmpty(leftHand))
		{
			int numberOfCardsToTakeFromLeftHand = getNumberOfCardsToTakeFromLeftHand(leftHand);

			List<Card> cardsToInsert = takeCardsFromLeftHandToInsert(leftHand, numberOfCardsToTakeFromLeftHand);

			indexFromRightToInsertInto += getIndexFromRightToInsertInto();

			insertCardsAtPositionFromRight(cards, cardsToInsert, indexFromRightToInsertInto);

			indexFromRightToInsertInto += numberOfCardsToTakeFromLeftHand;
		}

	}

	private int getNumberOfCardsToTakeFromLeftHand(List<Card> leftHand)
	{
		int number = rand.nextInt(4);
		if (number > leftHand.size())
		{
			return leftHand.size();
		}
		return number;
	}

	private int getIndexFromRightToInsertInto()
	{
		return rand.nextInt(4);
	}

	private List<Card> takeWithLeftHand(List<Card> cards)
	{
		int cardstoTake = 10 + rand.nextInt(20);
		List<Card> result = new ArrayList<>();
		for (int i = 0; i < cardstoTake; i++)
		{
			result.add(cards.remove(0));
		}
		cards.removeAll(result);
		return result;
	}

	private List<Card> takeCardsFromLeftHandToInsert(List<Card> leftHand, int numberOfCardsToTakeFromLeftHand)
	{
		List<Card> result = new ArrayList<>();
		for (int i = 0; i < numberOfCardsToTakeFromLeftHand; i++)
		{
			result.add(leftHand.remove(leftHand.size() - 1));
		}
		leftHand.removeAll(result);
		return result;
	}

	private void insertCardsAtPositionFromRight(List<Card> cards, List<Card> cardsToInsert, int indexFromRightToInsertInto)
	{
		Collections.reverse(cardsToInsert); //to insert them in the right order
		cards.addAll(cards.size() - indexFromRightToInsertInto, cardsToInsert);
	}

	@Override
	public String toString()
	{
		return "Deck{" +
				"cards=" + cards +
				'}';
	}

	public void printShuffleInformation()
	{
		Map<Integer, Integer> mapOfRanges = new HashMap<>();
		int currentBiggestNumberInRange = 0;
		int rangeLength = 0;
		for (Card currentCard : cards)
		{
			rangeLength++;
			if (currentBiggestNumberInRange == 0)
			{
				currentBiggestNumberInRange = currentCard.getIndex();
				continue;
			}

			if (currentCard.getIndex() == currentBiggestNumberInRange + 1)
			{
				currentBiggestNumberInRange++;
				continue;
			}

			if (!mapOfRanges.containsKey(rangeLength))
			{
				mapOfRanges.put(rangeLength, 0);
			}

			mapOfRanges.put(rangeLength, mapOfRanges.get(rangeLength) + 1);

			//reset and look for a new range
			currentBiggestNumberInRange = 0;
			rangeLength = 0;
		}

		System.out.println("Result:");
		for (Integer key : mapOfRanges.keySet())
		{
			System.out.println("there are " + mapOfRanges.get(key) + " ranges that are " + key + " long");
		}

	}
}
