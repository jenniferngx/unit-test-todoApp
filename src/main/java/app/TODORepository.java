package app;

import java.util.List;

/** The port that abstracts the  operations on the TODO table in the database. */

interface  TODORepository {
    
    List<String> retrieveTodos(String username);

	boolean addTodo(String username, String newTodo);

	void completeTodo(String todo);
    
}
