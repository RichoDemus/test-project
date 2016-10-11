package richo.testproject.json_array_parse.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bucket
{
	public final String name;

	@JsonCreator
	public Bucket(@JsonProperty("name") final String name)
	{
		this.name = name;
	}
}
