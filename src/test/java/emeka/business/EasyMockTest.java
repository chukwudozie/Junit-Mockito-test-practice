package emeka.business;

import org.easymock.EasyMockRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(EasyMockRunner.class)
public class EasyMockTest {

    // Mocks return default values

    @Test
    public void testArrayListMock(){
        List<String> listMock = mock(ArrayList.class);

        assertEquals(0,listMock.size());
//        stub(listMock.size()).toReturn(5);
        when(listMock.size()).thenReturn(5);
        listMock.add("Emmy");
        assertEquals(5,listMock.size());

    }

    @Test
    public void testArrayList_EasyMock(){

        List mock = createNiceMock(List.class);
//        expect(mock.size()).andStubReturn(5);
        expect(mock.get(0)).andStubReturn("Emeka");
        expect(mock.get(1)).andStubReturn("r");
        mock.clear();
        replay(mock);
        System.out.println(mock +" test");

        assertEquals("Emeka",mock.get(0));
        assertEquals("r",mock.get(1));

    }

    @Test
    //spy can be used to test specific functionality of a method in a class
    public void testArrayListSpy(){
        List<String> listMockSpy = spy(ArrayList.class);
        assertEquals(0,listMockSpy.size());
        listMockSpy.add("Emmy");
        assertEquals(1,listMockSpy.size());
        listMockSpy.remove(0);
        assertEquals(0,listMockSpy.size());
        verify(listMockSpy).add("Emmy");
        verify(listMockSpy).remove(0);
        verify(listMockSpy,never()).isEmpty();

    }
}
