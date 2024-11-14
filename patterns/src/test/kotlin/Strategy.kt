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
     * Tests the functionality of various string formatting strategies using the Printer class.
     *
     * This test method demonstrates how to use different string formatters to print a given input string.
     * It utilizes a lower case formatter, an upper case formatter, and a custom prefix formatter.
     *
     * @throws IllegalArgumentException if the input string is null or empty.
     *
     * Example usage:
     * ```
     * val inputString = "LOREM ipsum DOLOR sit amet"
     * val lowerCasePrinter = Printer(lowerCaseFormatter)
     * lowerCasePrinter.printString(inputString) // Output: "lorem ipsum dolor sit amet"
     *
     * val upperCasePrinter = Printer(upperCaseFormatter)
     * upperCasePrinter.printString(inputString) // Output: "LOREM IPSUM DOLOR SIT AMET"
     *
     * val prefixPrinter = Printer { "Prefix: $it" }
     * prefixPrinter.printString(inputString) // Output: "Prefix: LOREM ipsum DOLOR sit amet"
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

