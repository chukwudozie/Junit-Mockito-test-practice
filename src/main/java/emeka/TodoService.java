package emeka;

import java.util.List;

public interface TodoService {
    List<String>retrieveTodos(String user);

    default List<String> retrieveTodos2(String user) {
         return List.of("Linux","Learn Spring", "Learn JPA");
    }

    void deleteTodo(String todo);
}
