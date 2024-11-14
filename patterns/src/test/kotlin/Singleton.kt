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
     * Tests the Singleton implementation of the PrinterDriver class.
     *
     * This test verifies that multiple calls to the `print` method of the
     * `PrinterDriver` class return the same instance, ensuring that the
     * Singleton pattern is correctly implemented.
     *
     * The test starts by printing "Start" to the console, then it retrieves
     * two instances of the PrinterDriver using the `print` method. It asserts
     * that both instances are the same, confirming that only one instance
     * of PrinterDriver exists.
     *
     * @throws AssertionError if the instances retrieved are not the same,
     * indicating that the Singleton pattern is not correctly implemented.
     *
     * Example usage:
     * ```
     * @Test
     * fun testPrinterDriverSingleton() {
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
