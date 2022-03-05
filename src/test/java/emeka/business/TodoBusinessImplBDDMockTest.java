package emeka.business;

import emeka.TodoService;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplBDDMockTest {

    @Mock
    private  TodoService todoServiceMock;

    @InjectMocks
    private TodoBusinessImpl todoBusiness;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    private List<String> todos;
    private List<String> todos1;


    @Before
    public void setup(){
//         todoServiceMock = mock(TodoService.class);
//         todoBusiness = new TodoBusinessImpl(todoServiceMock);
         todos = Arrays.asList("Learn Spring MVC", "Learn Testing with Mockito", "Learn Spring boot security","Learn Introduction to Java hibernate and JPA", "Learn GitHub");
        todos1 = Arrays.asList("Learn Spring MVC", "Learn Testing with Mockito", "Learn Spring boot security", "Learn Spring GitHub");

        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);


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

    @Test
    public void testDeleteTodosNotRelatedToSpring_UsingVerifyMethod(){
        // Given

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

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

    @Test
    public void testDeleteTodosNotRelatedToSpring_BDD(){
        // Given

        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

        //when
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        //then
        then(todoServiceMock).should().deleteTodo("Learn Testing with Mockito");
        then(todoServiceMock).should().deleteTodo("Learn Introduction to Java hibernate and JPA");
        then(todoServiceMock).should().deleteTodo("Learn GitHub");

        //this checks that the deleteTodo method is not called when a specific todos contains Spring
        then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");

        // check the number of times a particular method is called
        then(todoServiceMock).should(times(1)).deleteTodo("Learn Testing with Mockito");
        then(todoServiceMock).should(atMostOnce()).deleteTodo("Learn Testing with Mockito");

    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_BDD_ArgumentCapture(){
        //Declare Argument Captor
//        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        // Given
        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos1);
        //when
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");
        //then
        //Declare Argument captor on a specific method
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture()); //"Learn Testing with Mockito"
        //Capture the argument
        assertThat(stringArgumentCaptor.getValue(),is("Learn Testing with Mockito"));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpring_BDD_ManyArgumentCapture(){
        List<String> expected = Arrays.asList("Learn Testing with Mockito","Learn Introduction to Java hibernate and JPA", "Learn GitHub");
        //Declare Argument Captor
//        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        // Given
        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
        //when
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");
        //then
        //Declare Argument captor on a specific method
        then(todoServiceMock).should(times(3)).deleteTodo(stringArgumentCaptor.capture()); //"Learn Testing with Mockito"
        //Capture the argument
        assertThat(stringArgumentCaptor.getAllValues(),is(expected));
        assertArrayEquals(stringArgumentCaptor.getAllValues().toArray(),expected.toArray());
        assertThat(stringArgumentCaptor.getAllValues().size(),is(3));

    }

}