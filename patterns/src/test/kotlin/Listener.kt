import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.properties.Delegates

interface TextChangedListener {

    fun onTextChanged(oldText: String, newText: String)
}

class PrintingTextChangedListener : TextChangedListener {

    var text = ""
/**
 * Called when the text has changed.
 *
 * This method is triggered whenever the text content is modified. It receives the old text
 * and the new text as parameters, allowing for comparison and handling of the change.
 *
 * @param oldText The previous text before the change occurred.
 * @param newText The new text after the change has been made.
 *
 * @throws IllegalArgumentException if either oldText or newText is null.
 *
 * Example usage:
 * ```
 * override fun onTextChanged(oldText: String, newText: String) {
 *     text = "Text is changed: $oldText -> $newText"
 * }
 * ```
 */

    override fun onTextChanged(oldText: String, newText: String) {
        text = "Text is changed: $oldText -> $newText"
    }
}

class TextView {

    val listeners = mutableListOf<TextChangedListener>()

    var text: String by Delegates.observable("<empty>") { _, old, new ->
        listeners.forEach { it.onTextChanged(old, new) }
    }
}

class ListenerTest {
    /**
     * Tests the functionality of the `PrintingTextChangedListener` by simulating text changes
     * in a `TextView`. This method verifies that the listener correctly captures the text changes
     * and asserts that the expected output matches the actual output.
     *
     * The test initializes a `PrintingTextChangedListener` and attaches it to a `TextView`.
     * It then changes the text of the `TextView` twice, checking that the listener records
     * the changes accurately.
     *
     * @throws IllegalStateException if the listener is not properly initialized or attached
     *         to the TextView before text changes occur.
     *
     * Example usage:
     * ```
     * @Test
     * fun testTextChangeListener() {
     *     val listener = PrintingTextChangedListener()
     *     val textView = TextView().apply {
     *         listeners.add(listener)
     *     }
     *     with(textView) {
     *         text = "Hello"
     *         text = "World"
     *     }
     *     assertThat(listener.text).isEqualTo("Text is changed: Hello -> World")
     * }
     * ```
     */

    @Test
    fun Listener() {
        val listener = PrintingTextChangedListener()

        val textView = TextView().apply {
            listeners.add(listener)
        }

        with(textView) {
            text = "Lorem ipsum"
            text = "dolor sit amet"
        }

        assertThat(listener.text).isEqualTo("Text is changed: Lorem ipsum -> dolor sit amet")
    }
}

