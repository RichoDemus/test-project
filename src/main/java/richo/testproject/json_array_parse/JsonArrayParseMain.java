package richo.testproject.json_array_parse;

import com.fasterxml.jackson.databind.ObjectMapper;
import richo.testproject.json_array_parse.domain.Bucket;

import java.io.IOException;
import java.util.Arrays;

public class JsonArrayParseMain
{
	public static void main(String[] args) throws IOException
	{
		final String data = "[{\"name\":\"bucketName\",\"someField\":[\"asd\",\"qwe\"]},{\"name\":\"bucketName2\",\"someField\":[\"23\"]}]";
		final Bucket[] result = new ObjectMapper().readValue(data, Bucket[].class);

		Arrays.stream(result).map(b -> b.name).forEach(System.out::println);
	}
}
