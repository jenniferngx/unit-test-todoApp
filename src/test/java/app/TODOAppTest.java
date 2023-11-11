package app;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;
// import java.util.*;
// import java.util.stream.*;
import org.junit.jupiter.api.*;
// import org.junit.jupiter.params.*;
// import org.junit.jupiter.params.provider.*;
// import org.mockito.*;
import org.mockito.Mockito;
import java.util.List;

class TODOAppTest {

	private final Long userId = 42L;
	private final String username = "tony-stark";
	private List<String> todos = List.of("Find all the infinity stones.", "Snap your fingers.", "Save the world.");
	private static TODOApp todoApp;
    private static TODORepository todoRepo;
    private static PersonRepository personRepo;
	
	@BeforeAll
	static void setup() {
		// TODO
		todoRepo = Mockito.mock(TODORepository.class);
        personRepo = Mockito.mock(PersonRepository.class);
        todoApp = new TODOApp(todoRepo, personRepo);
    }	
	

	@Test
	void retrieveTodosTest() {
		// TODO 
		Mockito.when(personRepo.findUsernameById(userId)).thenReturn(username);
        Mockito.when(todoRepo.retrieveTodos(username)).thenReturn(todos);
        List<String> result = todoApp.retrieveTodos(userId, "infinity");
        assertThat(result).contains("Find all the infinity stones.");
        assertThat(result).doesNotContain("Snap your fingers.", "Save the world.");	
	}

	@Test
	void addTodoTest() {
        Mockito.when(personRepo.findUsernameById(userId)).thenReturn(username);
        Mockito.when(todoRepo.addTodo(username, "Beat Thanos")).thenReturn(true);
        boolean added = todoApp.addTodo(userId, "Beat Thanos");
        assertTrue(added);
	}

	@Test
	void completeAllTodosTest() {
        Mockito.when(personRepo.findUsernameById(userId)).thenReturn(username);
        Mockito.when(todoRepo.retrieveTodos(username)).thenReturn(todos);
        todoApp.completeAllTodos(userId);
        Mockito.verify(todoRepo, Mockito.times(3)).completeTodo(Mockito.anyString());
    }
	

	@Test
	void completeAllTodosWithNoNotesTest() {
		Mockito.when(personRepo.findUsernameById(userId)).thenReturn(username);
		Mockito.when(todoRepo.retrieveTodos(username)).thenReturn(List.of());
		todoApp.completeAllTodos(userId);
		Mockito.verify(todoRepo, Mockito.never()).completeTodo(null);
	}

	@Test
	void completeAllTodosOneNoteTest() {
        Mockito.when(personRepo.findUsernameById(userId)).thenReturn(username);
        Mockito.when(todoRepo.retrieveTodos(username)).thenReturn(List.of("Task"));
        todoApp.completeAllTodos(userId);
        Mockito.verify(todoRepo, Mockito.times(1)).completeTodo("Task");
    }


	@AfterAll
	static void cleanup() {
		// TODO
		Mockito.reset(todoRepo);
		Mockito.reset(personRepo);
	}
}
