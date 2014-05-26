package richo.testproject.spark;

import spark.Spark;

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
		Spark.get("/", (request, response) -> "<html>\n\t<body>\n\t\t<h1>Hello World!</h1>\n\t</body>\n</html>");

	}

}
