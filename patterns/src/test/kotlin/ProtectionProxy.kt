import org.junit.jupiter.api.Test

interface File {
    fun read(name: String)
}

class NormalFile : File {
    override fun read(name: String) = println("Reading file: $name")
}

//Proxy:
class SecuredFile(private val normalFile: File) : File {
    var password: String = ""

    override fun read(name: String) {
        if (password == "secret") {
            println("Password is correct: $password")
            normalFile.read(name)
        } else {
            println("Incorrect password. Access denied!")
        }
    }
}

class ProtectionProxyTest {
    /**
     * This test method demonstrates the use of the Protection Proxy design pattern.
     * It creates a secured file that wraps a normal file and controls access to it
     * based on the provided password.
     *
     * The method performs the following actions:
     * 1. Reads the content of a file named "readme.md" without any password.
     * 2. Sets a password for the secured file.
     * 3. Attempts to read the content of "readme.md" again, this time with the password set.
     *
     * @throws SecurityException if an attempt is made to read the file without providing the correct password.
     * @throws FileNotFoundException if the specified file does not exist.
     *
     * Example usage:
     * ```
     * @Test
     * fun testProtectionProxy() {
     *     val securedFile = SecuredFile(NormalFile())
     *     with(securedFile) {
     *         // Read without password
     *         read("readme.md") // Should succeed
     *
     *         // Set password
     *         password = "secret"
     *
     *         // Read with password
     *         read("readme.md") // Should succeed if password is correct
     *     }
     * }
     * ```
     */
    @Test
    fun `Protection Proxy`() {
        val securedFile = SecuredFile(NormalFile())

        with(securedFile) {
            read("readme.md")
            password = "secret"
            read("readme.md")
        }
    }
}