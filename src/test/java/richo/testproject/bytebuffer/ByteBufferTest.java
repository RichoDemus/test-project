package richo.testproject.bytebuffer;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by Richo on 2014-05-23.
 */
public class ByteBufferTest
{

	private static final int INT_LENGTH = Integer.BYTES;
	private static final int LONG_LENGTH = Long.BYTES;

	@Test
	public void testByteBuffer() throws Exception
	{
		String msg = "Hello world!";

		ByteBuffer buffer = ByteBuffer.allocate(INT_LENGTH + LONG_LENGTH + msg.length());

		buffer.putInt(msg.length());

		long id = 123456L;
		buffer.putLong(id);

		buffer.put(msg.getBytes());

		byte[] rawBytes = buffer.array();

		InputStream is = new ByteArrayInputStream(rawBytes);

		byte[] lengthBytes = new byte[INT_LENGTH];

		is.read(lengthBytes,0, INT_LENGTH);

		int readLength = ByteBuffer.wrap(lengthBytes).getInt();

		byte[] idBytes = new byte[LONG_LENGTH];

		is.read(idBytes, 0, LONG_LENGTH);

		long readId = ByteBuffer.wrap(idBytes).getLong();

		byte[] msgBytes = new byte[readLength];

		is.read(msgBytes,0, readLength);

		String readMsg = new String(msgBytes);

		Assert.assertEquals("Read the wrong length", msg.length(), readLength);
		Assert.assertEquals("Read the wrong id", id, readId);
		Assert.assertEquals("Read the wrong message", msg, readMsg);


	}
}
