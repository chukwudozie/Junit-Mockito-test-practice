package emeka.business;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class SpyTest {



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
