package richo.testproject;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;


public class MockitoStuffTest
{
	@Test
	public void testCallListSizeOne() throws Exception
	{
		List<String> mockedList = Mockito.mock(List.class);


		Mockito.verifyNoMoreInteractions(Mockito.ignoreStubs(mockedList));

		MockitoStuff stuff = new MockitoStuff(mockedList);

		stuff.callListSizeOnce();
		//stuff.callListAddOnce(); it works

		Mockito.verify(mockedList).size();
		Mockito.verifyNoMoreInteractions(mockedList);

	}
}
