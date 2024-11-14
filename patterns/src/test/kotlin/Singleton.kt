import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

object PrinterDriver {
    init {
        println("Initializing with object: $this")
    }

    fun print(): PrinterDriver =
        apply { println("Printing with object: $this") }
}

class SingletonTest {
    /**
     * This test method verifies the Singleton pattern implementation of the `PrinterDriver` class.
     * It ensures that multiple calls to the `print()` method return the same instance of `PrinterDriver`.
     *
     * The test begins by printing a start message. It then calls the `print()` method twice,
     * storing the results in `printerFirst` and `printerSecond`. Finally, it asserts that both
     * instances are the same, confirming that the singleton behavior is maintained.
     *
     * @throws AssertionError if the instances are not the same, indicating that the singleton
     *         pattern is not correctly implemented in the `PrinterDriver` class.
     *
     * Example usage:
     * ```
     * @Test
     * fun testSingletonBehavior() {
     *     val printerFirst = PrinterDriver.print()
     *     val printerSecond = PrinterDriver.print()
     *     assertThat(printerFirst).isSameAs(printerSecond)
     * }
     * ```
     */

    @Test
    fun Singleton() {
        println("Start")
        val printerFirst = PrinterDriver.print()
        val printerSecond = PrinterDriver.print()

        assertThat(printerFirst).isSameAs(PrinterDriver)
        assertThat(printerSecond).isSameAs(PrinterDriver)
    }
}
