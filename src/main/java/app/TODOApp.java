package app;

import java.util.*;
import java.util.stream.Collectors;

class TODOApp {
	// dependencies
	private final TODORepository todoRepo;
	private final PersonRepository personRepo;

	public TODOApp(TODORepository todoService, PersonRepository personService) {
		this.todoRepo = todoService;
		this.personRepo = personService;
	}

	/**
	 * Searches all the ToDos related to a user. Select only todos that contain the
	 * keyword. It also updates the last visualization date of that todo.
	 *
	 * @param userId
	 *            the id of the user
	 * @param keyword
	 *            keyword to be searched for
	 * @return list of todos
	 */
	public List<String> retrieveTodos(Long userId, String keyword) {
		String user = personRepo.findUsernameById(userId);
		List<String> allTodos = todoRepo.retrieveTodos(user);
		return allTodos.stream().filter(t -> t.contains(keyword)).collect(Collectors.toList());
	}

	/**
	 * Adds a todo for a user based on their id.
	 *
	 * @param userId
	 *            the id of the
	 * @param newTodo
	 *            the new todo
	 * @return true, if the todo was successfully added
	 */
	public boolean addTodo(Long userId, String newTodo) {
		String user = personRepo.findUsernameById(userId);
		return todoRepo.addTodo(user, newTodo);
	}

	/**
	 * Completes all ToDos from a specific user.
	 *
	 * @param userId
	 *            the id of the user
	 */
	public void completeAllTodos(Long userId) {
		String user = personRepo.findUsernameById(userId);
		List<String> allTodos = todoRepo.retrieveTodos(user);
		for (String todo : allTodos) {
			todoRepo.completeTodo(todo);
		}
	}


}
