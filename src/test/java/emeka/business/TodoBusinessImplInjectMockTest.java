package emeka.business;

import emeka.dataAPI.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplInjectMockTest {

    @Mock
    //this is same as todoServiceMock = mock(TodoService.class);
    private TodoService todoServiceMock;

    @InjectMocks
    //this injects the mocks needed by the TodoBusinessImpl automatically
    //this is the same as todoBusiness = new TodoBusinessImpl(todoServiceMock);
    private TodoBusinessImpl todoBusiness;
    private List<String> todos;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;



    @Before
    public void setup(){

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

    @Test
    public void testDeleteTodosNotRelatedToSpring_UsingVerifyMethod(){
        // Given

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

        //when
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        //then
        verify(todoServiceMock).deleteTodo("Learn Testing with Mockito");
        verify(todoServiceMock).deleteTodo("Learn Introduction to Java hibernate and JPA");
        verify(todoServiceMock).deleteTodo("Learn GitHub");
        //this checks that the deleteTodo method is not called when a specific todos contains Spring
        verify(todoServiceMock,never()).deleteTodo("Learn Spring MVC");

        // check the number of times a particular method is called
        verify(todoServiceMock,times(1)).deleteTodo("Learn Testing with Mockito");
        verify(todoServiceMock,atMostOnce()).deleteTodo("Learn Testing with Mockito");
    }

}