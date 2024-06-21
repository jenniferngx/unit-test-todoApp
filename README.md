# Project: Test doubles

I am testing a simple TODO-list management application that allows for searching by keyword, adding a todo, and completing all the todos for a user with a specific ID.

The domain class is `TODOApp.java`. The two port classes `PersonRepository.java` and `TODORepository.java` each abstracts a DAO (data access object) that links to a different table belonging to the same database for the app.

To test the domain code in `TODOApp.java`, I have written my unit tests in `TODOAppTest.java`.
