import org.junit.jupiter.api.Test

class ComplexSystemStore(private val filePath: String) {

    private val cache: HashMap<String, String>

    init {
        println("Reading data from file: $filePath")
        cache = HashMap()
        //read properties from file and put to cache
    }

    fun store(key: String, payload: String) {
        cache[key] = payload
    }

    fun read(key: String): String = cache[key] ?: ""

    fun commit() = println("Storing cached data: $cache to file: $filePath")
}

data class User(val login: String)

//Facade:
class UserRepository {

    private val systemPreferences = ComplexSystemStore("/data/default.prefs")

    /**
     * Saves the login information of the specified user to the system preferences.
     *
     * This method stores the user's login under the key "USER_KEY" and commits the changes
     * to the system preferences. It is important to ensure that the user object is not null
     * and contains valid login information before calling this method.
     *
     * @param user The User object containing the login information to be saved.
     * @throws IllegalArgumentException if the user is null or if the user's login is empty.
     * @throws SecurityException if there is a security violation while accessing system preferences.
     *
     * @example
     * val user = User("john_doe")
     * try {
     *     save(user)
     *     println("User login saved successfully.")
     * } catch (e: IllegalArgumentException) {
     *     println("Error: ${e.message}")
     * } catch (e: SecurityException) {
     *     println("Security error: ${e.message}")
     * }
     */
    fun save(user: User) {
        systemPreferences.store("USER_KEY", user.login)
        systemPreferences.commit()
    }

    fun findFirst(): User = User(systemPreferences.read("USER_KEY"))
}

class FacadeTest {

    /**
     * Tests the functionality of the UserRepository facade.
     *
     * This test creates a new user, saves it to the repository, and then retrieves
     * the first user from the repository to verify that the save operation was successful.
     *
     * @throws IllegalArgumentException if the user cannot be saved due to invalid data.
     * @throws NoSuchElementException if no users are found in the repository when attempting to retrieve one.
     *
     * Example usage:
     * ```
     * @Test
     * fun testUserRepository() {
     *     val userRepository = UserRepository()
     *     val user = User("dbacinski")
     *     userRepository.save(user)
     *     val resultUser = userRepository.findFirst()
     *     println("Found stored user: $resultUser")
     * }
     * ```
     */
    @Test
    fun Facade() {
        val userRepository = UserRepository()
        val user = User("dbacinski")
        userRepository.save(user)
        val resultUser = userRepository.findFirst()
        println("Found stored user: $resultUser")
    }
}