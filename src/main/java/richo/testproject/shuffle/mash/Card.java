package richo.testproject.shuffle.mash;

/**
 * Created by tjerngrr on 2014-02-12.
 */
class Card
{
	private final int index;

	public Card(int index)
	{
		this.index = index;
	}

	public int getIndex()
	{
		return index;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Card card = (Card) o;

		if (index != card.index) return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		return index;
	}

	@Override
	public String toString()
	{
		return "Card[" + index + "]";
	}
}
