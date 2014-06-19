package richo.testproject.other;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tjerngrr
 * Date: 2013-07-08
 * Time: 09:59
 * To change this template use File | Settings | File Templates.
 */
public class MockitoStuff
{
	private final List<String> list;

	public MockitoStuff(List<String> list)
	{
		this.list = list;
	}

	public void callListSizeOnce()
	{
		list.size();
	}

	public void callListAddOnce()
	{
		list.add("asd");
	}
}
