package richo.testproject.streaming;


import com.google.common.io.Files;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.nio.ByteBuffer;

public class MovieSplitMain
{

	private static final int LENGTH = 100000;

	public static void main(String[] args) throws Exception
	{
		MovieSplitMain main = new MovieSplitMain();
		main.go();
	}

	private void go() throws Exception
	{
		System.out.println("Hello world");

		byte[] bytes = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("movie/testvid.h264"));

		System.out.println("read " + bytes.length + " bytes");

		ByteBuffer buffer = ByteBuffer.wrap(bytes);

		byte[] firstPath = new byte[LENGTH];

		buffer.get(firstPath, 0, firstPath.length);
		System.out.println("buffer size: " + buffer.remaining());

		byte[] middlePath = new byte[LENGTH];

		buffer.get(middlePath, 0, middlePath.length);
		System.out.println("buffer size: " + buffer.remaining());

		byte[] lastPath = new byte[buffer.remaining()];

		buffer.get(lastPath, 0, buffer.remaining() - 1);


		Files.write(firstPath, new File("first.h264"));
		Files.write(middlePath, new File("middle.h264"));
		Files.write(lastPath, new File("last.h264"));
	}
}
