package richo.testproject.other;

import org.junit.Test;
import richo.testproject.other.ToStringObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tjerngrr
 * Date: 2013-03-19
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */
public class ToStringObjectTest
{
	@Test
	public void testToString() throws Exception
	{
		Map<Object, Object> map = new HashMap<>();
		map.put("key", "value");
		map.put("list", Arrays.asList(new Object[]{"maliststring2", "maliststring2"}));

		ToStringObject obj = new ToStringObject(
				"String",
				1,
				Arrays.asList(new Object[]{"listString1", map}),
				new String[]{"arrayString1", "arrayString2"});
		System.out.println(obj);
	}
}
