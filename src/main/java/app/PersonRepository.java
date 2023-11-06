package app;

/**
 * The port that abstracts the operations on the Person table in the database.
 */
interface PersonRepository {
	String findUsernameById(Long id);
}