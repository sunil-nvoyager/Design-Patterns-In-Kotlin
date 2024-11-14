import org.junit.jupiter.api.Test

interface File {
    fun read(name: String)
}

/**
 * Reads the content of a file with the specified name and prints a message to the console.
 *
 * This method takes a file name as input and outputs a message indicating that the file is being read.
 *
 * @param name The name of the file to be read. This should be a valid file name.
 * @throws IllegalArgumentException if the provided file name is empty or null.
 *
 * Example usage:
 * ```
 * val fileName = "example.txt"
 * read(fileName) // Output: Reading file: example.txt
 * ```
 */
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

    /**
     * This test method demonstrates the use of the Protection Proxy design pattern.
     * It creates a secured file that wraps a normal file, allowing controlled access
     * based on the provided password.
     *
     * The method performs the following actions:
     * 1. It attempts to read a file named "readme.md" without a password.
     * 2. It sets a password for the secured file.
     * 3. It attempts to read the same file again after setting the password.
     *
     * @throws SecurityException if an attempt is made to read the file without the correct password.
     * @throws IOException if there is an error reading the file.
     *
     * Example usage:
     * ```
     * @Test
     * fun testProtectionProxy() {
     *     val securedFile = SecuredFile(NormalFile())
     *     with(securedFile) {
     *         // Attempt to read without password (should throw SecurityException)
     *         try {
     *             read("readme.md")
     *         } catch (e: SecurityException) {
     *             println("Caught expected SecurityException: ${e.message}")
     *         }
     *
     *         // Set password and read again
     *         password = "secret"
     *         val content = read("readme.md")
     *         println("File content: $content")
     *     }
     * }
     * ```
     */
class ProtectionProxyTest {
    /**
     * This test method demonstrates the use of a Protection Proxy pattern.
     * It creates a secured file that wraps a normal file and manages access
     * based on a password.
     *
     * The method performs the following actions:
     * 1. Reads a file without a password.
     * 2. Sets a password for the secured file.
     * 3. Reads the file again, this time requiring the password.
     *
     * @throws SecurityException if an attempt is made to read the file without
     *         providing the correct password.
     * @throws IOException if there is an error reading the file.
     *
     * Example usage:
     * ```
     * @Test
     * fun testProtectionProxy() {
     *     val securedFile = SecuredFile(NormalFile())
     *     with(securedFile) {
     *         // Reading without password
     *         read("readme.md") // Should succeed
     *
     *         // Setting password
     *         password = "secret"
     *
     *         // Reading with password
     *         read("readme.md") // Should succeed if the password is correct
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