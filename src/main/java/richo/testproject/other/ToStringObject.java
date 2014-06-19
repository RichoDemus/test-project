package richo.testproject.other;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tjerngrr
 * Date: 2013-03-19
 * Time: 11:00
 * To change this template use File | Settings | File Templates.
 */
public class ToStringObject
{
	private String string;
	private int integer;
	private List<?> list;
	private String[] stringArray;

	public ToStringObject(String string, int integer, List<?> list, String[] stringArray)
	{
		this.string = string;
		this.integer = integer;
		this.list = list;
		this.stringArray = stringArray;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
