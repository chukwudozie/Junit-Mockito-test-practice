package emeka.business;

import emeka.TodoService;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoBusinessImplBDDMockTest {

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
    public void testRetrieveTodosRelatedToSpring_UsingBDDMock(){
        // Given

        List<String> expectedTodos = Arrays.asList("Learn Spring MVC", "Learn Spring boot security");
        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        //when
        List<String> actualTodos = todoBusiness.retrieveTodosRelatedToSpring("Dummy");


        //then
        assertThat(actualTodos, is(expectedTodos));
        assertThat(actualTodos.size(), is(expectedTodos.size()));

    }

}