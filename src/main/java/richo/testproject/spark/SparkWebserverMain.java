package richo.testproject.spark;

import spark.Spark;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Class to test the java web framework Spark
 */
public class SparkWebserverMain
{
	//public variables

	//protected variables

	//package (no modifier) variables

	//private variables

	//constructors

	//methods

	public static void main(String[] args)
	{
		Spark.setPort(80);

		Spark.get("/", (request, response) ->
				"<!DOCTYPE HTML>\n" +
						"<html>\n" +
						"<script>\n" +
						"window.onload = function()\n" +
						"{\n" +
						"console.log('Hello world');\n" +
						"};\n" +
						"</script>\n" +
						"<body>\n" +
						"    Time: <span id=\"foo\"></span>\n" +
						"     \n" +
						"    <br><br>\n" +
						"    <button onclick=\"start()\">Start</button>\n" +
						" \n" +
						"    <script type=\"text/javascript\">\n" +
						"    function start() {\n" +
						" \n" +
						"        var eventSource = new EventSource(\"HelloServlet\");\n" +
						"         \n" +
						"        eventSource.onmessage = function(event) {\n" +
						"         \n" +
						"            document.getElementById('foo').innerHTML = event.data;\n" +
						"         \n" +
						"        };\n" +
						"         \n" +
						"    }\n" +
						"    </script>\n" +
						"</body>\n" +
						"</html>");

		Spark.get("/HelloServlet", (request, response) ->
		{
			System.out.println("Hello servlet called");
			response.type("text/event-stream");

			try
			{
				PrintWriter writer = new PrintWriter(response.raw().getOutputStream());

				for (int i = 0; i < 10; i++)
				{

					writer.write("data: " + System.currentTimeMillis() + "\n\n");
					writer.flush();
					System.out.println("Sending data..");

					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("Done");
			return "";
		});

	}

}
