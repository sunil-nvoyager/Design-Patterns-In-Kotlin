import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

sealed class AuthorizationState

object Unauthorized : AuthorizationState()

class Authorized(val userName: String) : AuthorizationState()

class AuthorizationPresenter {

    private var state: AuthorizationState = Unauthorized

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is Unauthorized -> false
        }

    val userName: String
        get() {
            return when (val state = this.state) { //val enables smart casting of state
                is Authorized -> state.userName
                is Unauthorized -> "Unknown"
            }
        }
/**
 * Logs in a user with the specified username.
 *
 * This function updates the application state to indicate that the user
 * has been authorized. It takes a username as a parameter and sets the
 * state to an instance of `Authorized` with the provided username.
 *
 * @param userName The username of the user to be logged in. Must not be empty.
 * @throws IllegalArgumentException if the provided username is empty.
 *
 * @example
 * // Example usage of loginUser function
 * try {
 *     loginUser("john_doe")
 * } catch (e: IllegalArgumentException) {
 *     println("Error: ${e.message}")
 * }
 */

    fun loginUser(userName: String) {
        state = Authorized(userName)
    }
/**
 * Logs out the current user by changing the state to Unauthorized.
 *
 * This method updates the application's state to reflect that the user is no longer authenticated.
 * It is typically called when a user chooses to log out of the application.
 *
 * @throws IllegalStateException if the user is not currently logged in.
 *
 * ## Example
 * ```
 * // Assuming the user is logged in
 * try {
 *     logoutUser()
 *     println("User has been logged out successfully.")
 * } catch (e: IllegalStateException) {
 *     println("Error: ${e.message}")
 * }
 * ```
 */

    fun logoutUser() {
        state = Unauthorized
    }

    override fun toString() = "User '$userName' is logged in: $isAuthorized"
}

class StateTest {
    /**
     * Tests the login and logout functionality of the AuthorizationPresenter class.
     *
     * This test verifies that a user can successfully log in with valid credentials,
     * and that the state of the AuthorizationPresenter reflects the correct authorization status
     * and username after login and logout operations.
     *
     * It performs the following steps:
     * 1. Creates an instance of AuthorizationPresenter.
     * 2. Logs in a user with the username "admin".
     * 3. Asserts that the user is authorized and the username is correctly set.
     * 4. Logs out the user.
     * 5. Asserts that the user is no longer authorized and the username is reset to "Unknown".
     *
     * @throws IllegalArgumentException if the login credentials are invalid.
     * @throws IllegalStateException if an operation is attempted on an unauthorized user.
     *
     * Example usage:
     * ```
     * @Test
     * fun testAuthorization() {
     *     val authorizationPresenter = AuthorizationPresenter()
     *     authorizationPresenter.loginUser("admin")
     *     assertThat(authorizationPresenter.isAuthorized).isEqualTo(true)
     *     assertThat(authorizationPresenter.userName).isEqualTo("admin")
     *
     *     authorizationPresenter.logoutUser()
     *     assertThat(authorizationPresenter.isAuthorized).isEqualTo(false)
     *     assertThat(authorizationPresenter.userName).isEqualTo("Unknown")
     * }
     * ```
     */

    @Test
    fun State() {
        val authorizationPresenter = AuthorizationPresenter()

        authorizationPresenter.loginUser("admin")
        println(authorizationPresenter)
        assertThat(authorizationPresenter.isAuthorized).isEqualTo(true)
        assertThat(authorizationPresenter.userName).isEqualTo("admin")

        authorizationPresenter.logoutUser()
        println(authorizationPresenter)
        assertThat(authorizationPresenter.isAuthorized).isEqualTo(false)
        assertThat(authorizationPresenter.userName).isEqualTo("Unknown")
    }
}

