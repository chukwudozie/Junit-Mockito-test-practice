package emeka.business;

import emeka.dataAPI.TodoService;
import emeka.dataAPI.TodoServiceStub;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TodoBusinessImplStubTest {

    private  TodoService todoService;
    private TodoBusinessImpl todoBusiness;

    @Before
    public void setup(){
         todoService = new TodoServiceStub();
         todoBusiness = new TodoBusinessImpl(todoService);


    }

    @Test
    public void testRetrieveTodosRelatedToSpring_UsingAStub(){
        List<String> actualTodos = todoBusiness.retrieveTodosRelatedToSpring("Emeka");
        List<String> expectedTodos = Arrays.asList("Learn Spring MVC", "Learn Spring boot security");

        assertEquals("the elements of the two must be the same",expectedTodos,actualTodos);
        assertEquals(2,actualTodos.size());
    }

}