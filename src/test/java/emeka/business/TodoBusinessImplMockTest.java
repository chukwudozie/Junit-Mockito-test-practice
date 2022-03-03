package emeka.business;

import emeka.TodoService;
import emeka.TodoServiceStub;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoBusinessImplMockTest {

    private  TodoService todoServiceMock;
    private TodoBusinessImpl todoBusiness;
    private List<String> todos;


    @Before
    public void setup(){
         todoServiceMock = mock(TodoService.class);
         todoBusiness = new TodoBusinessImpl(todoServiceMock);
         todos = Arrays.asList("Learn Spring MVC", "Learn Testing with Mockito", "Learn Spring boot security","Learn Introduction to Java hibernate and JPA", "Learn GitHub");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
        when(todoServiceMock.retrieveTodos("Dummy1")).thenReturn(List.of());
        when(todoServiceMock.retrieveTodos(null)).thenThrow(NullPointerException.class);
        when(todoServiceMock.retrieveTodos2("Dummy2")).thenCallRealMethod();


    }

    @Test
    public void testRetrieveTodosRelatedToSpring_UsingAMock(){
        List<String> actualTodos = todoBusiness.retrieveTodosRelatedToSpring("Dummy");
        List<String> expectedTodos = Arrays.asList("Learn Spring MVC", "Learn Spring boot security");
        assertEquals("the elements of the two must be the same",expectedTodos,actualTodos);
        assertEquals(expectedTodos.size(),actualTodos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_UsingEmptyList(){
        List<String> actualTodos = todoBusiness.retrieveTodosRelatedToSpring("Dummy1");
        List<String> expectedTodos = List.of();
        assertEquals("the elements of the two must be the same",expectedTodos,actualTodos);
        assertEquals(0,actualTodos.size());
    }

    @Test(expected = NullPointerException.class)
    public void testRetrieveTodosRelatedToSpring_UsingNullList(){
        List<String> actualTodos = todoBusiness.retrieveTodosRelatedToSpring(null);
        List<String> expectedTodos = List.of();
        assertEquals("the elements of the two must be the same",expectedTodos,actualTodos);
        assertEquals(0,actualTodos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_RandomTest(){
        List<String> actualTodos = todoBusiness.retrieveTodosRelatedToSpring2("Dummy2");
        List<String> expectedTodos = List.of("Learn Spring");
        assertEquals("the elements of the two must be the same",expectedTodos,actualTodos);
        assertEquals(expectedTodos.size(),actualTodos.size());
    }

}