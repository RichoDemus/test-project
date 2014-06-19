package richo.testproject.streaming;

import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.tracks.H264TrackImpl;
import org.apache.commons.io.IOUtils;
import spark.Spark;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Richo on 2014-05-29.
 */
public class MovieStreamer
{
	private byte[] movie;

	public static void main(String[] args) throws IOException
	{
		MovieStreamer main = new MovieStreamer();
		main.go();
	}

	private void go() throws IOException
	{
		/*
		H264TrackImpl h264Track = new H264TrackImpl(new FileInputStream("movie/testvid.h264"));
		    Movie m = new Movie();
		    m.addTrack(h264Track);
*/
		movie = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("movie/testvid.h264"));

		Spark.setPort(80);

		Spark.get("/", (request, response) ->
		{
			return "<html><body><h1>Hello</h1><br/><video width=\"320\" height=\"240\" controls>\n" +
								"  <source src=\"stream\" type=\"video/mp4\">\n" +
								//"  <source src=\"movie.ogg\" type=\"video/ogg\">\n" +
								"Your browser does not support the video tag.\n" +
								"</video></body></html>";
		});

		Spark.get("/stream", (request, response) ->
		{
			response.type("video/mp4");
			try
			{
				response.raw().getOutputStream().write(movie);
				response.raw().getOutputStream().flush();
				response.raw().getOutputStream().close();
				return null;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return null;
		});

	}
}
