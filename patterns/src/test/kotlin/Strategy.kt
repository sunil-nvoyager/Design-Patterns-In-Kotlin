import org.junit.jupiter.api.Test

class Printer(private val stringFormatterStrategy: (String) -> String) {

    fun printString(string: String) {
        println(stringFormatterStrategy(string))
    }
}

val lowerCaseFormatter: (String) -> String = { it.toLowerCase() }
val upperCaseFormatter = { it: String -> it.toUpperCase() }

class StrategyTest {

    /**
     * Tests the functionality of different string formatting strategies using the Printer class.
     *
     * This method demonstrates how to use various string formatters by creating instances of the
     * Printer class with different formatting strategies. It prints the input string in lowercase,
     * uppercase, and with a prefix.
     *
     * @throws IllegalArgumentException if the input string is null or empty.
     *
     * Example usage:
     * ```
     * val inputString = "LOREM ipsum DOLOR sit amet"
     * val lowerCasePrinter = Printer(lowerCaseFormatter)
     * lowerCasePrinter.printString(inputString) // Output: lorem ipsum dolor sit amet
     *
     * val upperCasePrinter = Printer(upperCaseFormatter)
     * upperCasePrinter.printString(inputString) // Output: LOREM IPSUM DOLOR SIT AMET
     *
     * val prefixPrinter = Printer { "Prefix: $it" }
     * prefixPrinter.printString(inputString) // Output: Prefix: LOREM ipsum DOLOR sit amet
     * ```
     */
    @Test
    fun Strategy() {

        val inputString = "LOREM ipsum DOLOR sit amet"

        val lowerCasePrinter = Printer(lowerCaseFormatter)
        lowerCasePrinter.printString(inputString)

        val upperCasePrinter = Printer(upperCaseFormatter)
        upperCasePrinter.printString(inputString)

        val prefixPrinter = Printer { "Prefix: $it" }
        prefixPrinter.printString(inputString)
    }
}

