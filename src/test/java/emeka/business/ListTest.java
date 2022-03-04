package emeka.business;

import org.junit.Test;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void testListSizeMock(){
        List<?> listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2,listMock.size());
    }

    @Test
    public void testListSizeMock_MultipleValues(){
        List<String> listMock = mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);
        assertEquals(2,listMock.size());
        assertEquals(3,listMock.size());

    }

    @Test
    public void testListSizeMock_GetValues(){
        List listMock = mock(List.class);
        // Use of argument Matchers
        when(listMock.get(anyInt())).thenReturn("Emeka");
        assertEquals("Emeka",listMock.get(0));
        assertEquals("Emeka",listMock.get(1));
    }

    @Test(expected = RuntimeException.class)
    public void testListSizeMock_ThrowException(){
        List listMock = mock(List.class);
        // Use of argument Matchers
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
        assertEquals("Emeka",listMock.get(0));
        assertEquals("Emeka",listMock.get(1));
    }

    @Test(expected = InvalidUseOfMatchersException.class)
    public void testListSizeMock_MixingUp(){
        List listMock = mock(List.class);
        // Use of argument Matchers
        when(listMock.subList(anyInt(),5)).thenReturn(List.of("Emeka"));
        assertEquals("Emeka",listMock.get(0));
        assertEquals("Emeka",listMock.get(1));
    }

    @Test
    public void testListSizeMock_UnstubedMethod(){
        List listMock = mock(List.class);
        // Use of argument Matchers
     when(listMock.get(anyInt())).thenReturn("Emeka");
     String expectedObject = "Emeka";
    assertEquals(null,listMock.remove(1));
    // Any Unstubbed List method returns a null value
    }
}
