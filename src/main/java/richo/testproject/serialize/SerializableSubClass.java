package richo.testproject.serialize;

/**
 * Created by Richo on 2014-05-23.
 */
class SerializableSubClass extends SerializableBaseClass
{
	private final int integer;


	SerializableSubClass(String str, int integer)
	{
		super(str);
		this.integer = integer;
	}

	int getInteger()
	{
		return integer;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}

		SerializableSubClass that = (SerializableSubClass) o;

		if (integer != that.integer)
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + integer;
		return result;
	}

	@Override
	public String toString()
	{
		return "SerializableSubClass{" +
				"integer=" + integer +
				", String=" + getStr() +
				'}';
	}
}
