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
     * This method is invoked whenever the text content is modified. It receives the old text
     * and the new text as parameters, allowing for any necessary updates or actions to be taken
     * based on the change.
     *
     * @param oldText The previous text before the change.
     * @param newText The new text after the change.
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
     * Tests the functionality of the `PrintingTextChangedListener` by simulating text changes in a `TextView`.
     *
     * This test creates an instance of `PrintingTextChangedListener`, attaches it to a `TextView`,
     * and changes the text of the `TextView` twice. It then asserts that the listener correctly
     * captures the text change events.
     *
     * @throws IllegalArgumentException if the listener is not properly initialized or if the text view is null.
     * @throws AssertionError if the expected text change does not match the actual text change captured by the listener.
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
     *         text = "Lorem ipsum"
     *         text = "dolor sit amet"
     *     }
     *     assertThat(listener.text).isEqualTo("Text is changed: Lorem ipsum -> dolor sit amet")
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

