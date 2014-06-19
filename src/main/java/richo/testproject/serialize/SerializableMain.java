package richo.testproject.serialize;

import java.io.*;

/**
 * Created by Richo on 2014-05-23.
 */
class SerializableMain
{
	public static void main(String[] args) throws Exception
	{
		SerializableMain main = new SerializableMain();
		main.doStuff();
	}

	private void doStuff() throws Exception
	{
		SerializableBaseClass mainClass = new SerializableBaseClass("Hello world");
		SerializableSubClass subClass = new SerializableSubClass("Hello world", 1337);

		SerializableBaseClass baseClass2 = deserialize(serialize(mainClass));
		SerializableSubClass subClass2 = (SerializableSubClass) deserialize(serialize(subClass));

		if(!subClass.equals(subClass2))
		{
			System.out.println("Error, subclass not equal");
		}

		if(!mainClass.equals(baseClass2))
		{
			System.out.println("Error, baseclass not equal");
		}
	}

	private byte[] serialize(SerializableBaseClass object) throws Exception
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(object);
		return bos.toByteArray();
	}

	private SerializableBaseClass deserialize(byte[] bytes) throws Exception
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = new ObjectInputStream(bis);
		return (SerializableBaseClass) in.readObject();
	}
}
