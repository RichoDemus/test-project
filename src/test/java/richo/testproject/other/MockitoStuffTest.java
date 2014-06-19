package richo.testproject.other;

import org.junit.Test;
import org.mockito.Mockito;
import richo.testproject.other.MockitoStuff;

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
