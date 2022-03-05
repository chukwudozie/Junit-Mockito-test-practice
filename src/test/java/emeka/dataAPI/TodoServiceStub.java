package emeka.dataAPI;

import emeka.TodoService;

import java.util.Arrays;
import java.util.List;

/**
 * A stub service Implementation class is like the dummy of the class. It is not the main thing but
 * Tries to behave as the main class
 */
public class TodoServiceStub implements TodoService {

    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring MVC", "Learn Testing with Mockito",
          "Learn Spring boot security","Learn Introduction to Java hibernate and JPA", "Learn GitHub");
    }

    @Override
    public void deleteTodo(String todo) {

    }
}
