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
 * before calling this method.
 *
 * @param user The User object containing the login information to be saved.
 * @throws IllegalArgumentException if the user parameter is null.
 * @throws IOException if there is an error while committing the changes to system preferences.
 *
 * @example
 * val user = User("john_doe")
 * try {
 *     save(user)
 *     println("User login saved successfully.")
 * } catch (e: IllegalArgumentException) {
 *     println("Error: ${e.message}")
 * } catch (e: IOException) {
 *     println("Error saving user login: ${e.message}")
 * }
 */

    fun save(user: User) {
        systemPreferences.store("USER_KEY", user.login)
        systemPreferences.commit()
    }
/**
 * Retrieves the first user from the system preferences.
 *
 * This method reads the value associated with the key "USER_KEY" from the system preferences
 * and returns a `User` object initialized with that value.
 *
 * @return A `User` object representing the first user.
 * @throws IllegalArgumentException if the value retrieved from system preferences is null or empty,
 *         indicating that no user was found.
 *
 * @example
 * val user = findFirst()
 * println("The first user is: ${user.name}")
 */

    fun findFirst(): User = User(systemPreferences.read("USER_KEY"))
}

class FacadeTest {
    /**
     * This test method demonstrates the usage of the UserRepository class to save and retrieve a User object.
     *
     * It creates an instance of UserRepository, saves a User with the username "dbacinski",
     * and then retrieves the first stored user from the repository. The retrieved user is printed to the console.
     *
     * @throws IllegalArgumentException if the user cannot be saved due to invalid data.
     * @throws NoSuchElementException if no users are found in the repository when attempting to retrieve the first user.
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