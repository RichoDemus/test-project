package richo.testproject.serialize;

import java.io.Serializable;

/**
 * Created by Richo on 2014-05-23.
 */
class SerializableBaseClass implements Serializable
{
	private final String str;

	SerializableBaseClass(String str)
	{
		this.str = str;
	}

	String getStr()
	{
		return str;
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

		SerializableBaseClass that = (SerializableBaseClass) o;

		if (!str.equals(that.str))
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		return str.hashCode();
	}

	@Override
	public String toString()
	{
		return "SerializableBaseClass{" +
				"str='" + str + '\'' +
				'}';
	}
}
